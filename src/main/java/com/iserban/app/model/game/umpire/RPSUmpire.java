package com.iserban.app.model.game.umpire;


import com.iserban.app.model.game.moves.Move;
import com.iserban.app.model.game.outcome.Outcome;
import com.iserban.app.model.players.Player;

import java.util.Map;
import java.util.Set;

/**
 * The umpire for the classic 2 player Rock, Paper, Scissors game.
 */
public class RPSUmpire implements Umpire {

    /**
     * Determines the outcome of a round.
     * @param moveMap map containing each player's move in a given round
     * @return outcome of round
     */
    @Override
    public Outcome determineOutcome(Map<Player, Move> moveMap) {
        if (moveMap == null) {
            throw new IllegalArgumentException("Move map can not be null.");
        }
        Set<Player> players = moveMap.keySet();
        if (players.size() != 2) {
            throw new IllegalArgumentException("Move map must contain 2 entries.");
        }
        Player player1 = (Player) players.toArray()[0];
        Player player2 = (Player) players.toArray()[1];
        Move move1 = moveMap.get(player1);
        Move move2 = moveMap.get(player2);
        Outcome outcome = new Outcome();
        if (move1.beats(move2)) {
            outcome.setWinningMove(move1);
            outcome.setWinningPlayer(player1);
        } else if (move1.ties(move2)) {
            outcome.setTie(true);
            outcome.setWinningMove(move1);
        } else {
            outcome.setWinningMove(move2);
            outcome.setWinningPlayer(player2);
        }
        return outcome;
    }
}
