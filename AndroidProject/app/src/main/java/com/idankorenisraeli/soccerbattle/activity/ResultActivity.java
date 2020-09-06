package com.idankorenisraeli.soccerbattle.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.idankorenisraeli.soccerbattle.common.CommonUtils;
import com.idankorenisraeli.soccerbattle.common.SoundManager;
import com.idankorenisraeli.soccerbattle.player.PlayerSide;
import com.idankorenisraeli.soccerbattle.R;
import com.idankorenisraeli.soccerbattle.game.GameData;

public class ResultActivity extends AppCompatActivity {

    TextView winnerTitle, resultMessage;
    ImageView backgroundImage, winnerImage;
    Button restartButton, topTenButton, backHomeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        PlayerSide winner = (PlayerSide) getIntent().getSerializableExtra("WinnerPlayer");
        int numOfTurns = getIntent().getIntExtra("TurnsPlayed", 0);

        findViews();
        initButtons();

        if(winner!=null) {
            setContent(winner, numOfTurns);
        }

        setBackgroundImage();

        SoundManager.getInstance().play(GameData.SOUND_KEYS.CROWD_FINISH);

    }

    private void findViews(){
        winnerTitle = findViewById(R.id.results_LBL_title);
        resultMessage = findViewById(R.id.results_LBL_message);
        winnerImage = findViewById(R.id.results_IMG_winner);
        restartButton = findViewById(R.id.top_ten_BTN_start);
        topTenButton = findViewById(R.id.results_BTN_top_ten);
        backHomeButton = findViewById(R.id.top_ten_BTN_home);
        backgroundImage = findViewById(R.id.common_IMG_background);
    }

    private void setContent(PlayerSide winner, int numOfTurns){
        CommonUtils utils = CommonUtils.getInstance();
        GameData data = GameData.getInstance();
        switch (winner){
            case LEFT:
                winnerTitle.setText(generateWinnerTitle(data.getPlayerLeft().getName()));
                resultMessage.setText(generateResultMessage(numOfTurns));
                utils.setImageResource(winnerImage, data.getPlayerLeft().getImageId());
                break;
            case RIGHT:
                winnerTitle.setText(generateWinnerTitle(data.getPlayerRight().getName()));
                resultMessage.setText(generateResultMessage(numOfTurns));
                utils.setImageResource(winnerImage, data.getPlayerRight().getImageId());
                break;
            default:
                winnerTitle.setText(generateWinnerTitle("N/A"));
                resultMessage.setText(generateResultMessage(0));
                utils.setImageResource(winnerImage, data.getPlayerLeft().getImageId());
                break;
        }
    }

    private String generateResultMessage(int numOfTurns){
        return getApplicationContext().getString(R.string.win_result_message, numOfTurns);
    }

    private String generateWinnerTitle(String winnerName){
        return getApplicationContext().getString(R.string.player_win, winnerName);
    }

    private void initButtons(){
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setEnabled(false);
                Intent intent = new Intent(ResultActivity.this, GameActivity.class);
                startActivity(intent);
                finish();
            }
        });
        topTenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setEnabled(false);
                Intent intent = new Intent(ResultActivity.this, TopTenActivity.class);
                startActivity(intent);
                finish();
            }
        });
        backHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setBackgroundImage(){
        // For background image
        CommonUtils.getInstance().setImageResource(backgroundImage,GameData.DRAWABLE_KEYS.BACKGROUND_FIELD_DRAWABLE_ID);
    }



    @Override
    public void onResume() {
        super.onResume();
        backHomeButton.setEnabled(true);
        restartButton.setEnabled(true);
        topTenButton.setEnabled(true);
    }

    @Override
    public void onBackPressed(){
        finish();
    }
}