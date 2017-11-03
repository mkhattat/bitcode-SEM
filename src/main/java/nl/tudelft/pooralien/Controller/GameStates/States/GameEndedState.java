package nl.tudelft.pooralien.Controller.GameStates.States;

import nl.tudelft.pooralien.Controller.Game;
import nl.tudelft.pooralien.Controller.GameStates.GameControllerMachine;
import nl.tudelft.pooralien.Controller.GameStates.State;
import nl.tudelft.pooralien.ui.HighScoreTable.HighScoreEnterNameDialog;
import nl.tudelft.pooralien.ui.HighScoreTable.HighScoreFrame;

/**
 * The GameEndedState Class.
 */
public class GameEndedState implements State {

    private GameControllerMachine gameControllerMachine;

    /**
     * The end game state of the state machine.
     * @param gameControllerMachine object, used to alter behavior.
     */
    public GameEndedState(GameControllerMachine gameControllerMachine) {
        this.gameControllerMachine = gameControllerMachine;
    }

    @Override
    public void mainMenu() {
        //NOT YET IMPLEMENTED.
    }

    @Override
    public void initGame() {
        throw new IllegalStateException("GameState: GameEndedState, initGame() is not possible.");
    }

    @Override
    public void startGame() {
        gameControllerMachine.setState(gameControllerMachine.getGameInitState());
        gameControllerMachine.initGame();
    }

    @Override
    public void pauseGame() {
        throw new IllegalStateException("GameState: GameEndedState, pauseGame() is not possible.");
    }

    @Override
    public void resumeGame() {
        throw new IllegalStateException("GameState: GameEndedState, resumeGame() is not possible.");
    }

    @Override
    public void endGame() {
        //Enter user input into
        new HighScoreEnterNameDialog(true, Game.getGame().getScoreCounter().getScore());

        //Show the highscores in a dialog
        new HighScoreFrame();


    }

    @Override
    public void loadGame() {
        //NOT YET IMPLEMENTED.
    }

    @Override
    public void saveGame() {
        //NOT YET IMPLEMENTED / NOT SURE IF POSSIBLE FROM THIS STATE.
    }

    @Override
    public void exit() {
        System.out.println("Game over!");
        System.out.println("Your score is: " + Game.getGame().getScoreCounter().getScore());
        System.exit(0);
    }

}
