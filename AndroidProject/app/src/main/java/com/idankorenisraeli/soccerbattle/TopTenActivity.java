package com.idankorenisraeli.soccerbattle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

public class TopTenActivity extends AppCompatActivity {

    Fragment mapFragment;
    TableLayout table;
    Button restartGameButton, backHomeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_ten);

        findViews();
        initButtons();
    }

    private void findViews(){
        mapFragment = getSupportFragmentManager().findFragmentById(R.id.top_ten_map_fragment);
        table = findViewById(R.id.top_ten_table);
        restartGameButton = findViewById(R.id.restart_game_button);
        backHomeButton = findViewById(R.id.back_home_button);
    }

    private void initButtons(){
        restartGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TopTenActivity.this, GameActivity.class);
                startActivity(intent);
                finish();
            }
        });
        backHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TopTenActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}