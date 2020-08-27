package com.idankorenisraeli.soccerbattle;

public class SoccerAttack {
    private String name;
    private int imageId;
    private int points; // Receiving points amount for performing this attack

    public SoccerAttack(String name, int points, int imageId) {
        this.name = name;
        this.points = points;
        this.imageId = imageId;
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

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
