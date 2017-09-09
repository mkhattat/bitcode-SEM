package nl.tudelft.pooralien;

/**
 * Created by mostafa on 7-9-17.
 */
public class Launcher {

    private MainScreen mainScreen;

    public static final int BOARD_WIDTH = 10;
    public static final int BOARD_HEIGHT = 10;

    /**
     * To prevent checkstyle error.
    private Launcher() {
    }
     */

    public void launch() {
        //StartupScreen startupScreen = new StartupScreen();
        //startupScreen.show();
        mainScreen = new MainScreen("Poor Alien", 800, 600);
        mainScreen.show();
    }

    /**
     * Entry point of the game.
     * @param args The program arguments.
     */
    public static void main(String[] args) {
        //StartupScreen startupScreen = new StartupScreen();
        //startupScreen.show();
        new Launcher().launch();
    }
}
