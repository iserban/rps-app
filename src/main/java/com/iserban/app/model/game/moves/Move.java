package com.iserban.app.model.game.moves;

/**
 * Generic move class.
 */
public interface Move {

    /**
     * Returns move name.
     */
    public String getName();

    /**
     * Returns true if the current move beats the opponent's move.
     */
    public boolean beats(Move opponentMove);

    /**
     * Returns true if the current move ties the opponent's move.
     */
    public boolean ties(Move opponentMove);
}
