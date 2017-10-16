package nl.tudelft.pooralien.Controller.GameStates.States;

import nl.tudelft.pooralien.Controller.GameStates.GameController;
import nl.tudelft.pooralien.Controller.GameStates.State;

public class MainMenuState implements State {
    @Override
    public void goToMainMenu() {
        // Already in main menu. -> OR should this method be called in the MainMenuState constructor
        System.out.println("CurrentState: MainMenuState, ");
    }

    @Override
    public void initGame(GameController gameController) {
        System.out.println("CurrentState: MainMenuState, Initializing the game");
    }

    @Override
    public void startGame(GameController gameController) {
        // Not possible.
        System.out.println("CurrentState: MainMenuState, " +
                "Not a possible move, first initialize the game.");
    }

    @Override
    public void pauseGame(GameController gameController) {
        // Not possible.
        System.out.println("CurrentState: MainMenuState, Game has not yet been started.");
    }

    @Override
    public void endGame(GameController gameController) {
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
