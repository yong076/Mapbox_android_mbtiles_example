package com.mapbox.mapboxsdk.android.testapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mapbox.mapboxsdk.android.testapp.ui.CustomInfoWindow;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.overlay.Icon;
import com.mapbox.mapboxsdk.overlay.Marker;
import com.mapbox.mapboxsdk.views.MapView;

public class CustomMarkerTestFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_custommarkermap, container, false);

        MapView mv = (MapView) view.findViewById(R.id.customMarkerMapView);
        mv.setCenter(new LatLng(-3.07881, 37.31369));
        mv.setZoom(8);

        Marker marker = new Marker("Mount Kilimanjaro", "", new LatLng(-3.06372, 36.71356));
        marker.setMarker(getResources().getDrawable(R.drawable.right_arrow));
        mv.addMarker(marker);

        Marker capital = new Marker(mv, "Dodoma", "", new LatLng(-6.17691, 35.74685));
        capital.setIcon(new Icon(getActivity(), Icon.Size.LARGE, "town-hall", "FF0000"));
        mv.addMarker(capital);

        Marker bigCity = new Marker(mv, "Dar es Salaam", "", new LatLng(-6.80610, 39.27046));
        bigCity.setToolTip(new CustomInfoWindow(mv));
        mv.addMarker(bigCity);

        return view;
    }
}
