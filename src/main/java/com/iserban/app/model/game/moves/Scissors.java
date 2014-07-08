package com.iserban.app.model.game.moves;

/**
 * Scissors move class. Singleton, since there can only be one instance of the move.
 */
public class Scissors extends RPSMove {
    private static final Scissors instance = new Scissors();

    private Scissors() {
        moveName = "Scissors";
    }

    public static Scissors getInstance() {
        return instance;
    }

    @Override
    public boolean beats(Move opponentMove) {
        if (opponentMove instanceof Paper) {
            return true;
        }
        return false;
    }
}
