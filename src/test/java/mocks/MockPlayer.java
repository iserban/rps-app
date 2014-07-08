package mocks;

import com.iserban.app.model.game.moves.Move;
import com.iserban.app.model.game.type.Game;
import com.iserban.app.model.players.Player;

/**
 * Mock player class.
 */
public class MockPlayer extends Player {
    private Move futureMove;

    public MockPlayer(Game game, String name) {
        super(game, name);
    }

    @Override
    public String getPlayerType() {
        return "Mock";
    }

    @Override
    public void move() {
        // set current move to the future move prepared
        setCurrentMove(futureMove);
    }

    /**
     * Method that sets the future move of the Mock player.
     * Allows controlling game play from setup stage in tests.
     */
    public void setFutureMove(Move futureMove) {
        this.futureMove = futureMove;
    }
}
