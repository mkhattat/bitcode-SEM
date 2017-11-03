package nl.tudelft.pooralien.Controller.clientStrategy;

import nl.tudelft.pooralien.Controller.Client;
import nl.tudelft.pooralien.Controller.Game;

/**
 * Implements what to do with the Play command.
 *
 */
public class Play implements Strategy {

    @Override
    public void execute(String[] args, Client client) {
        Game.getGame().getGameControllerMachine().setState(
                Game.getGame().getGameControllerMachine().getGamePlayState()
        );
        Game.getGame().notifyObservers();
    }
}
