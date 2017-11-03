package nl.tudelft.pooralien;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import nl.tudelft.pooralien.Controller.Game;
import nl.tudelft.pooralien.Controller.GameConfig;
import nl.tudelft.pooralien.ui.HighScoreTable.HighScoreFrame;
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
            JFrame mainWindow = new JFrame(getGameTitle());
            MainScreen mainScreen = new MainScreen();
            mainWindow.setSize(0, 0);
            mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            JPanel gameHolder = new JPanel();
            gameHolder.add(mainScreen);

            mainWindow.getContentPane().add(gameHolder);

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
        //READDED IN THE STATE BRANCH.
        new HighScoreFrame();
    }


    /**
     * Get gameTitle from config file.
     *
     * @return String The game title.
     */
    private String getGameTitle() {
        final int minLengthGameTitle = 1;
        final int maxLengthGameTitle = 50;
        final String defaultGameTitle = "P00rAl13n";

        return GameConfig.getString("gameTitle", minLengthGameTitle,
                maxLengthGameTitle, defaultGameTitle);
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
