package com.idankorenisraeli.soccerbattle.activity;

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

}
