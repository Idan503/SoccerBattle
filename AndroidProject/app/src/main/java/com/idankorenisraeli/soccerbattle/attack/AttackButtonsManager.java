package com.idankorenisraeli.soccerbattle.attack;


import android.widget.FrameLayout;

import com.idankorenisraeli.soccerbattle.game.GameManager;

// This script is for enabling and disabling attack buttons when turn changes
public class AttackButtonsManager {
    private static final float idleAlpha = 0.5f; // Opacity of buttons of player who doesn't play

    private static AttackButtonsManager single_instance = null;

    private FrameLayout[] leftAttackButtons;
    private FrameLayout[] rightAttackButtons;

    private AttackButtonsManager(FrameLayout[] leftArray, FrameLayout[] rightArray){
        leftAttackButtons = leftArray;
        rightAttackButtons = rightArray;
    }


    public static AttackButtonsManager getInstance(){
        return single_instance;
    }

    public static AttackButtonsManager initHelper(FrameLayout[] leftAttackButtons, FrameLayout[] rightAttackButtons){
        single_instance = new AttackButtonsManager(leftAttackButtons,rightAttackButtons);
        return single_instance;
    }

    public void updateAttackButtons(){
        switch (GameManager.getInstance().getCurrentTurn()){
            case LEFT:
                setButtonsOn(leftAttackButtons);
                setButtonsOff(rightAttackButtons);
                break;
            case RIGHT:
                setButtonsOn(rightAttackButtons);
                setButtonsOff(leftAttackButtons);
                break;
            case NONE:
                setButtonsOff(rightAttackButtons);
                setButtonsOff(leftAttackButtons);
        }
    }

    private void setButtonsOn(FrameLayout[] buttons){
        for(FrameLayout button : buttons){
            button.setClickable(!GameManager.getInstance().ROBOT_PLAYER); // When robot plays all buttons are disabled
            button.setAlpha(1f);
        }
    }

    private void setButtonsOff(FrameLayout[] buttons){
        for(FrameLayout button : buttons){
            button.setClickable(false);
            button.setAlpha(idleAlpha);
        }
    }



}
