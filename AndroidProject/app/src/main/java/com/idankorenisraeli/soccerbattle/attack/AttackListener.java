package com.idankorenisraeli.soccerbattle.attack;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

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
            updateBarProgress(view.getContext());
            AttackButtonsManager.getInstance().updateAttackButtons(); // Updating UI Attack buttons
            AttackMessageAnimator.getInstance().showMessage(message, player.getName(), attack.getName(), attack.getPoints());
        }
    }

    // Should be called every turn in case of points deduct
    private void updateBarProgress(Context context){
        GameData data = GameData.getInstance();
        bar.setProgress(player.getCurrentPoints()); // Setting the value of the progress bar
        if(player.getCurrentPoints() >= (int) (data.getGreenBarThreshold() * SoccerPlayer.MAX_POINTS))
            setBarProgressColor(context, GameData.ProgressBarColor.GREEN);
        else
            setBarProgressColor(context, GameData.ProgressBarColor.RED);
    }


    private void setBarProgressColor(Context context, GameData.ProgressBarColor color){
        GameData data = GameData.getInstance();
        Drawable progress;
        if (color == GameData.ProgressBarColor.GREEN) {
            progress = ContextCompat.getDrawable(context, data.getGreenBarId());
        } else {
            progress = ContextCompat.getDrawable(context, data.getRedBarId());
        }
        bar.setProgressDrawable(progress);
    }



}
