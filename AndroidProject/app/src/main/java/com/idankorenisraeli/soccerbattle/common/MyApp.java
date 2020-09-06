package com.idankorenisraeli.soccerbattle.common;

import android.app.Application;
import android.media.SoundPool;
import android.util.Log;

import com.idankorenisraeli.soccerbattle.R;
import com.idankorenisraeli.soccerbattle.game.GameData;

public class MyApp extends Application {
    @Override
    public void onCreate(){
    super.onCreate();

    // Initiating Singletone with Application Context only.
    CommonUtils.initHelper(this);
    SharedPrefsManager.initHelper(this);

    // Here we add all the sounds raw ids that will be played
    int[] soundsIds = new int[]
            {GameData.SOUND_KEYS.KICK_PASS,
            GameData.SOUND_KEYS.KICK_SHOT,
            GameData.SOUND_KEYS.KICK_GOAL,
            GameData.SOUND_KEYS.CROWD_GOAL,
            GameData.SOUND_KEYS.CROWD_FINISH,
            GameData.SOUND_KEYS.CROWD_AMBIENT};
    SoundManager.initHelper(this, soundsIds);


    }

}
