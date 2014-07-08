package com.iserban.app.runner;

import com.iserban.app.controller.Controller;
import com.iserban.app.controller.RPSController;
import com.iserban.app.model.game.type.Game;
import com.iserban.app.model.game.type.RPSGame;
import com.iserban.app.view.GameView;
import com.iserban.app.view.RPSGameView;

public class Main {

    public static void main(String[] args) {
        // init model
        Game game = new RPSGame();

        // init view
        GameView gameView = new RPSGameView();

        // init controller
        Controller controller = new RPSController(game, gameView);

        // add view as observer for model
        game.addObserver(gameView);

        // add controller to the model
        game.setController(controller);
    }
}
