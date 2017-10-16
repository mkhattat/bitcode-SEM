package nl.tudelft.pooralien.Controller.GameStates;

/**
 * State Interface. Used to define a contract.
 */
public interface State {

    /**
     * When the player boots the game or returns to the main screen.
     */
    public void goToMainMenu();

    /**
     * When the player starts a game. (FROM MAIN_MENU/GAME_ENDED TO INIT_GAME).
     */
    public void initGame(GameController gameController);

    /**
     * When the game is loaded/resumed by the player.
     */
    public void startGame(GameController gameController);

    /**
     * When the game is paused by the player.
     */
    public void pauseGame(GameController gameController);

    /**
     * When the game has ended by winning/losing condition.
     */
    public void endGame(GameController gameController);

    /**
     * When the game is exited.
     */
    public void exit();

}
