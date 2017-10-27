package nl.tudelft.pooralien.ui.HighScoreTable;

import nl.tudelft.pooralien.Controller.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HighScoreFrame {


    private JPanel highScoreBoard;
    private JPanel controlPanel;

    public HighScoreFrame() {

        JFrame frame = new JFrame("HighScoreBoard");

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        highScoreBoard = new JPanel();

        highScoreBoard.add(Game.getGame().getHighScoreTableTopX());

        frame.add(highScoreBoard, BorderLayout.CENTER);
        frame.add(addCloseButton(), BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
        frame.toFront();


    }

    private JPanel addCloseButton() {
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton newGameButton = new JButton("New game");
        controlPanel.add(newGameButton);

        JButton backtoMainMenu = new JButton("Main Menu");
        controlPanel.add(backtoMainMenu);

        newGameButton.addActionListener(new CreateNewGameButtonListener());
        backtoMainMenu.addActionListener(new BackToMainMenuButtonListener());

        return controlPanel;
    }

    private class CreateNewGameButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //NEEDS TO BE IMPLEMENTED IN STATE PATTERN BRANCH.
        }
    }

    private class BackToMainMenuButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //NEEDS TO BE IMPLEMENTED IN STATE PATTERN BRANCH.
        }
    }

}
