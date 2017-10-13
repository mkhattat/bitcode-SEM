package nl.tudelft.pooralien.Controller.GameStates;

/**
 * State Interface. Used to define a contract.
 */
public interface State {

    /**
     * When the player boots the game or returns to the main screen.
     */
    public void MainMenu();

    /**
     * When the game is loaded by the player.
     */
    public void startGame();

    /**
     * When the game is paused/resumed by the player.
     */
    public void pauseGame();

    /**
     * When the game has ended by winning/losing condition.
     */
    public void endGame();

    /**
     * When the game is loaded.
     */
    public void loadGame();

    /**
     * When the game is saved.
     */
    public void saveGame();

    /**
     * When the game is exited.
     */
    public void exit();

    /**
     * When the player has removed tiles from the board.
     */
    public void dragAnimation();


}
