package nl.tudelft.pooralien;


import nl.tu.delft.defpro.api.IDefProAPI;
import nl.tudelft.pooralien.ui.MainScreen;

import javax.swing.JFrame;

import static nl.tu.delft.defpro.api.APIProvider.getAPI;

/**
 * The Launcher of the game.
 */
public class Launcher {

    private MainScreen mainScreen;
    private String cfgPath = this.getClass().getResource("/config_poor_alien.txt")
            .getPath().replaceFirst("/", "");

    /**
     * Launch the game GUI.
     */
    public void launch() {
        try {
            IDefProAPI gameCfg = getAPI(cfgPath);
            JFrame mainWindow = new JFrame("");
            MainScreen mainScreen = new MainScreen();
            mainWindow.setSize(0, 0);
            mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainWindow.getContentPane().add(mainScreen);

            new MouseEventHandler(mainScreen);
            mainWindow.pack();
            mainWindow.setVisible(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
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
