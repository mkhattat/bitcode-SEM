package nl.tudelft.pooralien.Controller;


import java.awt.*;

/**
 * Created by Ivo Schols on 9-9-2017.
 */
public class BackgroundTile {

    private int coordinateX;
    private int coordinateY;
    private Color colorBackgroundTile;

    private static final int MAX_WIDTH_AND_HEIGHT = 10;
    private static final int MIN_WIDTH_AND_HEIGHT = 0;

    /**
     * @param coordinateX must be [0,10]
     * @param coordinateY must be [0,10]
     * @param colorBackgroundTile may be any Color
     */
    public BackgroundTile(int coordinateX, int coordinateY, Color colorBackgroundTile) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.colorBackgroundTile = colorBackgroundTile;
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
     * @param colorBackgroundTile change the color of the backgroundTile.
     */
    public void setColorBackgroundTile(Color colorBackgroundTile) {
        if (colorBackgroundTile instanceof Color) {
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
     * @param backgroundTile to which this tile must be compared.
     * @return Return true, if Xcoordinate, Ycoordinate and color values are the same else false.
     */
    public boolean equals(BackgroundTile backgroundTile) {
        if (!(backgroundTile instanceof BackgroundTile)) {
            return false;
        }
        if (!(backgroundTile.getCoordinateX() == this.getCoordinateX())) {
            return false;
        }
        if (!(backgroundTile.getCoordinateY() == this.getCoordinateY())) {
            return false;
        }
        if (!(backgroundTile.getColorBackgroundTile()).equals(this.getColorBackgroundTile())) {
            return false;
        }

        return true;
    }
}
