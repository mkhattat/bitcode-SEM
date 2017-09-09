package nl.tudelft.pooralien.Controller;

import org.junit.Before;
import org.junit.Test;

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

    @Before
    public void setup() {
        backgroundTileCatalog = new BackgroundTileCatalog();

        backgroundTile1 = new BackgroundTile(0,0, Color.WHITE);
        backgroundTile2 = new BackgroundTile(1, 1, Color.WHITE);
        backgroundTile3 = new BackgroundTile(2, 2, Color.WHITE);
        backgroundTile4 = new BackgroundTile(3, 3, Color.WHITE);
        backgroundTile5 = new BackgroundTile(3, 3, Color.WHITE);

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
    public void addAndRemoveTile() {
        backgroundTileCatalog.add(backgroundTile1);
        assertTrue(backgroundTileCatalog.remove(backgroundTile1.getCoordinateX(), backgroundTile1.getCoordinateY()));
    }
}
