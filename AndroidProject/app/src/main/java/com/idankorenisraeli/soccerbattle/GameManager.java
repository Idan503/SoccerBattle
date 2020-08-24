package com.idankorenisraeli.soccerbattle;

import android.content.Context;

public class GameManager {

    private static GameManager single_instance = null;

    private int turnsPlayed;

    private interface IMAGE_KEYS{
        int PLAYER_LEFT_IMAGE_ID = R.drawable.icons8_messi;
        int PLAYER_RIGHT_IMAGE_ID = R.drawable.icons8_ronaldo;
        int PASS_IMAGE_ID =  R.drawable.flaticon_kickoff;
        int SHOT_IMAGE_ID = R.drawable.flaticon_soccer_shot;
        int GOAL_IMAGE_ID = R.drawable.flaticon_soccer_goal;
    }


    // region Default Attacks

    private static final SoccerAttack ATTACK_PASS = new SoccerAttack("Pass", 5, IMAGE_KEYS.PASS_IMAGE_ID);
    private static final SoccerAttack ATTACK_SHOT = new SoccerAttack("Shot", 10, IMAGE_KEYS.SHOT_IMAGE_ID);
    private static final SoccerAttack ATTACK_GOAL = new SoccerAttack("Goal", 20, IMAGE_KEYS.GOAL_IMAGE_ID);

    private static final SoccerAttack[] DEFAULT_ATTACKS = new SoccerAttack[]{ATTACK_PASS, ATTACK_SHOT, ATTACK_GOAL};

    // endregion


    // region Default Players

    private static final SoccerPlayer PLAYER_LEFT = new SoccerPlayer("Messi", IMAGE_KEYS.PLAYER_LEFT_IMAGE_ID, DEFAULT_ATTACKS);
    private static final SoccerPlayer PLAYER_RIGHT = new SoccerPlayer("Ronaldo", IMAGE_KEYS.PLAYER_RIGHT_IMAGE_ID, DEFAULT_ATTACKS);

    private static final SoccerPlayer FIRST_TO_PLAY = PLAYER_LEFT;
    // endregion


    private GameManager(){
        turnsPlayed = 0;
    }

    public static GameManager getInstance(){
        if(single_instance == null)
            single_instance = new GameManager();
        return single_instance;
    }

    public SoccerAttack[] getDefaultAttacks(){
        return new SoccerAttack[]{ATTACK_PASS, ATTACK_SHOT, ATTACK_GOAL};
    }

    public SoccerPlayer getDefaultPlayerLeft(){
        return PLAYER_LEFT;
    }

    public SoccerPlayer getDefaultPlayerRight(){
        return PLAYER_RIGHT;
    }

    public void playedTurn(){
        turnsPlayed++;
    }

    public int getTurnsPlayed(){
        return turnsPlayed;
    }



}
