package nl.tudelft.pooralien.Controller;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.awt.*;

/**
 * Created by Ivo Schols on 9-9-2017.
 */
public class BackgroundTileTest {

    private BackgroundTile backgroundTile1;

    @Before
    public void setup() {
        backgroundTile1 = new BackgroundTile(0,0, Color.WHITE);
    }

    @Test
    public void invalidXCoordinateSetNoChange() {
        int currentX = backgroundTile1.getCoordinateX();
        backgroundTile1.setCoordinateX(-1);
        assertEquals(currentX, backgroundTile1.getCoordinateX());
    }

    @Test
    public void validXCoordinateSetChange() {
        int oldX = backgroundTile1.getCoordinateX();
        backgroundTile1.setCoordinateX(1);
        assertNotEquals(oldX, backgroundTile1.getCoordinateX());
    }
    @Test
    public void invalidYCoordinateSetNoChange() {
        int currentY = backgroundTile1.getCoordinateY();
        backgroundTile1.setCoordinateY(-1);
        assertEquals(currentY, backgroundTile1.getCoordinateY());
    }

    @Test
    public void validYCoordinateSetChange() {
        int oldY = backgroundTile1.getCoordinateY();
        backgroundTile1.setCoordinateY(1);
        assertNotEquals(oldY, backgroundTile1.getCoordinateY());
    }


}
