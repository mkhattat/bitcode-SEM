package nl.tudelft.pooralien.Controller;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.awt.*;

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
    public void addIllegalTileTooSmallX() {
        invalidBackgroundTileX = new BackgroundTile(-1, 0, Color.WHITE);
        assertFalse(backgroundTileCatalog.add(invalidBackgroundTileX));
    }

    @Test
    public void addIllegalTileTooBigX() {
        invalidBackgroundTileX = new BackgroundTile(11, 0, Color.WHITE);
        assertFalse(backgroundTileCatalog.add(invalidBackgroundTileX));
    }

    @Test
    public void addIllegalTileTooSmallY() {
        invalidBackgroundTileY = new BackgroundTile(0, -1, Color.WHITE);
        assertFalse(backgroundTileCatalog.add(invalidBackgroundTileY));
    }

    @Test
    public void addIllegalTileTooBigY() {
        invalidBackgroundTileY = new BackgroundTile(0, 11, Color.WHITE);
        assertFalse(backgroundTileCatalog.add(invalidBackgroundTileY));
    }

    @Test
    public void addIllegalTileTooSmallXTooBigY() {
        invalidBackgroundTileXY = new BackgroundTile(-1, 11, Color.WHITE);
        assertFalse(backgroundTileCatalog.add(invalidBackgroundTileXY));
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
    public void containsTileTrue() {
        backgroundTileCatalog.add(backgroundTile1);
        assertTrue(backgroundTileCatalog.contains(backgroundTile1.getCoordinateX(), backgroundTile1.getCoordinateY()) > -1);
    }

    @Test
    public void containsTileFalse() {
        backgroundTileCatalog.add(backgroundTile1);
        assertFalse(backgroundTileCatalog.contains(backgroundTile2.getCoordinateX(), backgroundTile2.getCoordinateY()) > -1);
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
    public void getBackgroundTileCatalogNonExistentTile() {
        backgroundTileCatalog.add(backgroundTile1);
        BackgroundTile backgroundTileTest = backgroundTileCatalog.get(backgroundTile1.getCoordinateX()+1, backgroundTile1.getCoordinateY()+1);
        assertNull(backgroundTileTest);
    }
}
