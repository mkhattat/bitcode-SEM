package nl.tudelft.pooralien;


import nl.tu.delft.defpro.api.IDefProAPI;
import nl.tudelft.pooralien.ui.MainScreen;

import javax.swing.JFrame;

import static nl.tu.delft.defpro.api.APIProvider.getAPI;

/**
 * The Launcher of the game.
 */
public class Launcher {

    private String cfgPath = this.getClass().getResource("/config.txt")
            .getPath().replaceFirst("^/(.:/)", "$1");

    // private String cfgPath = "config.txt";

    private static IDefProAPI gameCfg;

    /**
     * the Constructor of Launcher.
     * @throws Exception if the config file doesn't exist throws and Exception.
     */
    public Launcher() throws Exception {
        gameCfg = getAPI(cfgPath);
    }

    /**
     * Launch the game GUI.
     */
    public void launch() {
        try {
            JFrame mainWindow = new JFrame(gameCfg.getStringValueOf("gameTitle"));
            MainScreen mainScreen = new MainScreen();
            mainWindow.setSize(0, 0);
            mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainWindow.getContentPane().add(mainScreen);

            new MouseEventHandler(mainScreen);
            mainWindow.pack();
            if (!gameCfg.getBooleanValueOf("multiLevel")) {
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
