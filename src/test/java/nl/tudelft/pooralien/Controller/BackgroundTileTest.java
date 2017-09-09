package nl.tudelft.pooralien.Controller;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

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

    @Test
    public void invalidColorSet() {
        Color currentColor = backgroundTile1.getColorBackgroundTile();
        backgroundTile1.setColorBackgroundTile(null);
        assertTrue(backgroundTile1.getColorBackgroundTile().equals(currentColor));
    }

    @Test
    public void validColorSet() {
        Color oldColor = backgroundTile1.getColorBackgroundTile();
        assertNotEquals(oldColor, Color.BLACK);
        backgroundTile1.setColorBackgroundTile(Color.BLACK);
        assertFalse(backgroundTile1.getColorBackgroundTile().equals(oldColor));
    }

    @Test
    public void tileEqualsOtherTileTrue() {
        assertTrue(backgroundTile1.equals(new BackgroundTile(0, 0, Color.WHITE)));
    }

    @Test
    public void tileEqualsWithDifferentXFalse() {
        assertFalse(backgroundTile1.equals(new BackgroundTile(1, 0, Color.WHITE)));
    }

    @Test
    public void tileEqualsWithDifferentYFalse() {
        assertFalse(backgroundTile1.equals(new BackgroundTile(0, 1, Color.WHITE)));
    }

    @Test
    public void tileEqualsWithDifferentColorFalse() {
        assertFalse(backgroundTile1.equals(new BackgroundTile(0, 0, Color.BLACK)));
    }

}
