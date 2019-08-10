package com.hendyapp.listjson;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapPage extends FragmentActivity implements OnMapReadyCallback {
    private double lat,lng;
    private String name_user;
    GoogleMap map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_page);
        Intent getData = getIntent();
        this.lat=Double.parseDouble(getData.getStringExtra("latitude"));
        this.lng=Double.parseDouble(getData.getStringExtra("longitude"));
        this.name_user=getData.getStringExtra("nameofuser");
        SupportMapFragment mapFragment=(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapF);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map=googleMap;
        LatLng ll = new LatLng(this.lat,this.lng);
        map.addMarker(new MarkerOptions().position(ll).title(this.name_user+" Position"));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(ll,20));
    }
}
