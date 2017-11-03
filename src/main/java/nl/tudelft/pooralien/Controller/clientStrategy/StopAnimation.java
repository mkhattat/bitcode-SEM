package nl.tudelft.pooralien.Controller.clientStrategy;

import nl.tudelft.pooralien.Controller.Client;

/**
 * Implements what to do when recieving a StopAnimation command.
 *
 */
public class StopAnimation implements Strategy {

    @Override
    public void execute(String[] args, Client client) {
        client.stopAnimation();
    }
}
