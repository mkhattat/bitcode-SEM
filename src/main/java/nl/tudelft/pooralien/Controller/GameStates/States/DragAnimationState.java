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
        throw new IllegalStateException("GameState: DragAnimationState, "
                + "mainMenu() is not possible.");
    }

    @Override
    public void initGame() {
        throw new IllegalStateException("GameState: DragAnimationState, "
                + "initGame() is not possible.");
    }

    @Override
    public void startGame() {
        throw new IllegalStateException("GameState: DragAnimationState, "
                + "startGame() is not possible.");
    }

    @Override
    public void pauseGame() {
        //NOT YET IMPLEMENTED. (User input for the board is already disabled in this state).
    }

    @Override
    public void resumeGame() {
        //FIRST PAUSE THE GAME.
        throw new IllegalStateException("GameState: DragAnimationState, "
                + "resumeGame() is not possible.");
    }

    @Override
    public void endGame() {
        throw new IllegalStateException("GameState: DragAnimationState, "
                + "endGame() is not possible.");
    }

    @Override
    public void loadGame() {
        //FIRST PAUSE THE GAME.
        throw new IllegalStateException("GameState: DragAnimationState, "
                + "loadGame() is not possible.");
    }

    @Override
    public void saveGame() {
        //FIRST PAUSE THE GAME.
        throw new IllegalStateException("GameState: DragAnimationState, "
                + "saveGame() is not possible.");
    }

    @Override
    public void exit() {
        //NOT YET IMPLEMENTED -> Pop up warning screen if the player is sure he wants to leave
        //                          without saving?
    }

    @Override
    public void dragAnimation() {
        //NOT YET IMPLEMENTED.
    }

}
