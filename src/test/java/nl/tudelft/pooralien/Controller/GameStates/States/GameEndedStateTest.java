package nl.tudelft.pooralien.Controller.GameStates.States;

import nl.tudelft.pooralien.Controller.Game;
import nl.tudelft.pooralien.Controller.GameStates.GameControllerMachine;
import nl.tudelft.pooralien.Controller.GameStates.StateTest;
import nl.tudelft.pooralien.Launcher;
import nl.tudelft.pooralien.ui.MainScreen;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameEndedStateTest implements StateTest {

    private GameControllerMachine gameControllerMachine;

    @Before
    public void setup() {
        gameControllerMachine = new GameControllerMachine();
        gameControllerMachine.setState(gameControllerMachine.getGameEndedState());
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
                    e.getMessage(), "GameState: GameEndedState, " +
                            "initGame() is not possible.");
        }
    }

    @Override
    @Test
    public void startGameTest() {
        Game.getGame().setMainScreen(new MainScreen());
        gameControllerMachine.startGame();
        assertTrue("Expected to have changed states.",
                gameControllerMachine.equalsCurrentState(gameControllerMachine.getGamePlayState()));
    }

    @Override
    @Test
    public void pauseGameTest() {
        try {
            gameControllerMachine.pauseGame();
        } catch (IllegalStateException e) {
            assertEquals("Expected to get the following error message.",
                    e.getMessage(), "GameState: GameEndedState, " +
                            "pauseGame() is not possible.");
        }
    }

    @Override
    @Test
    public void resumeGameTest() {
        try {
            gameControllerMachine.resumeGame();
        } catch (IllegalStateException e) {
            assertEquals("Expected to get the following error message.",
                    e.getMessage(), "GameState: GameEndedState, " +
                            "resumeGame() is not possible.");
        }
    }

    @Override
    @Test
    public void endGameTest() {
        //Makes a dialog, hard to test. Not a JUnit test.
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
