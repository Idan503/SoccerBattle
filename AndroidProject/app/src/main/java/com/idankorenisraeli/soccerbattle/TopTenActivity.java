package com.idankorenisraeli.soccerbattle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class TopTenActivity extends FragmentActivity {

    TopTenMap map;
    TopTenTable table;
    Button restartGameButton, backHomeButton;

    private final float MAP_CAMERA_ZOOM = 10f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_ten);

        findViews();
        initButtons();
    }

    private void findViews(){
        map = (TopTenMap) getSupportFragmentManager().findFragmentById(R.id.top_ten_map_fragment);
        table = (TopTenTable) getSupportFragmentManager().findFragmentById(R.id.fragment_table);
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


    private void setMapMarker(GoogleMap map, TableEntry entry) {
        MarkerOptions markerOptions = new MarkerOptions();
        map.clear(); // Deleting previous markers

        markerOptions.position(entry.getLocation());
        markerOptions.title(entry.getName() + " - " + entry.getTurns() + " turns");
        map.addMarker(markerOptions);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(markerOptions.getPosition(), MAP_CAMERA_ZOOM ));
    }
}