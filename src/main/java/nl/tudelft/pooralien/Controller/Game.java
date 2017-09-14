package nl.tudelft.pooralien.Controller;

/**
 * class for controlling the flow of the game.
 */
public final class Game {
    private static Game game;
    private Board board;

    /**
     * Initialise the singleton Game object.
     */
    private Game() {
        board = new Board();
    }

    /**
     * Returns the single Game object.
     * @return the Game object
     */
    public static synchronized Game getGame() {
        if (game == null) {
            game = new Game();
        }
        return game;
    }

    /**
     * Returns the board.
     * @return the board being used.
     */
    public Board getBoard() {
        return board;
    }
}
