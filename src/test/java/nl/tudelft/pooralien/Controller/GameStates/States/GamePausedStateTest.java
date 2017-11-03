package nl.tudelft.pooralien.Controller.GameStates.States;

import nl.tudelft.pooralien.Controller.GameStates.GameControllerMachine;
import nl.tudelft.pooralien.Controller.GameStates.StateTest;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class GamePausedStateTest implements StateTest {

    private GameControllerMachine gameControllerMachine;

    @Before
    public void setup() {
        gameControllerMachine = new GameControllerMachine();
        gameControllerMachine.setState(gameControllerMachine.getGamePausedState());
    }

    @Override
    @Test
    public void mainMenuTest() {
        //NOT YET IMPLEMENTED.
    }

    @Override
    @Test
    public void initGameTest() {
        try {
            gameControllerMachine.initGame();
        } catch (IllegalStateException e) {
            assertEquals("Expected to get the following error message.",
                    e.getMessage(), "GameState: GamePausedState, " +
                            "initGame() is not possible.");
        }
    }

    @Override
    @Test
    public void startGameTest() {
        try {
            gameControllerMachine.startGame();
        } catch (IllegalStateException e) {
            assertEquals("Expected to get the following error message.",
                    e.getMessage(), "GameState: GamePausedState, " +
                            "startGame() is not possible.");
        }
    }

    @Override
    @Test
    public void pauseGameTest() {
        try {
            gameControllerMachine.pauseGame();
        } catch (IllegalStateException e) {
            assertEquals("Expected to get the following error message.",
                    e.getMessage(), "GameState: GamePausedState, " +
                            "pauseGame() is not possible.");
        }
    }

    @Override
    @Test
    public void resumeGameTest() {
        gameControllerMachine.resumeGame();
        assertTrue("Expected to be in the playState",
                gameControllerMachine.equalsCurrentState(gameControllerMachine.getGamePlayState()));
    }

    @Override
    @Test
    public void endGameTest() {
        try {
            gameControllerMachine.endGame();
        } catch (IllegalStateException e) {
            assertEquals("Expected to get the following error message.",
                    e.getMessage(), "GameState: GamePausedState, " +
                            "endGame() is not possible.");
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
        //NOT YET IMPLEMENTED.
    }

}
