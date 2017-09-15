package nl.tudelft.pooralien.ui;

import java.awt.Point;

/**
 * Interface for the Animation class.
 */
public interface Animation {
    /**
     * Initializing the drag move.
     * @param p is the point where mouse is pressed.
     */

    void start(Point p);

    /**
     * updating of the screen when mouse is dragged.
     * @param p the mouse coordinate of the new point.
     */

    void update(Point p);

    /**
     * End the animation.
     */
    void end();
}
