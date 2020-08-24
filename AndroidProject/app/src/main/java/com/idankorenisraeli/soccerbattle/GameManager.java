package com.idankorenisraeli.soccerbattle;

import android.content.Context;

public class GameManager {

    private static GameManager single_instance = null;

    private int turnsPlayed;

    private interface DRAWABLE_KEYS{
        int PLAYER_LEFT_DRAWABLE_ID = R.drawable.icons8_messi;
        int PLAYER_RIGHT_DRAWABLE_ID = R.drawable.icons8_ronaldo;
        int PASS_DRAWABLE_ID =  R.drawable.flaticon_kickoff;
        int SHOT_DRAWABLE_ID = R.drawable.flaticon_soccer_shot;
        int GOAL_DRAWABLE_ID = R.drawable.flaticon_soccer_goal;
        int BAR_GREEN_DRAWABLE_ID = R.drawable.seekbar_progress_green;
        int BAR_RED_DRAWABLE_ID = R.drawable.seekbar_progress_red;
    }



    // region Default Attacks

    private static final SoccerAttack ATTACK_PASS = new SoccerAttack("Pass", 5, DRAWABLE_KEYS.PASS_DRAWABLE_ID);
    private static final SoccerAttack ATTACK_SHOT = new SoccerAttack("Shot", 10, DRAWABLE_KEYS.SHOT_DRAWABLE_ID);
    private static final SoccerAttack ATTACK_GOAL = new SoccerAttack("Goal", 20, DRAWABLE_KEYS.GOAL_DRAWABLE_ID);

    private static final SoccerAttack[] DEFAULT_ATTACKS = new SoccerAttack[]{ATTACK_PASS, ATTACK_SHOT, ATTACK_GOAL};

    // endregion

    // region Default Players

    private static final SoccerPlayer PLAYER_LEFT = new SoccerPlayer("Messi", DRAWABLE_KEYS.PLAYER_LEFT_DRAWABLE_ID, DEFAULT_ATTACKS);
    private static final SoccerPlayer PLAYER_RIGHT = new SoccerPlayer("Ronaldo", DRAWABLE_KEYS.PLAYER_RIGHT_DRAWABLE_ID, DEFAULT_ATTACKS);

    private static final SoccerPlayer FIRST_TO_PLAY = PLAYER_LEFT;
    // endregion


    // region Progress Bar
    public enum ProgressBarColor{ RED, GREEN}
    private static final float GREEN_BAR_THRESHOLD = 0.75f;

    // endregion


    private GameManager(){
        turnsPlayed = 0;
    }


    public static GameManager getInstance(){
        if(single_instance == null)
            single_instance = new GameManager();
        return single_instance;
    }
    public SoccerPlayer getPlayerLeft(){
        return PLAYER_LEFT;
    }

    public SoccerPlayer getPlayerRight(){
        return PLAYER_RIGHT;
    }

    public void playedTurn(){
        turnsPlayed++;
    }

    public int getTurnsPlayed(){
        return turnsPlayed;
    }


    public boolean isGameOver(){
        return (PLAYER_LEFT.getCurrentPoints() >= SoccerPlayer.MAX_POINTS ||
                PLAYER_RIGHT.getCurrentPoints() >= SoccerPlayer.MAX_POINTS);
    }


    public float getGreenBarThreshold(){
        return GREEN_BAR_THRESHOLD;
    }

    public int getRedBarId(){
        return DRAWABLE_KEYS.BAR_RED_DRAWABLE_ID;
    }

    public int getGreenBarId(){
        return DRAWABLE_KEYS.BAR_GREEN_DRAWABLE_ID;
    }


}
