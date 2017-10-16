package nl.tudelft.pooralien.Controller.GameStates.States;

import nl.tudelft.pooralien.Controller.GameStates.GameControllerMachine;
import nl.tudelft.pooralien.Controller.GameStates.State;

public class MainMenuState implements State {

    GameControllerMachine gameControllerMachine;

    public MainMenuState(GameControllerMachine gameControllerMachine) {
        this.gameControllerMachine = gameControllerMachine;
    }

    @Override
    public void goToMainMenu() {
        // Already in main menu. -> OR should this method be called in the MainMenuState constructor
        System.out.println("CurrentState: MainMenuState, ");
    }

    @Override
    public void initGame(GameControllerMachine gameControllerMachine) {
        System.out.println("CurrentState: MainMenuState, Initializing the game");
    }

    @Override
    public void startGame(GameControllerMachine gameControllerMachine) {
        // Not possible.
        System.out.println("CurrentState: MainMenuState, " +
                "Not a possible move, first initialize the game.");
    }

    @Override
    public void pauseGame(GameControllerMachine gameControllerMachine) {
        // Not possible.
        System.out.println("CurrentState: MainMenuState, Game has not yet been started.");
    }

    @Override
    public void endGame(GameControllerMachine gameControllerMachine) {
        // Not possible.
        System.out.println("CurrentState: MainMenuState, " +
                "Cannot end a game that has not yet been started.");
    }

    @Override
    public void exit() {
        // 0 means program exited normally, without errors.
        System.out.println("CurrentState: MainMenuState, Exiting.");
        System.exit(0);
    }
}
