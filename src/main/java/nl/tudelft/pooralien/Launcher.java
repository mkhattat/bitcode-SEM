package nl.tudelft.pooralien;


import nl.tu.delft.defpro.api.IDefProAPI;
import nl.tudelft.pooralien.ui.MainScreen;

import javax.swing.JFrame;

import java.net.URISyntaxException;

import static nl.tu.delft.defpro.api.APIProvider.getAPI;

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
        } catch(Exception e) {
            e.printStackTrace();
        }
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
