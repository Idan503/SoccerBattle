package com.idankorenisraeli.soccerbattle.game;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.idankorenisraeli.soccerbattle.common.SharedPrefsManager;
import com.idankorenisraeli.soccerbattle.player.PlayerSide;
import com.idankorenisraeli.soccerbattle.player.SoccerPlayer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;

// Various information about current status of the game
public class GameManager{
    private static GameManager single_instance = null;
    private int turnsPlayed;
    private PlayerSide currentTurn;

    public final boolean ROBOT_PLAYER = false; // Will a robot play the game?

    private GameFinishedListener finishedListener;

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

        if(isGameOver() && finishedListener!=null){
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

    public void saveResult(Activity activity){
        SharedPrefsManager prefs = SharedPrefsManager.getInstance();
        TypeToken<ArrayList<GameResult>> token = new TypeToken<ArrayList<GameResult>>() {};
        ArrayList<GameResult> allResults = prefs.getArray(SharedPrefsManager.KEYS.SP_ALL_RESULTS, token);
        if(allResults == null)
            allResults = new ArrayList<>();

        allResults.add(new GameResult(activity, Objects.requireNonNull(getCurrentPlayer()).getName(),turnsPlayed));
        prefs.putArray(SharedPrefsManager.KEYS.SP_ALL_RESULTS,allResults);
    }


    // Getting the player that is has current turn
    private SoccerPlayer getCurrentPlayer(){
        switch (currentTurn){
            case LEFT:
                return GameData.getInstance().getPlayerLeft();
            case RIGHT:
                return GameData.getInstance().getPlayerRight();
        }
        return null;
    }
}
