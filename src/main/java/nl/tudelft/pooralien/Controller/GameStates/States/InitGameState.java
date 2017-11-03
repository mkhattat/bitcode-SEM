package nl.tudelft.pooralien.Controller.GameStates.States;

import nl.tudelft.pooralien.Controller.Game;
import nl.tudelft.pooralien.Controller.GameStates.GameControllerMachine;
import nl.tudelft.pooralien.Controller.GameStates.State;

/**
 * The InitGameState Class.
 */
public class InitGameState implements State {

    private GameControllerMachine gameControllerMachine;

    /**
     * The state where the game is paused of the state machine.
     * @param gameControllerMachine object, used to alter behavior.
     */
    public InitGameState(GameControllerMachine gameControllerMachine) {
        this.gameControllerMachine = gameControllerMachine;
    }

    @Override
    public void mainMenu() {
        throw new IllegalStateException("GameState: InitGameState, mainMenu() is not possible.");
    }

    @Override
    public void initGame() {
        //Disables user input
        Game.getGame().notifyObservers();
        //Reset score
        Game.getGame().getScoreCounter().setScore(0);
        //Makes a new board and refresh the GUI.
        Game.getGame().nextBoard();

        gameControllerMachine.setState(gameControllerMachine.getGamePlayState());
        gameControllerMachine.startGame();
    }

    @Override
    public void startGame() {
        throw new IllegalStateException("GameState: InitGameState, startGame() is not possible.");
    }

    @Override
    public void pauseGame() {
        throw new IllegalStateException("GameState: InitGameState, pauseGame() is not possible.");
    }

    @Override
    public void resumeGame() {
        throw new IllegalStateException("GameState: InitGameState, resumeGame() is not possible.");
    }

    @Override
    public void endGame() {
        throw new IllegalStateException("GameState: InitGameState, endGame() is not possible.");
    }

    @Override
    public void loadGame() {
        throw new IllegalStateException("GameState: InitGameState, loadGame() is not possible.");
    }

    @Override
    public void saveGame() {
        throw new IllegalStateException("GameState: InitGameState, saveGame() is not possible.");
    }

}
