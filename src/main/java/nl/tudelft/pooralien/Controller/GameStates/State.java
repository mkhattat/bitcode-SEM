package nl.tudelft.pooralien.Controller.GameStates;

/**
 * State Interface. Used to define a contract.
 */
public interface State {

    /**
     * When the player boots the game or returns to the main screen.
     */
    void mainMenu();

    /**
     * When the player presses play, initialize the game.
     */
    void initGame();

    /**
     * When the game is loaded by the player.
     */
    void startGame();

    /**
     * When the game is paused by the player.
     */
    void pauseGame();

    /**
     * When the game is resumed by the player.
     */
    void resumeGame();

    /**
     * When the game has ended by winning/losing condition.
     */
    void endGame();

    /**
     * When the game is loaded.
     */
    void loadGame();

    /**
     * When the game is saved.
     */
    void saveGame();

    /**
     * When the game is exited.
     */
    void exit();


}
