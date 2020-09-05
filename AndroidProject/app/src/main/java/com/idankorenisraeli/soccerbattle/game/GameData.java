package com.idankorenisraeli.soccerbattle.game;

import com.idankorenisraeli.soccerbattle.R;
import com.idankorenisraeli.soccerbattle.attack.SoccerAttack;
import com.idankorenisraeli.soccerbattle.player.SoccerPlayer;

// This class contains all game information which is loaded on runtime
public class GameData {


    private static GameData single_instance = null;

    public static GameData getInstance(){
        if(single_instance == null)
            single_instance = new GameData();
        return single_instance;
    }


    public interface SOUND_KEYS {
        int KICK_PASS = R.raw.kick_pass;
        int KICK_SHOT = R.raw.kick_shot;
        int KICK_GOAL = R.raw.kick_goal;
        int CROWD_GOAL = R.raw.crowd_goal;
        int CROWD_FINISH = R.raw.crowd_finish;
        int CROWD_AMBIENT = R.raw.crowd_ambient;
    }

    public interface DRAWABLE_KEYS{
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

    private static final SoccerAttack ATTACK_PASS = new SoccerAttack("Pass", 5, DRAWABLE_KEYS.PASS_DRAWABLE_ID, SOUND_KEYS.KICK_PASS);
    private static final SoccerAttack ATTACK_SHOT = new SoccerAttack("Shot", 10, DRAWABLE_KEYS.SHOT_DRAWABLE_ID, SOUND_KEYS.KICK_SHOT);
    private static final SoccerAttack ATTACK_GOAL = new SoccerAttack("Goal", 20, DRAWABLE_KEYS.GOAL_DRAWABLE_ID, SOUND_KEYS.KICK_GOAL);

    private static final SoccerAttack[] DEFAULT_ATTACKS = new SoccerAttack[]{ATTACK_PASS, ATTACK_SHOT, ATTACK_GOAL};

    // endregion

    // region Default Players

    private static SoccerPlayer PLAYER_LEFT = new SoccerPlayer("Messi", DRAWABLE_KEYS.PLAYER_LEFT_DRAWABLE_ID, DEFAULT_ATTACKS);
    private static SoccerPlayer PLAYER_RIGHT = new SoccerPlayer("Ronaldo", DRAWABLE_KEYS.PLAYER_RIGHT_DRAWABLE_ID, DEFAULT_ATTACKS);
    // endregion

    // region Progress Bar
    public enum ProgressBarColor{ RED, GREEN}
    public static final float GREEN_BAR_THRESHOLD = 0.7f;

    // endregion

    public void reset(){
        //Re-creating players scripts
        PLAYER_LEFT = new SoccerPlayer("Messi", DRAWABLE_KEYS.PLAYER_LEFT_DRAWABLE_ID, DEFAULT_ATTACKS);
        PLAYER_RIGHT = new SoccerPlayer("Ronaldo", DRAWABLE_KEYS.PLAYER_RIGHT_DRAWABLE_ID, DEFAULT_ATTACKS);
    }


    public SoccerPlayer getPlayerLeft() {
        return PLAYER_LEFT;
    }

    public SoccerPlayer getPlayerRight(){
        return PLAYER_RIGHT;
    }


}
