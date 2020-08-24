package com.idankorenisraeli.soccerbattle;

public class SoccerPlayer {
    private String name;
    private String imageURI;
    private SoccerAttack[] attacks;
    private int currentPoints;

    private final static int MAX_POINTS = 100; // Total number of points to win

    public SoccerPlayer(String name, String imageURI, SoccerAttack[] attacks) {
        this.name = name;
        this.imageURI = imageURI;
        this.attacks = attacks;
    }

    // Here attacks are being set by default
    public SoccerPlayer(String name, String imageURI) {
        this.name = name;
        this.imageURI = imageURI;
        this.attacks = GameManager.getInstance().getDefaultAttacks();
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

    public String getImageURI() {
        return imageURI;
    }

    public void setImageURI(String imageURI) {
        this.imageURI = imageURI;
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
