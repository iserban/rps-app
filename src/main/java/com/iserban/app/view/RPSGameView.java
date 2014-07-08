package com.iserban.app.view;


import com.iserban.app.controller.Controller;
import com.iserban.app.model.game.outcome.Outcome;
import com.iserban.app.model.game.type.RPSGame;
import com.iserban.app.model.players.Player;
import com.iserban.app.view.components.MainFrame;
import com.iserban.app.view.components.SelectMoveDialog;
import com.iserban.app.view.components.StartDialog;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;

/**
 * Concrete view for Game.
 */
public class RPSGameView implements GameView {
    private Controller controller;
    MainFrame mainGameFrame;

    @Override
    public Controller getController() {
        return controller;
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void startView(String gameName) {
        // initialize main frame
        mainGameFrame = new MainFrame(gameName, this);
        mainGameFrame.setLocationByPlatform(true);
        mainGameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainGameFrame.setMinimumSize(new Dimension(400, 250));
        mainGameFrame.setResizable(false);
        mainGameFrame.setVisible(true);

        // open dialogue for selecting game mode
        JDialog selectGameModeDialog = new StartDialog(this);
        selectGameModeDialog.pack();
        selectGameModeDialog.setLocationRelativeTo(mainGameFrame);
        selectGameModeDialog.setVisible(true);
    }

    @Override
    public void displayGamePanel() {
        mainGameFrame.addComponentsToPane(mainGameFrame.getContentPane());
    }

    @Override
    public void displaySelectMoveDialog() {
        JDialog selectMoveDialog = new SelectMoveDialog(this);
        selectMoveDialog.pack();
        selectMoveDialog.setLocationRelativeTo(mainGameFrame);
        selectMoveDialog.setVisible(true);
    }

    @Override
    public void update(Observable observable, Object o) {
        RPSGame game = (RPSGame) observable;
        Player player1 = game.getPlayerList().get(0);
        Player player2 = game.getPlayerList().get(1);
        String movePlayer1 = player1.getCurrentMove().getName();
        String movePlayer2 = player2.getCurrentMove().getName();

        // update latest moves
        mainGameFrame.setLabelMovePlayer1Text(movePlayer1);
        mainGameFrame.setLabelMovePlayer2Text(movePlayer2);

        // update outcome
        Outcome outcome = (Outcome) o;
        if (outcome.isTie()) {
            mainGameFrame.setLabelRoundOutcomeText("Tie!");
        } else {
            mainGameFrame.setLabelRoundOutcomeText(outcome.getWinningPlayer().getName() + " wins!");
        }

        // update scoreboard
        String scoreText = String.format("%s:%s",
                game.getScore().getScoreForPlayer(player1), game.getScore().getScoreForPlayer(player2));
        mainGameFrame.setLabelScore(scoreText);
    }
}
