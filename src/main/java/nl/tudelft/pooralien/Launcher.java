package nl.tudelft.pooralien;


import nl.tudelft.pooralien.ui.MainScreen;

import javax.swing.JFrame;

/**
 * The Launcher of the game.
 */
public class Launcher {

    private MainScreen mainScreen;

    /**
     * Launch the game GUI.
     */
    public void launch() {
        JFrame mainWindow = new JFrame("Poor Alien");
        MainScreen mainScreen = new MainScreen();
        mainWindow.setSize(0, 0);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.getContentPane().add(mainScreen);

        new MouseEventHandler(mainScreen);
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
