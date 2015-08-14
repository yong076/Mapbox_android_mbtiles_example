package com.mapbox.mapboxsdk.android.testapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mapbox.mapboxsdk.overlay.UserLocationOverlay;
import com.mapbox.mapboxsdk.views.MapView;

public class LocateMeTestFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_locate_me_test, container, false);
        MapView mv = (MapView) view.findViewById(R.id.locateMeMapView);
        mv.setUserLocationEnabled(true);
        mv.setUserLocationTrackingMode(UserLocationOverlay.TrackingMode.FOLLOW);
        mv.setUserLocationRequiredZoom(12);

        Log.d("LocateMeTestFragment", String.format("isMapRotationEnabled?  = %s", mv.isMapRotationEnabled()));
        if (!mv.isMapRotationEnabled()) {
            mv.setMapRotationEnabled(true);
            Log.d("LocateMeTestFragment", String.format("mapRotation wasn't enabled, now isMapRotationEnabled?  = %s", mv.isMapRotationEnabled()));
        }

        return view;
    }

}
