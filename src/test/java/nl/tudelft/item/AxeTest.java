package nl.tudelft.item;

import org.junit.Before;

public class AxeTest extends ItemTest {
    @Before
    public void setUp() {
        item = new Axe();
        spriteLoc = "AXE";
    }
}