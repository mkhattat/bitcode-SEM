package nl.tudelft.item;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class ItemFactoryTest {
    private ItemFactory iFactory;

    @Before
    public void setUp() {
        iFactory = new ItemFactory();
    }

    @Test
    public void resetRandomLowerBound() {
        iFactory.resetRandom();
        assertEquals(0, iFactory.getLowerBound());
    }

    @Test
    public void resetRandomUpperBound() {
        iFactory.resetRandom();
        assertEquals(iFactory.getItemCount(), iFactory.getUpperBound());
    }

    @Test
    public void resetRandomIntGen() {
        Random prev = iFactory.getIntGen();
        iFactory.resetRandom();
        assertNotEquals(prev, iFactory.getIntGen());
    }

    @Test
    public void getItemCount() {
        assertEquals(7, iFactory.getItemCount());
    }

    @Test
    public void getLowerBound() {
        assertEquals(0, iFactory.getLowerBound());
    }

    @Test
    public void setLowerBoundNormal() {
        int temp = 1;
        iFactory.setLowerBound(temp);
        assertEquals(temp, iFactory.getLowerBound());
    }

    @Test
    public void setLowerBoundNegativeException() {
        int temp = -1;
        try {
            iFactory.setLowerBound(temp);
        } catch (IllegalArgumentException e) {
            assertEquals("The lower bound may not be negative."
                    + "\nThe new lower bound: " + temp + ".", e.getMessage());
        }
    }

    @Test
    public void setLowerBoundNegativeValue() {
        int temp = iFactory.getLowerBound();
        try {
            iFactory.setLowerBound(-1);
        } catch (IllegalArgumentException e) {
            assertEquals(temp, iFactory.getLowerBound());
        }
    }

    @Test
    public void setLowerBoundUpperException() {
        int temp = iFactory.getUpperBound();
        try {
            iFactory.setLowerBound(temp);
        } catch (IllegalArgumentException e) {
            assertEquals("The lower bound may not be larger than or equal to the upper bound."
                    + "\nCurrent upper bound: " + iFactory.getUpperBound()
                    + ", new lower bound: " + temp + ".", e.getMessage());
        }
    }

    @Test
    public void setLowerBoundUpperValue() {
        int temp = iFactory.getLowerBound();
        try {
            iFactory.setLowerBound(iFactory.getUpperBound());
        } catch (IllegalArgumentException e) {
            assertEquals(temp, iFactory.getLowerBound());
        }
    }

    @Test
    public void createRandomItemAxe() {

    }

    @Test
    public void createRandomItemBone() {
    }

    @Test
    public void createRandomItemEye() {
    }

    @Test
    public void createRandomItemLeaf() {
    }

    @Test
    public void createRandomItemMask() {
    }

    @Test
    public void createRandomItemMouth() {
    }

    @Test
    public void createRandomItemSun() {
    }

    @Test
    public void createItemAxe() {

    }

    @Test
    public void createItemBone() {
    }

    @Test
    public void createItemEye() {
    }

    @Test
    public void createItemLeaf() {
    }

    @Test
    public void createItemMask() {
    }

    @Test
    public void createItemMouth() {
    }

    @Test
    public void createItemSun() {
    }

    @Test
    public void createItemIllegalArgument() {
    }
}