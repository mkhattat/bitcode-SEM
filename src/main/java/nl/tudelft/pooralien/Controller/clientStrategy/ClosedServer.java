package nl.tudelft.pooralien.Controller.clientStrategy;

import nl.tudelft.pooralien.Controller.Client;

/**
 * Implements what to do when the server sending
 * a message when it's about to closed.
 *
 */
public class ClosedServer implements Strategy {

    @Override
    public void execute(String[] args, Client client) {
        client.terminate();
    }
}
