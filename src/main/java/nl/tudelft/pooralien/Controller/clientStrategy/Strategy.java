package nl.tudelft.pooralien.Controller.clientStrategy;

import nl.tudelft.pooralien.Controller.Client;

/**
 * Interface for the strategy design pattern.
 *
 */
public interface Strategy {

    /**
     * Implement the algorithm here.
     *
     * @param args is the args for a specefiec command. can be empty.
     * @param client is the client who invoke this method.
     */
    void execute(String[] args, Client client);
}
