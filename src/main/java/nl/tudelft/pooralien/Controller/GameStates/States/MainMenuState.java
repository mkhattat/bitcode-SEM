package nl.tudelft.pooralien.Controller.GameStates.States;

import nl.tudelft.pooralien.Controller.GameStates.GameControllerMachine;
import nl.tudelft.pooralien.Controller.GameStates.State;
import nl.tudelft.pooralien.ui.StartupScreen;

/**
 * The MainMenuState class.
 */
public class MainMenuState implements State {

    private GameControllerMachine gameControllerMachine;

    /**
     * The Main Menu state of the state machine.
     * @param gameControllerMachine object, used to alter behavior.
     */
    public MainMenuState(GameControllerMachine gameControllerMachine) {
        this.gameControllerMachine = gameControllerMachine;
    }

    @Override
    public void mainMenu() {
        // Already in main menu. -> OR should this method be called in the MainMenuState constructor
        System.out.println("CurrentState: MainMenuState, ");
        StartupScreen startupScreen = new StartupScreen();
        startupScreen.show();

        gameControllerMachine.setState(gameControllerMachine.getGameInitState());
        gameControllerMachine.initGame();
    }

    @Override
    public void initGame() {
        throw new IllegalStateException("CurrentState: MainMenuState, "
                + "initGame() is not possible.");
    }

    @Override
    public void startGame() {
        throw new IllegalStateException("CurrentState: MainMenuState, "
                + "startGame() is not possible.");
    }

    @Override
    public void pauseGame() {
        throw new IllegalStateException("CurrentState: MainMenuState, "
                + "pauseGame() is not possible.");
    }

    @Override
    public void resumeGame() {
        throw new IllegalStateException("CurrentState: MainMenuState, "
                + "resumeGame() is not possible.");
    }

    @Override
    public void endGame() {
        throw new IllegalStateException("CurrentState: MainMenuState, "
                + "endGame() is not possible.");
    }

    @Override
    public void loadGame() {
        //NOT YET IMPLEMENTED.
    }

    @Override
    public void saveGame() {
        throw new IllegalStateException("CurrentState: MainMenuState, "
                + "saveGame() is not possible.");
    }

}
