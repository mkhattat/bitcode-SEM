package nl.tudelft.item;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class StandardItemFactoryTest {
    private StandardItemFactory iFactory;

    @Before
    public void setUp() {
        iFactory = new StandardItemFactory();
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
    public void getUpperBound() {
        assertEquals(iFactory.getItemCount(), iFactory.getUpperBound());
    }

    @Test
    public void setUpperBoundNormal() {
        int temp = 8;
        iFactory.setUpperBound(temp);
        assertEquals(temp, iFactory.getUpperBound());
    }

    @Test
    public void setUpperBoundNegativeException() {
        int temp = -5;
        try {
            iFactory.setUpperBound(temp);
        } catch (IllegalArgumentException e) {
            assertEquals("The upper bound may not be negative."
                    + "\nThe new upper bound: " + temp + ".", e.getMessage());
        }
    }

    @Test
    public void setUpperBoundNegativeValue() {
        int temp = iFactory.getUpperBound();
        try {
            iFactory.setUpperBound(-9);
        } catch (IllegalArgumentException e) {
            assertEquals(temp, iFactory.getUpperBound());
        }
    }

    @Test
    public void setUpperBoundLowerException() {
        int temp = iFactory.getLowerBound();
        try {
            iFactory.setUpperBound(temp);
        } catch (IllegalArgumentException e) {
            assertEquals("The upper bound may not be smaller than or equal to the lower bound."
                    + "\nNew upper bound: " + temp
                    + ", current lower bound: " + iFactory.getLowerBound() + ".", e.getMessage());
        }
    }

    @Test
    public void setUpperBoundLowerValue() {
        int temp = iFactory.getUpperBound();
        try {
            iFactory.setUpperBound(iFactory.getLowerBound());
        } catch (IllegalArgumentException e) {
            assertEquals(temp, iFactory.getUpperBound());
        }
    }

    @Test
    public void getIntGenNotNull() {
        assertNotNull(iFactory.getIntGen());
    }

    @Test
    public void createRandomItemAxe() {
        iFactory.setUpperBound(1);
        assertTrue(iFactory.createRandomItem() instanceof Axe);
    }

    @Test
    public void createRandomItemBone() {
        iFactory.setLowerBound(1);
        iFactory.setUpperBound(2);
        assertTrue(iFactory.createRandomItem() instanceof Bone);
    }

    @Test
    public void createRandomItemEye() {
        iFactory.setLowerBound(2);
        iFactory.setUpperBound(3);
        assertTrue(iFactory.createRandomItem() instanceof Eye);
    }

    @Test
    public void createRandomItemLeaf() {
        iFactory.setLowerBound(3);
        iFactory.setUpperBound(4);
        assertTrue(iFactory.createRandomItem() instanceof Leaf);
    }

    @Test
    public void createRandomItemMask() {
        iFactory.setLowerBound(4);
        iFactory.setUpperBound(5);
        assertTrue(iFactory.createRandomItem() instanceof Mask);
    }

    @Test
    public void createRandomItemMouth() {
        iFactory.setLowerBound(5);
        iFactory.setUpperBound(6);
        assertTrue(iFactory.createRandomItem() instanceof Mouth);
    }

    @Test
    public void createRandomItemSun() {
        iFactory.setLowerBound(6);
        assertTrue(iFactory.createRandomItem() instanceof Sun);
    }

    @Test
    public void createItemAxe() {
        assertTrue(iFactory.createItem("Axe") instanceof Axe);
    }

    @Test
    public void createItemBone() {
        assertTrue(iFactory.createItem("Bone") instanceof Bone);
    }

    @Test
    public void createItemEye() {
        assertTrue(iFactory.createItem("Eye") instanceof Eye);
    }

    @Test
    public void createItemLeaf() {
        assertTrue(iFactory.createItem("Leaf") instanceof Leaf);
    }

    @Test
    public void createItemMask() {
        assertTrue(iFactory.createItem("Mask") instanceof Mask);
    }

    @Test
    public void createItemMouth() {
        assertTrue(iFactory.createItem("Mouth") instanceof Mouth);
    }

    @Test
    public void createItemSun() {
        assertTrue(iFactory.createItem("Sun") instanceof Sun);
    }

    @Test
    public void createItemIllegalArgument() {
        String temp = "item";
        try {
            iFactory.createItem(temp);
        } catch (IllegalArgumentException e) {
            assertEquals("The provided item name: " + temp
                    + "\nDoes not match any of the following: "
                    + "axe, bone, eye, leaf, mask, mouth or sun.", e.getMessage());
        }
    }
}