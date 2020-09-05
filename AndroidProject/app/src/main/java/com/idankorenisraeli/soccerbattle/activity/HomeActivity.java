package com.idankorenisraeli.soccerbattle.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.idankorenisraeli.soccerbattle.common.CommonUtils;
import com.idankorenisraeli.soccerbattle.R;
import com.idankorenisraeli.soccerbattle.common.SoundManager;
import com.idankorenisraeli.soccerbattle.game.GameData;

public class HomeActivity extends AppCompatActivity {

    ImageView backgroundImage;
    Button startButton, tableButton;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        findViews();
        setBackgroundImage();
        context = this;

        Log.d("Created","HOME");

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });

        tableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, TopTenActivity.class);
                startActivity(intent);
            }
        });

        SoundPool pool = SoundManager.getInstance().getSoundPool();
        pool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int i, int i1) {
                SoundManager.getInstance().playLoop(GameData.SOUND_KEYS.CROWD_AMBIENT);
            }
        });
    }


    private void findViews(){
        backgroundImage = findViewById(R.id.common_IMG_background);
        startButton = findViewById(R.id.home_BTN_start);
        tableButton = findViewById(R.id.home_BTN_top_ten);
    }

    private void setBackgroundImage(){
        int imageId = GameData.DRAWABLE_KEYS.BACKGROUND_FIELD_DRAWABLE_ID;
        CommonUtils.getInstance().setImageResource(backgroundImage, imageId);
    }




    @Override
    public void onBackPressed(){
        finish();
        android.os.Process.killProcess(android.os.Process.myPid());
        // Guarantee application killed completely when user presses back button on this screen
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if(isFinishing()){
            SoundManager.getInstance().release();
        }
    }
}