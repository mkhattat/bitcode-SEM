package nl.tudelft.pooralien.ui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import nl.tudelft.pooralien.Controller.Board;
import nl.tudelft.pooralien.Controller.Client;
import nl.tudelft.pooralien.Controller.Game;
import nl.tudelft.pooralien.Controller.GameConfig;
import nl.tudelft.pooralien.Controller.Listener;
import nl.tudelft.pooralien.Controller.Server;
import nl.tudelft.pooralien.MouseActionObserver;
import nl.tudelft.pooralien.MouseEventHandler;
import nl.tudelft.pooralien.Observer;
import nl.tudelft.pooralien.Subject;

/**
 * MainScreen class is the GUI of the game screen.
 */
public class MainScreen implements Observer {
    private JPanel mainPanel;
    private JLayeredPane layerdPane;
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JPanel gridBoard;
    private JPanelTile[][] gridBoardHolder;
    private JPanel controlPanel;
    private GridBagConstraints gbc;
    private JButton disconnectNetwork = new JButton("Disconnect from the Network");
    private JButton pauseGame = new JButton("Pause game");
    private MouseEventHandler mouseEventHandler;
    private JLabel gameStateLable;
    private Client client;

    /**
     * Constructor of the MainScreen prepare the GUI.
     */
    public MainScreen() {
        Game.getGame().reset();
        this.layerdPane = new JLayeredPane();
        //Pass the MainScreen object to the Game object
        Game.getGame().setMainScreen(this);
        prepareGUI();
        //Makes a new board and refresh the GUI.
        Game.getGame().nextBoard();
        mouseEventHandler = new MouseEventHandler(this);
        Observer observer = new MouseActionObserver();
        mouseEventHandler.registerObserver(observer);
        layerdPane.addMouseListener(mouseEventHandler);
        layerdPane.addMouseMotionListener(mouseEventHandler);
    }

