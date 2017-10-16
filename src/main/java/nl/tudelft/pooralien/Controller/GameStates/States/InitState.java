package nl.tudelft.pooralien.Controller.GameStates.States;

import nl.tudelft.pooralien.Controller.GameStates.GameControllerMachine;
import nl.tudelft.pooralien.Controller.GameStates.State;
import nl.tudelft.pooralien.Launcher;
import nl.tudelft.pooralien.MouseEventHandler;
import nl.tudelft.pooralien.ui.MainScreen;

import javax.swing.*;

public class InitState implements State {

    GameControllerMachine gameControllerMachine;

    /**
     * the Constructor of Launcher.
     * @throws Exception if the config file doesn't exist throws and Exception.
     */
    public InitState(GameControllerMachine gameControllerMachine) throws Exception {
        this.gameControllerMachine = gameControllerMachine;
        this.launch();
    }

    /**
     * Launch the game GUI.
     */
    public void launch() {
        try {
            JFrame mainWindow = new JFrame(Launcher.getGameCfg().getStringValueOf("gameTitle"));
            MainScreen mainScreen = new MainScreen();
            mainWindow.setSize(0, 0);
            mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainWindow.getContentPane().add(mainScreen);

            new MouseEventHandler(mainScreen);
            mainWindow.pack();
            if (!Launcher.getGameCfg().getBooleanValueOf("multiLevel")) {
                mainWindow.setVisible(true);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void goToMainMenu() {
        //NOT POSSIBLE.
    }

    @Override
    public void initGame() {

    }

    @Override
    public void startGame() {

    }

    @Override
    public void pauseGame() {
        //NOT POSSIBLE.
    }

    @Override
    public void endGame() {
        // NOT POSSIBLE.
    }

    @Override
    public void exit() {

    }
}
