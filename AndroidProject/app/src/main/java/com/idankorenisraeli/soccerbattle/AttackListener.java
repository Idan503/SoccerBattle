package com.idankorenisraeli.soccerbattle;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.SeekBar;

import androidx.core.content.ContextCompat;

public class AttackListener implements View.OnClickListener {
    SoccerPlayer player;
    SoccerAttack attack;
    SeekBar bar;

    public AttackListener(SoccerPlayer player, SoccerAttack attack, SeekBar playerBar){
        this.player = player;
        this.attack = attack;


        this.bar = playerBar;
    }

    @Override
    public void onClick(View view) {
        if(!GameManager.getInstance().isGameOver()) {
            boolean isWin = player.performAttack(attack);

            updateBarProgress(view.getContext());
            AttackButtonsManager.getInstance().switchTurn(); // Next turn

            if (isWin) {
                CommonUtils.getInstance().showToast(player.getName() + " Wins!");
                AttackButtonsManager.getInstance().setNoneTurn(); // Game is over
            }
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
