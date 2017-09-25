package nl.tudelft.pooralien.Controller;

import java.awt.Color;

/**
 * class for controlling the flow of the game.
 */
public final class Game {
    private static Game game;
    private Board board;
    private BackgroundTileCatalog backgroundTileCatalog;
    private ScoreCounter scoreCounter;

    private static final int BACKGROUND_TILE_COUNT = 10;
    private static final Color STANDARD_COLOR = Color.MAGENTA;
    private static final int STARTING_SCORE = 0;

    /**
     * Initialise the singleton Game object.
     */
    private Game() {
        board = new Board();
        backgroundTileCatalog = new BackgroundTileCatalog(BACKGROUND_TILE_COUNT, STANDARD_COLOR);
        scoreCounter = new ScoreCounter(STARTING_SCORE);
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

    /**
     * Returns the backgroundTileCatalog.
     * @return the backgroundTileCatalog being used.
     */
    public BackgroundTileCatalog getBackgroundTileCatalog() {
        return backgroundTileCatalog;
    }

    /**
     * Returns the scoreCounter.
     * @return the scoreCounter being used.
     */
    public ScoreCounter getScoreCounter() {
        return scoreCounter;
    }
}
