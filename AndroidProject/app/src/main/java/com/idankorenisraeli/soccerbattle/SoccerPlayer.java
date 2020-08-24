package com.idankorenisraeli.soccerbattle;

public class SoccerPlayer {
    private String name;
    private int imageId;
    private SoccerAttack[] attacks;
    private int currentPoints;

    private final static int MAX_POINTS = 100; // Total number of points to win

    public SoccerPlayer(String name, int imageId, SoccerAttack[] attacks) {
        this.name = name;
        this.imageId = imageId;
        this.attacks = attacks;
    }

    // Player will receive the points for attacks performed, and might win (return value)
    public boolean performAttack(SoccerAttack attack){
        GameManager.getInstance().playedTurn();
        this.currentPoints += attack.getPoints();
        if(currentPoints > MAX_POINTS) {
            currentPoints = MAX_POINTS; // Player win
            return true;
        }
        return false; // Player didn't win yet
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
