package utils;

import com.iserban.app.controller.RPSController;
import com.iserban.app.model.game.moves.Move;
import com.iserban.app.model.game.type.Game;
import com.iserban.app.model.game.type.RPSGame;
import com.iserban.app.model.players.Player;
import org.junit.Assert;
import mocks.MockController;
import mocks.MockGame;
import mocks.MockPlayer;
import mocks.MockView;

public class TestUtils {

    /**
     * Helper method that returns a mock player.
     */
    public static Player getMockPlayer() {
        return new MockPlayer(new MockGame(), "Player 1");
    }

    /**
     * Helper method that returns a new game with numPlayers mock players.
     */
    public static RPSGame getRPSGameMockPlayers(int numPlayers) {
        RPSGame game = new RPSGame();
        for (int i=0; i<numPlayers; i++) {
            game.addPlayer(new MockPlayer(game, "Player " + i));
        }
        return game;
    }

    /**
     * Helper method that returns a new game linked to a mock controller and numPlayers mock players.
     */
    public static RPSGame getRPSGameMockPlayersMockController(int numPlayers) {
        RPSGame game = getRPSGameMockPlayers(numPlayers);
        game.setController(new MockController());
        return game;
    }

    /**
     * Helper method that returns a new controller linked to a real game and mock view.
     */
    public static RPSController getRPSControllerMockView() {
        RPSGame game = new RPSGame();
        RPSController controller = new RPSController(game, new MockView());
        game.setController(controller);
        return controller;
    }

    /**
     * Helper method that checks the current game score.
     */
    public static void assertScore(Game game, int expectedScorePlayer1, int expectedScorePlayer2) {
        Assert.assertEquals(expectedScorePlayer1, game.getScore().getScoreForPlayer(game.getPlayer(1)));
        Assert.assertEquals(expectedScorePlayer2, game.getScore().getScoreForPlayer(game.getPlayer(2)));
    }

    /**
     * Helper method that sets the future moves for the 2 players.
     */
    public static void setFutureMovesForMockPlayers(Game game, Move futureMovePlayer1, Move futureMovePlayer2) {
        MockPlayer player1 = (MockPlayer) game.getPlayer(1);
        MockPlayer player2 = (MockPlayer) game.getPlayer(2);
        player1.setFutureMove(futureMovePlayer1);
        player2.setFutureMove(futureMovePlayer2);
    }
}
