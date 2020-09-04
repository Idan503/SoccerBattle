package com.idankorenisraeli.soccerbattle.top_ten;

import com.idankorenisraeli.soccerbattle.game.GameResult;
import java.util.Comparator;

// Sort for the table
public class SortByTurns implements Comparator<GameResult> {
    public int compare(GameResult a, GameResult b){
        return a.getTurns() - b.getTurns();
    }
}
