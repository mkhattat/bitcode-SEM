package nl.tudelft.pooralien;

import nl.tudelft.pooralien.Controller.Game;
import nl.tudelft.pooralien.ui.Animation;
import nl.tudelft.pooralien.ui.MainScreen;
import nl.tudelft.pooralien.ui.RTLDragAnimation;
import nl.tudelft.pooralien.ui.TTBDragAnimation;

import java.awt.Point;

/**
 * Implements the MouseActionObserver from the Observer interface.
 */
public class MouseActionObserver implements Observer {

    private Animation dragAnimation;

    /**
     * Initilalize the observer with the MouseEventHandler.
     */
    public MouseActionObserver() {
        dragAnimation = null;
    }

    /**
     * The Update method is called whenever data in the MouseEventHandler changes.
     * The method will perform actions based on the different changes.
     *
     * @param subject the observed subject MouseEventHandler
     */
    public void update(Subject subject) {
        if (!(subject instanceof MouseEventHandler)) {
            return;
        }
        MouseEventHandler mouseEventHandler = (MouseEventHandler) subject;

        MainScreen mainScreen = mouseEventHandler.getMainScreen();
        MouseEventHandler.MouseAction mouseAction = mouseEventHandler.getMouseAction();

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
                if (Game.getGame().gameIsRunning()) {
                    this.startAnimation(p, mouseAction.getMouseActionType(), mainScreen);
                }
            }
        }
    }

    /**
     * End the animation.
     */
    protected void stopAnimation() {
        dragAnimation.end();
        dragAnimation = null;
    }

    /**
     * Update the position of the animation.
     *
     * @param p the updated mouse position
     */
    protected void updateAnimation(Point p) {
        if (dragAnimation != null) {
            dragAnimation.update(p);
        }
    }

    /**
     * Update the position of the animation.
     *
     * @param p
     *            the mouse position.
     * @param type is the horizontal or vertical.
     * @param mainScreen mainscreen to create the animation.
     */
    protected void startAnimation(Point p, int type, MainScreen mainScreen) {
        if (type
                == MouseEventHandler.MouseAction.HORIZONTAL_DRAG_ACTION) {
            dragAnimation = new RTLDragAnimation(mainScreen);
            dragAnimation.start(p);
        }
        else if (type
                == MouseEventHandler.MouseAction.VERTICAL_DRAG_ACTION) {
            dragAnimation = new TTBDragAnimation(mainScreen);
            dragAnimation.start(p);
        }
    }

}
