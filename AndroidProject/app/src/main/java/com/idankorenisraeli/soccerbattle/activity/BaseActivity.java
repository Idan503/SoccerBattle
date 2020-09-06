package com.idankorenisraeli.soccerbattle.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;

import androidx.appcompat.app.AppCompatActivity;

import com.idankorenisraeli.soccerbattle.common.SoundManager;
import com.idankorenisraeli.soccerbattle.game.GameData;

public class BaseActivity extends AppCompatActivity {
    @Override
    public void onResume(){
        super.onResume();
        if(!SoundManager.getInstance().isPlayingAmbient())
            SoundManager.getInstance().playAmbient(GameData.SOUND_KEYS.CROWD_AMBIENT);
    }


    // a sound control of the background ambient is shared with all activities
    @Override
    public void onPause() {
        super.onPause();
        SoundManager.getInstance().resetAmbient();
    }


    protected void lockOrientation(){
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        else
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

}
