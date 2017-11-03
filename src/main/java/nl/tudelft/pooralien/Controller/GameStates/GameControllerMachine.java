package nl.tudelft.pooralien.Controller.GameStates;

import nl.tudelft.pooralien.Controller.GameStates.States.GameEndedState;
import nl.tudelft.pooralien.Controller.GameStates.States.GamePlayState;
import nl.tudelft.pooralien.Controller.GameStates.States.GamePausedState;
import nl.tudelft.pooralien.Controller.GameStates.States.InitGameState;
import nl.tudelft.pooralien.Controller.GameStates.States.MainMenuState;

/**
 * The GameControllerMachine Class.
 */
public class GameControllerMachine {

    private State state;
    private State gameEndedState;
    private State gamePlayState;
    private State gamePausedState;
    private State gameInitState;
    private State mainMenuState;



    /**
     * Allows GameControllerMachine to be constructed in different gameStates.
     */
    public GameControllerMachine() {
        gameEndedState = new GameEndedState(this);
        gamePlayState = new GamePlayState(this);
        gamePausedState = new GamePausedState(this);
        gameInitState = new InitGameState(this);
        mainMenuState = new MainMenuState(this);


        // If no valid State is passed then assume that the game has just been launched.
        // RIGHT NOW NO MAIN MENU EXISTS SO STRAIGHT TO GAME (WITH BOARD).
        try {
            this.state = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Allows this object to be changed to different gameStates.
     * @param newState new game state.
     */
    public void setState(State newState) {
        if (newState != null) {
            this.state = newState;
        }
    }

    /**
     * Checks if the state is equal to the current state.
     * @param otherState state to be checked against.
     * @return true if the otherState is equal to the current state.
     */
    public boolean equalsCurrentState(State otherState) {
        return otherState.equals(this.state);
    }

    /**
     * Execute the current states goToMainMenu method.
     */
    public void mainMenu() {
        state.mainMenu();
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
     * Execute the current states resumeGame method.
     */
    public void resumeGame() {
        state.resumeGame();
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
        // 0 means program exited normally, without errors.
        System.out.println("Exiting game...");
        System.exit(0);
    }

    /**
     * @return the current state of the GameControllerMachine.
     */
    public State getCurrentState() {
        return this.state;
    }

    /**
     * @return gameEndedState, state object.
     */
    public State getGameEndedState() {
        return gameEndedState;
    }

    /**
     * @return gameInProgress, state object.
     */
    public State getGamePlayState() {
        return gamePlayState;
    }

    /**
     * @return gamePausedState, state object.
     */
    public State getGamePausedState() {
        return gamePausedState;
    }

    /**
     * @return gameInitState, state object.
     */
    public State getGameInitState() {
        return gameInitState;
    }

    /**
     * @return mainMenuState, state object.
     */
    public State getMainMenuState() {
        return mainMenuState;
    }
}
