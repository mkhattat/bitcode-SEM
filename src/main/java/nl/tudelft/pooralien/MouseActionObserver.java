package nl.tudelft.pooralien;

import nl.tudelft.pooralien.ui.Animation;
import nl.tudelft.pooralien.ui.MainScreen;
import nl.tudelft.pooralien.ui.RTLDragAnimation;
import nl.tudelft.pooralien.ui.TTBDragAnimation;
import java.awt.Point;

/**
 * Implements the MouseActionObserver from the Observer interface.
 */
public class MouseActionObserver implements Observer {

    private MouseEventHandler mouseEventHandler;
    private MouseEventHandler.MouseAction mouseAction;
    private Animation dragAnimation;
    private MainScreen mainScreen;

    /**
     * Initilalize the observer with the MouseEventHandler.
     *
     * @param handler the MouseEventHandler used to caputure the mouse events
     */
    public MouseActionObserver(MouseEventHandler handler) {
        mouseEventHandler = handler;
        dragAnimation = null;
    }

    /**
     * The Update method is called whenever data in the MouseEventHandler changes.
     * The method will perform actions based on the different changes.
     */
    public void update() {

        mouseAction = mouseEventHandler.getMouseAction();
        mainScreen = mouseEventHandler.getMainScreen();

        Point p = mouseAction.getPosition();

        if (mouseAction.getMouseActionType()
                != MouseEventHandler.MouseAction.CLICK_ACTION) {

            if (dragAnimation != null) {
                if (mouseAction.isActionReleased()) {
                    this.stopAnimation();
                }
                else {
                    this.updateAnimation(p);
                }

            }
            else {
                this.startAnimation(p);
            }
        }
    }

    /**
     * End the animation.
     */
    private void stopAnimation() {
        dragAnimation.end();
        dragAnimation = null;
    }

    /**
     * Update the position of the animation.
     *
     * @param p the updated mouse position
     */
    private void updateAnimation(Point p) {
        dragAnimation.update(p);
    }

    /**
     * Update the position of the animation.
     *
     * @param p the mouse position
     */
    private void startAnimation(Point p) {
        if (mouseAction.getMouseActionType()
                == MouseEventHandler.MouseAction.HORIZONTAL_DRAG_ACTION) {
            dragAnimation = new RTLDragAnimation(mainScreen);
            dragAnimation.start(p);
        }
        else if (mouseAction.getMouseActionType()
                == MouseEventHandler.MouseAction.VERTICAL_DRAG_ACTION) {
            dragAnimation = new TTBDragAnimation(mainScreen);
            dragAnimation.start(p);
        }
    }

}
