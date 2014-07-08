package com.iserban.app.model.game.umpire;


import com.iserban.app.model.game.moves.Move;
import com.iserban.app.model.game.outcome.Outcome;
import com.iserban.app.model.players.Player;

import java.util.Map;

/**
 * Umpire will determine the winning player in a game round by looking at each player's moves.
 */
public interface Umpire {

    /**
     * Determine the winning player of a round
     * @param moveMap map containing each player's move in a given round
     * @return outcome of the round
     */
    public Outcome determineOutcome(Map<Player, Move> moveMap);
}
