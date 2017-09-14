package nl.tudelft.pooralien;

import nl.tudelft.pooralien.ui.Animation;
import nl.tudelft.pooralien.ui.MainScreen;
import nl.tudelft.pooralien.ui.RTLDragAnimation;
import nl.tudelft.pooralien.ui.TTBDragAnimation;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Point;

/**
 * Handles mouse events inside a given JFrame using the
 * java.awt.event library by override functions of
 * MouseListener and MouseMotionListener.
 */
public class MouseEventHandler implements MouseListener, MouseMotionListener {

    private MouseEventHandler.MouseAction mouseAction;
    private MainScreen mainScreen;
    private Animation dragAnimation;

    /**
     * Initiate event mouse listeners on a given JFrame.
     *
     * @param mainScreen the JFrame where where to listen for events
     */
    public MouseEventHandler(MainScreen mainScreen) {
        mainScreen.addMouseListener(this);
        mainScreen.addMouseMotionListener(this);
        this.mainScreen = mainScreen;
    }

    /**
     * Is executed when mouse is pressed in the JFrame.
     * Creates a new mouseAction object.
     *
     * @param e the mouse event that was captured
     */
    public void mousePressed(MouseEvent e) {
        Point mousePosition = new Point(e.getX(), e.getY());
        mouseAction = new MouseEventHandler.MouseAction(mousePosition);
    }

    /**
     * Is executed when mouse is released.
     * Checks if the mouse action was ether a click action
     * or a drag action.
     *
     * @param e the mouse event that was captured
     */
    public void mouseReleased(MouseEvent e) {
        int xPos = mouseAction.getInitialPosition().x;
        int yPos = mouseAction.getInitialPosition().y;

        if (mouseAction.getMouseActionType() == MouseAction.CLICK_ACTION) {
            System.out.println("Click Action on x:" + xPos + ", y:" + yPos);
        } else {
            if (dragAnimation == null) {
                return;
            }
            dragAnimation.end();
            dragAnimation = null;
        }
    }

    /**
     * Is executed when mouse clicked in the JFrame.
     * This is a placeholder.
     *
     * @param e the mouse event that was captured
     */
    public void mouseClicked(MouseEvent e) {
        // mouse clicked
    }

    /**
     * Is executed when has moved in the JFrame.
     * This is a placeholder.
     *
     * @param e the mouse event that was captured
     */
    public void mouseMoved(MouseEvent e) {
        // mouse moved
    }


    /**
     * Is executed when mouse entered in the JFrame.
     * This is a placeholder.
     *
     * @param e the mouse event that was captured
     */
    public void mouseEntered(MouseEvent e) {
        // mouse entered

    }

    /**
     * Is executed when mouse exits the JFrame.
     * This is a placeholder.
     *
     * @param e the mouse event that was captured
     */
    public void mouseExited(MouseEvent e) {
        // mouse exits frame
    }

    /**
     * Is executed when mouse dragged in the JFrame.
     * Updates the position of the mouse inside the
     * mouseAction object. Checks is the mouse action
     * type is click or drag and notifies animation the
     * class.
     *
     * @param e the mouse event that was captured
     */
    public void mouseDragged(MouseEvent e) {
        Point mousePosition = new Point(e.getX(), e.getY());
        mouseAction.setPosition(mousePosition);

        if (mouseAction.getMouseActionType() != MouseAction.CLICK_ACTION) {
            Point p = e.getPoint();
            if (dragAnimation != null) {
                dragAnimation.update(p);
                return;
            }
            if (mouseAction.getMouseActionType() == MouseAction.HORIZONTAL_DRAG_ACTION) {
                dragAnimation = new RTLDragAnimation(mainScreen);
                dragAnimation.start(p);
            } else if (mouseAction.getMouseActionType() == MouseAction.VERTICAL_DRAG_ACTION) {
                dragAnimation = new TTBDragAnimation(mainScreen);
                dragAnimation.start(p);
            }
        }
    }

    /**
     * MouseAction class containing x-position, y-positien
     * and action type of a mouse action.
     */
    public class MouseAction {
        public static final int CLICK_ACTION = 1;
        public static final int HORIZONTAL_DRAG_ACTION = 2;
        public static final int VERTICAL_DRAG_ACTION = 3;

        private static final int DRAG_THRESHOLD = 5;

        private Point startPoint;
        private Point currentPoint;

        private int mouseActionType;


        /**
         * Initialize MouseAction object with initial x and
         * y positions and initially set the mouseActionType
         * on click.
         *
         * @param point Point object to x and y position
         */
        public MouseAction(Point point) {
            startPoint = point;
            mouseActionType = CLICK_ACTION;
        }

        /**
         * Sets a new current position.
         *
         * @param point Point object to x and y position
         */
        public void setPosition(Point point) {
            if (mouseActionType == CLICK_ACTION) {
                determineDragDirection(point);
            }
            currentPoint = point;
        }

        /**
         * gets the current x and y position.
         *
         * @return current point object
         */
        public Point getPosition() {
            return currentPoint;
        }

        /**
         * gets initial x and y position.
         *
         * @return initial point object
         */
        public Point getInitialPosition() {
            return startPoint;
        }

        /**
         * gets the current mouse action type. one of:
         * CLICK_ACTION, HORIZONTAL_DRAG_ACTION or VERICAL_DRAG_ACTION
         *
         * @return mouse type action
         */
        private int getMouseActionType() {
            return mouseActionType;
        }

        /**
         * checks if mouse has dragged more then DRAG_THRESHOLD over
         * x or y axis and set mouse type action accordingly.
         *
         * @param point point object with current position
         */
        private void determineDragDirection(Point point) {
            if (Math.abs(startPoint.x - point.x) >= DRAG_THRESHOLD) {
                mouseActionType = HORIZONTAL_DRAG_ACTION;
            } else if (Math.abs(startPoint.y - point.y) >= DRAG_THRESHOLD) {
                mouseActionType = VERTICAL_DRAG_ACTION;
            } else {
                mouseActionType = CLICK_ACTION;
            }
        }
    }
}