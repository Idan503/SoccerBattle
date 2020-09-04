package com.idankorenisraeli.soccerbattle.game;

import com.idankorenisraeli.soccerbattle.player.PlayerSide;

public interface GameFinishedListener {
    void onGameFinished(int turns, PlayerSide winner);
}
