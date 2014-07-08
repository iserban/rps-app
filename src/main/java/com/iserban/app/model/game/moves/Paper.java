package com.iserban.app.model.game.moves;

/**
 * Paper move class. Singleton, since there can only be one instance of the move.
 */
public class Paper extends RPSMove {
    private static final Paper instance = new Paper();

    private Paper() {
        moveName = "Paper";
    }

    public static Paper getInstance() {
        return instance;
    }

    @Override
    public boolean beats(Move opponentMove) {
        if (opponentMove instanceof Rock) {
            return true;
        }
        return false;
    }
}
