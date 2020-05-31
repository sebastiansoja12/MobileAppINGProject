package com.example.ingproject.Views;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.ingproject.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class activity_maps extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Bundle bundle;
    private String username;
    private double lat,lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
        Intent intent= getIntent();
        Bundle pos = intent.getExtras();
        assert pos != null;
      lat=pos.getDouble("lat");
      lng=pos.getDouble("lng");
      username=pos.getString("username");





    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
mMap=googleMap;
            // Add a marker in Sydney, Australia,
            // and move the map's camera to the same location.
            LatLng latLng = new LatLng(lat, lng);
            googleMap.addMarker(new MarkerOptions().position(latLng)
                    .title(username + "'s home"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        }
    }


