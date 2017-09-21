package nl.tudelft.pooralien.Controller;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.awt.*;
import java.util.NoSuchElementException;

/**
 * Created by Ivo Schols on 9-9-2017.
 */
public class BackgroundTileCatalogTest {

    BackgroundTileCatalog backgroundTileCatalog;

    private BackgroundTile backgroundTile1;
    private BackgroundTile backgroundTile2;
    private BackgroundTile backgroundTile3;
    private BackgroundTile backgroundTile4;
    private BackgroundTile backgroundTile5;

    private BackgroundTile invalidBackgroundTileX;
    private BackgroundTile invalidBackgroundTileY;
    private BackgroundTile invalidBackgroundTileXY;

    private static final int BACKGROUND_TILE_COUNT = 10;
    private static final int MAX_TILE_COUNT = 100;

    @Before
    public void setup() {
        backgroundTileCatalog = new BackgroundTileCatalog();

        backgroundTile1 = new BackgroundTile(0,0, Color.WHITE);
        backgroundTile2 = new BackgroundTile(1, 1, Color.WHITE);
        backgroundTile3 = new BackgroundTile(2, 2, Color.WHITE);
        backgroundTile4 = new BackgroundTile(3, 10, Color.WHITE);
        backgroundTile5 = new BackgroundTile(10, 4, Color.WHITE);
    }

    @Test
    public void BackgroundTileCatalogTooSmall() {
        BackgroundTileCatalog backgroundTileCatalogTenTiles;

        try {
            backgroundTileCatalogTenTiles =
                    new BackgroundTileCatalog(-1, Color.WHITE);
        } catch (IllegalArgumentException e) {
            assertEquals(
                    "BackgroundTileCount must be bigger than 0 to be added to the catalog"
                    , e.getMessage());
        }
    }

    @Test
    public void BackgroundTileCatalogTooBig() {
        BackgroundTileCatalog backgroundTileCatalogTenTiles;

        try {
            backgroundTileCatalogTenTiles =
                    new BackgroundTileCatalog(MAX_TILE_COUNT + 1, Color.WHITE);
        } catch (IllegalArgumentException e) {
            assertEquals(
                    "BackgroundTileCount must be smaller than 101 to be added to the catalog"
                    , e.getMessage());
        }
    }

    @Test
    public void BackgroundTileCatalog10Tiles() {
        BackgroundTileCatalog backgroundTileCatalogTenTiles =
                new BackgroundTileCatalog(BACKGROUND_TILE_COUNT, Color.WHITE);

        assertEquals(BACKGROUND_TILE_COUNT, backgroundTileCatalogTenTiles.size());
    }

    @Test
    public void addTileToEmptyCatalog() {
        assertTrue(backgroundTileCatalog.size() == 0);
        backgroundTileCatalog.add(backgroundTile1);
        assertTrue(backgroundTileCatalog.size() == 1);
    }

    @Test
    public void addSameTileTwice() {
        assertTrue(backgroundTileCatalog.add(backgroundTile1));
        assertFalse(backgroundTileCatalog.add(backgroundTile1));
    }

    @Test
    public void addFiveDifferentTiles() {
        backgroundTileCatalog.add(backgroundTile1);
        backgroundTileCatalog.add(backgroundTile2);
        backgroundTileCatalog.add(backgroundTile3);
        backgroundTileCatalog.add(backgroundTile4);
        backgroundTileCatalog.add(backgroundTile5);

        assertTrue(backgroundTileCatalog.size() == 5);
    }

    @Test
    public void addAndRemoveTile() {
        backgroundTileCatalog.add(backgroundTile1);
        assertTrue(backgroundTileCatalog.remove(backgroundTile1.getCoordinateX(), backgroundTile1.getCoordinateY()));
    }

    @Test
    public void addAndRemoveTileCheckSize() {
        backgroundTileCatalog.add(backgroundTile1);
        assertTrue(backgroundTileCatalog.size() == 1);

        backgroundTileCatalog.remove(backgroundTile1.getCoordinateX(), backgroundTile1.getCoordinateY());
        assertTrue(backgroundTileCatalog.size() == 0);
    }

    @Test
    public void removeNonExistentTile() {
        backgroundTileCatalog.add(backgroundTile1);
        assertFalse(backgroundTileCatalog.remove(backgroundTile1.getCoordinateX()+1, backgroundTile1.getCoordinateY()+1));
    }

    @Test
    public void indexOfTileTrue() {
        backgroundTileCatalog.add(backgroundTile1);
        assertTrue(backgroundTileCatalog.indexOf(backgroundTile1.getCoordinateX(), backgroundTile1.getCoordinateY()) > -1);
    }

    @Test
    public void indexOfTileFalse() {
        backgroundTileCatalog.add(backgroundTile1);
        assertFalse(backgroundTileCatalog.indexOf(backgroundTile2.getCoordinateX(), backgroundTile2.getCoordinateY()) > -1);
    }

    @Test
    public void getBackgroundTileCatalogSameTile() {
        backgroundTileCatalog.add(backgroundTile1);
        BackgroundTile backgroundTileTest = backgroundTileCatalog.get(backgroundTile1.getCoordinateX(), backgroundTile1.getCoordinateY());
        assertTrue(backgroundTile1.equals(backgroundTileTest));
    }

    @Test
    public void getBackgroundTileCatalogDifferentTile() {
        backgroundTileCatalog.add(backgroundTile1);
        backgroundTileCatalog.add(backgroundTile2);
        BackgroundTile backgroundTileTest = backgroundTileCatalog.get(backgroundTile1.getCoordinateX(), backgroundTile1.getCoordinateY());
        assertFalse(backgroundTile2.equals(backgroundTileTest));
    }

    @Test
    public void getBackgroundTileCatalogInvalidCoordinate() {
        int invalidCoordinateXY = -1;
        backgroundTileCatalog.add(backgroundTile1);

        try {
            backgroundTileCatalog.get(invalidCoordinateXY, invalidCoordinateXY);
        } catch (IndexOutOfBoundsException e) {
            assertEquals(
                    "X and Y coordinates must always be between -1 and 11."
                    + "\ncurrent X: " + invalidCoordinateXY + "."
                    + "\ncurrent Y: " + invalidCoordinateXY + "."
                    , e.getMessage());
        }
    }

    @Test
    public void getBackgroundTileCatalogNonExistentTile() {
        backgroundTileCatalog.add(backgroundTile1);

        try {
            backgroundTileCatalog.get(backgroundTile1.getCoordinateX()+1, backgroundTile1.getCoordinateY()+1);
        } catch (NoSuchElementException e) {
            assertEquals(
                    "There is no BackgroundTile at position (X,Y): ("
                    + (backgroundTile1.getCoordinateX()+1) + "," + (backgroundTile1.getCoordinateY()+1) + ")."
                    , e.getMessage());
        }
    }
}
