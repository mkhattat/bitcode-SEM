package nl.tudelft.board;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by DexterQuintin on 9/7/2017.
 */
public class ItemTest {
    private Item item;

    @Before
    public void setUp() {
        item = new Item(0);
    }

    @Test
    public void getIdEquals() {
        assertEquals(0, item.getId());
    }

    @Test
    public void setId() {
        item.setId(35);
        assertEquals(35, item.getId());
    }

    @Test
    public void testEqualsSame() {
        Item item2 = new Item(0);
        assertTrue(item.equals(item2));
        assertTrue(item2.equals(item));
    }

    @Test
    public void testEqualsOther() {
        Item item2 = new Item(-77);
        assertFalse(item.equals(item2));
        assertFalse(item2.equals(item));
    }

    @Test
    public void testEqualsString() {
        String item2 = "item";
        assertFalse(item.equals(item2));
    }

    @Test
    public void testHashCode() {
        Item item2 = new Item(item.getId());
        assertEquals(item.hashCode(), item2.hashCode());
    }

}