package nl.tudelft.pooralien;

import nl.tudelft.pooralien.ui.RTLDragAnimation;
import nl.tudelft.pooralien.ui.TTBDragAnimation;

import java.awt.*;

public class MouseActionObserver implements Observer{

    MouseEventHandler mouseEventHandler;

    public MouseActionObserver(MouseEventHandler handler){
        mouseEventHandler = handler;
    }

    public void update() {
        Point p = mouseEventHandler.mouseAction.getPosition();

        if (mouseEventHandler.mouseAction.getMouseActionType() != MouseEventHandler.MouseAction.CLICK_ACTION) {

            if (mouseEventHandler.dragAnimation != null) {
                // end the animation when mouse is released
                if (mouseEventHandler.mouseAction.isActionReleased()) {
                    mouseEventHandler.dragAnimation.end();
                    mouseEventHandler.dragAnimation = null;
                }
                // update animation if animation exists
                else {
                    mouseEventHandler.dragAnimation.update(p);
                }

            }
            // start an animation if it does not exist
            else {
                if (mouseEventHandler.mouseAction.getMouseActionType() == MouseEventHandler.MouseAction.HORIZONTAL_DRAG_ACTION) {
                    mouseEventHandler.dragAnimation = new RTLDragAnimation(mouseEventHandler.mainScreen);
                    mouseEventHandler.dragAnimation.start(p);
                }
                else if (mouseEventHandler.mouseAction.getMouseActionType() == MouseEventHandler.MouseAction.VERTICAL_DRAG_ACTION) {
                    mouseEventHandler.dragAnimation = new TTBDragAnimation(mouseEventHandler.mainScreen);
                    mouseEventHandler.dragAnimation.start(p);
                }
            }
        }
    }
}
