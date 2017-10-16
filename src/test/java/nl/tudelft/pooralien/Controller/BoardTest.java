package nl.tudelft.pooralien.Controller;
import nl.tudelft.item.Item;
import nl.tudelft.pooralien.Launcher;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.awt.Point;
import static org.junit.Assert.assertNotNull;

public abstract class BoardTest {

    protected StandardBoard board;
    protected Launcher launcher;

    @Before
    public abstract void setUp() throws Exception;

    @Test
    public abstract void getWidth();

    @Test
    public abstract void getHeight();

    @Test
    public abstract void getMinGroupSize();

    @Test
    public abstract void getItemFactory();

    @Test
    public abstract void setItem() ;

    @Test
    public abstract void getItem();

    @Test
    public abstract void getItemXOOBPos();

    @Test
    public abstract  void getItemXOOBNeg() ;

    @Test
    public abstract void getItemYOOBPos();

    @Test
    public abstract void getItemYOOBNeg();

    @Test
    public abstract void remove();

    @Test
    public abstract void removeGroups();

    @Test
    public abstract void findGroup();

    @Test
    public abstract void removeGroup();
}