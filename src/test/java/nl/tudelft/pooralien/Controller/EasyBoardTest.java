package nl.tudelft.pooralien.Controller;


import nl.tu.delft.defpro.exception.NotExistingVariableException;
import nl.tudelft.item.EasyItemFactory;
import nl.tudelft.item.Item;
import nl.tudelft.item.StandardItemFactory;
import nl.tudelft.pooralien.Launcher;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class EasyBoardTest extends BoardTest {
    @Before
    public void setUp() throws Exception {
        launcher = new Launcher();
        board = new EasyBoardFactory().createRandomBoard();
    }

    @Test
    public void getWidth() {
        assertEquals(10, board.getWidth());
    }

    @Test
    public void getHeight() {
        assertEquals(10, board.getHeight());
    }

    @Test
    public void getMinGroupSize() {
        assertEquals(3, board.getMinGroupSize());
    }

    @Test
    public void getItemFactory() {
        assertTrue(board.getItemFactory() instanceof EasyItemFactory);
    }


    @Test
    public void setItem() {
        Item i = board.getItemFactory().createRandomItem();
        assertTrue(board.setItem(i, 0, 0));
        assertEquals(i.getSprite(), board.getItem(0, 0).getSprite());
    }

    @Test
    public void getItem() {
        assertNotNull(board.getItem(0,0));
    }

    @Test
    public void getItemXOOBPos() {
        try {
            board.getItem(board.getWidth(), 0);
        } catch (IllegalArgumentException e) {
            assertEquals("The provided X-coordinate " + board.getWidth()
                    + " is outside of the board's bounds [0-"
                    + board.getWidth() + "].", e.getMessage());
        }
    }

    @Test
    public void getItemXOOBNeg() {
        try {
            board.getItem(-1, 0);
        } catch (IllegalArgumentException e) {
            assertEquals("The provided X-coordinate " + -1
                    + " is outside of the board's bounds [0-"
                    + board.getWidth() + "].", e.getMessage());
        }
    }

    @Test
    public void getItemYOOBPos() {
        try {
            board.getItem(0, board.getHeight());
        } catch (IllegalArgumentException e) {
            assertEquals("The provided Y-coordinate " + board.getHeight()
                    + " is outside of the board's bounds [0-"
                    + board.getHeight() + "].", e.getMessage());
        }
    }

    @Test
    public void getItemYOOBNeg() {
        try {
            board.getItem(0, -1);
        } catch (IllegalArgumentException e) {
            assertEquals("The provided Y-coordinate " + -1
                    + " is outside of the board's bounds [0-"
                    + board.getHeight() + "].", e.getMessage());
        }
    }

    @Test
    public void remove() {
        board.remove(0, 0);
        assertNotNull(board.getItem(0, 0));
    }

    @Test
    public void removeGroups() {
        assertFalse(board.removeGroups());
    }

    @Test
    public void findGroup() {
        ArrayList<Point> l = board.findGroup(0,0);
        assertTrue(l.size() < board.getMinGroupSize());
    }

    @Test
    public void removeGroup() {
        ArrayList<Point> l = new ArrayList<>();
        l.add(new Point(0,0));
        board.removeGroup(l);
        assertNotNull(board.getItem(0,0));
    }
}