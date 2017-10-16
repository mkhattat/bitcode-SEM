package nl.tudelft.pooralien.Controller.GameStates;

import nl.tudelft.pooralien.Controller.GameStates.States.*;

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
     */
    public GameControllerMachine() throws Exception {
        GameEndedState = new GameEndedState(this);
        GameInProgressState = new GameInProgressState(this);
        GamePausedState = new GamePausedState(this);
        InitState = new InitState(this);
        MainMenuState = new MainMenuState(this);


        // If no valid State is passed then assume that the game has just been launched.
        // RIGHT NOW NO MAIN MENU EXISTS SO STRAIGHT TO GAME (WITH BOARD).
        try {
            this.state = InitState;
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

    public void gameEnded() {
        state.endGame();
    }

}
