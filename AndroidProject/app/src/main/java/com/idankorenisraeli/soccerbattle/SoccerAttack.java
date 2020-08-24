package com.idankorenisraeli.soccerbattle;

public class SoccerAttack {
    private String name;
    private String imageURI;
    private int points; // Receiving amount for player performing this attack

    public SoccerAttack(String name, int points, String imageURI) {
        this.name = name;
        this.points = points;
        this.imageURI = imageURI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getImageURI() {
        return imageURI;
    }

    public void setImageURI(String imageURI) {
        this.imageURI = imageURI;
    }
}
