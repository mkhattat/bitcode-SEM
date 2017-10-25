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
        try {
            JFrame mainWindow = new JFrame(gameCfg.getStringValueOf("gameTitle"));
            MainScreen mainScreen = new MainScreen();
            mainWindow.setSize(0, 0);
            mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel gameAndScoreHolder = new JPanel();
            gameAndScoreHolder.add(mainScreen);
            gameAndScoreHolder.add(Game.getGame().getHighScoreTableTopX());

            mainWindow.getContentPane().add(gameAndScoreHolder);

            mainWindow.pack();
            Game.getGame().registerObserver(mainScreen);
            Game.getGame().setMultiplayer(false);
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
            new GameControllerMachine();
        } catch (Exception e) {
            e.printStackTrace();
        }


        //StartupScreen startupScreen = new StartupScreen();
        //startupScreen.show();

    }
}
