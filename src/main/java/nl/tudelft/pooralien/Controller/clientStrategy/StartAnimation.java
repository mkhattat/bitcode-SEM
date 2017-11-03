package nl.tudelft.pooralien.Controller.clientStrategy;

import java.awt.Point;

import nl.tudelft.pooralien.MouseEventHandler;
import nl.tudelft.pooralien.Controller.Client;

/**
 * Implements how to strat the move animation.
 *
 */
public class StartAnimation implements Strategy {

    @Override
    public void execute(String[] args, Client client) {
        Point p = new Point(Integer.parseInt(args[0]),
                Integer.parseInt(args[1]));
        int type = 0;
        if (args[2].equals("HORIZONTAL")) {
            type = MouseEventHandler.MouseAction.HORIZONTAL_DRAG_ACTION;
        } else if (args[2].equals("VERTICAL")) {
            type = MouseEventHandler.MouseAction.VERTICAL_DRAG_ACTION;
        }
        client.startAnimation(p, type, client.getMainScreen());
    }
}
