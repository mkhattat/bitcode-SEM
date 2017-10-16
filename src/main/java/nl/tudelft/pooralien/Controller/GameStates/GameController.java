package nl.tudelft.pooralien.Controller.GameStates;

import nl.tudelft.pooralien.Controller.GameStates.States.InitState;
import nl.tudelft.pooralien.Controller.GameStates.States.MainMenuState;

/**
 * GameController
 */
public class GameController {

    private State state;

    /**
     * Allows GameController to be constructed in different gameStates.
     * @param state, where GameController needs to be constructed with.
     */
    public GameController(State state) {
        // If no valid State is passed then assume that the game has just been launched.
        // RIGHT NOW NO MAIN MENU EXISTS SO STRAIGHT TO GAME (WITH BOARD).
        if(state != null) {
            this.state = state;
        } else {
            try {
                this.state = new InitState();
            } catch (Exception e) {
                e.printStackTrace();
            }
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
     * Get the current state the GameController is in.
     * @return State, which is currently used.
     */
    public State getState() {
        return this.state;
    }

}
