package nl.tudelft.pooralien.Controller.clientStrategy;

import nl.tudelft.pooralien.Controller.Client;
import nl.tudelft.pooralien.Controller.Game;

/**
 * Implements what to do with the Ready command.
 *
 */
public class Ready implements Strategy {

    @Override
    public void execute(String[] args, Client client) {
        Game.getGame().setMultiplayer(true);
        Game.getGame().getScoreCounter().setScore(0);
    }
}
