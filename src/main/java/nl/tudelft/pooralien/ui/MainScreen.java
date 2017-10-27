package nl.tudelft.pooralien.ui;

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
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import nl.tu.delft.defpro.exception.NotExistingVariableException;
import nl.tudelft.pooralien.Controller.*;
import nl.tudelft.pooralien.Launcher;
import nl.tudelft.pooralien.MouseActionObserver;
import nl.tudelft.pooralien.MouseEventHandler;
import nl.tudelft.pooralien.Observer;
import nl.tudelft.pooralien.Subject;

/**
 * MainScreen class is the GUI of the game screen.
 */
public class MainScreen extends JLayeredPane implements Observer {
    private JPanel mainFrame;
    private JLabel headerLabel;
    private JPanel gridBoard;
    private JPanelTile[][] gridBoardHolder;
    private JPanel controlPanel;
    private GridBagConstraints gbc;
    private JButton createNetwrok = new JButton("Create Network");
    private JButton connectNetwork = new JButton("Connect to a Network");
    private JButton disconnectNetwork = new JButton("Disconnect from the Network");
    private MouseEventHandler mouseEventHandler;
    private JLabel gameStateLable;
    private Client client;
    private int port;

    /**
     * Constructor of the MainScreen prepare the GUI.
     */
    public MainScreen() {
        prepareGUI();
        refreshBoard();

        mouseEventHandler = new MouseEventHandler(this);
        Observer observer = new MouseActionObserver();
        mouseEventHandler.registerObserver(observer);

        port = GameConfig.getInteger("port", 1000, 65000, 9090);
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
        revalidate();
        repaint();
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
        revalidate();
        repaint();
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
        revalidate();
        repaint();
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
     * prepare the GUI on the screen.
     */
    private void prepareGUI() {
        mainFrame = new JPanel();
        mainFrame.setLayout(new GridBagLayout());


        gbc = new GridBagConstraints();

        createHeaderLabel();
        createGridBoard();
        createControlPanel();

        mainFrame.setSize(mainFrame.getPreferredSize());
        mainFrame.setLocation(0, 0);
        add(mainFrame, JLayeredPane.DEFAULT_LAYER);
        setPreferredSize(mainFrame.getPreferredSize());
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
        mainFrame.add(panel, gbc);
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

        BufferedImage image = null;
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
        mainFrame.add(gridBoard, gbc);
    }

    /**
     * create the control panel with two buttons.
     */
    private void createControlPanel() {
        controlPanel = new JPanel();
        JButton save = new JButton("Save");
        controlPanel.add(save);
        JButton load = new JButton("Load");
        controlPanel.add(load);
        controlPanel.add(createNetwrok);
        controlPanel.add(connectNetwork);
        disconnectNetwork.setVisible(false);
        controlPanel.add(disconnectNetwork);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.SOUTH;


        createNetwrok.addActionListener(new CreateNetworkListener());
        connectNetwork.addActionListener(new ConnectNetworkListener());
        disconnectNetwork.addActionListener(new DisconnectListener());
        mainFrame.add(controlPanel, gbc);

    }

    /**
     * An listener for create network button.
     */
    private class CreateNetworkListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // show a new network window
            Listener listener = new Listener(port);
            Game.getGame().pauseGame();
            ConnectionScreen connectionScreen = new ConnectionScreen(listener);
            Server.getServer().registerObserver(connectionScreen);
            Client thisPlayer = new Client("localhost", port, mouseEventHandler);
            thisPlayer.start();
            createNetwrok.setVisible(false);
            connectNetwork.setVisible(false);
            mouseEventHandler.registerObserver(thisPlayer);
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
            client = new Client(serverAddress, port, mouseEventHandler);
            // TODO: show a error message if it fails to connect
            if (client.start()) {
                Game.getGame().pauseGame();
                mouseEventHandler.registerObserver(client);
                createNetwrok.setVisible(false);
                connectNetwork.setVisible(false);
                disconnectNetwork.setVisible(true);
            }
        }
    }

    /**
     * An listener for disconnect button.
     */
    private class DisconnectListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            connectNetwork.setVisible(true);
            createNetwrok.setVisible(true);
            disconnectNetwork.setVisible(false);
            Server.getServer().destroy();
            if (client != null) {
                client.terminate();
            }
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
            connectNetwork.setVisible(false);
            createNetwrok.setVisible(false);
            disconnectNetwork.setVisible(true);
            if (Game.getGame().gameIsRunning()) {
                setStateLabel("It's your turn");
            } else {
                setStateLabel("Waiting for the other player!");
            }
        } else {
            connectNetwork.setVisible(true);
            createNetwrok.setVisible(true);
            disconnectNetwork.setVisible(false);
            if (Game.getGame().gameIsRunning()) {
                setStateLabel("Offline mode");
            } else {
                setStateLabel("The game is paused");
            }
        }
    }
}
