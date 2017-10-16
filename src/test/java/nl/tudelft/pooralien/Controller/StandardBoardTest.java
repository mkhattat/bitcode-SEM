package nl.tudelft.pooralien.Controller;

import nl.tu.delft.defpro.exception.NotExistingVariableException;
import nl.tudelft.item.StandardItemFactory;
import nl.tudelft.pooralien.Launcher;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StandardBoardTest extends BoardTest {
    @Before
    public void setUp() throws Exception {
        launcher = new Launcher();
        board = new StandardBoardFactory().createRandomBoard();
    }

    @Test
    public void getWidth() {
        try {
            assertEquals((int) Launcher.getGameCfg().getIntegerValueOf("maxBoardWidth"), board.getWidth());
        } catch (NotExistingVariableException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void getHeight() {
        try {
            assertEquals((int) Launcher.getGameCfg().getIntegerValueOf("maxBoardHeight"), board.getHeight());
        } catch (NotExistingVariableException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void getMinGroupSize() {
        try {
            assertEquals((int) Launcher.getGameCfg().getIntegerValueOf("minItemsInRow"), board.getMinGroupSize());
        } catch (NotExistingVariableException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void getItemFactory() {
        assertTrue(board.getItemFactory() instanceof StandardItemFactory);
    }
}