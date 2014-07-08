package mocks;

import com.iserban.app.controller.Controller;
import com.iserban.app.model.players.Player;

/**
 * Mock controller class used to test game class.
 */
public class MockController implements Controller {
    @Override
    public String[] getGameModes() {
        return new String[0];
    }

    @Override
    public String[] getGameMoves() {
        return new String[0];
    }

    @Override
    public String[] getPlayerNames() {
        return new String[0];
    }

    @Override
    public String[] getPlayerTypes() {
        return new String[0];
    }

    @Override
    public void selectGameMode(int index) {

    }

    @Override
    public void playNextRound() {

    }

    @Override
    public void selectMove(int index) {

    }

    @Override
    public void startNewGame() {

    }

    @Override
    public void promptForMove(Player player) {

    }
}
