package nl.tudelft.pooralien.Controller.GameStates.States;

import nl.tudelft.pooralien.Controller.GameStates.GameControllerMachine;
import nl.tudelft.pooralien.Controller.GameStates.State;

public class GameEndedState implements State {

    GameControllerMachine gameControllerMachine;

    public GameEndedState(GameControllerMachine gameControllerMachine) {
        this.gameControllerMachine = gameControllerMachine;
    }

    @Override
    public void goToMainMenu() {

    }

    @Override
    public void initGame(GameControllerMachine gameControllerMachine) {

    }

    @Override
    public void startGame(GameControllerMachine gameControllerMachine) {

    }

    @Override
    public void pauseGame(GameControllerMachine gameControllerMachine) {

    }

    @Override
    public void endGame(GameControllerMachine gameControllerMachine) {

    }

    @Override
    public void exit() {

    }
}
