package nl.tudelft.pooralien;

import nl.tudelft.pooralien.Controller.GameStates.GameControllerMachine;
import nl.tudelft.pooralien.Controller.Game;
import nl.tudelft.pooralien.Controller.GameConfig;

/**
 * The Launcher of the game.
 */
public class Launcher {

    /**
     * Launch the game GUI.
     */
    public void launch() {
        GameControllerMachine gameControllerMachine = Game.getGame().getGameControllerMachine();
        gameControllerMachine.setState(gameControllerMachine.getMainMenuState());
        gameControllerMachine.mainMenu();
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


        //StartupScreen startupScreen = new StartupScreen();
        //startupScreen.show();

    }
}
