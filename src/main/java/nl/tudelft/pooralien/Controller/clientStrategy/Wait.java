package nl.tudelft.pooralien.Controller.clientStrategy;

import nl.tudelft.pooralien.Controller.Client;
import nl.tudelft.pooralien.Controller.Game;

/**
 * Implements what to do when receiving a Wait command.
 *
 */
public class Wait implements Strategy {

    @Override
    public void execute(String[] args, Client client) {
        Game.getGame().getGameControllerMachine().setState(
                Game.getGame().getGameControllerMachine().getGamePausedState()
        );
        Game.getGame().notifyObservers();
    }
}
