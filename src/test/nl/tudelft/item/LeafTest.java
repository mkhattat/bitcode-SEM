package nl.tudelft.item;

import org.junit.Before;

import static org.junit.Assert.*;

public class LeafTest extends ItemTest {

    @Before
    public void setUp() {
        item = new Leaf();
        spriteLoc = "LEAF";
    }
}