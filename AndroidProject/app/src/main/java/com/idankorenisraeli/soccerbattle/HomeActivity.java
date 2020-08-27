package com.idankorenisraeli.soccerbattle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {

    ImageView backgroundImage;
    Button startButton, tableButton;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        findViews();
        setBackgroundImage();
        context = this;

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
                Intent intent = new Intent(HomeActivity.this, TableHighscoreActivity.class);
                startActivity(intent);
            }
        });

    }



    private void findViews(){
        backgroundImage = findViewById(R.id.image_background);
        startButton = findViewById(R.id.home_start_button);
        tableButton = findViewById(R.id.home_records_table_button);
    }

    private void setBackgroundImage(){
        int imageId = GameData.getInstance().getBackgroundId();
        CommonUtils.getInstance().setImageResource(backgroundImage, imageId);
    }




    @Override
    public void onBackPressed(){
        finish();
        android.os.Process.killProcess(android.os.Process.myPid());
        // Guarantee application killed completely when user presses back button on this screen
    }
}