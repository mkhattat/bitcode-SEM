package nl.tudelft.pooralien.Controller.GameStates.States;

import nl.tudelft.pooralien.Controller.GameStates.GameControllerMachine;
import nl.tudelft.pooralien.Controller.GameStates.StateTest;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;


public class GamePlayStateTest implements StateTest {

    private GameControllerMachine gameControllerMachine;

    @Before
    public void setup() {
        gameControllerMachine = new GameControllerMachine();
        gameControllerMachine.setState(gameControllerMachine.getGamePlayState());
    }

    @Override
    @Test
    public void mainMenuTest() {
        try {
            gameControllerMachine.mainMenu();
        } catch (IllegalStateException e) {
            assertEquals("Expected to get the following error message.",
                    e.getMessage(), "GameState: GamePlayState, mainMenu() is not possible.");
        }
    }

    @Override
    @Test
    public void initGameTest() {
        try {
            gameControllerMachine.initGame();
        } catch (IllegalStateException e) {
            assertEquals("Expected to get the following error message.",
                    e.getMessage(), "GameState: GamePlayState, initGame() is not possible.");
        }
    }

    @Override
    @Test
    public void startGameTest() {
        try {
            gameControllerMachine.startGame();
        } catch (IllegalStateException e) {
            assertEquals("Expected to get the following error message.",
                    e.getMessage(), "GameState: GamePlayState, startGame() is not possible.");
        }
    }

    @Override
    @Test
    public void pauseGameTest() {
//
    }

    @Override
    @Test
    public void resumeGameTest() {
        try {
            gameControllerMachine.resumeGame();
        } catch (IllegalStateException e) {
            assertEquals("Expected to get the following error message.",
                    e.getMessage(), "GameState: GamePlayState, resumeGame() is not possible.");
        }
    }

    @Override
    @Test
    public void endGameTest() {
        try {
            gameControllerMachine.endGame();
        } catch (IllegalStateException e) {
            assertEquals("Expected to get the following error message.",
                    e.getMessage(), "GameState: GamePlayState, endGame() is not possible.");
        }
    }

    @Override
    @Test
    public void loadGameTest() {
        try {
            gameControllerMachine.loadGame();
        } catch (IllegalStateException e) {
            assertEquals("Expected to get the following error message.",
                    e.getMessage(), "GameState: GamePlayState, loadGame() is not possible.");
        }
    }

    @Override
    @Test
    public void saveGameTest() {
        try {
            gameControllerMachine.saveGame();
        } catch (IllegalStateException e) {
            assertEquals("Expected to get the following error message.",
                    e.getMessage(), "GameState: GamePlayState, saveGame() is not possible.");
        }
    }

}
