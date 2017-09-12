package nl.tudelft.pooralien.ui;

import nl.tudelft.pooralien.Launcher;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

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

        MyMouseAdapter mouseAdapter = new MyMouseAdapter(this);
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);

    }

    private void createHeaderLabel() {
        headerLabel = new JLabel("Score goes here!");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        mainFrame.add(headerLabel, gbc);
    }

    private void createGridBoard() {
        // initial gridBoardHolder
        gridBoardHolder = new JPanelTile[Launcher.BOARD_WIDTH][Launcher.BOARD_HEIGHT];
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.CENTER;

        GridLayout gridLayout = new GridLayout(Launcher.BOARD_WIDTH, Launcher.BOARD_HEIGHT);
        gridBoard = new JPanel();
        gridBoard.setBackground(Color.darkGray);
        gridBoard.setLayout(gridLayout);

        BufferedImage image = null;
        try {
            for (int x = 0; x < Launcher.BOARD_WIDTH; x++) {
                for (int y = 0; y < Launcher.BOARD_HEIGHT; y++) {
                    Random r = new Random();
                    int rn = r.nextInt(8 - 1) + 1;
                    image = loadImage("sample" + rn);
                    gridBoardHolder[x][y] = new JPanelTile(x, y);
                    gridBoardHolder[x][y].add(new JLabel(new ImageIcon(image)));
                    gridBoard.add(gridBoardHolder[x][y]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        mainFrame.add(gridBoard, gbc);
    }

    private void createControlPanel() {
        controlPanel = new JPanel();
        controlPanel.add(new JButton("Start"));
        controlPanel.add(new JButton("Stop"));

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.SOUTH;

        mainFrame.add(controlPanel, gbc);

    }

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
