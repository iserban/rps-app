package com.iserban.app.model.players;

import com.iserban.app.model.game.moves.Move;
import com.iserban.app.model.game.type.Game;

/**
 * Player class.
 */
public abstract class Player {
    private Game game;
    private String name;
    private Move currentMove;

    protected Player(Game game, String name) {
        this.name = name;
        this.game = game;
    }

    public String getName() {
        return name;
    }

    public abstract String getPlayerType();

    public Game getGame() {
        return game;
    }

    public Move getCurrentMove() {
        return currentMove;
    }

    public void setCurrentMove(Move currentMove) {
        this.currentMove = currentMove;
    }

    public abstract void move();
}
