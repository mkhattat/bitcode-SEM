package nl.tudelft.item;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class StandardItemFactoryTest {
    private StandardItemFactory iFactory;

    @Before
    public void setUp() {
        iFactory = new StandardItemFactory();
    }

    @Test
    public void getItemCount() {
        assertEquals(7, iFactory.getItemCount());
    }

    @Test
    public void createRandomItem() {
        assertNotNull(iFactory.createRandomItem());
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