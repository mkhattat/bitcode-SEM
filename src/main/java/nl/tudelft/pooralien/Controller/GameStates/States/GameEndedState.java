package nl.tudelft.pooralien.Controller.GameStates.States;

import nl.tudelft.pooralien.Controller.Game;
import nl.tudelft.pooralien.Controller.GameStates.GameControllerMachine;
import nl.tudelft.pooralien.Controller.GameStates.State;
import nl.tudelft.pooralien.ui.HighScoreTable.HighScoreEnterNameDialog;

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

    }

    @Override
    public void startGame() {
        gameControllerMachine.setState(gameControllerMachine.getGamePlayState());
        gameControllerMachine.startGame();
    }

    @Override
    public void pauseGame() {
        // NOT POSSIBLE
    }

    @Override
    public void resumeGame() {

    }

    @Override
    public void endGame() {
        //Enter user input into
        HighScoreEnterNameDialog highScoreEnterNameDialog =
                new HighScoreEnterNameDialog(true, Game.getGame().getScoreCounter().getScore());

        if (Game.getGame().getBackgroundTileCatalog().size() == 0) {
            startGame();

        } else {
            //Placeholder until the required
            //game state functionality is in place.
            System.out.println("Game over!");
            System.out.println("Your score is: " + Game.getGame().getScoreCounter().getScore());
            System.exit(0);
        }
    }

    @Override
    public void loadGame() {

    }

    @Override
    public void saveGame() {

    }

    @Override
    public void exit() {

    }

    @Override
    public void dragAnimation() {

    }

}
