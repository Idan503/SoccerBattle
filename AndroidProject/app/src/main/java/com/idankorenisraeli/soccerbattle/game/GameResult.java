package com.idankorenisraeli.soccerbattle.game;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.maps.model.LatLng;

import java.util.Objects;

public class GameResult {
    private String name;
    private int turns;
    private LatLng location;

    private static final int REQUEST_LOCATION = 1;

    public GameResult(String name, int turns, LatLng location) {
        this.name = name;
        this.turns = turns;
        this.location = location;
    }

    public GameResult(Activity activity, String name, int turns) {
        this.name = name;
        this.turns = turns;
        this.location = getCurrentLocation(activity);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTurns() {
        return turns;
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

    //Getting last known location with GPS permission
    private LatLng getCurrentLocation(@NonNull Activity activity){
        LocationManager locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        if(ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(Objects.requireNonNull(activity),new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION);
        } else{
            Location currentLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if(currentLocation!=null){
                return new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
            }
        }
        return null;
    }

}
