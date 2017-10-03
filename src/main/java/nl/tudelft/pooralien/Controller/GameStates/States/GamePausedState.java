package nl.tudelft.pooralien.Controller.GameStates.States;

import nl.tudelft.pooralien.Controller.Game;
import nl.tudelft.pooralien.Controller.GameStates.GameControllerMachine;
import nl.tudelft.pooralien.Controller.GameStates.State;

/**
 * The GamePausedState Class.
 */
public class GamePausedState implements State {

    private GameControllerMachine gameControllerMachine;

    /**
     * The state where the game is paused of the state machine.
     * @param gameControllerMachine object, used to alter behavior.
     */
    public GamePausedState(GameControllerMachine gameControllerMachine) {
        this.gameControllerMachine = gameControllerMachine;
    }

    @Override
    public void mainMenu() {
        //NOT YET IMPLEMENTED.
    }

    @Override
    public void initGame() {
        throw new IllegalStateException("GameState: GamePausedState, initGame() is not possible.");
    }

    @Override
    public void startGame() {
        throw new IllegalStateException("GameState: GamePausedState, startGame() is not possible.");
    }

    @Override
    public void pauseGame() {
        throw new IllegalStateException("GameState: GamePausedState, pauseGame() is not possible.");
    }

    @Override
    public void resumeGame() {
        gameControllerMachine.setState(gameControllerMachine.getGamePlayState());

        //Needed to notify observers.
        Game.getGame().notifyObservers();
    }

    @Override
    public void endGame() {

    }

    @Override
    public void loadGame() {
        //NOT YET IMPLEMENTED.
    }

    @Override
    public void saveGame() {
        //NOT YET IMPLEMENTED.
    }

    @Override
    public void exit() {

    }

}