    /**
     * Launch the main screen.
     *
     */
    public void launch() {
        try {
            mainFrame = new JFrame(GameConfig.getString("gameTitle", 0,
                    "Poor Alien".length() * 2, "Poor Alien"));
            mainFrame.setSize(0, 0);
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.getContentPane().add(layerdPane);
            mainFrame.pack();
            mainFrame.setVisible(true);
            Game.getGame().registerObserver(this);
            Game.getGame().setMultiplayer(false);
            Game.getGame().getGameControllerMachine().setState(
                    Game.getGame().getGameControllerMachine().getGamePlayState());
            Game.getGame().getGameControllerMachine().startGame();
        } catch (Exception error) {
            System.out.println(error.getMessage());
            error.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Close the main screen.
     *
     */
    public void close() {
        if (mainFrame != null) {
            mainFrame.dispose();
        }
    }

    /**
     * Connect to a network.
     *
     * @param serverAddress is ip address of the server.
     * @param port is the port the server running on.
     * @return true if we can start a connection to the server.
     */
    public boolean connectToNetwork(String serverAddress, int port) {
        client = new Client(serverAddress, port, mouseEventHandler);
        mouseEventHandler.registerObserver(client);
        disconnectNetwork.setVisible(true);
        if (client.start()) {
            return true;
        }
        return false;
    }

    /**
     * Create a Host (Network).
     *
     * @param port the port which the server is going to be created on.
     */
    public void createHost(int port) {
        // show a new network window
        Listener listener = new Listener(port);
        ConnectionScreen connectionScreen = new ConnectionScreen(listener, this);
        Server.getServer().registerObserver(connectionScreen);
        Client thisPlayer = new Client("localhost", port, mouseEventHandler);
        thisPlayer.start();
        mouseEventHandler.registerObserver(thisPlayer);
    }

    /**
     * Add a component to the main screen.
     *
     * @param comp component which is going to be added to the mainscreen.
     * @param constraints the constraints related to the component.
     */
    public void add(Component comp, Object constraints) {
        layerdPane.add(comp, constraints);
    }

    /**
     * Revalidate the main screen.
     *
     */
    public void revalidate() {
        layerdPane.revalidate();
    }

    /**
     * Repaint the main screen.
     *
     */
    public void repaint() {
        layerdPane.repaint();
    }

    /**
     * Remove a component from the main screen.
     *
     * @param comp is the component which is going to be removed from main screen.
     */
    public void remove(Component comp) {
        layerdPane.remove(comp);
    }

    /**
     * Get the panel grid board containing which is placed on the screen.
     * @return GridBoard of the type JPanel.
     */
    public JPanel getGridBoard() {
        return gridBoard;
    }

    /**
     * Replace an Item on the screen by loading an image
     * onto the specific position on the screen.
     * @param x the X position.
     * @param y the Y position.
     * @param fileName file name of the new image which is going to be replaced.
     */
    public void replaceItem(int x, int y, String fileName) {
        BufferedImage image = loadImage(fileName);
        gridBoardHolder[x][y].removeAll();
        gridBoardHolder[x][y].add(new JLabel(new ImageIcon(image)));
        layerdPane.revalidate();
        layerdPane.repaint();
    }

    /**
     * replace items (images) on the board.
     * @param x the X position on the board.
     * @param y the Y position on the board.
     * @param label is the label containing an image.
     */
    public void replaceItem(int x, int y, JLabel label) {
        gridBoardHolder[x][y].removeAll();
        gridBoardHolder[x][y].add(label);
        layerdPane.revalidate();
        layerdPane.repaint();
    }

    /**
     * Add border or remove border depending on if they are in the global catalog.
     * @param x coordinate of the item where the border should be added/removed.
     * @param y coordinated of the item where the border should be added/removed.
     */
    public void replaceBorder(int x, int y) {
        gridBoardHolder[x][y].setBackground(null);

        if (Game.getGame().getBackgroundTileCatalog().contains(x, y)) {
            gridBoardHolder[x][y].setBackground(
                    Game.getGame().getBackgroundTileCatalog().get(x, y).getColorBackgroundTile());
        }
        layerdPane.revalidate();
        layerdPane.repaint();
    }

    /**
     * Get the item on the grid board at a specific position.
     * @param x the X position on the grid board.
     * @param y the Y position on the grid board.
     * @return the object of type JPanelTile on the grid board.
     */
    public JPanelTile getItem(int x, int y) {
        return gridBoardHolder[x][y];
    }

    /**
     * Remove an item (image) on the grid board at a specific position.
     * @param x the X position on the grid board.
     * @param y the Y position on the grid board.
     */
    public void removeItem(int x, int y) {
        gridBoardHolder[x][y].removeAll();
    }

    /**
     * Sync the board on the screen with the board data structure.
     */
    public void refreshBoard() {
        Board board = Game.getGame().getBoard();
        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                replaceItem(x, y, board.getItem(x, y).getSprite());
                replaceBorder(x, y);
                setHeaderText(
                        "Remaining Moves: "
                                + Game.getGame().getMoves()
                        + "     Score: "
                                + Game.getGame().getScoreCounter().getScore() + "      "
                );
            }
        }
    }

    /**
     * Set the text of the label at the top of the screen.
     * @param text the text of the label.
     */
    public void setHeaderText(String text) {
        headerLabel.setText(text);
    }

    /**
     * Set the text of the game state.
     *
     * @param text the text of the label.
     */
    public void setStateLabel(String text) {
        gameStateLable.setText(text);
    }

    /**
     * Set the text of the pause button.
     * @param text the text of the button.
     */
    public void setPauseGameButtonText(String text) {
        pauseGame.setText(text);
    }

