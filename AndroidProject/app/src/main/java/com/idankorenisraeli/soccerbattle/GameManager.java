package com.idankorenisraeli.soccerbattle;

import android.content.Context;

// Various information about current status of the game
public class GameManager {

    private static final PlayerSide DEFAULT_FIRST_TURN = PlayerSide.LEFT;

    private static GameManager single_instance = null;
    private int turnsPlayed;
    private PlayerSide currentTurn;

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

        if(currentTurn==PlayerSide.LEFT){
            currentTurn = PlayerSide.RIGHT;
        }
        else if(currentTurn==PlayerSide.RIGHT)
            currentTurn = PlayerSide.LEFT;

        if(isGameOver())
            currentTurn = PlayerSide.NONE;
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

    public PlayerSide getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(PlayerSide turn){
        currentTurn = turn;
    }

}
