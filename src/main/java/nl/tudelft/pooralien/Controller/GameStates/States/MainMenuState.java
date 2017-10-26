package nl.tudelft.pooralien.Controller.GameStates.States;

import nl.tudelft.pooralien.Controller.Game;
import nl.tudelft.pooralien.Controller.GameStates.GameControllerMachine;
import nl.tudelft.pooralien.Controller.GameStates.State;
import nl.tudelft.pooralien.Launcher;
import nl.tudelft.pooralien.ui.MainScreen;

import javax.swing.*;

public class MainMenuState implements State {

    GameControllerMachine gameControllerMachine;

    public MainMenuState(GameControllerMachine gameControllerMachine) {
        this.gameControllerMachine = gameControllerMachine;
    }

    @Override
    public void MainMenu() {
        // Already in main menu. -> OR should this method be called in the MainMenuState constructor
        System.out.println("CurrentState: MainMenuState, ");

        try {
            JFrame mainWindow = new JFrame(Launcher.getGameCfg().getStringValueOf("gameTitle"));
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
            if (!Launcher.getGameCfg().getBooleanValueOf("multiLevel")) {
                mainWindow.setVisible(true);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        startGame();
    }

    @Override
    public void startGame() {
        gameControllerMachine.setState(gameControllerMachine.getGamePlayState());
    }

    @Override
    public void pauseGame() {
        // Not possible.
        System.out.println("CurrentState: MainMenuState, Game has not yet been started.");
    }

    @Override
    public void resumeGame() {
        // Not possible.
        System.out.println("CurrentState: MainMenuState, Game has not yet been started.");
    }

    @Override
    public void endGame() {
        // Not possible.
        System.out.println("CurrentState: MainMenuState, " +
                "Cannot end a game that has not yet been started.");
    }

    @Override
    public void loadGame() {

    }

    @Override
    public void saveGame() {

    }

    @Override
    public void exit() {
        // 0 means program exited normally, without errors.
        System.out.println("CurrentState: MainMenuState, Exiting.");
        System.exit(0);
    }

    @Override
    public void dragAnimation() {
        // Not possible.
        System.out.println("CurrentState: MainMenuState, " +
                "Not possible, first initialize the game.");
    }

}
