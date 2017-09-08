package nl.tudelft.item;

import org.junit.Before;

import static org.junit.Assert.*;

public class EyeTest extends ItemTest {

    @Before
    public void setUp() {
        item = new Eye();
        spriteLoc = "EYE";
    }
}