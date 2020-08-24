package com.idankorenisraeli.soccerbattle;

public class GameManager {

    private static GameManager single_instance = null;

    private int turnsPlayed;

    private interface IMAGE_KEYS{
        String PLAYER_LEFT_IMAGE_URI = "@drawable/icons8_messi";
        String PLAYER_RIGHT_IMAGE_URI= "@drawable/icons8_ronaldo";
        String PASS_IMAGE_URI = "@drawable/flaticon_kickoff";
        String SHOT_IMAGE_URI = "@drawable/flaticon_shot";
        String GOAL_IMAGE_URI = "@drawable/flaticon_goal";
    }

    // region Default Players

    private static final SoccerPlayer PLAYER_LEFT = new SoccerPlayer("Messi", IMAGE_KEYS.PLAYER_LEFT_IMAGE_URI);
    private static final SoccerPlayer PLAYER_RIGHT = new SoccerPlayer("Ronaldo", IMAGE_KEYS.PLAYER_RIGHT_IMAGE_URI);
    // endregion

    // region Default Attacks

    private static final SoccerAttack ATTACK_PASS = new SoccerAttack("Pass", 5, IMAGE_KEYS.PASS_IMAGE_URI);
    private static final SoccerAttack ATTACK_SHOT = new SoccerAttack("Shot", 10, IMAGE_KEYS.SHOT_IMAGE_URI);
    private static final SoccerAttack ATTACK_GOAL = new SoccerAttack("Goal", 20, IMAGE_KEYS.GOAL_IMAGE_URI);
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
