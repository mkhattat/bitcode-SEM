package nl.tudelft.pooralien;

import static nl.tu.delft.defpro.api.APIProvider.getAPI;

import java.net.URISyntaxException;

import javax.swing.JFrame;

import nl.tu.delft.defpro.api.IDefProAPI;
import nl.tudelft.pooralien.Controller.Game;
import nl.tudelft.pooralien.ui.MainScreen;
import nl.tudelft.pooralien.ui.StartupScreen;

/**
 * The Launcher of the game.
 */
public class Launcher {

    private static String cfgPath;
    private static IDefProAPI gameCfg;

    static {
        try {
            cfgPath = Launcher.class.getResource("/config.txt").toURI()
                    .getPath().replaceFirst("^/(.:/)", "$1");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        try {
            gameCfg = getAPI(cfgPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Launch the game GUI.
     */
    public void launch() {
        StartupScreen startupScreen = new StartupScreen();
        startupScreen.show();
    }

    /**
     * Game config file object.
     * @return an IDefProAPI object.
     */
    public static IDefProAPI getGameCfg() {
        return gameCfg;
    }

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