    /**
     * prepare the GUI on the screen.
     */
    private void prepareGUI() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());


        gbc = new GridBagConstraints();

        createHeaderLabel();
        createGridBoard();
        createControlPanel();

        mainPanel.setSize(mainPanel.getPreferredSize());
        mainPanel.setLocation(0, 0);
        layerdPane.add(mainPanel, JLayeredPane.DEFAULT_LAYER);
        layerdPane.setPreferredSize(mainPanel.getPreferredSize());
    }

    /**
     * create the header label.
     */
    private void createHeaderLabel() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        headerLabel = new JLabel("Initialization of the board");
        gameStateLable = new JLabel("It's your turn");
        panel.add(headerLabel);
        panel.add(gameStateLable);
        mainPanel.add(panel, gbc);
    }

    /**
     * create the grid board.
     */
    private void createGridBoard() {
        // initial gridBoardHolder
        Board board = Game.getGame().getBoard();
        gridBoardHolder = new JPanelTile[board.getWidth()][board.getHeight()];
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.CENTER;

        GridLayout gridLayout = new GridLayout(board.getWidth(), board.getHeight());
        gridBoard = new JPanel();
        gridBoard.setLayout(gridLayout);

        BufferedImage image;
        try {
            for (int x = 0; x < board.getWidth(); x++) {
                for (int y = 0; y < board.getHeight(); y++) {
                    image = loadImage(board.getItem(x, y).getSprite());
                    gridBoardHolder[x][y] = new JPanelTile(new Point(x, y));
                    gridBoardHolder[x][y].add(new JLabel(new ImageIcon(image)));
                    gridBoard.add(gridBoardHolder[x][y]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        mainPanel.add(gridBoard, gbc);
    }

    /**
     * create the control panel with two buttons.
     */
    private void createControlPanel() {
        controlPanel = new JPanel();
        JButton exit = new JButton("Return to main screen");
        exit.addActionListener(new ExitListener());
        controlPanel.add(exit);
        controlPanel.add(pauseGame);
        pauseGame.addActionListener(new PauseGameListener());
        disconnectNetwork.setVisible(false);
        controlPanel.add(disconnectNetwork);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.SOUTH;

        disconnectNetwork.addActionListener(new DisconnectListener());
        mainPanel.add(controlPanel, gbc);

    }

    /**
     * An listener for the pause game button.
     */
    private class PauseGameListener implements ActionListener {
        private boolean buttonTextIsPaused = true;
        public void actionPerformed(ActionEvent e) {
            if (buttonTextIsPaused) {
                Game.getGame().getGameControllerMachine().pauseGame();
                buttonTextIsPaused = false;
                pauseGame.setText("Unpause Game");
            } else {
                Game.getGame().getGameControllerMachine().resumeGame();
                pauseGame.setText("Pause Game");
                buttonTextIsPaused = true;
            }

        }
    }

    /**
     * An listener for disconnect button.
     */
    private class DisconnectListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            disconnectNetwork.setVisible(false);
            Server.getServer().destroy();
            if (client != null) {
                client.terminate();
            }
        }
    }

    /**
     * An listener for Exit button.
     */
    private class ExitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (Game.getGame().multiplayerMode()) {
                new DisconnectListener().actionPerformed(null);
            }
            StartupScreen startupScreen = new StartupScreen();
            startupScreen.show();
            close();
        }
    }

    /**
     * Load an image from the resources folder.
     * @param fileName the name of image.
     * @return Buffered Image of the loaded image.
     */
    private BufferedImage loadImage(String fileName) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(this.getClass().getResource("/" + fileName + ".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    @Override
    public void update(Subject subject) {
        if (!(subject instanceof Game)) {
            return;
        }

        if (Game.getGame().multiplayerMode()) {
            disconnectNetwork.setVisible(true);
            if (Game.getGame().gameIsRunning()) {
                setStateLabel("It's your turn");
            } else {
                setStateLabel("Waiting for the other player!");
            }
        } else {
            disconnectNetwork.setVisible(false);
            if (Game.getGame().gameIsRunning()) {
                setStateLabel("Offline mode");
            } else {
                setStateLabel("The game is paused");
            }
        }
    }
}
