package com.iserban.app.view.components;

import com.iserban.app.view.GameView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Dialog for selecting game move.
 */
public class SelectMoveDialog extends JDialog {
    private GameView view;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox selectMoveComboBox;

    public SelectMoveDialog(final GameView view) {
        this.view = view;
        addComponentsToPane(getContentPane());
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        selectMoveComboBox.setModel(new DefaultComboBoxModel(view.getController().getGameMoves()));

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
    }

    private void addComponentsToPane(Container contentPane) {
        GridLayout layout = new GridLayout(2, 1);
        JPanel movePanel = new JPanel(layout);
        movePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        movePanel.add(new JLabel("Select move:"));
        selectMoveComboBox = new JComboBox();
        movePanel.add(selectMoveComboBox);


        JPanel controls = new JPanel();
        buttonOK = new JButton("OK");
        controls.add(buttonOK);
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel = new JButton("Cancel");
        controls.add(buttonCancel);
        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        contentPane.add(movePanel, BorderLayout.NORTH);
        contentPane.add(controls, BorderLayout.SOUTH);
    }

    private void onOK() {
        // tell controller which move was selected
        view.getController().selectMove(selectMoveComboBox.getSelectedIndex());
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
