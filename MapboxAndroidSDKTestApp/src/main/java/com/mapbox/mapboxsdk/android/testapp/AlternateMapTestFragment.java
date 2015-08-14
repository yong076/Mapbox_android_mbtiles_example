/**
 * @author Brad Leege <bleege@gmail.com>
 * Created on 4/4/14 at 2:07 PM
 */

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

public class AlternateMapTestFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alternatemap, container, false);

        MapView mv = (MapView) view.findViewById(R.id.altMapView);
        mv.setCenter(new LatLng(43.07472, -89.38421));
        mv.setZoom(14);

        Marker cap = new Marker(mv, "State Capitol", "Madison, WI", new LatLng(43.07472, -89.38421));
        cap.setIcon(new Icon(getActivity(), Icon.Size.LARGE, "town-hall", "FF0000"));
        mv.addMarker(cap);

        return view;
    }
}
