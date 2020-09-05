package com.idankorenisraeli.soccerbattle.activity;

import androidx.fragment.app.FragmentActivity;

import android.app.Activity;
import android.content.DialogInterface;
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
import com.idankorenisraeli.soccerbattle.common.MaterialDialogProperties;
import com.idankorenisraeli.soccerbattle.common.SharedPrefsManager;
import com.idankorenisraeli.soccerbattle.game.GameData;
import com.idankorenisraeli.soccerbattle.game.GameResult;
import com.idankorenisraeli.soccerbattle.top_ten.TopTenMap;
import com.idankorenisraeli.soccerbattle.top_ten.TopTenTable;

public class TopTenActivity extends FragmentActivity {

    TopTenMap map;
    TopTenTable table;
    Button restartGameButton, backHomeButton;
    ImageView backgroundImage;

    String resultDeleteMessage = "Delete this saved game result?";
    MaterialDialogProperties resultDeleteConfirmation = new MaterialDialogProperties(TopTenActivity.this,
            "Delete Result", "Delete", "Cancel", resultDeleteMessage,null,null);

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
            tableLayout.getChildAt(row).setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    final GameResult toDelete = table.getEntryByRank(currentRank);
                    // Generating the dialog properties and showing the relevant dialog
                    resultDeleteConfirmation.setMessage(resultDeleteMessage + "\n> " + toDelete.toString());
                    resultDeleteConfirmation.setOnPositive(new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            table.deleteResult(toDelete);
                            restart(); // Result has been deleted - table need to be refreshed.
                        }
                    });
                    CommonUtils.getInstance().showMaterialAlertDialog(resultDeleteConfirmation);

                    return false;
                }
            });
        }
    }

    private void restart(){
        finish();
        startActivity(getIntent());
    }


    private void setBackgroundImage(){
        // For background image
        CommonUtils.getInstance().setImageResource(backgroundImage, GameData.DRAWABLE_KEYS.BACKGROUND_FIELD_DRAWABLE_ID);
    }



    @Override
    public void onBackPressed(){
        finish();
    }
}