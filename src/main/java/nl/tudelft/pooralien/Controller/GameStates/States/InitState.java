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
        this.initGame();
    }

    @Override
    public void goToMainMenu() {
        throw new IllegalStateException("State: InitState, game has not yet initialized.");
    }

    @Override
    public void initGame() {
        System.out.println(gameControllerMachine.getState());
    }

    @Override
    public void startGame() {
        throw new IllegalStateException("State: InitState, cannot start the game. "
                + "Can only initialize");
    }

    @Override
    public void pauseGame() {
        throw new IllegalStateException("State: InitState, game has not yet started.");
    }

    @Override
    public void endGame() {
        throw new IllegalStateException("State: InitState, game has not yet started.");
    }

    @Override
    public void loadGame() {
        throw new IllegalStateException("State: InitState, game has not yet initialized.");
    }

    @Override
    public void saveGame() {
        throw new IllegalStateException("State: InitState, game has not yet initialized.");
    }

    @Override
    public void exit() {
        // 0 means program exited normally, without errors.
        System.out.println("CurrentState: InitState, Exiting.");
        System.exit(0);
    }

    @Override
    public void dragAnimation() {

    }


}
