package nl.tudelft.item;

import org.junit.Before;

import static org.junit.Assert.*;

public class SunTest extends ItemTest {

    @Before
    public void setUp() {
        item = new Sun();
        spriteLoc = "SUN";
    }
}