package com.idankorenisraeli.soccerbattle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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

    }

    private void findViews(){
        winnerTitle = findViewById(R.id.winner_title_text);
        resultMessage = findViewById(R.id.result_message_text);
        winnerImage = findViewById(R.id.winner_image);
        restartButton = findViewById(R.id.restart_game_button);
        topTenButton = findViewById(R.id.top_ten_button);
        backHomeButton = findViewById(R.id.back_home_button);
        backgroundImage = findViewById(R.id.image_background);
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
                Intent intent = new Intent(ResultActivity.this, GameActivity.class);
                startActivity(intent);
                finish();
            }
        });
        topTenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this, TopTenActivity.class);
                startActivity(intent);
                finish();
            }
        });
        backHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void setBackgroundImage(){
        // For background image
        CommonUtils.getInstance().setImageResource(backgroundImage,GameData.getInstance().getBackgroundId());
    }
}