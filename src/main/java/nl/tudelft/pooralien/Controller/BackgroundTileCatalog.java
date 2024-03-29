package nl.tudelft.pooralien.Controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * Created by Ivo Schols on 9-9-2017.
 */
public class BackgroundTileCatalog {

    private ArrayList<BackgroundTile> backgroundTiles = new ArrayList<>();

    private int maxWidthAndHeight;
    private int maxTileCount;
    private Color color;

    /**
     * Random used to generate random items by generating random ints.
     * These ints will be between the lower bound (inclusive) and the upper bound (exclusive).
     */
    private Random intGen;

    /**
     * Create an empty BackgroundTileCatalog object.
     */
    public BackgroundTileCatalog() {
        intGen = new Random();
        initWidthHeight();
        initMaxTileCount();
    }

    /**
     * Initiliazes the max width and height using the config file.
     */
    private void initWidthHeight() {
        final int min = 5;
        final int max = 20;
        final int standard = 10;
        maxWidthAndHeight = GameConfig.getInteger("maxBoardWidth", min, max, standard);
    }

    /**
     * Initializes the max tile count using the config file.
     */
    private void initMaxTileCount() {
        final int min = 5;
        final int max = 20;
        final int standard = 10;
        maxTileCount = GameConfig.getInteger("maxBoardWidth", min, max, standard)
                * GameConfig.getInteger("maxBoardHeight", min, max, standard);
    }

    /**
     * Pre: count is not smaller than 0 and not bigger than 100.
     * Pre: tileColor must be an instanceof Color (otherwise game is unplayable)
     * Create an BackgroundTileCatalog that is is pre filled with white BackgroundTiles.
     * @param backgroundTileCount count of BackgroundTiles that need to be added to
     *                            the BackgroundTileCatalog object.
     * @param tileColor background color of the tile to be added to the catalog.
     * @throws IllegalArgumentException if the pre conditions are not met.
     */
    public BackgroundTileCatalog(int backgroundTileCount, Color tileColor) {
        initWidthHeight();
        initMaxTileCount();

        checkBackgroundTileCount(backgroundTileCount);
        checkTileColor(tileColor);

        this.color = tileColor;

        addRandomBackgroundTilesToCatalog(backgroundTileCount, tileColor);
    }

    /**
     * Checks if the BackgroundTileCount is valid (not smaller than 0 or bigger than maxTileCount).
     * @param backgroundTileCount check if count is legal.
     */
    private void checkBackgroundTileCount(int backgroundTileCount) {
        if (backgroundTileCount < 0) {
            throw new IllegalArgumentException(
                    "BackgroundTileCount must be bigger than 0 to be added to the catalog");
        }
        if (backgroundTileCount > maxTileCount) {
            throw new IllegalArgumentException(
                    "BackgroundTileCount must be smaller than 101 to be added to the catalog");
        }
        if (backgroundTileCount < 0) {
            throw new IllegalArgumentException(
                    "BackgroundTileCount must be bigger than 0 to be added to the catalog");
        }
        if (backgroundTileCount > maxTileCount) {
            throw new IllegalArgumentException(
                    "BackgroundTileCount must be smaller than 101 to be added to the catalog");
        }
    }

    /**
     * Check if the tileColor object is not null,
     * which would make the game unplayable due to invisible backgroundTiles.
     * @param tileColor object to be checked.
     */
    private void checkTileColor(Color tileColor) {
        if (tileColor == null) {
            throw new IllegalArgumentException("colorBackgroundTile should be a Color object");
        }
    }

    /**
     * Adds random backgroundTiles to the catalog, with the specified tileColor.
     * @param backgroundTileCount amount of backgroundTiles to be added to the catalog.
     * @param tileColor color of the tiles to be added.
     */
    private void addRandomBackgroundTilesToCatalog(int backgroundTileCount, Color tileColor) {
        intGen = new Random();

        int tilesAdded = 0;

        while (tilesAdded != (backgroundTileCount)) {
            if (this.addRandomBackgroundTile(tileColor)) {
                tilesAdded++;
            }
        }
    }

