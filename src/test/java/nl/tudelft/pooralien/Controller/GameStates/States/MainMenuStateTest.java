package nl.tudelft.pooralien.Controller.GameStates.States;


import nl.tudelft.pooralien.Controller.Game;
import nl.tudelft.pooralien.Controller.GameStates.GameControllerMachine;
import nl.tudelft.pooralien.Controller.GameStates.StateTest;
import nl.tudelft.pooralien.ui.MainScreen;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class MainMenuStateTest implements StateTest {

    private GameControllerMachine gameControllerMachine;

    @Before
    public void setup() {
        gameControllerMachine = new GameControllerMachine();
        gameControllerMachine.setState(gameControllerMachine.getMainMenuState());
    }

    @Override
    @Test
    public void mainMenuTest() {
        //Test is no longer a JUnit test.
    }

    @Override
    @Test
    public void initGameTest() {
        try {
            gameControllerMachine.initGame();
        } catch (IllegalStateException e) {
            assertEquals("Expected error message to be the same",
                    e.getMessage(), "CurrentState: MainMenuState, initGame() is not possible.");
        }
    }

    @Override
    @Test
    public void startGameTest() {
        try {
            gameControllerMachine.startGame();
        } catch (IllegalStateException e) {
            assertEquals("Expected error message to be the same",
                    e.getMessage(), "CurrentState: MainMenuState, startGame() is not possible.");
        }
    }

    @Override
    @Test
    public void pauseGameTest() {
        try {
            gameControllerMachine.pauseGame();
        } catch (IllegalStateException e) {
            assertEquals("Expected error message to be the same",
                    e.getMessage(), "CurrentState: MainMenuState, pauseGame() is not possible.");
        }
    }

    @Override
    @Test
    public void resumeGameTest() {
        try {
            gameControllerMachine.resumeGame();
        } catch (IllegalStateException e) {
            assertEquals("Expected error message to be the same",
                    e.getMessage(), "CurrentState: MainMenuState, resumeGame() is not possible.");
        }
    }

    @Override
    @Test
    public void endGameTest() {
        try {
            gameControllerMachine.endGame();
        } catch (IllegalStateException e) {
            assertEquals("Expected error message to be the same",
                    e.getMessage(), "CurrentState: MainMenuState, endGame() is not possible.");
        }
    }

    @Override
    @Test
    public void loadGameTest() {
        //NOT YET IMPLEMENTED.
    }

    @Override
    @Test
    public void saveGameTest() {
        try {
            gameControllerMachine.saveGame();
        } catch (IllegalStateException e) {
            assertEquals("Expected error message to be the same",
                    e.getMessage(), "CurrentState: MainMenuState, saveGame() is not possible.");
        }
    }

}
