package nl.tudelft.item;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public abstract class ItemTest {
    protected String spriteLoc;
    protected Item item;

    @Before
    public void setUp() {};

    @Test
    public void getSprite() {
        assertEquals(spriteLoc, item.getSprite());
    };
}