package com.iserban.app.view;

import com.iserban.app.controller.Controller;

import java.util.Observer;

/**
 * Interface for game view.
 */
public interface GameView extends Observer {
    
    Controller getController();

    void setController(Controller controller);

    void startView(String gameName);

    void displayGamePanel();

    void displaySelectMoveDialog();
}
