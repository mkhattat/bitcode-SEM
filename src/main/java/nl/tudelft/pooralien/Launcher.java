package nl.tudelft.pooralien;

import static nl.tu.delft.defpro.api.APIProvider.getAPI;
import static nl.tudelft.pooralien.InputDefender.*;

import java.net.URISyntaxException;

import javax.swing.JFrame;

import nl.tu.delft.defpro.api.IDefProAPI;
import nl.tudelft.pooralien.Controller.Game;
import nl.tudelft.pooralien.Controller.GameConfig;
import nl.tudelft.pooralien.ui.MainScreen;

/**
 * The Launcher of the game.
 */
public class Launcher {

    private static String cfgPath;
    private static IDefProAPI gameCfgOld;

    /*
    static {
        try {
            cfgPath = Launcher.class.getResource("/config.txt").toURI()
                    .getPath().replaceFirst("^/(.:/)", "$1");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        try {
            gameCfgOld = getAPI(cfgPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */

    /**
     * Launch the game GUI.
     */
    public void launch() {
        try {
            String gameTitle = GameConfig.getString("gameTitle", 1,50, "PoorAlien");
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
