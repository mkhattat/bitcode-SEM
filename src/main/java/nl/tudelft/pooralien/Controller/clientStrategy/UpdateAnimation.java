package nl.tudelft.pooralien.Controller.clientStrategy;

import java.awt.Point;

import nl.tudelft.pooralien.Controller.Client;

/**
 * Implements what to do when recieving an UpdateAnimation command.
 *
 */
public class UpdateAnimation implements Strategy {

    @Override
    public void execute(String[] args, Client client) {
        if (args.length == 2) {
            Point p = new Point(Integer.parseInt(args[0]),
                    Integer.parseInt(args[1]));
            client.updateAnimation(p);
        }
    }
}
