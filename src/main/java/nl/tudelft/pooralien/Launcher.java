package nl.tudelft.pooralien;


import nl.tu.delft.defpro.api.IDefProAPI;
import nl.tudelft.pooralien.Controller.GameStates.GameControllerMachine;

import static nl.tu.delft.defpro.api.APIProvider.getAPI;

/**
 * The Launcher of the game.
 */
public class Launcher {

    private String cfgPath = this.getClass().getResource("/config.txt").toURI()
            .getPath().replaceFirst("^/(.:/)", "$1");


    private static IDefProAPI gameCfg;

    /**
     * the Constructor of Launcher.
     * @throws Exception if the config file doesn't exist throws and Exception.
     */
    public Launcher() throws Exception {
        gameCfg = getAPI(cfgPath);
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
        try {
            new Launcher();
        } catch (Exception e) {
            e.printStackTrace();
        }


        //StartupScreen startupScreen = new StartupScreen();
        //startupScreen.show();
        new GameControllerMachine();

    }
}
