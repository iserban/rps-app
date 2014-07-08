package com.iserban.app.model.players;

import com.iserban.app.controller.RPSController;
import org.junit.Assert;
import org.junit.Test;
import mocks.MockGame;
import utils.TestUtils;

public class RPSHumanPlayerTest {

    @Test
    public void testGetPlayerType() {
        Player player = new RPSHumanPlayer(new MockGame(), "Player 1");
        Assert.assertEquals("Human", player.getPlayerType());
    }

    @Test
    public void testMove() {
        RPSController controller = TestUtils.getRPSControllerMockView();
        Player player = new RPSHumanPlayer(controller.getGame(), "Player 1");
        controller.getGame().addPlayer(player);

        player.move();

        // human player does not set the current move when moving
        Assert.assertNull(player.getCurrentMove());
    }
}
