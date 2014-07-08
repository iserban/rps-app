package com.iserban.app.model.game.outcome;

import com.iserban.app.model.game.moves.Move;
import com.iserban.app.model.players.Player;

/**
 * Outcome of a game round. Contains info on the winning player, winning move and whether the round resulted in a tie.
 * NOTE: Assumes only one player can win a round. In the event of a tie there is no winning player.
 */
public class Outcome {
    boolean tie;
    Player winningPlayer;
    Move winningMove;

    public boolean isTie() {
        return tie;
    }

    public void setTie(boolean tie) {
        this.tie = tie;
    }

    public Player getWinningPlayer() {
        return winningPlayer;
    }

    public void setWinningPlayer(Player winningPlayer) {
        this.winningPlayer = winningPlayer;
    }

    public Move getWinningMove() {
        return winningMove;
    }

    public void setWinningMove(Move winningMove) {
        this.winningMove = winningMove;
    }
}
