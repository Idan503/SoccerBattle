package com.idankorenisraeli.soccerbattle;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.text.Layout;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    // region Players & Attacks UI Variable Declarations

    /*  The battle is player "Left" (Left in English) vs player "Right" (Right in English).
        Images, names, attacks of players, are all being set dynamically */
    SeekBar playerLeftBar, playerRightBar;

    ImageView playerLeftImage, playerRightImage;
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
        GameManager manager = GameManager.getInstance();

        SoccerPlayer leftPlayer = manager.getDefaultPlayerLeft();
        SoccerPlayer rightPlayer = manager.getDefaultPlayerRight();

        setElementsUI(leftPlayer, rightPlayer);

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
        GlideUtils.getInstance().setImageResource(playerLeftImage, left.getImageId());
        GlideUtils.getInstance().setImageResource(attackLeftImage1, leftAttacks[0].getImageId());
        GlideUtils.getInstance().setImageResource(attackLeftImage2, leftAttacks[1].getImageId());
        GlideUtils.getInstance().setImageResource(attackLeftImage3, leftAttacks[2].getImageId());
        attackLeftText1.setText(leftAttacks[0].getName());
        attackLeftText2.setText(leftAttacks[1].getName());
        attackLeftText3.setText(leftAttacks[2].getName());

        // For right player
        SoccerAttack[] rightAttacks = right.getAttacks();
        GlideUtils.getInstance().setImageResource(playerRightImage, right.getImageId());
        GlideUtils.getInstance().setImageResource(attackRightImage1, rightAttacks[0].getImageId());
        GlideUtils.getInstance().setImageResource(attackRightImage2, rightAttacks[1].getImageId());
        GlideUtils.getInstance().setImageResource(attackRightImage3, rightAttacks[2].getImageId());
        attackRightText1.setText(rightAttacks[0].getName());
        attackRightText2.setText(rightAttacks[1].getName());
        attackRightText3.setText(rightAttacks[2].getName());
    }




}