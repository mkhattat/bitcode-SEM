package nl.tudelft.pooralien.Controller.GameStates.States;

import nl.tudelft.pooralien.Controller.GameStates.GameControllerMachine;
import nl.tudelft.pooralien.Controller.GameStates.State;

/**
 * The DragAnimationState Class.
 */
public class DragAnimationState implements State {

    private GameControllerMachine gameControllerMachine;

    /**
     * The state of the state machine when an animation is being played.
     * @param gameControllerMachine object, used to alter behavior.
     */
    public DragAnimationState(GameControllerMachine gameControllerMachine) {
        this.gameControllerMachine = gameControllerMachine;
    }

    @Override
    public void mainMenu() {
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
    public void resumeGame() {

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
