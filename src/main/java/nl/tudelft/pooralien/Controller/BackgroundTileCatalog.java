package nl.tudelft.pooralien.Controller;

import java.util.ArrayList;

/**
 * Created by Ivo Schols on 9-9-2017.
 */
public class BackgroundTileCatalog {

    private ArrayList<BackgroundTile> backgroundTiles = new ArrayList<>();

    private static final int MAX_WIDTH_AND_HEIGHT = 10;
    private static final int MIN_WIDTH_AND_HEIGHT = 0;

    /**
     * Add backgroundTile to backgroundTiles ArrayList.
     * @param backgroundTile which must be added.
     * @return false if the X or Y coordinates are invalid or if the tile is already in the list.
     */
    public boolean add(BackgroundTile backgroundTile) {
        // If there is already a backgroundTile in this position return false.
        if (contains(backgroundTile.getCoordinateX(), backgroundTile.getCoordinateY()) > -1) {
            return false;
        }

        if (backgroundTile.getCoordinateX() < MIN_WIDTH_AND_HEIGHT) {
            return false;
        }
        if (backgroundTile.getCoordinateX() > MAX_WIDTH_AND_HEIGHT) {
            return false;
        }

        if (backgroundTile.getCoordinateY() < MIN_WIDTH_AND_HEIGHT) {
            return false;
        }
        if (backgroundTile.getCoordinateY() > MAX_WIDTH_AND_HEIGHT) {
            return false;
        }

        // If the backgroundTile could not be added return false.
        if (!(backgroundTiles.add(backgroundTile))) {
            return false;
        }

        return true;
    }

    /**
     * Remove a backgroundTile from the catalog by its respective coordinates.
     * @param coordinateX of the backgroundTile to be removed.
     * @param coordinateY of the backgroundTile to be removed.
     * @return true if the backgroundTile exists and is removed.
     */
    public boolean remove(int coordinateX, int coordinateY) {
        int positionInCatalog = this.contains(coordinateX, coordinateY);

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
    public int contains(int coordinateX, int coordinateY) {
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
     * @return BackgroundTile at coordinatesXY, if the object is not in the list return null.
     */
    public BackgroundTile get(int coordinateX, int coordinateY) {
        int positionInCatalog = this.contains(coordinateX, coordinateY);

        if (positionInCatalog > -1) {
            return backgroundTiles.get(positionInCatalog);
        }

        return null;
    }

    /**
     * @return integer value with number of elements in the catalog.
     */
    public int size() {
        return backgroundTiles.size();
    }
}
