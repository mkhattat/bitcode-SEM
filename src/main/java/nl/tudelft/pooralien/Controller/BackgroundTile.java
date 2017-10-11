package nl.tudelft.pooralien.Controller;

import nl.tu.delft.defpro.exception.NotExistingVariableException;
import nl.tudelft.pooralien.Launcher;

import java.awt.Color;

/**
 * Created by Ivo Schols on 9-9-2017.
 */
public class BackgroundTile {

    private int coordinateX;
    private int coordinateY;
    private Color colorBackgroundTile;
    private int maxWidthAndHeight;

    /**
     * @param coordinateX must be [0,10]
     * @param coordinateY must be [0,10]
     * @param colorBackgroundTile colorBackgroundTile;
     */
    public BackgroundTile(int coordinateX, int coordinateY, Color colorBackgroundTile) {
        initWidthHeight();
        if (!(coordinateX >= 0 && coordinateX <= maxWidthAndHeight)) {
            throw new IllegalArgumentException("Coordinate X must be between -1 and 11");
        }
        if (!(coordinateY >= 0 && coordinateY <= maxWidthAndHeight)) {
            throw new IllegalArgumentException("Coordinate Y must be between -1 and 11");
        }
        // instance check is needed because a null would make the backgroundTiles hidden,
        // and as a result the game would be unplayable
        if (!(colorBackgroundTile instanceof Color)) {
            throw new IllegalArgumentException("colorBackgroundTile should be a Color object");
        }

        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.colorBackgroundTile = colorBackgroundTile;
    }

    private void initWidthHeight() {
        try {
            maxWidthAndHeight = Launcher.getGameCfg().getIntegerValueOf("maxBoardWidth");
        } catch (NotExistingVariableException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
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

        if (!(backgroundTile.getColorBackgroundTile().equals(this.getColorBackgroundTile()))) {
            return false;
        }

        return true;
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
