package com.iserban.app.view.components;

import com.iserban.app.view.GameView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class StartDialog extends JDialog {
    private GameView view;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonQuit;
    private JComboBox comboBoxGameMode;

    public StartDialog(final GameView view) {
        this.view = view;
        addComponentsToPane(getContentPane());
        setModal(true);
        comboBoxGameMode.setModel(new DefaultComboBoxModel(view.getController().getGameModes()));
        getRootPane().setDefaultButton(buttonOK);
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
        buttonQuit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onQuit();
            }
        });

        // call onQuit() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onQuit();
            }
        });
    }

    private void addComponentsToPane(Container contentPane) {
        GridLayout layout = new GridLayout(2, 1);
        JPanel movePanel = new JPanel(layout);
        movePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        movePanel.add(new JLabel("Please select Game Mode:"));
        comboBoxGameMode = new JComboBox();
        movePanel.add(comboBoxGameMode);


        JPanel controls = new JPanel();
        buttonOK = new JButton("OK");
        controls.add(buttonOK);
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonQuit = new JButton("Quit");
        controls.add(buttonQuit);
        buttonQuit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onQuit();
            }
        });

        contentPane.add(movePanel, BorderLayout.NORTH);
        contentPane.add(controls, BorderLayout.SOUTH);
    }

    private void onOK() {
        // tell controller which game mode was selected
        view.getController().selectGameMode(comboBoxGameMode.getSelectedIndex());
        dispose();
    }

    private void onQuit() {
        // add your code here if necessary
        dispose();
        System.exit(0);
    }
}
