package com.idankorenisraeli.soccerbattle.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.idankorenisraeli.soccerbattle.common.CommonUtils;
import com.idankorenisraeli.soccerbattle.game.RobotPlayer;
import com.idankorenisraeli.soccerbattle.player.PlayerSide;
import com.idankorenisraeli.soccerbattle.R;
import com.idankorenisraeli.soccerbattle.player.SoccerPlayer;
import com.idankorenisraeli.soccerbattle.attack.AttackButtonsManager;
import com.idankorenisraeli.soccerbattle.attack.AttackListener;
import com.idankorenisraeli.soccerbattle.attack.SoccerAttack;
import com.idankorenisraeli.soccerbattle.dice.DiceFragment;
import com.idankorenisraeli.soccerbattle.dice.DiceRolledListener;
import com.idankorenisraeli.soccerbattle.game.GameData;
import com.idankorenisraeli.soccerbattle.game.GameFinishedListener;
import com.idankorenisraeli.soccerbattle.game.GameManager;

public class GameActivity extends AppCompatActivity implements DiceRolledListener, GameFinishedListener {

    // region Players Declarations

    /*  The battle is player "Left" vs player "Right".
        Images, names, attacks of players, are all being set dynamically */
    SeekBar playerLeftBar, playerRightBar;
    ImageView playerLeftImage, playerRightImage;
    SoccerPlayer playerLeft, playerRight;
    // endregion

    //region Dice
    DiceFragment leftDice, rightDice;
    int[] diceResult = {0,0}; // When dice hasn't rolled yer, its value here is 0.
    //endregion

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
        GameManager.initHelper(this);

