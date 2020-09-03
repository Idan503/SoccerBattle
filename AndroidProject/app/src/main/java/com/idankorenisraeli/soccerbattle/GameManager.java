package com.idankorenisraeli.soccerbattle;

import android.app.Activity;
import android.content.Context;

// Various information about current status of the game
public class GameManager{
    private static GameManager single_instance = null;
    private int turnsPlayed;
    private PlayerSide currentTurn;

    GameFinishedListener finishedListener;

    private GameManager(Context context){
        turnsPlayed = 0;
        currentTurn = PlayerSide.NONE;
        try {
            finishedListener = (GameFinishedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement GameFinishedListener.");
        }
    }

    public static GameManager getInstance() {
        if (single_instance == null)
            System.out.println("initHelper should be called first");
        return single_instance;
    }

    public static GameManager initHelper(Context context){
        if(single_instance == null)
            single_instance = new GameManager(context);
        return single_instance;
    }

    public void playedTurn(){
        turnsPlayed++;

        if(isGameOver()){
            finishedListener.onGameFinished(turnsPlayed,currentTurn);
        }

        if(currentTurn==PlayerSide.LEFT){
            currentTurn = PlayerSide.RIGHT;
        }
        else if(currentTurn==PlayerSide.RIGHT)
            currentTurn = PlayerSide.LEFT;
    }

    public int getTurnsPlayed(){
        return turnsPlayed;
    }


    public boolean isGameOver(){
        GameData data = GameData.getInstance();
        SoccerPlayer left = data.getPlayerLeft();
        SoccerPlayer right = data.getPlayerRight();
        return (left.getCurrentPoints() >= SoccerPlayer.MAX_POINTS ||
                right.getCurrentPoints() >= SoccerPlayer.MAX_POINTS);
    }

    public PlayerSide getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(PlayerSide turn){
        currentTurn = turn;
    }

    public void reset(Context context){
        single_instance = new GameManager(context);
    }

}
