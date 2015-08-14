/**
 * Created by Nicholas Hallahan on 12/24/14.
 * nhallahan@spatialdev.com
 */
package com.spatialdev.osm.model;

import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class OSMRelation extends OSMElement {

    // These are the members that refer to another OSM Element.
    private class RelationMember {
        public Long ref;
        public String type;
        public String role;
        public OSMElement linkedElement;

        public RelationMember(Long ref, String type, String role) {
            this.ref = ref;
            this.type = type;
            this.role = role;
        }
    }

    private LinkedList<RelationMember> nodeMembers = new LinkedList<>();
    private LinkedList<RelationMember> wayMembers = new LinkedList<>();
    private LinkedList<RelationMember> relationMembers = new LinkedList<>();

    private LinkedList<OSMNode> linkedNodes = new LinkedList<>();
    private LinkedList<OSMWay> linkedWays = new LinkedList<>();
    private LinkedList<OSMRelation> linkedRelations = new LinkedList<>();

    private int unlinkedMembersCount = 0;

    public OSMRelation(String idStr,
                       String versionStr,
                       String timestampStr,
                       String changesetStr,
                       String uidStr,
                       String userStr) {

        super(idStr, versionStr, timestampStr, changesetStr, uidStr, userStr);
    }

    @Override
    void xml(XmlSerializer xmlSerializer) throws IOException {
        xmlSerializer.startTag(null, "relation");
        if (isModified()) {
            xmlSerializer.attribute(null, "action", "modify");
        }
        setOsmElementXmlAttributes(xmlSerializer);
        // generate members
        setRelationXmlMembers(xmlSerializer);
        // generate tags
        super.xml(xmlSerializer);
        xmlSerializer.endTag(null, "relation");
    }

    private void setRelationXmlMembers(XmlSerializer xmlSerializer) throws IOException {
        for (RelationMember mem: nodeMembers) {
            writeXmlMember(mem, xmlSerializer);
        }
        for (RelationMember mem: wayMembers) {
            writeXmlMember(mem, xmlSerializer);
        }
        for (RelationMember mem: relationMembers) {
            writeXmlMember(mem, xmlSerializer);
        }
    }

    private void writeXmlMember(RelationMember mem, XmlSerializer xmlSerializer) throws IOException {
        xmlSerializer.startTag(null, "member");
        xmlSerializer.attribute(null, "type", mem.type);
        xmlSerializer.attribute(null, "ref", String.valueOf(mem.ref));
        xmlSerializer.attribute(null, "role", mem.role);
        xmlSerializer.endTag(null, "member");
    }

    public void addNodeRef(long id, String role) {
        nodeMembers.add(new RelationMember(id, "node", role));
    }

    public void addWayRef(long id, String role) {
        wayMembers.add(new RelationMember(id, "way", role));
    }

    public void addRelationRef(long id, String role) {
        relationMembers.add(new RelationMember(id, "relation", role));
    }

    int link(Map<Long, OSMNode> nodes, Map<Long, OSMWay> ways, Map<Long, OSMRelation> relations) {
        int unlinkedNodes = linkNodes(nodes);
        int unlinkedWays = linkWays(ways);
        int unlinkedRelations = linkRelations(relations);
        unlinkedMembersCount = unlinkedNodes + unlinkedWays + unlinkedRelations;
        return unlinkedMembersCount;
    }

    private int linkNodes(Map<Long, OSMNode> nodes) {
        int unlinkedCount = 0;
        for (RelationMember mem : nodeMembers) {
            OSMNode node = nodes.get(mem.ref);
            if (node == null) {
                ++unlinkedCount;
            } else {
                node.addRelation(this);
                mem.linkedElement = node;
                linkedNodes.push(node);
            }
        }
        return unlinkedCount;
    }

    private int linkWays(Map<Long, OSMWay> ways) {
        int unlinkedCount = 0;
        for (RelationMember mem : wayMembers) {
            OSMWay way = ways.get(mem.ref);
            if (way == null) {
                ++unlinkedCount;
            } else {
                way.addRelation(this);
                mem.linkedElement = way;
                linkedWays.push(way);
            }
        }
        return unlinkedCount;
    }

    private int linkRelations(Map<Long, OSMRelation> relations) {
        int unlinkedCount = 0;
        for (RelationMember mem: relationMembers) {
            OSMRelation rel = relations.get(mem.ref);
            if (rel == null) {
                ++unlinkedCount;
            } else {
                rel.addRelation(this);
                mem.linkedElement = rel;
                linkedRelations.push(rel);
            }
        }
        return unlinkedCount;
    }

    public void addRelation(OSMRelation relation) {
        linkedRelations.push(relation);
    }

    public List<OSMRelation> getRelations() {
        return linkedRelations;
    }

    public int getUnlinkedMemberCount() {
        return unlinkedMembersCount;
    }

}
