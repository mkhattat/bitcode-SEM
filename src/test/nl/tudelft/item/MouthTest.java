package nl.tudelft.item;

import org.junit.Before;

public class MouthTest extends ItemTest {

    @Before
    public void setUp() {
        item = new Mouth();
        spriteLoc = "MOUTH";
    }
}