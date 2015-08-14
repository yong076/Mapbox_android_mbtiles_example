package com.mapbox.mapboxsdk.android.testapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mapbox.mapboxsdk.tileprovider.tilesource.ITileLayer;
import com.mapbox.mapboxsdk.tileprovider.tilesource.MBTilesLayer;
import com.mapbox.mapboxsdk.tileprovider.tilesource.TileLayer;
import com.mapbox.mapboxsdk.views.MapView;

public class MBTilesTestFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mbtiles, container, false);

        MapView mv = (MapView) view.findViewById(R.id.mbTilesMap);

        mv.setUseDataConnection(false);

        TileLayer mbTileLayer = new MBTilesLayer(getActivity(), "openstreetmap-hongdae.mbtiles");
        mv.setTileSource(new ITileLayer[] {mbTileLayer});

        mv.setScrollableAreaLimit(mbTileLayer.getBoundingBox());
        mv.setMinZoomLevel(mv.getTileProvider().getMinimumZoomLevel());
        mv.setMaxZoomLevel(mv.getTileProvider().getMaximumZoomLevel());
        mv.setCenter(mv.getTileProvider().getCenterCoordinate());

        mv.setUseSafeCanvas(true);

        return view;
    }
}