        Log.d("Created","GAME");
        if(savedInstanceState==null)
            resetGame();
        initUI();
        initDice();
        initGame();
    }

    // region Runtime Interface Initialisation
    private void initUI(){
        setContentView(R.layout.activity_game);
        findViews();
        generatePlayers();
        setAttacksListeners();
    }

    private void initDice(){
        leftDice = new DiceFragment();
        rightDice = new DiceFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.game_DICE_left, leftDice);
        ft.replace(R.id.game_DICE_right, rightDice);
        leftDice.setSide(PlayerSide.LEFT);
        rightDice.setSide(PlayerSide.RIGHT);
        if(!isFinishing())
            ft.commit();
    }

    private void findViews(){
        attacksLeft = findViewById(R.id.game_LAY_attacks_left);
        attacksRight = findViewById(R.id.game_LAY_attacks_right);
        findAttackViews();

        playerLeftBar = findViewById(R.id.game_SB_player_left);
        playerRightBar = findViewById(R.id.game_SB_player_right);
        playerLeftImage = findViewById(R.id.game_IMG_player_left);
        playerRightImage = findViewById(R.id.game_IMG_player_right);
        backgroundImage = findViewById(R.id.common_IMG_background);
    }

    // Attack views are inside different included layouts
    private void findAttackViews(){
        // Clickable layouts of attacks - buttons
        attackLeftLayout1 = attacksLeft.findViewById(R.id.attacks_LAY_1);
        attackLeftLayout2 = attacksLeft.findViewById(R.id.attacks_LAY_2);
        attackLeftLayout3 = attacksLeft.findViewById(R.id.attacks_LAY_3);

        attackRightLayout1 = attacksRight.findViewById(R.id.attacks_LAY_1);
        attackRightLayout2 = attacksRight.findViewById(R.id.attacks_LAY_2);
        attackRightLayout3 = attacksRight.findViewById(R.id.attacks_LAY_3);

        // Images of the attacks
        attackLeftImage1 = attacksLeft.findViewById(R.id.attacks_IMG_1);
        attackLeftImage2 = attacksLeft.findViewById(R.id.attacks_IMG_2);
        attackLeftImage3 = attacksLeft.findViewById(R.id.attacks_IMG_3);

        attackRightImage1 = attacksRight.findViewById(R.id.attacks_IMG_1);
        attackRightImage2 = attacksRight.findViewById(R.id.attacks_IMG_2);
        attackRightImage3 = attacksRight.findViewById(R.id.attacks_IMG_3);

        // Texts of names of attacks
        attackLeftText1 = attacksLeft.findViewById(R.id.attacks_LBL_1);
        attackLeftText2 = attacksLeft.findViewById(R.id.attacks_LBL_2);
        attackLeftText3 = attacksLeft.findViewById(R.id.attacks_LBL_3);

        attackRightText1 = attacksRight.findViewById(R.id.attacks_LBL_1);
        attackRightText2 = attacksRight.findViewById(R.id.attacks_LBL_2);
        attackRightText3 = attacksRight.findViewById(R.id.attacks_LBL_3);

        attackMessage = findViewById(R.id.game_LBL_attack);
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
        utils.setImageResource(backgroundImage, GameData.getInstance().getBackgroundId());
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

    private void initGame(){
        attackManager = AttackButtonsManager.initHelper(
                new FrameLayout[]{attackLeftLayout1,attackLeftLayout2,attackLeftLayout3},
                new FrameLayout[]{attackRightLayout1,attackRightLayout2,attackRightLayout3});

        attackManager.updateAttackButtons();

        if(bothDiceRolled() && GameManager.getInstance().ROBOT_PLAYER){
            initRobot();
        }

    }

    private void initRobot(){
        FrameLayout[] leftAttacks = {attackLeftLayout1, attackLeftLayout2, attackLeftLayout3};
        FrameLayout[] rightAttacks = {attackRightLayout1, attackRightLayout2, attackRightLayout3};
        RobotPlayer robot = new RobotPlayer(leftAttacks, rightAttacks);
        robot.play();
    }

    // region Rotation Configuration Handling
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Code gets to here after device is rotated
        // Our layout should be reloaded because of different version for landscape/portrait
        initUI();
        if(!bothDiceRolled()) // Prevent showing dice after game is started
            resetDice(); // Re-render dice on screen
        initGame();
    }


    // endregion


    @Override
    public void onDiceRolled(int result, PlayerSide side) {
        switch (side){
            case LEFT:
                diceResult[0] = result;
                break;
            case RIGHT:
                diceResult[1] = result;
                break;
        }
        if(bothDiceRolled())
        {
            final int DELAY_BEFORE_RESET = 1500; // in ms, wait before dice restart to show tie result
            // Code gets to here after both dice are rolled (non zero)
            if(diceResult[0] == diceResult[1])
            {
                CommonUtils.getInstance().showToast("Dice draw, please roll again");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        resetDice();
                    }
                },DELAY_BEFORE_RESET);
            }
            else {
                // Calculating who win the dice
                if(diceResult[0] > diceResult[1]){
                    GameManager.getInstance().setCurrentTurn(PlayerSide.LEFT);
                } else{
                    GameManager.getInstance().setCurrentTurn(PlayerSide.RIGHT);
                }

                // Game can be started
                initGame();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        leftDice.fadeOut();
                        rightDice.fadeOut();
                    }
                },DELAY_BEFORE_RESET);

            }
        }
    }

    private void resetDice(){
        diceResult[0] = 0;
        diceResult[1] = 0;

        // Killing any live-rolling dice, to prevent old result effecting
        if(leftDice!=null && leftDice.isRolling())
            leftDice.cancelRoll();
        if(rightDice!=null && rightDice.isRolling())
            rightDice.cancelRoll();
        initDice();
    }

    @Override
    public void onGameFinished(int turns, PlayerSide winner) {
        GameManager.getInstance().saveResult(this);
        Intent intent = new Intent(GameActivity.this, ResultActivity.class);
        intent.putExtra("WinnerPlayer", GameManager.getInstance().getCurrentTurn());
        intent.putExtra("TurnsPlayed", GameManager.getInstance().getTurnsPlayed());
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed(){
        finish();
    }

    private void resetGame(){
        GameManager.getInstance().reset(this);
        GameData.getInstance().reset();
        resetDice();
    }

    private boolean bothDiceRolled(){
        return diceResult[0] > 0 && diceResult[1] > 0;
    }

}