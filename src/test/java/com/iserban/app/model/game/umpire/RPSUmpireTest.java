package com.iserban.app.model.game.umpire;

import com.iserban.app.model.game.moves.Move;
import com.iserban.app.model.game.moves.Paper;
import com.iserban.app.model.game.moves.Rock;
import com.iserban.app.model.game.moves.Scissors;
import com.iserban.app.model.game.outcome.Outcome;
import com.iserban.app.model.players.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import utils.TestUtils;

import java.util.HashMap;
import java.util.Map;

public class RPSUmpireTest {
    Player player1;
    Player player2;
    Player player3;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        player1 = TestUtils.getMockPlayer();
        player2 = TestUtils.getMockPlayer();
        player3 = TestUtils.getMockPlayer();
    }

    /**
     * Tests method when moveMap is null.
     */
    @Test
    public void testDetermineOutcomeNullMoveMap() {
        Umpire umpire = new RPSUmpire();

        // should throw exception
        exception.expect(IllegalArgumentException.class);
        umpire.determineOutcome(null);
    }

    /**
     * Tests method when moveMap is empty.
     */
    @Test
    public void testDetermineOutcomeEmptyMoveMap() {
        Umpire umpire = new RPSUmpire();

        Map<Player, Move> moveMap = new HashMap<Player, Move>();

        // should throw exception
        exception.expect(IllegalArgumentException.class);
        umpire.determineOutcome(moveMap);
    }

    /**
     * Tests method when moveMap size smaller than 2.
     */
    @Test
    public void testDetermineOutcomeTooFewPlayers() {
        Umpire umpire = new RPSUmpire();

        Map<Player, Move> moveMap = new HashMap<Player, Move>();
        moveMap.put(player1, Rock.getInstance());

        // should throw exception
        exception.expect(IllegalArgumentException.class);
        umpire.determineOutcome(moveMap);
    }

    /**
     * Tests method when moveMap size larger than 2.
     */
    @Test
    public void testDetermineOutcomeTooManyPlayers() {
        Umpire umpire = new RPSUmpire();

        Map<Player, Move> moveMap = new HashMap<Player, Move>();
        moveMap.put(player1, Rock.getInstance());
        moveMap.put(player2, Rock.getInstance());
        moveMap.put(player3, Rock.getInstance());

        // should throw exception
        exception.expect(IllegalArgumentException.class);
        umpire.determineOutcome(moveMap);
    }

    /**
     * Tests method when player 1 wins round.
     */
    @Test
    public void testDetermineOutcomePlayer1Wins() {
        Umpire umpire = new RPSUmpire();

        Map<Player, Move> moveMap = new HashMap<Player, Move>();
        Move movePlayer1 = Paper.getInstance();
        Move movePlayer2 = Rock.getInstance();
        moveMap.put(player1, movePlayer1);
        moveMap.put(player2, movePlayer2);

        Outcome outcome = umpire.determineOutcome(moveMap);
        Assert.assertFalse(outcome.isTie());
        Assert.assertEquals(outcome.getWinningPlayer(), player1);
        Assert.assertEquals(outcome.getWinningMove(), movePlayer1);
    }

    /**
     * Tests method when player 2 wins round.
     */
    @Test
    public void testDetermineOutcomePlayer2Wins() {
        Umpire umpire = new RPSUmpire();

        Map<Player, Move> moveMap = new HashMap<Player, Move>();
        Move movePlayer1 = Paper.getInstance();
        Move movePlayer2 = Scissors.getInstance();
        moveMap.put(player1, movePlayer1);
        moveMap.put(player2, movePlayer2);

        Outcome outcome = umpire.determineOutcome(moveMap);
        Assert.assertFalse(outcome.isTie());
        Assert.assertEquals(outcome.getWinningPlayer(), player2);
        Assert.assertEquals(outcome.getWinningMove(), movePlayer2);
    }

    /**
     * Tests method when round end in a tie.
     */
    @Test
    public void testDetermineOutcomeTie() {
        Umpire umpire = new RPSUmpire();

        Map<Player, Move> moveMap = new HashMap<Player, Move>();
        Move sameMove = Rock.getInstance();
        moveMap.put(player1, sameMove);
        moveMap.put(player2, sameMove);

        Outcome outcome = umpire.determineOutcome(moveMap);
        Assert.assertTrue(outcome.isTie());
        Assert.assertNull(outcome.getWinningPlayer());
        Assert.assertEquals(outcome.getWinningMove(), sameMove);
    }

}
