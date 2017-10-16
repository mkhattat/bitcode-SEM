package nl.tudelft.pooralien.Controller.GameStates.States;

import nl.tudelft.pooralien.Controller.GameStates.GameControllerMachine;
import nl.tudelft.pooralien.Controller.GameStates.State;

public class GamePausedState implements State {

    GameControllerMachine gameControllerMachine;

    public GamePausedState(GameControllerMachine gameControllerMachine) {
        this.gameControllerMachine = gameControllerMachine;
    }

    @Override
    public void goToMainMenu() {

    }

    @Override
    public void initGame() {

    }

    @Override
    public void startGame() {

    }

    @Override
    public void pauseGame() {

    }

    @Override
    public void endGame() {

    }

    @Override
    public void exit() {

    }
}
