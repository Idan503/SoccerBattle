package com.idankorenisraeli.soccerbattle.game;

import android.os.Handler;
import android.widget.FrameLayout;

import com.idankorenisraeli.soccerbattle.player.PlayerSide;

import java.util.Random;

public class RobotPlayer {
    private FrameLayout[] leftAttacks;
    private FrameLayout[] rightAttacks;

    private static final int WAIT_TIME = 2000; // Waiting time between each turn

    private static RobotPlayer single_instance = null;

    private RobotPlayer(FrameLayout[] left, FrameLayout[] right){
        leftAttacks = left;
        rightAttacks = right;
    }

    public static RobotPlayer getInstance(){
        return single_instance;
    }

    public static RobotPlayer initHelper(FrameLayout[] left, FrameLayout[] right){
        if(single_instance==null)
            single_instance = new RobotPlayer(left, right);
        return single_instance;
    }

    // Performing one turn every WAIT_TIME ms
    public void play(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                GameManager manager = GameManager.getInstance();
                if(manager.isPaused())
                    performRandomAttack(manager.getCurrentTurn());
                if(!manager.isGameOver() && manager.isPaused())
                    play();
            }
        }, WAIT_TIME);
    }


    private void performRandomAttack(PlayerSide currentTurn){
        Random random = new Random();
        int attackChosen; // Each turn a random attack is chosen
        switch (currentTurn){
            case LEFT:
                attackChosen = random.nextInt(leftAttacks.length);
                leftAttacks[attackChosen].performClick();
                break;
            case RIGHT:
                attackChosen = random.nextInt(rightAttacks.length);
                rightAttacks[attackChosen].performClick();
                break;
        }
    }

    public static void reset(){
        single_instance = null;
    }

}
