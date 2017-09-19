package nl.tudelft.pooralien.ui;

import nl.tudelft.pooralien.Controller.Board;
import nl.tudelft.pooralien.Controller.Game;
import javax.imageio.ImageIO;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * MainScreen class is the GUI of the game screen.
 */
public class MainScreen extends JLayeredPane {
    private JPanel mainFrame;
    private JLabel headerLabel;
    private JPanel gridBoard;
    private JPanelTile[][] gridBoardHolder;
    private JPanel controlPanel;
    private GridBagConstraints gbc;

    /**
     * Constructor of the MainScreen prepare the GUI.
     */
    public MainScreen() {
        prepareGUI();
        refreshBoard();
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
            gridBoardHolder[x][y].setBackground(Color.MAGENTA);
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
        for (int x = 0; x < Board.WIDTH; x++) {
            for (int y = 0; y < Board.HEIGHT; y++) {
                replaceItem(x, y, Game.getGame().getBoard().getItem(x, y).getSprite());
                replaceBorder(x, y);
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
        headerLabel = new JLabel("Score goes here!");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        mainFrame.add(headerLabel, gbc);
    }

    /**
     * create the grid board.
     */
    private void createGridBoard() {
        // initial gridBoardHolder
        gridBoardHolder = new JPanelTile[Board.WIDTH][Board.HEIGHT];
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.CENTER;

        GridLayout gridLayout = new GridLayout(Board.WIDTH, Board.HEIGHT);
        gridBoard = new JPanel();
        gridBoard.setLayout(gridLayout);

        BufferedImage image = null;
        Game game = Game.getGame();
        try {
            for (int x = 0; x < Board.WIDTH; x++) {
                for (int y = 0; y < Board.HEIGHT; y++) {
                    image = loadImage(game.getBoard().getItem(x, y).getSprite());
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
        controlPanel.add(new JButton("Save"));
        controlPanel.add(new JButton("Load"));

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.SOUTH;

        mainFrame.add(controlPanel, gbc);

    }

    /**
     * Load an image from the resources folder.
     * @param fileName the name of image.
     * @return Buffered Image of the loaded image.
     */
    private BufferedImage loadImage(String fileName) {
        BufferedImage image = null;
        try {
            File file = new File(this.getClass().getResource("/" + fileName + ".png").toURI());
            image = ImageIO.read(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }
}
