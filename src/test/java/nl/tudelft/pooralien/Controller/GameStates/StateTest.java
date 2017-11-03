package nl.tudelft.pooralien.Controller.GameStates;

/**
 * State Test Interface. Used to define a contract.
 */
public interface StateTest {
    /**
     * When the player boots the game or returns to the main screen.
     */
    void mainMenuTest();

    /**
     * When the player presses play, initialize the game.
     */
    void initGameTest();

    /**
     * When the game is loaded by the player.
     */
    void startGameTest();

    /**
     * When the game is paused by the player.
     */
    void pauseGameTest();

    /**
     * When the game is resumed by the player.
     */
    void resumeGameTest();

    /**
     * When the game has ended by winning/losing condition.
     */
    void endGameTest();

    /**
     * When the game is loaded.
     */
    void loadGameTest();

    /**
     * When the game is saved.
     */
    void saveGameTest();

}
