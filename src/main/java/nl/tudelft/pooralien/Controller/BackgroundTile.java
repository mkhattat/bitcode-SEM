package nl.tudelft.pooralien.Controller;

/**
 * Created by Ivo Schols on 9-9-2017.
 */
public class BackgroundTile {

    private int coordinateX;
    private int coordinateY;

    private static final int MAX_WIDTH_AND_HEIGHT = 10;
    private static final int MIN_WIDTH_AND_HEIGHT = 0;

    /**
     * @param coordinateX must be [0,10]
     * @param coordinateY must be [0,10]
     */
    public BackgroundTile(int coordinateX, int coordinateY) {
        if (!(coordinateX >= MIN_WIDTH_AND_HEIGHT && coordinateX <= MAX_WIDTH_AND_HEIGHT)) {
            throw new IllegalArgumentException("Coordinate X must be between -1 and 11");
        }
        if (!(coordinateY >= MIN_WIDTH_AND_HEIGHT && coordinateY <= MAX_WIDTH_AND_HEIGHT)) {
            throw new IllegalArgumentException("Coordinate Y must be between -1 and 11");
        }

        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    /**
     * Pre: coordinateX is bigger or equal than 0 and smaller or equal than 10.
     * @param coordinateX can be changed so that the board structure can be changed.
     */
    public void setCoordinateX(int coordinateX) {
        // Bigger than -1 and smaller than 11 as the board has 10 columns.
        if (coordinateX >= MIN_WIDTH_AND_HEIGHT && coordinateX <= MAX_WIDTH_AND_HEIGHT) {
            this.coordinateX = coordinateX;
        }
    }

    /**
     * @return coordinateX integer which is always (supposed) to be [0,10].
     */
    public int getCoordinateX() {
        return coordinateX;
    }

    /**
     * Pre: coordinateY is bigger or equal than 0 and smaller or equal than 10.
     * @param coordinateY can be changed to that the board structure can be changed.
     */
    public void setCoordinateY(int coordinateY) {
        if (coordinateY >= MIN_WIDTH_AND_HEIGHT && coordinateY <= MAX_WIDTH_AND_HEIGHT) {
            this.coordinateY = coordinateY;
        }
    }

    /**
     * @return coordinateY integer which is always (supposed) to be [0,10].
     */
    public int getCoordinateY() {
        return coordinateY;
    }

    /**
     * @param object to which this tile must be compared.
     * @return Return true, if Xcoordinate, Ycoordinate and color values are the same else false.
     */
    public boolean equals(Object object) {
        if (!(object instanceof BackgroundTile)) {
            return false;
        }
        BackgroundTile backgroundTile = (BackgroundTile) object;

        if (!(backgroundTile.getCoordinateX() == this.getCoordinateX())) {
            return false;
        }
        if (!(backgroundTile.getCoordinateY() == this.getCoordinateY())) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = coordinateX;
        final int primeNumber = 31;
        result = primeNumber * result + coordinateY;
        return result;
    }

}
