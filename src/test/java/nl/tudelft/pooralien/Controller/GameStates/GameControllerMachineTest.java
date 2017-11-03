package nl.tudelft.pooralien.Controller.GameStates;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GameControllerMachineTest {

    GameControllerMachine gameControllerMachine;

    @Before
    public void setup() {
        gameControllerMachine = new GameControllerMachine();
    }

    @Test
    public void setGamePlayState() {
        gameControllerMachine.setState(gameControllerMachine.getGamePlayState());
        assertTrue("Expected the state machine to be in play",
                gameControllerMachine.equalsCurrentState(gameControllerMachine.getGamePlayState()));
    }

    @Test
    public void cantSetStateToNull() {
        gameControllerMachine.setState(gameControllerMachine.getGamePlayState());

        gameControllerMachine.setState(null);

        assertTrue("Expected the state machine to be in play",
                gameControllerMachine.equalsCurrentState(gameControllerMachine.getGamePlayState()));
    }
}
