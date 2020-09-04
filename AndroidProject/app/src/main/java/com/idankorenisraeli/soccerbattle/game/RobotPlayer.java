package com.idankorenisraeli.soccerbattle.game;

import android.os.Handler;
import android.widget.FrameLayout;

import com.idankorenisraeli.soccerbattle.player.PlayerSide;

import java.util.Random;

public class RobotPlayer {
    private FrameLayout[] leftAttacks;
    private FrameLayout[] rightAttacks;

    private static final int WAIT_TIME = 2500; // Waiting time between each turn

    public RobotPlayer(FrameLayout[] left, FrameLayout[] right){
        leftAttacks = left;
        rightAttacks = right;
    }

    // Performing one turn every WAIT_TIME ms
    public void play(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                GameManager manager = GameManager.getInstance();
                performRandomAttack(manager.getCurrentTurn());
                if(!manager.isGameOver())
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

}
