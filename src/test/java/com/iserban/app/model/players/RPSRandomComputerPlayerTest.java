package com.iserban.app.model.players;

import com.iserban.app.model.game.type.RPSGame;
import org.junit.Assert;
import org.junit.Test;
import mocks.MockGame;

public class RPSRandomComputerPlayerTest {

    @Test
    public void testGetPlayerType() {
        Player player = new RPSRandomComputerPlayer(new MockGame(), "Player 1");
        Assert.assertEquals("Computer", player.getPlayerType());
    }

    @Test
    public void testMove() {
        RPSGame game = new RPSGame();
        Player player = new RPSRandomComputerPlayer(game, "Player 1");
        player.move();

        // computer player sets the current move when moving
        Assert.assertNotNull(player.getCurrentMove());
        Assert.assertTrue(game.getPossibleMoves().contains(player.getCurrentMove()));
    }

}
