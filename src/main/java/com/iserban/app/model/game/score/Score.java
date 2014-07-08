package com.iserban.app.model.game.score;


import com.iserban.app.model.players.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Holds the score of the game for each player.
 */
public class Score {
    private Map<Player, Integer> scoreMap = new HashMap<Player, Integer>();

    /**
     * Returns the current score for the specified player.
     */
    public int getScoreForPlayer(Player player) {
        if (scoreMap.isEmpty()) {
            throw new IllegalStateException("Trying to get score from empty score map.");
        }
        if (player == null) {
            throw new IllegalArgumentException("Player can not be null.");
        }
        if (!scoreMap.containsKey(player)) {
            throw new IllegalStateException("Player not found in score map.");
        }
        return scoreMap.get(player);
    }

    public void setScoreForPlayer(Player player, int score) {
        scoreMap.put(player, score);
    }

}
