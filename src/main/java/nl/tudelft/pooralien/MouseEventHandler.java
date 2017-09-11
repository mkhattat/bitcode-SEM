package nl.tudelft.pooralien;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Handles mouse events inside a given JFrame using the
 * java.awt.event library by override functions of
 * MouseListener and MouseMotionListener.
 */
public class MouseEventHandler implements MouseListener, MouseMotionListener {

    private MouseEventHandler.MouseAction mouseAction;

    /**
     * Initiate event mouse listeners on a given JFrame.
     *
     * @param  window the JFrame where where to listen for events
     */
    public MouseEventHandler(Launcher window) {
        window.addMouseListener(this);
        window.addMouseMotionListener(this);
    }

    /**
     * Is executed when mouse is pressed in the JFrame.
     * Creates a new mouseAction object.
     *
     * @param  e the mouse event that was captured
     */
    public void mousePressed(MouseEvent e) {
        System.out.println("Create MouseAction Object");
        mouseAction = new MouseEventHandler.MouseAction(e.getX(), e.getY());
    }

    /**
     * Is executed when mouse is released.
     * Checks if the mouse action was ether a click action
     * or a drag action.
     *
     * @param  e the mouse event that was captured
     */
    public void mouseReleased(MouseEvent e) {
        int xPos = mouseAction.getInitialPosition()[0];
        int yPos = mouseAction.getInitialPosition()[1];

        if (mouseAction.getMouseActionType() == MouseAction.CLICK_ACTION) {
            System.out.println("Click Action on x:" + xPos + ", y:" + yPos);
        } else {
            System.out.println("End Drag Animation");
        }
    }

    /**
     * Is executed when mouse clicked in the JFrame.
     * This is a placeholder.
     *
     * @param  e the mouse event that was captured
     */
    public void mouseClicked(MouseEvent e) {
        // mouse clicked
    }

    /**
     * Is executed when has moved in the JFrame.
     * This is a placeholder.
     *
     * @param  e the mouse event that was captured
     */
    public void mouseMoved(MouseEvent e) {
        // mouse moved
    }


    /**
     * Is executed when mouse entered in the JFrame.
     * This is a placeholder.
     *
     * @param  e the mouse event that was captured
     */
    public void mouseEntered(MouseEvent e) {
        // mouse entered

    }

    /**
     * Is executed when mouse exits the JFrame.
     * This is a placeholder.
     *
     * @param  e the mouse event that was captured
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
     * @param  e the mouse event that was captured
     */
    public void mouseDragged(MouseEvent e) {
        mouseAction.setPosition(e.getX(), e.getY());
        if (mouseAction.getMouseActionType() != MouseAction.CLICK_ACTION) {
            if (mouseAction.getMouseActionType() == MouseAction.HORIZONTAL_DRAG_ACTION) {
                System.out.println("Animate DRAG Horizontal x:" + e.getX() + ", y:" + e.getY());
            } else if (mouseAction.getMouseActionType() == MouseAction.VERICAL_DRAG_ACTION) {
                System.out.println("Animate DRAG Vertical x:" + e.getX() + ", y:" + e.getY());
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
        public static final int VERICAL_DRAG_ACTION = 3;

        private static final int DRAG_THRESHOLD = 5;

        private int xPosStart, yPosStart;
        private int xPosCurrent, yPosCurrent;
        private int mouseActionType;


        /**
         * Initialize MouseAction object with initial x and
         * y positions and initially set the mouseActionType
         * on click.
         *
         * @param xPos the initial x position.
         * @param yPos the initial y position.
         */
        public MouseAction(int xPos, int yPos) {
            xPosStart = xPos;
            yPosStart = yPos;
            mouseActionType = CLICK_ACTION;
        }

        /**
         * Initialize MouseAction object with initial x and
         * y positions and initially set the mouseActionType
         * on click.
         *
         * @param xPos the initial x position.
         * @param yPos the initial y position.
         */
        public void setPosition(int xPos, int yPos) {
            if (mouseActionType == CLICK_ACTION) {
                determineDragDirection(xPos, yPos);
            }
            xPosCurrent = xPos;
            yPosCurrent = yPos;
        }

        /**
         * gets the current x and y position.
         *
         * @return two element array {x,y}
         */
        public int[] getPosition() {
            int[] positionArray = {xPosCurrent, yPosCurrent};
            return positionArray;
        }

        /**
         * gets initial x and y position.
         *
         * @return two element array {x,y}
         */
        public int[] getInitialPosition() {
            int [] positionArray = {xPosStart, yPosStart};
            return positionArray;

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
         * @param xPos current x position
         * @param yPos current y position
         */
        private void determineDragDirection(int xPos, int yPos) {
            if (Math.abs(xPosStart - xPos) >= DRAG_THRESHOLD) {
                mouseActionType = HORIZONTAL_DRAG_ACTION;
            } else if (Math.abs(yPosStart - yPos) >= DRAG_THRESHOLD) {
                mouseActionType = VERICAL_DRAG_ACTION;
            } else {
                mouseActionType = CLICK_ACTION;
            }
        }
    }
}