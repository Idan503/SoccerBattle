package com.idankorenisraeli.soccerbattle.player;

import com.idankorenisraeli.soccerbattle.attack.SoccerAttack;
import com.idankorenisraeli.soccerbattle.game.GameManager;

public class SoccerPlayer {
    private String name;
    private int imageId;
    private SoccerAttack[] attacks;
    private int currentPoints;

    public static final int MAX_POINTS = 100; // Total number of points to win

    public SoccerPlayer(String name, int imageId, SoccerAttack[] attacks) {
        this.name = name;
        this.imageId = imageId;
        this.attacks = attacks;
    }

    // Player will receive the points for attacks performed, and might win (return value)
    public boolean performAttack(SoccerAttack attack){
        boolean isWin;
        this.currentPoints += attack.getPoints();
        if(currentPoints >= MAX_POINTS) {
            currentPoints = MAX_POINTS; // Player win
            isWin = true;
        } else
            isWin = false;
        GameManager.getInstance().playedTurn();
        return isWin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public SoccerAttack[] getAttacks() {
        return attacks;
    }

    public void setAttacks(SoccerAttack[] attacks) {
        this.attacks = attacks;
    }

    public int getCurrentPoints() {
        return currentPoints;
    }

    public void setCurrentPoints(int currentPoints) {
        this.currentPoints = currentPoints;
    }
}
