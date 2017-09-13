package nl.tudelft.pooralien;


import nl.tudelft.pooralien.ui.MainScreen;

import javax.swing.JFrame;

/**
 * The Launcher of the game.
 */
public class Launcher {

    private MainScreen mainScreen;

    public static final int BOARD_WIDTH = 10;
    public static final int BOARD_HEIGHT = 10;
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;

    /**
     * Launch the game GUI.
     */
    public void launch() {
        //StartupScreen startupScreen = new StartupScreen();
        //startupScreen.show();
        JFrame mainWindow = new JFrame("Poor Alien");
        mainWindow.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.getContentPane().add(new MainScreen());

        mainWindow.pack();
        mainWindow.setVisible(true);
    }

    /**
     * Entry point of the game.
     * @param args The program arguments.
     */
    public static void main(String[] args) {
        //StartupScreen startupScreen = new StartupScreen();
        //startupScreen.show();
        new Launcher().launch();
    }
}
