package nl.tudelft.pooralien.Controller.clientStrategy;

import java.awt.Color;

import nl.tudelft.pooralien.Controller.BackgroundTile;
import nl.tudelft.pooralien.Controller.BackgroundTileCatalog;
import nl.tudelft.pooralien.Controller.Client;
import nl.tudelft.pooralien.Controller.Game;

/**
 * Implements what to do when recieving a NewBackgroundCatalog command.
 *
 */
public class NewBackgroundCatalog implements Strategy {

    @Override
    public void execute(String[] args, Client client) {
        Color c = new Color(Integer.parseInt(args[0]));
        BackgroundTileCatalog btc = new BackgroundTileCatalog();
        for (int i = 1; i < args.length; i++) {
            String[] coordinate = args[i].split("\\s");
            if (coordinate.length > 1) {
                btc.add(new BackgroundTile(
                            Integer.parseInt(coordinate[0]),
                            Integer.parseInt(coordinate[1]),
                            c));
            }
        }
        Game.getGame().setBackgroundTileCatalog(btc);
        client.getMainScreen().refreshBoard();
    }
}
