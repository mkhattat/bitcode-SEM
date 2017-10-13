package nl.tudelft.pooralien;

import static nl.tu.delft.defpro.api.APIProvider.getAPI;

import java.net.URISyntaxException;
import nl.tudelft.pooralien.Controller.GameStates.GameControllerMachine;

import javax.swing.JFrame;
import javax.swing.JPanel;

import nl.tu.delft.defpro.api.IDefProAPI;
import nl.tudelft.pooralien.Controller.Game;
import nl.tudelft.pooralien.ui.MainScreen;

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
        GameControllerMachine gameControllerMachine = Game.getGame().getGameControllerMachine();
        gameControllerMachine.setState(gameControllerMachine.getMainMenuState());
        gameControllerMachine.MainMenu();
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


        //StartupScreen startupScreen = new StartupScreen();
        //startupScreen.show();

    }
}
