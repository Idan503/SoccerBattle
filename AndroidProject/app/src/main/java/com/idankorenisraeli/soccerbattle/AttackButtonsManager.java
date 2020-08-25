package com.idankorenisraeli.soccerbattle;


import android.widget.FrameLayout;

// This script is for enabling and disabling attack buttons when turn changes
public class AttackButtonsManager {
    private static final float idleAlpha = 0.5f; // Opacity of buttons of player who doesn't play

    private static AttackButtonsManager single_instance;

    FrameLayout[] leftAttackButtons;
    FrameLayout[] rightAttackButtons;
    boolean leftTurn = false;
    boolean rightTurn = false;

    private AttackButtonsManager(FrameLayout[] leftArray, FrameLayout[] rightArray){
        leftAttackButtons = leftArray;
        rightAttackButtons = rightArray;
    }

    public static AttackButtonsManager getInstance(){
        return single_instance;
    }

    public static AttackButtonsManager initHelper(FrameLayout[] leftAttackButtons, FrameLayout[] rightAttackButtons){
        if(single_instance == null)
            single_instance = new AttackButtonsManager(leftAttackButtons,rightAttackButtons);
        return single_instance;
    }

    public void setLeftTurn(){
        setButtonsOff(rightAttackButtons);
        rightTurn = false;
        setButtonsOn(leftAttackButtons);
        leftTurn = true;
    }

    public void setRightTurn(){
        setButtonsOff(leftAttackButtons);
        leftTurn = false;
        setButtonsOn(rightAttackButtons);
        rightTurn = false;
    }

    // Game cannot be over when calling this function
    public void switchTurn(){
        if(leftTurn){
            setButtonsOff(leftAttackButtons);
            leftTurn = false;
            setButtonsOn(rightAttackButtons);
            rightTurn = true;
        }
        else {
            if (rightTurn) {
                setButtonsOn(leftAttackButtons);
                leftTurn = true;
                setButtonsOff(rightAttackButtons);
                rightTurn = false;
            }
        }
    }

    // At the end of the game
    public void setNoneTurn(){
        setButtonsOff(leftAttackButtons);
        leftTurn = false;
        setButtonsOff(rightAttackButtons);
        rightTurn = false;
    }

    private void setButtonsOn(FrameLayout[] buttons){
        for(FrameLayout button : buttons){
            button.setClickable(true);
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
