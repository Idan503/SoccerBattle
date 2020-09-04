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
import com.idankorenisraeli.soccerbattle.common.CommonUtils;

import java.util.Objects;

public class GameResult {
    private String name;
    private int turns;
    private LatLng location;


    public GameResult(String name, int turns, LatLng location) {
        this.name = name;
        this.turns = turns;
        this.location = location;
    }

    public GameResult(String name, int turns) {
        this.name = name;
        this.turns = turns;
        this.location = CommonUtils.getInstance().getCurrentLocation();
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


    @NonNull
    @Override
    public String toString(){
        return this.name + " won in " + this.turns;
    }

}
