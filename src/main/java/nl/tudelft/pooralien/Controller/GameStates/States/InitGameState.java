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
        //Not possible.
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
        //Not possible.
    }

    @Override
    public void pauseGame() {
        //Not possible.
    }

    @Override
    public void resumeGame() {
        //Not possible.
    }

    @Override
    public void endGame() {
        //Not possible.
    }

    @Override
    public void loadGame() {
        //Not possible.
    }

    @Override
    public void saveGame() {
        //Not possible.
    }

    @Override
    public void exit() {

    }
}
