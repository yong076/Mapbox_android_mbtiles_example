package com.mapbox.mapboxsdk.android.testapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.overlay.Icon;
import com.mapbox.mapboxsdk.overlay.Marker;
import com.mapbox.mapboxsdk.views.MapView;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ClusteredMarkersTestFragment extends Fragment {
    private static final String TAG = ClusteredMarkersTestFragment.class.getSimpleName();
    private MapView mapView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_markers, container, false);

        // Setup Map
        mapView = (MapView) view.findViewById(R.id.markersMapView);
        mapView.setCenter(new LatLng(38.11493876707079, 13.3647260069847));
        mapView.setZoom(14);
        mapView.setClusteringEnabled(true, null, 18);

        // Dynamically create 100 markers
        Random r = new Random();


        List<Marker> markers = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            LatLng position = new LatLng(new LatLng(38.11493876707079f + r.nextFloat() / 100,
                    13.3647260069847f + r.nextFloat() / 100));

            Marker marker = new Marker(mapView, "", "", position);

            marker.setIcon(new Icon(getActivity(), Icon.Size.SMALL, "marker-stroked", "FF0000"));
            markers.add(marker);
        }
        mapView.addMarkers(markers);

        return view;
    }
}
