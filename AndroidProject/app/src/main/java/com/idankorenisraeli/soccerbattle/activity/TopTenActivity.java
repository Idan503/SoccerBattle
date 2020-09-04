package com.idankorenisraeli.soccerbattle.activity;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.MarkerOptions;
import com.idankorenisraeli.soccerbattle.R;
import com.idankorenisraeli.soccerbattle.game.GameResult;
import com.idankorenisraeli.soccerbattle.top_ten.TopTenMap;
import com.idankorenisraeli.soccerbattle.top_ten.TopTenTable;

public class TopTenActivity extends FragmentActivity {

    TopTenMap map;
    TopTenTable table;
    Button restartGameButton, backHomeButton;

    private static final float MAP_CAMERA_ZOOM = 10f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_ten);

        findViews();
        initButtons();
    }

    private void findViews(){
        map = (TopTenMap) getSupportFragmentManager().findFragmentById(R.id.top_ten_FRAG_map);
        table = (TopTenTable) getSupportFragmentManager().findFragmentById(R.id.top_ten_FRAG_table);
        restartGameButton = findViewById(R.id.top_ten_BTN_restart);
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

                    setMapMarker(map.getMap(),  table.getEntryByRank(currentRank));
                }
            });
        }
    }



    private void setMapMarker(GoogleMap map, GameResult entry) {
        MarkerOptions markerOptions = new MarkerOptions();
        map.clear(); // Deleting previous markers

        markerOptions.position(entry.getLocation());
        markerOptions.title(entry.getName() + " - " + entry.getTurns() + " turns");
        map.addMarker(markerOptions);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(markerOptions.getPosition(), MAP_CAMERA_ZOOM ));
    }
}