package com.iserban.app.view.components;


import com.iserban.app.view.GameView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class for main game frame.
 */
public class MainFrame extends JFrame {
    GameView view;
    private JLabel labelScore;
    private JLabel labelMovePlayer1;
    private JLabel labelMovePlayer2;
    private JLabel labelRoundOutcome;
    private JButton buttonPlay;
    private JButton buttonNewGame;


    public MainFrame(String title, GameView view) {
        super(title);
        this.view = view;
    }

    public void addComponentsToPane(Container contentPane) {
        JPanel scoreBoard = new JPanel();
        scoreBoard.setBorder(new EmptyBorder(10, 10, 10, 10));
        scoreBoard.add(new JLabel("Score:"));
        labelScore = new JLabel("0:0");
        scoreBoard.add(labelScore);

        GridLayout layout = new GridLayout(3, 1);
        layout.setVgap(20);
        layout.setHgap(20);
        JPanel playerPanel = new JPanel(layout);
        playerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        playerPanel.add(new JLabel(getPlayerLabelText(1)));
        playerPanel.add(new JLabel(getPlayerLabelText(2)));
        labelMovePlayer1 = new JLabel();
        playerPanel.add(labelMovePlayer1);
        labelMovePlayer2 = new JLabel();
        playerPanel.add(labelMovePlayer2);
        labelRoundOutcome = new JLabel();
        playerPanel.add(labelRoundOutcome);

        JPanel controls = new JPanel();
        controls.setBorder(new EmptyBorder(10, 10, 10, 10));
        buttonPlay = new JButton("Play");
        controls.add(buttonPlay);
        buttonPlay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onPlay();
            }
        });

        buttonNewGame = new JButton("New Game");
        controls.add(buttonNewGame);
        buttonNewGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onNewGame();
            }
        });

        contentPane.add(scoreBoard, BorderLayout.NORTH);
        contentPane.add(playerPanel, BorderLayout.CENTER);
        contentPane.add(controls, BorderLayout.SOUTH);
        this.pack();
    }

    public void setLabelMovePlayer1Text(String move) {
        labelMovePlayer1.setText(move);
    }

    public void setLabelMovePlayer2Text(String move) {
        labelMovePlayer2.setText(move);
    }

    public void setLabelRoundOutcomeText(String text) {
        labelRoundOutcome.setText(text);
    }
    public void setLabelScore(String text) { labelScore.setText(text); }

    private void onPlay() {
        view.getController().playNextRound();
    }

    private void onNewGame() {
        dispose();
        view.getController().startNewGame();
    }

    private String getPlayerLabelText(int playerIndex) {
        return String.format("%s (%s)",
                view.getController().getPlayerNames()[playerIndex - 1],
                view.getController().getPlayerTypes()[playerIndex - 1]);
    }
}
