package nl.tudelft.pooralien.Controller;
import nl.tudelft.item.Item;
import nl.tudelft.item.ItemFactory;
import nl.tudelft.pooralien.Launcher;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Created by Sam on 9/13/2017.
 */
public class BoardTest {

    private ItemFactory itemFactory;
    private Board boardAuto;
    private Item items;
    private Item items1;

    private Item[][] boardManu;
    private Item itemsManu;
    private Item itemsManu1;

    @Before
    public void setup() {
        //make a new board withh random items
        try {
            Launcher l = new Launcher();
        } catch (Exception e) {
            e.printStackTrace();
        }
        boardAuto = new Board();
        items = boardAuto.getItem(0,0);
        items1 = boardAuto.getItem(2,2);

        itemFactory = new ItemFactory();
        boardManu = new Item[3][3];

        boardManu[0][0] = itemFactory.createItem("eye");
        boardManu[0][1] = itemFactory.createItem("eye");
        boardManu[0][2] = itemFactory.createItem("bone");

        boardManu[1][0] = itemFactory.createItem("bone");
        boardManu[1][1] = itemFactory.createItem("eye");
        boardManu[1][2] = itemFactory.createItem("bone");

        boardManu[2][0] = itemFactory.createItem("sun");
        boardManu[2][1] = itemFactory.createItem("eye");
        boardManu[2][2] = itemFactory.createItem("sun");
        itemsManu = boardManu[0][0];
        itemsManu1 = boardManu[2][2];

        // make a new random board
        //make a new board using a file

    }

    @Test
    public void setItem() throws Exception {
        boardAuto.setItem(items, 0, 1);
        assertTrue(items.equals(boardAuto.getItem(0, 1)));
        assertFalse(items1.equals(boardAuto.getItem(0,1)));

        itemsManu = boardManu[0][2];
        boardManu[0][2] = itemFactory.createItem("eye");
        itemsManu1 = boardManu[0][2];
        assertTrue(boardManu[0][2].getSprite().equals(itemsManu1.getSprite()));

    }

    @Test
    public void getItem() throws Exception {
        assertTrue(boardAuto.getItem(0,0).equals(items));
    }

    @Test
    public void findHSimilaresAt() throws Exception {
        ArrayList<Integer> removed = new ArrayList<>();
        removed = boardAuto.findHSimilaresAt(5,5);
        if(removed.isEmpty()){
            assertFalse(boardAuto.getItem(5,4).equals(boardAuto.getItem(5,6)));
        }
        else{
            assertTrue(boardAuto.getItem(5,4).equals(boardAuto.getItem(5,5)));
        }
    }

    @Test
    public void findVSimilaresAt() throws Exception {
        ArrayList<Integer> removed = new ArrayList<>();
        removed = boardAuto.findVSimilaresAt(5,5);
        if(removed.isEmpty()){
            assertFalse(boardAuto.getItem(4,5).equals(boardAuto.getItem(6,5)));
        }
        else{
            assertTrue(boardAuto.getItem(4,5).equals(boardAuto.getItem(5,5)));
        }
    }

    @Test
    public void remove() throws Exception {
        Item removedItem = boardAuto.getItem(5,5);
        boardAuto.remove(5,5);
        assertFalse(removedItem.equals(boardAuto.getItem(5,5)));
    }
}