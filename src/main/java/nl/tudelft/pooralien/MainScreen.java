package nl.tudelft.pooralien;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by mostafa on 7-9-17.
 */
public class MainScreen {
    private JFrame window;
    private JLayeredPane rootPane;
    private JPanel mainFrame;
    private JLabel headerLabel;
    private JPanel gridBoard;
    private JPanelTile[][] gridBoardHolder;
    private JPanel controlPanel;
    private GridBagConstraints gbc;

    public MainScreen(String title, int width, int height) {
        window = new JFrame(title);
        window.setSize(width, height);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        prepareGUI();
    }


    public JPanel[][] getGridBoard() {
        return gridBoardHolder;
    }

    public void show() {
        window.setVisible(true);
    }

    public void hide() {
        window.setVisible(false);
    }

    private void prepareGUI() {
        rootPane = new JLayeredPane();
        mainFrame = new JPanel();
        mainFrame.setLayout(new GridBagLayout());


        gbc = new GridBagConstraints();

        createHeaderLabel();
        createGridBoard();
        createControlPanel();

        mainFrame.setSize(mainFrame.getPreferredSize());
        mainFrame.setLocation(0, 0);
        rootPane.add(mainFrame, JLayeredPane.DEFAULT_LAYER);
        rootPane.setPreferredSize(mainFrame.getPreferredSize());


        window.getContentPane().add(rootPane);
        window.pack();
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
        gridLayout.setHgap(5);
        gridLayout.setVgap(5);

        gridBoard = new JPanel();
        gridBoard.setBackground(Color.darkGray);
        gridBoard.setLayout(gridLayout);


        BufferedImage image = null;
        try {
            File file = new File(this.getClass().getResource("/sample.png").toURI());
            image = ImageIO.read(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int x = 0; x < Launcher.BOARD_WIDTH; x++) {
            for (int y = 0; y < Launcher.BOARD_HEIGHT; y++) {
                gridBoardHolder[x][y] = new JPanelTile(x, y);
                gridBoardHolder[x][y].add(new JLabel(new ImageIcon(image)));
                gridBoard.add(gridBoardHolder[x][y]);
            }

        }

        gridBoardHolder[9][9].removeAll();
        gridBoardHolder[9][9].add(new JLabel("TEST"));

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
}
