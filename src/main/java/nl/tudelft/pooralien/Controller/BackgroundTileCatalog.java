package nl.tudelft.pooralien.Controller;

import java.util.ArrayList;

/**
 * Created by Ivo Schols on 9-9-2017.
 */
public class BackgroundTileCatalog {

    private ArrayList<BackgroundTile> backgroundTiles = new ArrayList<>();

    /**
     * Add backgroundTile to backgroundTiles ArrayList.
     * @param backgroundTile
     * @return
     */
    public boolean add(BackgroundTile backgroundTile) {
        // If there is already a backgroundTile in this position return false.
        if (contains(backgroundTile.getCoordinateX(), backgroundTile.getCoordinateY()) > -1) {
            return false;
        }

        // If the backgroundTile has been successfully added return true.
        if (backgroundTiles.add(backgroundTile)) {
            return true;
        }

        return false;
    }

    /**
     * Remove a backgroundTile from the catalog by its respective coordinates.
     * @param coordinateX
     * @param coordinateY
     * @return
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
     * @return the position of the backgroundTile in the catalog or -1 if the backgroundTile is not in the catalog.
     */
    public int contains(int coordinateX, int coordinateY) {
        for (BackgroundTile backgroundTile : backgroundTiles) {
            if (backgroundTile.getCoordinateX() == coordinateX && backgroundTile.getCoordinateY() == coordinateY) {
                return backgroundTiles.indexOf(backgroundTile);
            }
        }
        return -1;
    }

    /**
     * @param coordinateX of the backgroundTile to be checked.
     * @param coordinateY of the backgroundTile to be checked.
     * @return BackgroundTile object at respective coordinates, if the object is not in the list return null.
     */
    public BackgroundTile get(int coordinateX, int coordinateY) {
        int positionInCatalog = this.contains(coordinateX, coordinateY);

        if(positionInCatalog > -1) {
            return backgroundTiles.get(positionInCatalog);
        }

        return null;
    }

    /**
     * @return
     */
    public int size() {
        return backgroundTiles.size();
    }
}
