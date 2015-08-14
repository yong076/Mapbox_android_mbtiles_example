package com.mapbox.mapboxsdk.android.testapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.mapbox.mapboxsdk.api.ILatLng;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.overlay.Marker;
import com.mapbox.mapboxsdk.util.MapboxUtils;
import com.mapbox.mapboxsdk.views.MapView;
import com.mapbox.mapboxsdk.views.MapViewListener;

public class TapForUTFGridTestFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tapforutfgrid, container, false);

        MapView mv = (MapView) view.findViewById(R.id.tapForUTFGridMapView);
        mv.setCenter(new LatLng(-33.99541, 18.48885));
        mv.setZoom(12);

        mv.setMapViewListener(new MapViewListener() {
            @Override
            public void onShowMarker(MapView pMapView, Marker pMarker) {}

            @Override
            public void onHideMarker(MapView pMapView, Marker pMarker) {}

            @Override
            public void onTapMarker(MapView pMapView, Marker pMarker) {}

            @Override
            public void onLongPressMarker(MapView pMapView, Marker pMarker) {}

            @Override
            public void onTapMap(MapView pMapView, ILatLng pPosition) {
                String coords = String.format("Zoom = %f, Lat = %f, Lon = %f", pMapView.getZoomLevel(), pPosition.getLatitude(), pPosition.getLongitude());
                String utfGrid = MapboxUtils.getUTFGridString(pPosition, Float.valueOf(pMapView.getZoomLevel()).intValue());
                Log.i("TapForUTFGridTestFragment", String.format("coords = '%s', UTFGrid = '%s'", coords, utfGrid));
                Toast.makeText(getActivity(), coords + " == " + utfGrid, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLongPressMap(MapView pMapView, ILatLng pPosition) {}
        });


        return view;
    }

}
