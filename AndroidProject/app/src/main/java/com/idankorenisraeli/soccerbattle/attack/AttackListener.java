package com.idankorenisraeli.soccerbattle.attack;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.idankorenisraeli.soccerbattle.common.SoundManager;
import com.idankorenisraeli.soccerbattle.game.GameData;
import com.idankorenisraeli.soccerbattle.game.GameManager;
import com.idankorenisraeli.soccerbattle.player.SoccerPlayer;

public class AttackListener implements View.OnClickListener {
    SoccerPlayer player;
    SoccerAttack attack;
    SeekBar bar;
    TextView message;


    public AttackListener(SoccerPlayer player, SoccerAttack attack, SeekBar playerBar, TextView message){
        this.player = player;
        this.attack = attack;
        this.bar = playerBar;
        this.message = message;
        updateBarProgress(playerBar.getContext());
    }

    @Override
    public void onClick(View view) {
        if(!GameManager.getInstance().isGameOver()) {
            player.performAttack(attack);
            playAttackSound();
            updateBarProgress(view.getContext());
            AttackButtonsManager.getInstance().updateAttackButtons(); // Updating UI Attack buttons
            AttackMessageAnimator.getInstance().showMessage(message, player.getName(), attack.getName(), attack.getPoints());
        }
    }

    private void playAttackSound(){
        SoundManager.getInstance().play(attack.getSoundId());
        // For special attack (goals in this game), there will be another sound
        if(attack.getName().toLowerCase().contains("goal")){
            SoundManager.getInstance().play(GameData.SOUND_KEYS.CROWD_GOAL);
        }
    }

    private void updateBarProgress(Context context){
        bar.setProgress(player.getCurrentPoints()); // Setting the value of the progress bar
        if(player.getCurrentPoints() >= (int) (GameData.GREEN_BAR_THRESHOLD * SoccerPlayer.MAX_POINTS))
            setBarProgressColor(context, GameData.ProgressBarColor.GREEN);
        else
            setBarProgressColor(context, GameData.ProgressBarColor.RED);
    }


    private void setBarProgressColor(Context context, GameData.ProgressBarColor color){
        Drawable progress;
        if (color == GameData.ProgressBarColor.GREEN) {
            progress = ContextCompat.getDrawable(context, GameData.DRAWABLE_KEYS.BAR_GREEN_DRAWABLE_ID);
        } else {
            progress = ContextCompat.getDrawable(context, GameData.DRAWABLE_KEYS.BAR_RED_DRAWABLE_ID);
        }
        bar.setProgressDrawable(progress);
    }



}
