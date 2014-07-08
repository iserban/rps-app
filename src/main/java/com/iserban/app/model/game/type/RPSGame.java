package com.iserban.app.model.game.type;


import com.iserban.app.model.game.moves.Move;
import com.iserban.app.model.game.moves.Paper;
import com.iserban.app.model.game.moves.Rock;
import com.iserban.app.model.game.moves.Scissors;
import com.iserban.app.model.game.outcome.Outcome;
import com.iserban.app.model.game.score.Score;
import com.iserban.app.model.game.umpire.RPSUmpire;
import com.iserban.app.model.players.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Game class for the classic 2 player Rock, Paper, Scissors game.
 */
public class RPSGame extends Game {

    public RPSGame() {
        // set name
        name = "Rock, Paper, Scissors";

        // add possible moves for the game
        possibleMoves = new ArrayList<Move>();
        possibleMoves.add(Rock.getInstance());
        possibleMoves.add(Paper.getInstance());
        possibleMoves.add(Scissors.getInstance());

        // set an umpire
        umpire = new RPSUmpire();

        // set game to default state
        resetGame();
    }

    @Override
    public void playRound() {
        Player player1 = getPlayer(1);
        Player player2 = getPlayer(2);

        // reset player current moves
        player1.setCurrentMove(null);
        player2.setCurrentMove(null);

        // players move
        player1.move();
        player2.move();

        // after players are done moving retrieve the current move
        Move movePlayer1 = player1.getCurrentMove();
        Move movePlayer2 = player2.getCurrentMove();

        // skip round if either of the players did not move
        // e.g. when the human user cancels the move
        if (movePlayer1 == null || movePlayer2 == null) {
            return;
        }

        // umpire decides outcome of the game
        Map<Player, Move> moveMap = new HashMap<Player, Move>();
        moveMap.put(player1, movePlayer1);
        moveMap.put(player2, movePlayer2);
        Outcome outcome = umpire.determineOutcome(moveMap);

        // update score according to outcome (if round was a tie do not update score)
        if (!outcome.isTie()) {
            Player winner = outcome.getWinningPlayer();
            int newScoreWinner = score.getScoreForPlayer(winner) + 1;
            score.setScoreForPlayer(winner, newScoreWinner);
        }

        // notify observers that the round has been completed
        setChanged();
        notifyObservers(outcome);
    }

    @Override
    public void resetGame() {
        // reset score
        score = new Score();

        // instantiate player list
        playerList = new ArrayList<Player>();
    }
}
