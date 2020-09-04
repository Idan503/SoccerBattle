package com.idankorenisraeli.soccerbattle.activity;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.MarkerOptions;
import com.idankorenisraeli.soccerbattle.R;
import com.idankorenisraeli.soccerbattle.common.CommonUtils;
import com.idankorenisraeli.soccerbattle.game.GameData;
import com.idankorenisraeli.soccerbattle.game.GameResult;
import com.idankorenisraeli.soccerbattle.top_ten.TopTenMap;
import com.idankorenisraeli.soccerbattle.top_ten.TopTenTable;

public class TopTenActivity extends FragmentActivity {

    TopTenMap map;
    TopTenTable table;
    Button restartGameButton, backHomeButton;
    ImageView backgroundImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_ten);


        findViews();
        setBackgroundImage();
        initButtons();
    }

    private void findViews(){
        map = (TopTenMap) getSupportFragmentManager().findFragmentById(R.id.top_ten_FRAG_map);
        table = (TopTenTable) getSupportFragmentManager().findFragmentById(R.id.top_ten_FRAG_table);
        backgroundImage = findViewById(R.id.common_IMG_background);
        restartGameButton = findViewById(R.id.top_ten_BTN_start);
        backHomeButton = findViewById(R.id.top_ten_BTN_home);
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

        TableLayout tableLayout = table.getTableView();

        for(int row = 1; row < tableLayout.getChildCount(); row++){
            final int currentRank = row;
            tableLayout.getChildAt(row).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // When clicking on each row

                    map.setMarker(table.getEntryByRank(currentRank));
                }
            });
        }
    }

    private void setBackgroundImage(){
        // For background image
        CommonUtils.getInstance().setImageResource(backgroundImage, GameData.getInstance().getBackgroundId());
    }


    @Override
    public void onBackPressed(){
        finish();
    }
}