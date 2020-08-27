package com.idankorenisraeli.soccerbattle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
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

    AttackButtonsManager attackManager;
    TextView attackMessage;
    // endregion

    // region Other Declarations

    ImageView backgroundImage;
    // endregion


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
        startGame();
    }

    // region Runtime Interface Initialisation
    private void initUI(){
        setContentView(R.layout.activity_main);
        findViews();
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

        backgroundImage = findViewById(R.id.image_background);
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

        attackMessage = findViewById(R.id.attack_message);
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
        playerLeftBar.setProgress(playerLeft.getCurrentPoints());
        playerLeftBar.setEnabled(false); // Prevent user change

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
        playerRightBar.setProgress(playerRight.getCurrentPoints());
        playerRightBar.setEnabled(false); // Prevent user change

        // For background image
        utils.setImageResource(backgroundImage,GameData.getInstance().getBackgroundId());
    }

    private void generatePlayers(){
        GameData data = GameData.getInstance();
        playerLeft = data.getPlayerLeft();
        playerRight = data.getPlayerRight();

        setElementsUI(playerLeft, playerRight);
    }

    private void setAttacksListeners(){
        SoccerAttack[] leftAttacks = playerLeft.getAttacks();
        // There might be more attack messages placeholder in the future, so it is passed in constructor too
        attackLeftLayout1.setOnClickListener(new AttackListener(playerLeft,leftAttacks[0],playerLeftBar, attackMessage));
        attackLeftLayout2.setOnClickListener(new AttackListener(playerLeft,leftAttacks[1],playerLeftBar, attackMessage));
        attackLeftLayout3.setOnClickListener(new AttackListener(playerLeft,leftAttacks[2],playerLeftBar, attackMessage));

        SoccerAttack[] rightAttacks = playerRight.getAttacks();
        attackRightLayout1.setOnClickListener(new AttackListener(playerRight,rightAttacks[0],playerRightBar, attackMessage));
        attackRightLayout2.setOnClickListener(new AttackListener(playerRight,rightAttacks[1],playerRightBar, attackMessage));
        attackRightLayout3.setOnClickListener(new AttackListener(playerRight,rightAttacks[2],playerRightBar, attackMessage));
    }

    // endregion

    private void startGame(){
        attackManager = AttackButtonsManager.initHelper(
                new FrameLayout[]{attackLeftLayout1,attackLeftLayout2,attackLeftLayout3},
                new FrameLayout[]{attackRightLayout1,attackRightLayout2,attackRightLayout3});

        attackManager.updateAttackButtons();
    }

    // region Rotation Configuration Handling
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Code gets to here after device is rotated
        // Our layout should be reloaded because of different version for landscape/portrait
        initUI();
        startGame();
    }


    // endregion

    @Override
    public void onBackPressed(){
        finish();
        android.os.Process.killProcess(android.os.Process.myPid());
        // Guarantee application killed completely when user presses back button on this screen
    }
}