package com.idankorenisraeli.soccerbattle.attack;

import com.idankorenisraeli.soccerbattle.game.GameData;

public class SoccerAttack {
    private String name;
    private int imageId;
    private int points; // Receiving points amount for performing this attack
    private int soundId;

    public SoccerAttack(String name, int points, int imageId, int soundId) {
        this.name = name;
        this.points = points;
        this.imageId = imageId;
        this.soundId = soundId;
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

    public int getSoundId() {
        return soundId;
    }

    public void setSoundId(int soundId) {
        this.soundId = soundId;
    }
}
