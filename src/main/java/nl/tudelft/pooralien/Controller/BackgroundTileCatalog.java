package nl.tudelft.pooralien.Controller;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Created by Ivo Schols on 9-9-2017.
 */
public class BackgroundTileCatalog {

    private ArrayList<BackgroundTile> backgroundTiles = new ArrayList<>();

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
        if (this.indexOf(coordinateX,coordinateY) > -1) {
            return true;
        }
        return false;
    }

    /**
     * @param coordinateX of the backgroundTile to be checked.
     * @param coordinateY of the backgroundTile to be checked.
     * @return BackgroundTile at coordinatesXY, if the object is not in the list return null.
     */
    public BackgroundTile get(int coordinateX, int coordinateY) {
        if (coordinateX < 0 || coordinateX > 10 || coordinateY < 0 || coordinateY > 10) {
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
}
