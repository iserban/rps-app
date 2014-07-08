package com.iserban.app.model.players;

import com.iserban.app.model.game.type.Game;

/**
 * Class for RPS human player.
 */
public class RPSHumanPlayer extends Player {

    public RPSHumanPlayer(Game game, String name) {
        super(game, name);
    }

    @Override
    public String getPlayerType() {
        return "Human";
    }

    /**
     * Human players will call the controller to prompt the user for a move.
     * @return player move
     */
    @Override
    public void move() {
        getGame().getController().promptForMove(this);
    }

}
