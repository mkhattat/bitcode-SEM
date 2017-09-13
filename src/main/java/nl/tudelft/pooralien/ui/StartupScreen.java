package nl.tudelft.pooralien.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * A simple startup screen.
 */
public class StartupScreen {
    private JFrame mainFrame;
    private JLabel welcomeLabel;
    private JPanel btnPanel;
    private GridBagConstraints gbc;

    public StartupScreen() {
        prepareGUI();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Poor Alien");
        mainFrame.setSize(800,600);
        mainFrame.setLayout(new GridBagLayout());

        gbc = new GridBagConstraints();

        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                /// Add some warning here if the user wants to close the game which is running
                System.exit(0);
            }
        });

        createWelcomeLabel();
        createButtonPanel();
    }

    private void createWelcomeLabel() {
        welcomeLabel = new JLabel("Welcome to the Poor Alien Game!");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.NORTH;

        mainFrame.add(welcomeLabel, gbc);
    }

    private void createButtonPanel() {
        btnPanel = new JPanel();
        btnPanel.add(new JButton("New Game"));
        btnPanel.add(new JButton("Exit"));

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.SOUTH;

        mainFrame.add(btnPanel, gbc);

    }

    public void show() {
        mainFrame.setVisible(true);
    }

    public void hide() {
        mainFrame.setVisible(false);
    }
}
