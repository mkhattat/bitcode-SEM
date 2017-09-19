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

    private static final int MAX_WIDTH_AND_HEIGHT = 10;
    private static final int MIN_WIDTH_AND_HEIGHT = 0;


    @Before
    public void setup() {
        backgroundTile1 = new BackgroundTile(0,0);
    }

    @Test
    public void BackgroundTileCoordinateXTooSmall() {
        BackgroundTile backgroundTile;

        try {
            backgroundTile = new BackgroundTile(-1, 0);
        } catch (IllegalArgumentException e) {
            assertEquals(
                    "Coordinate X must be between -1 and 11"
                    , e.getMessage());
        }
    }

    @Test
    public void BackgroundTileCoordinateXTooBig() {
        BackgroundTile backgroundTile;

        try {
            backgroundTile = new BackgroundTile(11, 0);
        } catch (IllegalArgumentException e) {
            assertEquals(
                    "Coordinate X must be between -1 and 11"
                    , e.getMessage());
        }
    }

    @Test
    public void BackgroundTileCoordinateYTooSmall() {
        BackgroundTile backgroundTile;

        try {
            backgroundTile = new BackgroundTile(0, -1);
        } catch (IllegalArgumentException e) {
            assertEquals(
                    "Coordinate Y must be between -1 and 11"
                    , e.getMessage());
        }
    }

    @Test
    public void BackgroundTileCoordinateYTooBig() {
        BackgroundTile backgroundTile;

        try {
            backgroundTile = new BackgroundTile(0, 11);
        } catch (IllegalArgumentException e) {
            assertEquals(
                    "Coordinate Y must be between -1 and 11"
                    , e.getMessage());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructInvalidXCoordinateTooSmall() {
        BackgroundTile backgroundTile = new BackgroundTile(-1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructInvalidXCoordinateTooBig() {
        BackgroundTile backgroundTile = new BackgroundTile(11, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructInvalidYCoordinateTooSmall() {
        BackgroundTile backgroundTile = new BackgroundTile(0, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructInvalidYCoordinateTooBig() {
        BackgroundTile backgroundTile = new BackgroundTile(0, 11);
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
    public void tileEqualsOtherTileTrue() {
        assertTrue(backgroundTile1.equals(new BackgroundTile(0, 0)));
    }

    @Test
    public void tileEqualsWithDifferentXFalse() {
        assertFalse(backgroundTile1.equals(new BackgroundTile(1, 0)));
    }

    @Test
    public void tileEqualsWithDifferentYFalse() {
        assertFalse(backgroundTile1.equals(new BackgroundTile(0, 1)));
    }

    @Test
    public void tileEqualsNullFalse() {
        assertFalse(backgroundTile1.equals(null));
    }

    @Test
    public void hashCodeEqualsTrue() {
        assertEquals(new BackgroundTile(1, 2).hashCode(),
                new BackgroundTile(1, 2).hashCode());
    }

    @Test
    public  void hashCodeEqualsFalse() {
        assertNotEquals(new BackgroundTile(1, 2).hashCode(),
                new BackgroundTile(2, 1).hashCode());
    }

}
