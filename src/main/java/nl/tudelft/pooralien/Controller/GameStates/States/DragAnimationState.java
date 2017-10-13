package nl.tudelft.pooralien.Controller.GameStates.States;

import nl.tudelft.pooralien.Controller.GameStates.GameControllerMachine;
import nl.tudelft.pooralien.Controller.GameStates.State;

public class DragAnimationState implements State {

    GameControllerMachine gameControllerMachine;

    public DragAnimationState(GameControllerMachine gameControllerMachine) {
        this.gameControllerMachine = gameControllerMachine;
    }

    @Override
    public void MainMenu() {
        //NOT POSSIBLE.
    }

    @Override
    public void startGame() {
        //NOT POSSIBLE.
    }

    @Override
    public void pauseGame() {
        //NOT POSSIBLE.
    }

    @Override
    public void endGame() {
        //NOT POSSIBLE.
    }

    @Override
    public void loadGame() {
        //POSSIBLE.
    }

    @Override
    public void saveGame() {
        //NOT POSSIBLE.
    }

    @Override
    public void exit() {

    }

    @Override
    public void dragAnimation() {

    }

}
