package com.idankorenisraeli.soccerbattle;

import android.content.Context;

// This class contains all game information which is loaded on runtime
public class GameData {

    private static GameData single_instance = null;

    public static GameData getInstance(){
        if(single_instance == null)
            single_instance = new GameData();
        return single_instance;
    }


    private interface DRAWABLE_KEYS{
        int PLAYER_LEFT_DRAWABLE_ID = R.drawable.icons8_messi;
        int PLAYER_RIGHT_DRAWABLE_ID = R.drawable.icons8_ronaldo;
        int PASS_DRAWABLE_ID =  R.drawable.flaticon_kickoff;
        int SHOT_DRAWABLE_ID = R.drawable.flaticon_soccer_shot;
        int GOAL_DRAWABLE_ID = R.drawable.flaticon_soccer_goal;
        int BAR_GREEN_DRAWABLE_ID = R.drawable.seekbar_progress_green;
        int BAR_RED_DRAWABLE_ID = R.drawable.seekbar_progress_red;
        int BACKGROUND_FIELD_DRAWABLE_ID = R.drawable.background_soccer_field;
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
    // endregion

    // region Progress Bar
    public enum ProgressBarColor{ RED, GREEN}
    private static final float GREEN_BAR_THRESHOLD = 0.7f;

    // endregion

    public int getRedBarId(){
        return DRAWABLE_KEYS.BAR_RED_DRAWABLE_ID;
    }

    public int getGreenBarId(){
        return DRAWABLE_KEYS.BAR_GREEN_DRAWABLE_ID;
    }

    public int getBackgroundId(){return DRAWABLE_KEYS.BACKGROUND_FIELD_DRAWABLE_ID;}

    public SoccerPlayer getPlayerLeft(){
        return PLAYER_LEFT;
    }

    public SoccerPlayer getPlayerRight(){
        return PLAYER_RIGHT;
    }

    public float getGreenBarThreshold(){
        return GREEN_BAR_THRESHOLD;
    }


}
