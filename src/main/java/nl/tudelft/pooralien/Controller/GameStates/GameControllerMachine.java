package nl.tudelft.pooralien.Controller.GameStates;

import nl.tudelft.pooralien.Controller.GameStates.States.InitState;
import nl.tudelft.pooralien.Controller.GameStates.States.MainMenuState;

/**
 * GameControllerMachine
 */
public class GameControllerMachine {

    private State state;
    private State GameEndedState;
    private State GameInProgressState;
    private State GamePausedState;
    private State InitState;
    private State MainMenuState;



    /**
     * Allows GameControllerMachine to be constructed in different gameStates.
     * @param state, where GameControllerMachine needs to be constructed with.
     */
    public GameControllerMachine() {
        // If no valid State is passed then assume that the game has just been launched.
        // RIGHT NOW NO MAIN MENU EXISTS SO STRAIGHT TO GAME (WITH BOARD).
        try {
            this.state = new InitState(null);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * Allows this object to be changed to different gameStates.
     * @param newState, new game state.
     */
    public void setState(State newState) {
        if(newState != null) {
            this.state = newState;
        }
    }

    /**
     * Get the current state the GameControllerMachine is in.
     * @return State, which is currently used.
     */
    public State getState() {
        return this.state;
    }

}
