package com.idankorenisraeli.soccerbattle;

import android.content.Context;

public class GameManager {

    private static GameManager single_instance = null;
    private int turnsPlayed;

    private static final SoccerPlayer startingPlayer = GameData.getInstance().getPlayerLeft();

    private GameManager(){
        turnsPlayed = 0;
    }

    public static GameManager getInstance(){
        if(single_instance == null)
            single_instance = new GameManager();
        return single_instance;
    }

    public void playedTurn(){
        turnsPlayed++;
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

    public SoccerPlayer getStartingPlayer(){
        return startingPlayer;
    }

}