    /**
     * Add backgroundTile to backgroundTiles ArrayList.
     * @param backgroundTile which must be added.
     * @return false if the X or Y coordinates are invalid or if the tile is already in the list.
     */
    public boolean add(BackgroundTile backgroundTile) {
        // If there is already a backgroundTile in this position return false.
        if (contains(backgroundTile.getCoordinateX(), backgroundTile.getCoordinateY())) {
            return false;
        }

        // If the backgroundTile is added return true else false.
        return backgroundTiles.add(backgroundTile);
    }

    /**
     * Remove a backgroundTile from the catalog by its respective coordinates.
     * @param coordinateX of the backgroundTile to be removed.
     * @param coordinateY of the backgroundTile to be removed.
     * @return true if the backgroundTile exists and is removed.
     */
    public boolean remove(int coordinateX, int coordinateY) {
        int positionInCatalog = this.indexOf(coordinateX, coordinateY);

        if (positionInCatalog > -1) {
            backgroundTiles.remove(positionInCatalog);
            backgroundTiles.trimToSize();
            return true;
        }

        return false;
    }

    /**
     * @param coordinateX of the backgroundTile to be checked.
     * @param coordinateY of the backgroundTile to be checked.
     * @return the position of the backgroundTile in the catalog or -1 if invalid.
     */
    public int indexOf(int coordinateX, int coordinateY) {
        for (BackgroundTile backgroundTile : backgroundTiles) {
            if (backgroundTile.getCoordinateX() == coordinateX
                    && backgroundTile.getCoordinateY() == coordinateY) {
                return backgroundTiles.indexOf(backgroundTile);
            }
        }
        return -1;
    }

    /**
     * @param coordinateX of the backgroundTile to be checked.
     * @param coordinateY of the backgroundTile to be checked.
     * @return True if a backgroundTile exists on these X and Y coordinates.
     */
    public boolean contains(int coordinateX, int coordinateY) {
        return (this.indexOf(coordinateX, coordinateY) > -1);
    }

    /**
     * @param coordinateX of the backgroundTile to be checked.
     * @param coordinateY of the backgroundTile to be checked.
     * @return BackgroundTile at coordinatesXY, if the object is not in the list return null.
     * @throws IndexOutOfBoundsException if the coordinates are not between 1 and 11.
     * @throws NoSuchElementException if there is no tile at these coordinates.
     */
    public BackgroundTile get(int coordinateX, int coordinateY)
            throws IndexOutOfBoundsException, NoSuchElementException {
        if (coordinateX < 0 || coordinateX >= maxWidthAndHeight
                || coordinateY < 0 || coordinateY >= maxWidthAndHeight) {
            throw new IndexOutOfBoundsException(
                    "X and Y coordinates must always be between -1 and 11."
                    + "\ncurrent X: " + coordinateX + "."
                    + "\ncurrent Y: " + coordinateY + "."
            );
        }

        int positionInCatalog = this.indexOf(coordinateX, coordinateY);

        if (positionInCatalog > -1) {
            return backgroundTiles.get(positionInCatalog);
        }

        throw new NoSuchElementException(
                "There is no BackgroundTile at position (X,Y): "
                + "(" + coordinateX + "," + coordinateY + ")."
        );
    }

    /**
     * @return integer value with number of elements in the catalog.
     */
    public int size() {
        return backgroundTiles.size();
    }

    /**
     * Pre: tileColor must be an instanceof Color (otherwise game is unplayable).
     * @param tileColor background color of the tile to be added to the catalog.
     * @return Returns true if the tile has been added to the catalog.
     */
    private boolean addRandomBackgroundTile(Color tileColor) {
        if (tileColor == null) {
            throw new IllegalArgumentException("colorBackgroundTile should be a Color object");
        }

        int positionOnBoardX = intGen.nextInt(maxWidthAndHeight);
        int positionOnBoardY = intGen.nextInt(maxWidthAndHeight);

        return (this.add(new BackgroundTile(positionOnBoardX, positionOnBoardY, tileColor)));
    }

    @Override
    public String toString() {
        Color standardColor = Color.BLUE;
        if (color != null) {
            standardColor = color;
        }
        String newBTC = String.valueOf(standardColor.getRGB());
        StringBuilder buffer = new StringBuilder();
        buffer.append(newBTC);
        for (BackgroundTile item : backgroundTiles) {
            buffer.append(",")
                    .append(item.getCoordinateX())
                    .append(" ")
                    .append(item.getCoordinateY());
        }
        return buffer.toString();
    }
}
