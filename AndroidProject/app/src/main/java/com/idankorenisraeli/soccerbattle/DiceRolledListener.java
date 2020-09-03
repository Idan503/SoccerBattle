package com.idankorenisraeli.soccerbattle;

public interface DiceRolledListener {
    void onDiceRolled(int result, PlayerSide side);
    // Listener for when dice finished rolling, getting (random) dice result
    // result number is from 1 to 6
}
