package nl.tudelft.pooralien.Controller;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StandardBoardFactoryTest {
    private BoardFactory bFactory;

    @Before
    public void setUp() throws Exception {
        bFactory = new StandardBoardFactory();
    }

    @Test
    public void createBoard() throws Exception {
        String layout = "AXE BONE EYE LEAF MASK MOUTH SUN AXE BONE EYE "
                + "LEAF MASK MOUTH SUN AXE BONE EYE LEAF MASK MOUTH "
                + "SUN AXE BONE EYE LEAF MASK MOUTH SUN AXE BONE "
                + "EYE LEAF MASK MOUTH SUN AXE BONE EYE LEAF MASK "
                + "MOUTH SUN AXE BONE EYE LEAF MASK MOUTH SUN AXE "
                + "BONE EYE LEAF MASK MOUTH SUN AXE BONE EYE LEAF "
                + "MASK MOUTH SUN AXE BONE EYE LEAF MASK MOUTH SUN "
                + "AXE BONE EYE LEAF MASK MOUTH SUN AXE BONE EYE "
                + "LEAF MASK MOUTH SUN AXE BONE EYE LEAF MASK MOUTH "
                + "SUN AXE BONE EYE LEAF MASK MOUTH SUN AXE BONE ";
        Board board = bFactory.createBoard(layout);

        assertTrue(board instanceof StandardBoard);
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
            bFactory.createBoard("Axe Eye Bone Mask Bone Bone Sun Mouth Mouth Leaf "
                    + "Axe Eye Bone Mask Bone Bone Sun Mouth Mouth Leaf "
                    + "Axe Eye Bone Mask Bone Bone Sun Mouth Mouth Leaf "
                    + "Axe Eye Bone Mask Bone Bone Sun Mouth Mouth Leaf "
                    + "Axe Eye Bone Mask Bone Bone Sun Mouth Mouth Leaf "
                    + "Axe Eye Bone Mask Bone Bone Sun Mouth Mouth Leaf "
                    + "Axe Eye Bone Mask Bone Bone Sun Mouth Mouth Leaf "
                    + "Axe Eye Bone Mask Bone Bone Sun Mouth Mouth Leaf "
                    + "Axe Eye Bone Mask Bone Bone Sun Mouth Mouth Leaf "
                    + "Axe Eye Bone Mask Bone Bone Sun Mouth Mouth Leaf "
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
                    "The provided item name: Moon"
                            + "\nDoes not match any of the following: "
                            + "axe, bone, eye, leaf, mask, mouth or sun.",
                    e.getMessage()
            );
        }
    }

    @Test
    public void createRandomBoard() throws Exception {
        Board board = bFactory.createRandomBoard();
        assertTrue(board instanceof StandardBoard);
        assertFalse(board.removeGroups());
    }

}