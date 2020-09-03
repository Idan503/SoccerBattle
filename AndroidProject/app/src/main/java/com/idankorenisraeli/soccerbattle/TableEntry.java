package com.idankorenisraeli.soccerbattle;

import com.google.android.gms.maps.model.LatLng;

public class TableEntry {
    private String name;
    private int turns;
    private LatLng location;

    public TableEntry(String name, int turns, LatLng location) {
        this.name = name;
        this.turns = turns;
        this.location = location;
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
}
