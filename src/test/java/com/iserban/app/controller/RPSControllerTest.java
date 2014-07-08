package com.iserban.app.controller;

import mocks.MockPlayer;
import mocks.MockView;
import com.iserban.app.model.game.moves.Paper;
import com.iserban.app.model.game.moves.Rock;
import com.iserban.app.model.game.moves.Scissors;
import com.iserban.app.model.game.type.Game;
import com.iserban.app.model.game.type.RPSGame;
import com.iserban.app.model.players.Player;
import com.iserban.app.model.players.RPSHumanPlayer;
import com.iserban.app.model.players.RPSRandomComputerPlayer;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import utils.TestUtils;

import java.util.List;

public class RPSControllerTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    /**
     * Tests getting game modes.
     */
    @Test
    public void testGetGameModes() {
        Controller controller = TestUtils.getRPSControllerMockView();
        String[] gameModes = controller.getGameModes();

        Assert.assertEquals(2, gameModes.length);
        Assert.assertEquals("Human vs. Computer", gameModes[0]);
        Assert.assertEquals("Computer vs. Computer", gameModes[1]);
    }

    /**
     * Tests getting game moves.
     */
    @Test
    public void testGetGameMoves() {
        Controller controller = TestUtils.getRPSControllerMockView();
        String[] gameMoves = controller.getGameMoves();

        Assert.assertEquals(3, gameMoves.length);
        Assert.assertEquals(Rock.getInstance().getName(), gameMoves[0]);
        Assert.assertEquals(Paper.getInstance().getName(), gameMoves[1]);
        Assert.assertEquals(Scissors.getInstance().getName(), gameMoves[2]);
    }

    /**
     * Tests getting player names when game has no players.
     */
    @Test
    public void testGetPlayerNamesNoPlayers() {
        Controller controller = TestUtils.getRPSControllerMockView();

        String[] playerNames = controller.getPlayerNames();
        Assert.assertEquals(0, playerNames.length);
    }

    /**
     * Tests getting player names.
     */
    @Test
    public void testGetPlayerNames() {
        RPSGame game = TestUtils.getRPSGameMockPlayers(3);
        Controller controller = new RPSController(game, new MockView());
        game.setController(controller);

        String[] playerNames = controller.getPlayerNames();
        List<Player> playerList = game.getPlayerList();
        Assert.assertEquals(playerList.size(), playerNames.length);
        for (int i=0; i<playerList.size(); i++) {
            Assert.assertEquals(playerList.get(i).getName(), playerNames[i]);
        }
    }

    /**
     * Tests getting player types when game has no players.
     */
    @Test
    public void testGetPlayerTypesNoPlayers() {
        Controller controller = TestUtils.getRPSControllerMockView();

        String[] playerTypes = controller.getPlayerTypes();
        Assert.assertEquals(0, playerTypes.length);
    }

    /**
     * Tests getting player types.
     */
    @Test
    public void testGetPlayerTypes() {
        RPSGame game = new RPSGame();
        // add players of different types to game
        game.addPlayer(new RPSRandomComputerPlayer(game, "Player 1"));
        game.addPlayer(new MockPlayer(game, "Player 2"));
        game.addPlayer(new RPSRandomComputerPlayer(game, "Player 3"));
        Controller controller = new RPSController(game, new MockView());
        game.setController(controller);

        // check player types
        String[] playerTypes = controller.getPlayerTypes();
        List<Player> playerList = game.getPlayerList();
        Assert.assertEquals(playerList.size(), playerTypes.length);
        for (int i=0; i<playerList.size(); i++) {
            Assert.assertEquals(playerList.get(i).getPlayerType(), playerTypes[i]);
        }
    }

    /**
     * Tests selecting Human vs. Computer game mode.
     */
    @Test
    public void testSelectGameModeHumanVsComputer() {
        RPSController controller = TestUtils.getRPSControllerMockView();

        // select game mode 0
        controller.selectGameMode(0);
        Player player1 = controller.getGame().getPlayer(1);
        Player player2 = controller.getGame().getPlayer(2);
        Assert.assertEquals(RPSHumanPlayer.class, player1.getClass());
        Assert.assertEquals(RPSRandomComputerPlayer.class, player2.getClass());
    }

    /**
     * Tests selecting Human vs. Computer game mode.
     */
    @Test
    public void testSelectGameModeComputerVsComputer() {
        RPSController controller = TestUtils.getRPSControllerMockView();

        // select game mode 1
        controller.selectGameMode(1);
        Player player1 = controller.getGame().getPlayer(1);
        Player player2 = controller.getGame().getPlayer(2);
        Assert.assertEquals(RPSRandomComputerPlayer.class, player1.getClass());
        Assert.assertEquals(RPSRandomComputerPlayer.class, player2.getClass());
    }

    /**
     * Tests selecting invalid game mode.
     */
    @Test
    public void testSelectGameModeInvalidMode() {
        Controller controller = TestUtils.getRPSControllerMockView();

        // select game mode != 0,1
        exception.expect(IllegalArgumentException.class);
        controller.selectGameMode(2);
    }

    /**
     * Tests playing a round.
     */
    @Test
    public void testPlayRound() {
        Game game = TestUtils.getRPSGameMockPlayers(2);
        Controller controller = new RPSController(game, new MockView());
        game.setController(controller);

        // set the future move for the mock players so that player 1 wins
        TestUtils.setFutureMovesForMockPlayers(game, Rock.getInstance(), Scissors.getInstance());

        controller.playNextRound();

        // verify the score is 1:0
        TestUtils.assertScore(game, 1, 0);
    }

    /**
     * Test prompting for move for null player.
     */
    @Test
    public void testPromptForMovePlayerNull() {
        RPSController controller = TestUtils.getRPSControllerMockView();

        exception.expect(IllegalArgumentException.class);
        controller.promptForMove(null);
    }

    /**
     * Test prompting for move for a player.
     */
    @Test
    public void testPromptForMove() {
        RPSController controller = TestUtils.getRPSControllerMockView();
        Player mockPlayer = TestUtils.getMockPlayer();
        controller.getGame().addPlayer(mockPlayer);

        controller.promptForMove(mockPlayer);
        Assert.assertEquals(mockPlayer, controller.getCurrentPlayer());
    }

    @Test
    public void testResetGame() {
        Game game = TestUtils.getRPSGameMockPlayers(2);
        Controller controller = new RPSController(game, new MockView());
        game.setController(controller);

        // set the future move for the mock players
        TestUtils.setFutureMovesForMockPlayers(game, Paper.getInstance(), Scissors.getInstance());

        // play a few rounds
        for (int i=0; i<3; i++) {
            game.playRound();
        }

        // reset game
        game.resetGame();
        Assert.assertEquals(0, game.getPlayerList().size());
    }
}
