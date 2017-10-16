package nl.tudelft.pooralien.Controller;

import nl.tu.delft.defpro.exception.NotExistingVariableException;

import java.awt.Color;
import java.util.List;
import nl.tudelft.pooralien.Launcher;


/**
 * class for controlling the flow of the game.
 */
public final class Game {
    private static Game game;
    private StandardBoard board;
    private BackgroundTileCatalog backgroundTileCatalog;
    private ScoreCounter scoreCounter;

    /**
     * Initialise the singleton Game object.
     */
    private Game() {
        board = new StandardBoard();
        int backgroundTileCount;
        int startingScore = 0;
        Color standardColor;
        try {
            backgroundTileCount = Launcher.getGameCfg().getIntegerValueOf("backgroundTileCount");
            List<Integer> rgb = Launcher.getGameCfg().getListIntValueOf("colorBackgroundTile");
            standardColor = new Color(rgb.get(0), rgb.get(1), rgb.get(2));
        } catch (NotExistingVariableException e) {
            e.printStackTrace();
            backgroundTileCount = -1;
            standardColor = Color.MAGENTA;
        }
        backgroundTileCatalog = new BackgroundTileCatalog(backgroundTileCount, standardColor);
        scoreCounter = new ScoreCounter(startingScore);
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
    public StandardBoard getBoard() {
        if (board == null) {
            board = new StandardBoard();
        }
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
