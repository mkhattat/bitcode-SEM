package nl.tudelft.pooralien.ui;

import nl.tudelft.pooralien.Launcher;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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

    /**
     * Constructor for the StartupScreen.
     */
    public StartupScreen() {
        prepareGUI();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Poor Alien");
        mainFrame.setSize(Launcher.SCREEN_WIDTH, Launcher.SCREEN_HEIGHT);
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

    /**
     * Show the Startup Screen.
     */
    public void show() {
        mainFrame.setVisible(true);
    }

    /**
     * Hide the Startup Screen.
     */
    public void hide() {
        mainFrame.setVisible(false);
    }
}
