package com.idankorenisraeli.soccerbattle;

import android.content.Context;

public class GameManager {
    public enum PlayerTurn{LEFT, RIGHT, NONE}

    private static final PlayerTurn DEFAULT_FIRST_TURN = PlayerTurn.LEFT;

    private static GameManager single_instance = null;
    private int turnsPlayed;
    private PlayerTurn currentTurn;

    private GameManager(){
        turnsPlayed = 0;
        currentTurn = DEFAULT_FIRST_TURN;
    }

    public static GameManager getInstance(){
        if(single_instance == null)
            single_instance = new GameManager();
        return single_instance;
    }

    public void playedTurn(){
        turnsPlayed++;

        if(currentTurn==PlayerTurn.LEFT){
            currentTurn = PlayerTurn.RIGHT;
        }
        else if(currentTurn==PlayerTurn.RIGHT)
            currentTurn = PlayerTurn.LEFT;

        if(isGameOver())
            currentTurn = PlayerTurn.NONE;
    }

    public int getTurnsPlayed(){
        return turnsPlayed;
    }


    public boolean isGameOver(){
        GameData data = GameData.getInstance();
        SoccerPlayer left = data.getPlayerLeft();
        SoccerPlayer right = data.getPlayerLeft();
        return (left.getCurrentPoints() >= SoccerPlayer.MAX_POINTS ||
                right.getCurrentPoints() >= SoccerPlayer.MAX_POINTS);
    }

    public PlayerTurn getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(PlayerTurn turn){
        currentTurn = turn;
    }

}
