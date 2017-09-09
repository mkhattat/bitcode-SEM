package nl.tudelft.pooralien.Controller;

import java.awt.*;

/**
 * Created by Ivo Schols on 9-9-2017.
 */
public class BackgroundTile {

    int coordinateX;
    int coordinateY;
    Color colorBackgroundTile;

    public BackgroundTile(int coordinateX, int coordinateY, Color colorBackgroundTile) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.colorBackgroundTile = colorBackgroundTile;
    }

    /**
     * Pre: coordinateX is bigger than -1 and smaller than 11.
     * @param coordinateX can be changed so that the board structure can be changed.
     */
    public void setCoordinateX(int coordinateX) {
        // Bigger than -1 and smaller than 11 as the board has 10 columns.
        if (coordinateX > -1 && coordinateX < 11) {
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
     * Pre: coordinateY is bigger than -1 and smaller than 11.
     * @param coordinateY can be changed to that the board structure can be changed.
     */
    public void setCoordinateY(int coordinateY) {
        if (coordinateY > -1 && coordinateY < 11) {
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
        this.colorBackgroundTile = colorBackgroundTile;
    }

    /**
     * @return colorBackgroundTile for drawing the tile background.
     */
    public Color getColorBackgroundTile() {
        return colorBackgroundTile;
    }
}
