package com.idankorenisraeli.soccerbattle.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.NonNull;

import com.idankorenisraeli.soccerbattle.game.GameData;

import java.util.HashMap;
import java.util.Objects;

public class SoundManager {
    private Context context;
    @SuppressLint("StaticFieldLeak")
    // There is no possibility of memory leak because the single instance is generated on Application class
    private static SoundManager single_instance = null;

    // Soundpool is for short sounds (SFX), while MediaPlayer is for longer sounds
    private SoundPool soundPool;
    private MediaPlayer player;
    private HashMap<Integer,Integer> keyToSound = new HashMap<>();

    private final static int DEFAULT_PRIORITY = 1;
    private SoundManager(SoundPool pool, Context context,int[] soundKeys){
        this.soundPool = pool;
        this.context = context;
        loadAllSounds(soundKeys);
    }

    public static SoundManager getInstance(){
        return single_instance;
    }

    public static SoundManager initHelper(Context context, int[] allKeys){
        if(single_instance==null) {
            AudioAttributes attributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            SoundPool pool = new SoundPool.Builder()
                    .setMaxStreams(10)
                    .setAudioAttributes(attributes)
                    .build();

            single_instance = new SoundManager(pool, context,allKeys);
        }
        return single_instance;
    }

    public void loadAllSounds(int[] keys){
        for(int key:keys)
            keyToSound.put(key, soundPool.load(context,key,DEFAULT_PRIORITY));
    }

    public void play(int soundKey) throws Resources.NotFoundException {
        if(keyToSound.containsKey(soundKey)) {
            soundPool.play(Objects.requireNonNull(keyToSound.get(soundKey)), 1, 1, 1, 0, 1);
        }
        else
            throw new Resources.NotFoundException("Could not find requested sound");
    }

    // Playing the same sound over and over
    public void playAmbient(int soundId){
        player = MediaPlayer.create(context,soundId);
        player.setLooping(true);
        player.start();

    }

    public void resetAmbient(){
        if(player!=null)
            player.reset();
    }


    public boolean isPlayingAmbient(){
        if(player==null)
            return false;
        return player.isPlaying();
    }

    public SoundPool getSoundPool(){
        return soundPool;
    }


    public void release(){
        soundPool.release();
        player.release();
        soundPool = null;
    }




}
