package com.iserban.app.model.game.moves;

/**
 * Generic Rock, Paper, Scissors move class.
 */
public abstract class RPSMove implements Move {
    protected String moveName;

    @Override
    public String getName() {
        return moveName;
    }

    @Override
    public abstract boolean beats(Move opponentMove);

    @Override
    public boolean ties(Move opponentMove) {
        return this.getClass().equals(opponentMove.getClass());
    }
}
