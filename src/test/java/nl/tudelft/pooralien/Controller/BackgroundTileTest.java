package nl.tudelft.pooralien.Controller;

import nl.tudelft.pooralien.Launcher;
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
    public void setup() throws Exception {
        new Launcher();

        backgroundTile1 = new BackgroundTile(0,0, Color.WHITE);
    }

    @Test
    public void backgroundTileCoordinateXTooSmall() {
        try {
            new BackgroundTile(-1, 0, Color.WHITE);
        } catch (IllegalArgumentException e) {
            assertEquals(
                    "Coordinate X must be between -1 and 11"
                    , e.getMessage());
        }
    }

    @Test
    public void backgroundTileCoordinateXTooBig() {
        try {
            new BackgroundTile(11, 0, Color.WHITE);
        } catch (IllegalArgumentException e) {
            assertEquals(
                    "Coordinate X must be between -1 and 11"
                    , e.getMessage());
        }
    }

    @Test
    public void backgroundTileCoordinateYTooSmall() {
        try {
            new BackgroundTile(0, -1, Color.WHITE);
        } catch (IllegalArgumentException e) {
            assertEquals(
                    "Coordinate Y must be between -1 and 11"
                    , e.getMessage());
        }
    }

    @Test
    public void backgroundTileColorIsNull() {
        try {
            new BackgroundTile(0, 0, null);
        } catch (IllegalArgumentException e) {
            assertEquals(
                    "colorBackgroundTile should be a Color object"
                    , e.getMessage());
        }
    }

    @Test
    public void backgroundTileCoordinateYTooBig() {
        try {
            new BackgroundTile(0, 11, Color.WHITE);
        } catch (IllegalArgumentException e) {
            assertEquals(
                    "Coordinate Y must be between -1 and 11"
                    , e.getMessage());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructInvalidXCoordinateTooSmall() {
        new BackgroundTile(-1, 0, Color.WHITE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructInvalidXCoordinateTooBig() {
        new BackgroundTile(11, 0, Color.WHITE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructInvalidYCoordinateTooSmall() {
        new BackgroundTile(0, -1, Color.WHITE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructInvalidYCoordinateTooBig() {
        new BackgroundTile(0, 11, Color.WHITE);
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
    public void validColorSetChange() {
        backgroundTile1.setColorBackgroundTile(Color.GREEN);
        assertEquals(backgroundTile1.getColorBackgroundTile(), Color.GREEN);
    }

    @Test
    public  void invalidColorSetChange() {
        backgroundTile1.setColorBackgroundTile(null);
        assertNotEquals(backgroundTile1.getColorBackgroundTile(), null);
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

    @Test
    public void tileEqualsNullFalse() {
        assertNotNull(backgroundTile1);
    }

    @Test
    public void hashCodeEqualsTrue() {
        assertEquals(new BackgroundTile(1, 2, Color.WHITE).hashCode(),
                new BackgroundTile(1, 2, Color.WHITE).hashCode());
    }

    @Test
    public  void hashCodeEqualsFalse() {
        assertNotEquals(new BackgroundTile(1, 2, Color.WHITE).hashCode(),
                new BackgroundTile(2, 1, Color.WHITE).hashCode());
    }

}
