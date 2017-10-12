package nl.tudelft.item;

import org.junit.Before;

import static org.junit.Assert.*;

public class MaskTest extends ItemTest {

    @Before
    public void setUp() {
        item = new Mask();
        spriteLoc = "MASK";
    }
}