package nl.tudelft.pooralien;

import static nl.tu.delft.defpro.api.APIProvider.getAPI;

import java.net.URISyntaxException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import nl.tu.delft.defpro.api.IDefProAPI;
import nl.tudelft.pooralien.Controller.Game;
import nl.tudelft.pooralien.ui.HighScoreTable.HighScoreFrame;
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

            JPanel gameHolder = new JPanel();
            gameHolder.add(mainScreen);

            mainWindow.getContentPane().add(gameHolder);

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
        //READDED IN THE STATE BRANCH.
        //HighScoreFrame highScoreFrame = new HighScoreFrame();
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
