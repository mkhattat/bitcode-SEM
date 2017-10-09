package nl.tudelft.pooralien.Controller.clientStrategy;

import nl.tudelft.pooralien.Controller.Client;
import nl.tudelft.pooralien.Controller.Game;
import nl.tudelft.pooralien.Controller.StandardBoard;
import nl.tudelft.pooralien.Controller.StandardBoardFactory;

/**
 * Implements how to create a new board.
 *
 */
public class NewBoard implements Strategy {

    @Override
    public void execute(String[] args, Client client) {
        StandardBoardFactory bFactory = new StandardBoardFactory();
        StandardBoard newBoard = bFactory.createBoard(args[0]);
        Game.getGame().setBoard(newBoard);
        client.getMainScreen().refreshBoard();
    }
}
