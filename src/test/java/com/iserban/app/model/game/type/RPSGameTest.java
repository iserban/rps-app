package com.iserban.app.model.game.type;

import com.iserban.app.model.game.moves.Move;
import com.iserban.app.model.game.moves.Paper;
import com.iserban.app.model.game.moves.Rock;
import com.iserban.app.model.game.moves.Scissors;
import com.iserban.app.model.players.Player;
import org.junit.Assert;
import org.junit.Test;
import utils.TestUtils;

import java.util.List;

public class RPSGameTest {

    /**
     * Tests getting game name.
     */
    @Test
    public void testGetName() {
        Game game = new RPSGame();
        Assert.assertEquals("Rock, Paper, Scissors", game.getName());
    }

    /**
     * Tests getting game possible moves.
     */
    @Test
    public void testGetPossibleMoves() {
        Game game = new RPSGame();
        List<Move> possibleMoves = game.getPossibleMoves();

        // assert the 3 accepted moves are present
        Assert.assertTrue(possibleMoves.contains(Rock.getInstance()));
        Assert.assertTrue(possibleMoves.contains(Paper.getInstance()));
        Assert.assertTrue(possibleMoves.contains(Scissors.getInstance()));

        // assert that there are no other possible moves
        Assert.assertEquals(3, possibleMoves.size());
    }

    /**
     * Tests adding players to the game.
     */
    @Test
    public void testAddPlayer() {
        Game game = new RPSGame();

        for (int i=0; i<3; i++) {
            Player player = TestUtils.getMockPlayer();
            // add player to list
            game.addPlayer(player);

            // assert player in player list
            Assert.assertTrue(game.getPlayerList().contains(player));
            // assert player list size is correct (player is not added twice)
            Assert.assertEquals(i+1, game.getPlayerList().size());
        }
    }

    /**
     * Tests getting player by player index.
     */
    @Test
    public void testGetPlayer() {
        Game game = new RPSGame();
        Player player1 = TestUtils.getMockPlayer();
        Player player2 = TestUtils.getMockPlayer();
        Player player3 = TestUtils.getMockPlayer();

        // add 3 players
        game.addPlayer(player1);
        game.addPlayer(player2);
        game.addPlayer(player3);

        // retrieve player by player index
        Assert.assertEquals(player1, game.getPlayer(1));
        Assert.assertEquals(player2, game.getPlayer(2));
        Assert.assertEquals(player3, game.getPlayer(3));
    }

    /**
     * Tests playing rounds won by player 1.
     */
    @Test
    public void testPlayRoundPlayer1Wins() {
        Game game = TestUtils.getRPSGameMockPlayersMockController(2);

        // set the future move for the mock players
        TestUtils.setFutureMovesForMockPlayers(game, Paper.getInstance(), Rock.getInstance());

        game.playRound();

        // verify the score is 1:0
        TestUtils.assertScore(game, 1, 0);

        // play round again
        game.playRound();

        // verify the score was incremented (2:0)
        TestUtils.assertScore(game, 2, 0);
    }

    /**
     * Tests playing rounds won by player 2.
     */
    @Test
    public void testPlayRoundPlayer2Wins() {
        Game game = TestUtils.getRPSGameMockPlayersMockController(2);

        // set the future move for the mock players
        TestUtils.setFutureMovesForMockPlayers(game, Paper.getInstance(), Scissors.getInstance());

        // play round
        game.playRound();

        // verify the score is 0:1
        TestUtils.assertScore(game, 0, 1);

        // play round again
        game.playRound();

        // verify the score was incremented (0:2)
        TestUtils.assertScore(game, 0, 2);
    }

    /**
     * Tests playing a round that is a tie.
     */
    @Test
    public void testPlayRoundTie() {
        Game game = TestUtils.getRPSGameMockPlayersMockController(2);

        // set the future move for the mock players
        TestUtils.setFutureMovesForMockPlayers(game, Paper.getInstance(), Paper.getInstance());

        game.playRound();

        // verify the score is 0:0
        TestUtils.assertScore(game, 0, 0);
    }

    /**
     * Tests resetting the game.
     */
    @Test
    public void testResetGame() {
        Game game = TestUtils.getRPSGameMockPlayersMockController(2);

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
