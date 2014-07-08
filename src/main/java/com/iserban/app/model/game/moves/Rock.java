package com.iserban.app.model.game.moves;


/**
 * Rock move class. Singleton, since there can only be one instance of the move.
 */
public class Rock extends RPSMove {
    private static final Rock instance = new Rock();

    private Rock() {
        moveName = "Rock";
    }

    public static Rock getInstance() {
        return instance;
    }

    @Override
    public boolean beats(Move opponentMove) {
        if (opponentMove instanceof Scissors) {
            return true;
        }
        return false;
    }
}
