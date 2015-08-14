package com.mapbox.mapboxsdk.android.testapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.views.MapView;
import com.spatialdev.osm.OSMMap;
import com.spatialdev.osm.model.JTSModel;
import com.spatialdev.osm.model.OSMDataSet;
import com.spatialdev.osm.model.OSMXmlParser;
import java.io.IOException;

public class LocalOSMTestFragment extends Fragment {

    private static final String TAG = "LocalOSMTestFragment";
    private MapView mapView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_local_osmtest, container, false);

        // Setup Map
        mapView = (MapView) view.findViewById(R.id.localOSMMapView);
        mapView.setCenter(new LatLng(23.707873, 90.409774));
        mapView.setZoom(19);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        new LoadOSMXMLTask().execute();
    }

    private class LoadOSMXMLTask extends AsyncTask<Void, Void, JTSModel> {

        @Override
        protected JTSModel doInBackground(Void... params) {

            JTSModel jtsModel = null;
            try {
                OSMDataSet ds = OSMXmlParser.parseFromAssets(getActivity(), "osm/dhaka_roads_buildings_hospitals_med.osm");
                jtsModel = new JTSModel(ds);
            } catch (IOException e) {
                Log.e(TAG, "IOException loading the OMS Data: " + e);
            }

            return jtsModel;
        }

        @Override
        protected void onPostExecute(JTSModel jtsModel) {
            if (jtsModel == null) {
                Log.i(TAG, "No jtsModel to load, ");
                Toast.makeText(getActivity(), "No JTSModel was build, so nothing to show.  Please try again.", Toast.LENGTH_SHORT).show();
                return;
            }
            OSMMap osmMap = new OSMMap(mapView, jtsModel);
        }
    }
}
