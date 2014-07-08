package com.iserban.app.model.players;


import com.iserban.app.model.game.moves.Move;
import com.iserban.app.model.game.type.Game;

import java.util.List;
import java.util.Random;

/**
 * Random computer player class: player will select the next move at random and will not use the move the opponent
 * makes in any way.
 */
public class RPSRandomComputerPlayer extends Player {

    public RPSRandomComputerPlayer(Game game, String name) {
        super(game, name);
    }

    public String getPlayerType() {
        return "Computer";
    }

    /**
     * Computer player will just select a random move out of the possible move list.
     */
    @Override
    public void move() {
        // select random move
        Random random = new Random();
        List<Move> possibleMoves = getGame().getPossibleMoves();
        int moveIndex = random.nextInt(possibleMoves.size());
        Move move = possibleMoves.get(moveIndex);
        setCurrentMove(move);
    }

}
