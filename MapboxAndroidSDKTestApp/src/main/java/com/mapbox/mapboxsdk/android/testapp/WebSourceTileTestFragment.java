package com.mapbox.mapboxsdk.android.testapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.tileprovider.tilesource.WebSourceTileLayer;
import com.mapbox.mapboxsdk.views.MapView;

public class WebSourceTileTestFragment extends Fragment {

    private static final String TAG = "WebSourceTileTest";

    private MapView mapView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_websourcetile, container, false);

        // Setup Map
        mapView = (MapView) view.findViewById(R.id.mapview);

        WebSourceTileLayer ws = new WebSourceTileLayer("openstreetmap", "http://tile.openstreetmap.org/{z}/{x}/{y}.png");
        ws.setName("OpenStreetMap")
            .setAttribution("© OpenStreetMap Contributors")
            .setMinimumZoomLevel(1)
            .setMaximumZoomLevel(18);

        mapView.setTileSource(ws);
        resetMap();

        Button animateButton = (Button) view.findViewById(R.id.animateToNewLocationButton);
        Button resetButton = (Button) view.findViewById(R.id.resetToOriginalButton);
        Button gotoButton = (Button) view.findViewById(R.id.goToLocationButton);

        animateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateTo();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetMap();
            }
        });

        gotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo();
            }
        });

        return view;
    }

    private void animateTo() {
        boolean result = mapView.getController().animateTo(new LatLng(34.16141, -118.16766));
        Log.i(TAG, "animatedTo() result = " + result);
    }

    private void resetMap() {
        mapView.setCenter(new LatLng(34.19997, -118.17163));
        mapView.setZoom(12);
    }

    private void goTo() {
        mapView.getController().goTo(new LatLng(34.01393, -118.28795), null);
    }
}
