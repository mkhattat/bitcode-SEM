package nl.tudelft.pooralien.Controller.GameStates.States;

import nl.tudelft.pooralien.Controller.GameStates.GameControllerMachine;
import nl.tudelft.pooralien.Controller.GameStates.StateTest;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class InitGameStateTest implements StateTest {

    private GameControllerMachine gameControllerMachine;

    @Before
    public void setup() {
        gameControllerMachine = new GameControllerMachine();
        gameControllerMachine.setState(gameControllerMachine.getGameInitState());
    }

    @Override
    @Test
    public void mainMenuTest() {
        try {
            gameControllerMachine.mainMenu();
        } catch (IllegalStateException e) {
            assertEquals("Expected error message to be the same",
                    e.getMessage(), "GameState: InitGameState, mainMenu() is not possible.");
        }
    }

    @Override
    @Test
    public void initGameTest() {
        gameControllerMachine.initGame();

        assertTrue("Expected to be equal",
                gameControllerMachine.equalsCurrentState(gameControllerMachine.getGamePlayState()));
    }

    @Override
    @Test
    public void startGameTest() {
        try {
            gameControllerMachine.startGame();
        } catch (IllegalStateException e) {
            assertEquals("Expected error message to be the same",
                    e.getMessage(), "GameState: InitGameState, startGame() is not possible.");
        }
    }

    @Override
    @Test
    public void pauseGameTest() {
        try {
            gameControllerMachine.pauseGame();
        } catch (IllegalStateException e) {
            assertEquals("Expected error message to be the same",
                    e.getMessage(), "GameState: InitGameState, pauseGame() is not possible.");
        }
    }

    @Override
    @Test
    public void resumeGameTest() {
        try {
            gameControllerMachine.resumeGame();
        } catch (IllegalStateException e) {
            assertEquals("Expected error message to be the same",
                    e.getMessage(), "GameState: InitGameState, resumeGame() is not possible.");
        }
    }

    @Override
    @Test
    public void endGameTest() {
        try {
            gameControllerMachine.endGame();
        } catch (IllegalStateException e) {
            assertEquals("Expected error message to be the same",
                    e.getMessage(), "GameState: InitGameState, endGame() is not possible.");
        }
    }

    @Override
    @Test
    public void loadGameTest() {
        try {
            gameControllerMachine.loadGame();
        } catch (IllegalStateException e) {
            assertEquals("Expected error message to be the same",
                    e.getMessage(), "GameState: InitGameState, loadGame() is not possible.");
        }
    }

    @Override
    @Test
    public void saveGameTest() {
        try {
            gameControllerMachine.saveGame();
        } catch (IllegalStateException e) {
            assertEquals("Expected error message to be the same",
                    e.getMessage(), "GameState: InitGameState, saveGame() is not possible.");
        }
    }

}
