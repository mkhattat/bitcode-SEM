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
    private BoardFactory bFactory;
    private Board board;
    private BackgroundTileCatalog backgroundTileCatalog;
    private ScoreCounter scoreCounter;
    private int moves;

    /**
     * Initialise the singleton Game object.
     */
    private Game() {
        bFactory = new StandardBoardFactory();
        board = bFactory.createRandomBoard();
        int backgroundTileCount;
        int startingScore = 0;
        Color standardColor;
        try {
            moves = Launcher.getGameCfg().getIntegerValueOf("standardMaxMoves");
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
    public Board getBoard() {
        if (board == null) {
            board = bFactory.createRandomBoard();
        }
        return board;
    }

    /**
     * Retrieves the amount of remaining moves.
     * @return The amount of remaining moves.
     */
    public int getMoves() {
        return moves;
    }

    /**
     * Reduces the amount of moves by one.
     */
    public void useMove() {
        if (moves > 0) {
            moves--;
            if (moves == 0) {
                //Placeholder until the required
                //game state functionality is in place.
                System.out.println("No moves left!");
            }
        }
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
