package nl.tudelft.pooralien.Controller.GameStates.States;

import nl.tudelft.pooralien.Controller.Game;
import nl.tudelft.pooralien.Controller.GameStates.GameControllerMachine;
import nl.tudelft.pooralien.Controller.GameStates.State;

/**
 * The GamePlayState class.
 */
public class GamePlayState implements State {

    private GameControllerMachine gameControllerMachine;

    /**
     * The state of the state machine where the game is in play.
     * @param gameControllerMachine object, used to alter behavior.
     */
    public GamePlayState(GameControllerMachine gameControllerMachine) {
        this.gameControllerMachine = gameControllerMachine;
    }

    @Override
    public void mainMenu() {
        //FIRST PAUSE/END THE GAME.
        throw new IllegalStateException("GameState: GamePlayState, mainMenu() is not possible.");
    }

    @Override
    public void initGame() {
        throw new IllegalStateException("GameState: GamePlayState, initGame() is not possible.");
    }

    @Override
    public void startGame() {
        Game.getGame().resumeGame();
    }

    @Override
    public void pauseGame() {
        Game.getGame().pauseGame();
        gameControllerMachine.setState(gameControllerMachine.getGamePausedState());
    }

    @Override
    public void resumeGame() {
        throw new IllegalStateException("GameState: GamePlayState, resumeGame() is not possible.");
    }

    @Override
    public void endGame() {
        gameControllerMachine.setState(gameControllerMachine.getGameEndedState());
        gameControllerMachine.endGame();
    }

    @Override
    public void loadGame() {
        //FIRST PAUSE/END THE GAME.
        throw new IllegalStateException("GameState: GamePlayState, loadGame() is not possible.");
    }

    @Override
    public void saveGame() {
        //FIRST PAUSE/END THE GAME.
        throw new IllegalStateException("GameState: GamePlayState, saveGame() is not possible.");
    }


    @Override
    public void exit() {

    }

    @Override
    public void dragAnimation() {
        throw new IllegalStateException("GameState: GamePlayState, "
                + "dragAnimation() is not possible.");
    }

}
