package nl.tudelft.pooralien.Controller;

import java.awt.Color;

/**
 * Created by Ivo Schols on 9-9-2017.
 */
public class BackgroundTile {

    private int coordinateX;
    private int coordinateY;
    private Color colorBackgroundTile;
    private Integer maxWidthAndHeight;

    /**
     * @param coordinateX must be [0,10]
     * @param coordinateY must be [0,10]
     * @param colorBackgroundTile colorBackgroundTile;
     */
    public BackgroundTile(int coordinateX, int coordinateY, Color colorBackgroundTile) {
        initWidthHeight();

        checkCoordinate(coordinateX);
        checkCoordinate(coordinateY);

        // instance check is needed because a null would make the backgroundTiles hidden,
        // and as a result the game would be unplayable
        checkColorBackgroundTile(colorBackgroundTile);

        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.colorBackgroundTile = colorBackgroundTile;
    }

    /**
     * Check if the coordinate is not smaller than 0 or bigger than maxWidthAndHeight variable.
     * @param coordinate to be checked.
     */
    private void checkCoordinate(int coordinate) {
        if (!(coordinate >= 0 && coordinate <= maxWidthAndHeight)) {
            throw new IllegalArgumentException("Coordinate must be between -1 and "
                    + (maxWidthAndHeight + 1));
        }
    }

    /**
     * Check if the colorBackgroundTile is not null. If it was null then the backgroundTiles,
     * would be invisible which would break the game.
     * @param colorBackgroundTile object to be tested.
     */
    private void checkColorBackgroundTile(Color colorBackgroundTile) {
        if ((colorBackgroundTile == null)) {
            throw new IllegalArgumentException("colorBackgroundTile should be a Color object");
        }
    }

    private void initWidthHeight() {
        final int min = 5;
        final int max = 20;
        final int standard = 10;
        maxWidthAndHeight = GameConfig.getInteger("maxBoardWidth", min, max, standard);
    }

    /**
     * Pre: coordinateX is bigger or equal than 0 and smaller or equal than 10.
     * @param coordinateX can be changed so that the board structure can be changed.
     */
    public void setCoordinateX(int coordinateX) {
        // Bigger than -1 and smaller than 11 as the board has 10 columns.
        if (coordinateX >= 0 && coordinateX <= maxWidthAndHeight) {
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
        if (coordinateY >= 0 && coordinateY <= maxWidthAndHeight) {
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
     * Pre: must be a Color object (null would make the game unplayable).
     * @param colorBackgroundTile change the color of the backgroundTile.
     */
    public void setColorBackgroundTile(Color colorBackgroundTile) {
        if (colorBackgroundTile != null) {
            this.colorBackgroundTile = colorBackgroundTile;
        }
    }

    /**
     * @return colorBackgroundTile for drawing the tile background.
     */
    public Color getColorBackgroundTile() {
        return colorBackgroundTile;
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

        return backgroundTile.getCoordinateX() == this.getCoordinateX()
                && backgroundTile.getCoordinateY() == this.getCoordinateY()
                && backgroundTile.getColorBackgroundTile().equals(this.getColorBackgroundTile());
    }

    @Override
    public int hashCode() {
        int result = coordinateX;
        final int primeNumber = 31;
        result = primeNumber * result + coordinateY;
        result = primeNumber * result + colorBackgroundTile.hashCode();
        return result;
    }

}
