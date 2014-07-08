package com.iserban.app.model.game.score;

import com.iserban.app.model.players.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import utils.TestUtils;

public class ScoreTest {
    private Player player;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        player = TestUtils.getMockPlayer();
    }

    /**
     * Tests get score method for valid score map.
     */
    @Test
    public void testGetScore() {
        Score score = new Score();
        Player player1 = TestUtils.getMockPlayer();
        Player player2 = TestUtils.getMockPlayer();
        Player player3 = TestUtils.getMockPlayer();
        score.setScoreForPlayer(player1, 1);
        score.setScoreForPlayer(player2, 2);
        score.setScoreForPlayer(player3, 3);

        // retrieve scores in different order
        Assert.assertEquals(2, score.getScoreForPlayer(player2));
        Assert.assertEquals(1, score.getScoreForPlayer(player1));
        Assert.assertEquals(3, score.getScoreForPlayer(player3));
    }

    /**
     * Tests get score method for empty score map.
     */
    @Test
    public void testGetScoreEmptyScoreMap() {
        Score score = new Score();
        exception.expect(IllegalStateException.class);
        score.getScoreForPlayer(player);
    }

    /**
     * Tests get score method when player is not in the score map.
     */
    @Test
    public void testGetScorePlayerNotInScoreMap() {
        Score score = new Score();
        Player playerInMap = TestUtils.getMockPlayer();
        Player playerNotInMap = TestUtils.getMockPlayer();
        score.setScoreForPlayer(playerInMap, 100);

        exception.expect(IllegalStateException.class);
        score.getScoreForPlayer(playerNotInMap);
    }

    /**
     * Tests get score method for null player.
     */
    @Test
    public void testGetScoreNullPlayer() {
        Score score = new Score();
        score.setScoreForPlayer(TestUtils.getMockPlayer(), 0);

        exception.expect(IllegalArgumentException.class);
        score.getScoreForPlayer(null);
    }
}
