package nl.tudelft.item;

import org.junit.Before;

public class BoneTest extends ItemTest {
    @Before
    public void setUp() {
        item = new Bone();
        spriteLoc = "BONE";
    }
}