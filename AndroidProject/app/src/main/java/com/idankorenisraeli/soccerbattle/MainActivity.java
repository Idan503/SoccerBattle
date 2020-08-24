package com.idankorenisraeli.soccerbattle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // region Players Declarations

    /*  The battle is player "Left" vs player "Right".
        Images, names, attacks of players, are all being set dynamically */
    SeekBar playerLeftBar, playerRightBar;
    ImageView playerLeftImage, playerRightImage;
    SoccerPlayer playerLeft, playerRight;
    // endregion

    // region Attacks Declarations

    FrameLayout attackLeftLayout1, attackLeftLayout2, attackLeftLayout3; // Layouts are also attack buttons
    FrameLayout attackRightLayout1, attackRightLayout2, attackRightLayout3; // Layouts are also attack buttons
    ImageView attackLeftImage1, attackLeftImage2, attackLeftImage3;
    ImageView attackRightImage1, attackRightImage2, attackRightImage3;
    TextView attackLeftText1, attackLeftText2, attackLeftText3;
    TextView attackRightText1, attackRightText2, attackRightText3;
    LinearLayout attacksLeft, attacksRight;
    // endregion


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        hideStatusBar();
        generatePlayers();
        setAttacksListeners();



    }



    private void findViews(){
        attacksLeft = findViewById(R.id.layout_attacks_left);
        attacksRight = findViewById(R.id.layout_attacks_right);
        findAttackViews();

        playerLeftBar = findViewById(R.id.bar_player_left);
        playerRightBar = findViewById(R.id.bar_player_right);

        playerLeftImage = findViewById(R.id.image_player_left);
        playerRightImage = findViewById(R.id.image_player_right);
    }

    // Making the activity full-screen for better game experience
    private void hideStatusBar(){
        View view = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;

        view.setSystemUiVisibility(uiOptions);
        ActionBar actionBar = getActionBar();
        if(actionBar!=null)
            actionBar.hide();
    }

    // Attack views are inside different included layouts
    private void findAttackViews(){
        // Clickable layouts of attacks - buttons
        attackLeftLayout1 = attacksLeft.findViewById(R.id.attacks_layout_button_1);
        attackLeftLayout2 = attacksLeft.findViewById(R.id.attacks_layout_button_2);
        attackLeftLayout3 = attacksLeft.findViewById(R.id.attacks_layout_button_3);

        attackRightLayout1 = attacksRight.findViewById(R.id.attacks_layout_button_1);
        attackRightLayout2 = attacksRight.findViewById(R.id.attacks_layout_button_2);
        attackRightLayout3 = attacksRight.findViewById(R.id.attacks_layout_button_3);

        // Images of the attacks
        attackLeftImage1 = attacksLeft.findViewById(R.id.attacks_image_1);
        attackLeftImage2 = attacksLeft.findViewById(R.id.attacks_image_2);
        attackLeftImage3 = attacksLeft.findViewById(R.id.attacks_image_3);

        attackRightImage1 = attacksRight.findViewById(R.id.attacks_image_1);
        attackRightImage2 = attacksRight.findViewById(R.id.attacks_image_2);
        attackRightImage3 = attacksRight.findViewById(R.id.attacks_image_3);

        // Texts of names of attacks
        attackLeftText1 = attacksLeft.findViewById(R.id.attacks_text_1);
        attackLeftText2 = attacksLeft.findViewById(R.id.attacks_text_2);
        attackLeftText3 = attacksLeft.findViewById(R.id.attacks_text_3);

        attackRightText1 = attacksRight.findViewById(R.id.attacks_text_1);
        attackRightText2 = attacksRight.findViewById(R.id.attacks_text_2);
        attackRightText3 = attacksRight.findViewById(R.id.attacks_text_3);
    }

    // Using all UI views found and setting their texts/images
    private void setElementsUI(SoccerPlayer left, SoccerPlayer right){
        // For left player
        SoccerAttack[] leftAttacks = left.getAttacks();
        CommonUtils utils = CommonUtils.getInstance();
        utils.setImageResource(playerLeftImage, left.getImageId());
        utils.setImageResource(attackLeftImage1, leftAttacks[0].getImageId());
        utils.setImageResource(attackLeftImage2, leftAttacks[1].getImageId());
        utils.setImageResource(attackLeftImage3, leftAttacks[2].getImageId());
        attackLeftText1.setText(leftAttacks[0].getName());
        attackLeftText2.setText(leftAttacks[1].getName());
        attackLeftText3.setText(leftAttacks[2].getName());
        playerLeftBar.setMax(SoccerPlayer.MAX_POINTS);

        // For right player
        SoccerAttack[] rightAttacks = right.getAttacks();
        utils.setImageResource(playerRightImage, right.getImageId());
        utils.setImageResource(attackRightImage1, rightAttacks[0].getImageId());
        utils.setImageResource(attackRightImage2, rightAttacks[1].getImageId());
        utils.setImageResource(attackRightImage3, rightAttacks[2].getImageId());
        attackRightText1.setText(rightAttacks[0].getName());
        attackRightText2.setText(rightAttacks[1].getName());
        attackRightText3.setText(rightAttacks[2].getName());
        playerRightBar.setMax(SoccerPlayer.MAX_POINTS);
    }

    private void generatePlayers(){
        playerLeft = GameManager.getInstance().getPlayerLeft();
        playerRight = GameManager.getInstance().getPlayerRight();

        setElementsUI(playerLeft, playerRight);
    }

    private void setAttacksListeners(){
        SoccerAttack[] leftAttacks = playerLeft.getAttacks();
        attackLeftLayout1.setOnClickListener(new AttackButtonListener(playerLeft,leftAttacks[0],playerLeftBar));
        attackLeftLayout2.setOnClickListener(new AttackButtonListener(playerLeft,leftAttacks[1],playerLeftBar));
        attackLeftLayout3.setOnClickListener(new AttackButtonListener(playerLeft,leftAttacks[2],playerLeftBar));

        SoccerAttack[] rightAttacks = playerRight.getAttacks();
        attackRightLayout1.setOnClickListener(new AttackButtonListener(playerRight,rightAttacks[0],playerRightBar));
        attackRightLayout2.setOnClickListener(new AttackButtonListener(playerRight,rightAttacks[1],playerRightBar));
        attackRightLayout3.setOnClickListener(new AttackButtonListener(playerRight,rightAttacks[2],playerRightBar));
    }




}