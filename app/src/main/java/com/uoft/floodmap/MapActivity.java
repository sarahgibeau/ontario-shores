package com.uoft.floodmap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import com.esri.arcgisruntime.ArcGISRuntimeEnvironment;
import com.esri.arcgisruntime.data.ServiceFeatureTable;
import com.esri.arcgisruntime.layers.FeatureLayer;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.LayerList;
import com.esri.arcgisruntime.mapping.view.MapView;

public class MapActivity extends AppCompatActivity implements View.OnClickListener {

    private MapView mMapView;
    private RadioGroup rGroup;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
       /* new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){

            }*/


        mMapView = findViewById(R.id.mapView);
        rGroup = findViewById(R.id.radioGroup);
        button = findViewById(R.id.data);
        button.setOnClickListener(this);

        Intent intent = getIntent();
        String scenario = intent.getStringExtra("scenario");

        if (mMapView != null) {
            ArcGISRuntimeEnvironment.setLicense(getResources().getString(R.string.arcgis_license_key));
            double latitude = 43.635109881100225;
            double longitude = -79.3807061794585;
            ArcGISMap map = new ArcGISMap(Basemap.Type.TOPOGRAPHIC_VECTOR, latitude, longitude, 13);
            mMapView.setMap(map);

            rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    LayerList list = map.getOperationalLayers();
                    switch (checkedId) {
                        case R.id.radioButton:
                            Log.i("app", "Radio button 1 clicked");
                            list.clear();
                            break;

                        case R.id.radioButton2:
                            Log.i("app", "Radio button 2 clicked");
                            list.clear();
                            addLayer(map, getResources().getString(R.string.first_url));
                            break;

                        case R.id.radioButton3:
                            Log.i("app", "Radio button 3 clicked");
                            list.clear();
                            if (scenario.equals("low")) {
                                addLayer(map, getResources().getString(R.string.first_url));
                            } else {
                                addLayer(map, getResources().getString(R.string.second_url));
                            }
                            break;
                    }
                }
            });
        }
    }

    private void addLayer(ArcGISMap map, String url) {

        ServiceFeatureTable serviceFeatureTable = new ServiceFeatureTable(url);
        FeatureLayer featureLayer = new FeatureLayer(serviceFeatureTable);
        map.getOperationalLayers().add(featureLayer);
    }

    public void onClick(View v) {
        Log.i("app", "data button clicked");
        Intent i = new Intent(this, InfoActivity.class);
        startActivity(i);
    }

    @Override
    protected void onPause() {
        if (mMapView != null) {
            mMapView.pause();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mMapView != null) {
            mMapView.resume();
        }
    }

    @Override
    protected void onDestroy() {
        if (mMapView != null) {
            mMapView.dispose();
        }
        super.onDestroy();
    }

}