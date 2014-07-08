package mocks;

import com.iserban.app.controller.Controller;
import com.iserban.app.view.GameView;

import java.util.Observable;

/**
 * Mock view class used in tests.
 */
public class MockView implements GameView {
    @Override
    public Controller getController() {
        return null;
    }

    @Override
    public void setController(Controller controller) {

    }

    @Override
    public void startView(String gameName) {

    }

    @Override
    public void displayGamePanel() {

    }

    @Override
    public void displaySelectMoveDialog() {

    }

    @Override
    public void update(Observable observable, Object o) {

    }
}
