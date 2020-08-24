package com.idankorenisraeli.soccerbattle;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.SeekBar;

import androidx.core.content.ContextCompat;

public class AttackButtonListener implements View.OnClickListener {
    SoccerPlayer player;
    SoccerAttack attack;
    SeekBar bar;

    GameManager manager;

    public AttackButtonListener(SoccerPlayer player, SoccerAttack attack, SeekBar playerBar){
        this.player = player;
        this.attack = attack;
        this.bar = playerBar;
        manager = GameManager.getInstance();
    }

    @Override
    public void onClick(View view) {
        if(!manager.isGameOver()) {
            boolean isWin = player.performAttack(attack);
            bar.setProgress(player.getCurrentPoints());
            updateBarProgressColor(view.getContext());

            if (isWin) {
                CommonUtils.getInstance().showToast(player.getName() + " Wins!");
            }
        }
    }

    // Should be called every turn in case of points deduct
    private void updateBarProgressColor(Context context){
        if(player.getCurrentPoints() >= (int) (manager.getGreenBarThreshold() * SoccerPlayer.MAX_POINTS))
            setBarProgressColor(context, GameManager.ProgressBarColor.GREEN);
        else
            setBarProgressColor(context, GameManager.ProgressBarColor.RED);
    }


    private void setBarProgressColor(Context context, GameManager.ProgressBarColor color){
        Drawable progress;
        if (color == GameManager.ProgressBarColor.GREEN) {
            progress = ContextCompat.getDrawable(context, manager.getGreenBarId());
        } else {
            progress = ContextCompat.getDrawable(context, manager.getRedBarId());
        }
        bar.setProgressDrawable(progress);
    }


}
