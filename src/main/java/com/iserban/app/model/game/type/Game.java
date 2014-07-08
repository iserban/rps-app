package com.iserban.app.model.game.type;

import com.iserban.app.controller.Controller;
import com.iserban.app.model.game.moves.Move;
import com.iserban.app.model.game.score.Score;
import com.iserban.app.model.game.umpire.Umpire;
import com.iserban.app.model.players.Player;

import java.util.List;
import java.util.Observable;

/**
 * Generic Game class.
 *
 * In the MVC pattern, the game class is the model. It is observable by the view.
 */
public abstract class Game extends Observable {
    protected String name;
    protected List<Move> possibleMoves;
    protected Score score;
    protected List<Player> playerList;
    protected Umpire umpire;
    protected Controller controller;

    public String getName() {
        return name;
    }

    public List<Move> getPossibleMoves() {
        return possibleMoves;
    }

    public Score getScore() {
        return score;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void addPlayer(Player player) {
        playerList.add(player);
        score.setScoreForPlayer(player, 0);
    }

    /**
     * Returns player with playerIndex (1-based indexing).
     */
    public Player getPlayer(int playerIndex) {
        return playerList.get(playerIndex - 1);
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public abstract void playRound();

    public abstract void resetGame();
}
