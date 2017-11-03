package nl.tudelft.pooralien;

import javax.swing.JFrame;

import nl.tudelft.pooralien.Controller.Game;
import nl.tudelft.pooralien.Controller.GameConfig;
import nl.tudelft.pooralien.ui.MainScreen;

/**
 * The Launcher of the game.
 */
public class Launcher {

    /**
     * Launch the game GUI.
     */
    public void launch() {
        try {
            final int minLengthGameTitle = 1;
            final int maxLengthGameTitle = 50;
            final String defaultGameTitle = "P00rAl13n";

            String gameTitle = GameConfig.getString("gameTitle", minLengthGameTitle,
                    maxLengthGameTitle, defaultGameTitle);

            JFrame mainWindow = new JFrame(gameTitle);
            MainScreen mainScreen = new MainScreen();
            mainWindow.setSize(0, 0);
            mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainWindow.getContentPane().add(mainScreen);

            mainWindow.pack();
            Game.getGame().registerObserver(mainScreen);
            Game.getGame().setMultiplayer(false);
            if (!GameConfig.getBoolean("multiLevel", false)) {
                mainWindow.setVisible(true);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * Game config file object.
     * @return an IDefProAPI object.
     */
    /*
    public static IDefProAPI getGameCfgOld() {
        return gameCfgOld;
    }
    */

    /**
     * Entry point of the game.
     * @param args The program arguments.
     */
    public static void main(String[] args) {
        //StartupScreen startupScreen = new StartupScreen();
        //startupScreen.show();
        try {
            new Launcher().launch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
