package nl.tudelft.pooralien.Controller;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EasyBoardFactoryTest {
    private BoardFactory bFactory;

    @Before
    public void setUp() throws Exception {
        bFactory = new EasyBoardFactory();
    }

    @Test
    public void createBoard() throws Exception {
        String layout = "AXE BONE EYE LEAF MASK MOUTH LEAF AXE BONE EYE "
                + "LEAF MASK MOUTH EYE AXE BONE EYE LEAF MASK MOUTH "
                + "EYE AXE BONE EYE LEAF MASK MOUTH LEAF AXE BONE "
                + "EYE LEAF MASK MOUTH LEAF AXE BONE EYE LEAF MASK "
                + "MOUTH LEAF AXE BONE EYE LEAF MASK MOUTH LEAF AXE "
                + "BONE EYE LEAF MASK MOUTH LEAF AXE BONE EYE LEAF "
                + "MASK MOUTH LEAF AXE BONE EYE LEAF MASK MOUTH LEAF "
                + "AXE BONE EYE LEAF MASK MOUTH LEAF AXE BONE EYE "
                + "LEAF MASK MOUTH LEAF AXE BONE EYE LEAF MASK MOUTH "
                + "LEAF AXE BONE EYE LEAF MASK MOUTH LEAF AXE BONE ";
        Board board = bFactory.createBoard(layout);

        assertTrue(board instanceof EasyBoard);
        assertEquals(layout, board.toString());
    }

    @Test
    public void createBoardNotEnoughItems() throws Exception {
        try {
            bFactory.createBoard("Axe Eye ");
        } catch (IllegalArgumentException e) {
            assertEquals(
                    "The provided board layout contained "
                            + 2 + " items, but should contain "
                            + 100 + " items.",
                    e.getMessage()
            );
        }
    }

    @Test
    public void createBoardTooManyItems() throws Exception {
        try {
            bFactory.createBoard("Axe Eye Bone Mask Bone Bone Leaf Mouth Mouth Leaf "
                    + "Axe Eye Bone Mask Bone Bone Leaf Mouth Mouth Leaf "
                    + "Axe Eye Bone Mask Bone Bone Leaf Mouth Mouth Leaf "
                    + "Axe Eye Bone Mask Bone Bone Leaf Mouth Mouth Leaf "
                    + "Axe Eye Bone Mask Bone Bone Leaf Mouth Mouth Leaf "
                    + "Axe Eye Bone Mask Bone Bone Leaf Mouth Mouth Leaf "
                    + "Axe Eye Bone Mask Bone Bone Leaf Mouth Mouth Leaf "
                    + "Axe Eye Bone Mask Bone Bone Leaf Mouth Mouth Leaf "
                    + "Axe Eye Bone Mask Bone Bone Leaf Mouth Mouth Leaf "
                    + "Axe Eye Bone Mask Bone Bone Leaf Mouth Mouth Leaf "
                    + "Leaf ");
        } catch (IllegalArgumentException e) {
            assertEquals(
                    "The provided board layout contained "
                            + 101 + " items, but should contain "
                            + 100 + " items.",
                    e.getMessage()
            );
        }
    }

    @Test
    public void createBoardIllegalItem() throws Exception {
        try {
            bFactory.createBoard("Axe Eye Bone Mask Bone Bone Sun Mouth Mouth Leaf "
                    + "Axe Eye Bone Mask Bone Bone Sun Mouth Mouth Leaf "
                    + "Axe Eye Bone Mask Bone Bone Sun Mouth Mouth Leaf "
                    + "Axe Eye Bone Mask Bone Bone Sun Mouth Mouth Leaf "
                    + "Axe Eye Bone Mask Bone Bone Sun Mouth Mouth Leaf "
                    + "Axe Eye Bone Mask Bone Bone Sun Mouth Mouth Leaf "
                    + "Axe Eye Bone Mask Bone Bone Sun Mouth Mouth Leaf "
                    + "Axe Eye Bone Mask Bone Bone Sun Mouth Mouth Leaf "
                    + "Axe Eye Bone Mask Bone Bone Sun Mouth Mouth Leaf "
                    + "Axe Eye Bone Mask Bone Bone Sun Mouth Mouth Moon "
            );
        } catch (IllegalArgumentException e) {
            assertEquals(
                    "The provided item name: Sun"
                            + "\nDoes not match any of the following: "
                            + "axe, bone, eye, leaf, mask or mouth.",
                    e.getMessage()
            );
        }
    }

    @Test
    public void createRandomBoard() throws Exception {
        Board board = bFactory.createRandomBoard();
        assertTrue(board instanceof EasyBoard);
        assertFalse(board.removeGroups());
    }

}