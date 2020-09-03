package com.idankorenisraeli.soccerbattle;

public interface GameFinishedListener {
    void onGameFinished(int turns, PlayerSide winner);
}
