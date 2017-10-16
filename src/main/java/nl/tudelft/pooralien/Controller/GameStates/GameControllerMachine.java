package nl.tudelft.pooralien.Controller.GameStates;

import nl.tudelft.pooralien.Controller.GameStates.States.*;

/**
 * GameControllerMachine
 */
public class GameControllerMachine {

    private State state;
    private State DragAnimationState;
    private State GameEndedState;
    private State GameInProgressState;
    private State GamePausedState;
    private State InitState;
    private State MainMenuState;



    /**
     * Allows GameControllerMachine to be constructed in different gameStates.
     */
    public GameControllerMachine() throws Exception {
        DragAnimationState = new DragAnimationState(this);
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

    public boolean equalsCurrentState(Object O) {
        if(!(O instanceof State)) {
            return false;
        }
        State otherState = (State) O;

        return this.state.equals(otherState);

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

    /**
     * Execute the current states goToMainMenu method.
     */
    public void goToMainMenu() {
        state.goToMainMenu();
    }

    /**
     * Execute the current states initGame method.
     */
    public void initGame() {
        state.initGame();
    }

    /**
     * Execute the current states startGame method.
     */
    public void startGame() {
        state.startGame();
    }

    /**
     * Execute the current states pauseGame method.
     */
    public void pauseGame() {
        state.pauseGame();
    }

    /**
     * Execute the current states endGame method.
     */
    public void endGame() {
        state.endGame();
    }

    /**
     * Execute the current states loadGame method.
     */
    public void loadGame() {
        state.loadGame();
    }

    /**
     * Execute the current states saveGame method.
     */
    public void saveGame() {
        state.saveGame();
    }

    /**
     * Execute the current states exitGame method.
     */
    public void exitGame() {
        state.exit();
    }

    /**
     * Execute the current states dragAnimation method.
     */
    public void dragAnimation() {
        state.dragAnimation();
    }

    /**
     * @return DragAnimationState, state object.
     */
    public State DragAnimationState() {
        return DragAnimationState;
    }

    /**
     * @return GameEndedState, state object.
     */
    public State getGameEndedState() {
        return GameEndedState;
    }

    /**
     * @return GameInProgress, state object.
     */
    public State getGameInProgressState() {
        return GameInProgressState;
    }

    /**
     * @return GamePausedState, state object.
     */
    public State getGamePausedState() {
        return GamePausedState;
    }

    /**
     * @return InitState, state object.
     */
    public State getInitState() {
        return InitState;
    }

    /**
     * @return MainMenuState, state object.
     */
    public State getMainMenuState() {
        return MainMenuState;
    }
}
