package nl.tudelft.pooralien.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import nl.tu.delft.defpro.exception.NotExistingVariableException;
import nl.tudelft.pooralien.Controller.Game;
import nl.tudelft.pooralien.Controller.GameConfig;
import nl.tudelft.pooralien.Launcher;
import nl.tudelft.pooralien.ui.HighScoreTable.HighScoreFrame;

/**
 * A simple startup screen.
 */
public class StartupScreen {
    private JFrame mainFrame;
    private JLabel welcomeLabel;
    private JPanel multiPlayerPanel;
    private JPanel highScorePanel;
    private JButton createNetwrok = new JButton("Create Network");
    private JButton connectNetwork = new JButton("Connect to a Network");
    private GridBagConstraints gbc;
    private int port;
    private static final int WIDTH_SIZE = 600;
    private static final int HEIGHT_SIZE = 600;

    final int minPort = 1000;
    final int maxPort = 65000;
    final int defaultPort = 9090;

    /**
     * Constructor for the StartupScreen.
     */
    public StartupScreen() {
        port = GameConfig.getInteger("port", minPort, maxPort, defaultPort);
        prepareGUI();
    }

    /**
     * Prepare the items which is going to be shown on the screen.
     */
    private void prepareGUI() {
        mainFrame = new JFrame("Poor Alien");
        mainFrame.setSize(WIDTH_SIZE, HEIGHT_SIZE);
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
        createOfflinePanel();
        createMultiPlayerPanel();
        createHighScorePanel();
        createExitPanel();
    }

    /**
     * setup a label for the welcome text.
     */
    private void createWelcomeLabel() {
        welcomeLabel = new JLabel("Welcome to the Poor Alien Game!");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;

        mainFrame.add(welcomeLabel, gbc);
    }

    /**
     * create a panel containing two buttons.
     */
    private void createOfflinePanel() {
        JButton newGame = new JButton("New Single Player");
        newGame.addActionListener(new NewGameListener());

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;

        mainFrame.add(newGame, gbc);
    }

    /**
     * Create a panel containing multiplayer buttons.
     *
     */
    private void createMultiPlayerPanel() {
        multiPlayerPanel = new JPanel();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1;

        multiPlayerPanel.add(createNetwrok);
        multiPlayerPanel.add(connectNetwork);

        createNetwrok.addActionListener(new CreateNetworkListener());
        connectNetwork.addActionListener(new ConnectNetworkListener());
        mainFrame.add(multiPlayerPanel, gbc);
    }

    /**
     * Add high score button to the screen.
     *
     */
    private void createHighScorePanel() {
        highScorePanel = new JPanel();
        gbc.gridx = 0;
        gbc.gridy = 2 + 1;
        gbc.weightx = 1;

        JButton highScoreBtn = new JButton("Highest Score");
        highScoreBtn.addActionListener(new HighScoreListener());
        highScorePanel.add(highScoreBtn);
        mainFrame.add(highScorePanel, gbc);
    }

    /**
     * Add an Exit button to the screen.
     *
     */
    private void createExitPanel() {
        JButton exitBtn = new JButton("EXIT");
        exitBtn.addActionListener(new ExitListener());

        gbc.gridx = 0;
        gbc.gridy = 2 + 1 + 1;
        gbc.weightx = 1;

        mainFrame.add(exitBtn, gbc);
    }

    /**
     * An listener for create network button.
     */
    private class CreateNetworkListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            createNetwrok.setVisible(false);
            connectNetwork.setVisible(false);
            MainScreen mainScreen = new MainScreen();
            mainScreen.createHost(port);
            close();
        }
    }

    /**
     * An listener for connect network button.
     */
    private class ConnectNetworkListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Ask for the host IP address.
            String serverAddress = JOptionPane.showInputDialog(
                    "Enter IP Address of a machine that is \n"
                    + "running the server on port " + port + ":");
            if (serverAddress == null) {
                return;
            }
            MainScreen mainScreen = new MainScreen();
            boolean isConnected = mainScreen.connectToNetwork(serverAddress, port);
            if (!isConnected) {
                return;
            }
            mainScreen.launch();
            close();
        }
    }

    /**
     * Action listener for exit button.
     */
    private class ExitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }


    /**
     * Action listener for new game button.
     */
    private class NewGameListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String[] difficulties = { "Easy", "Normal", "Hard" };
            JFrame frame = new JFrame("New Game");
            String difficultiesdialog = (String) JOptionPane.showInputDialog(
                    frame,
                    "Choose a difficulty from the list below",
                    "New Game",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    difficulties,
                    difficulties[0]
            );
            if (difficultiesdialog == null) {
                return;
            }
            if (difficultiesdialog.equals(difficulties[0])) {
                Game.setEasyMode();
            }
            if (difficultiesdialog.equals(difficulties[1])) {
                Game.setStandardMode();
            }
            if (difficultiesdialog.equals(difficulties[2])) {
                Game.setHardMode();
            }
            MainScreen mainScreen = new MainScreen();
            mainScreen.launch();
            close();
        }
    }

    /**
     * Action listener for highscore button.
     */
    private class HighScoreListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new HighScoreFrame();
        }
    }

    /**
     * Show the Startup Screen.
     */
    public void show() {
        mainFrame.setVisible(true);
    }

    /**
     * Close the Startup Screen.
     */
    public void close() {
        mainFrame.dispose();
    }
}
