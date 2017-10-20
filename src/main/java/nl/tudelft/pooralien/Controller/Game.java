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
        initMoves();
        initBTCatalog();
        scoreCounter = new ScoreCounter(0);
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
     * Initializes the backgroundtilecatalog.
     */
    private void initBTCatalog() {
        int backgroundTileCount = -1;
        Color standardColor = Color.MAGENTA;
        try {
            moves = Launcher.getGameCfg().getIntegerValueOf("standardMaxMoves");
            backgroundTileCount = Launcher.getGameCfg().getIntegerValueOf("backgroundTileCount");
            List<Integer> rgb = Launcher.getGameCfg().getListIntValueOf("colorBackgroundTile");
            standardColor = new Color(rgb.get(0), rgb.get(1), rgb.get(2));
        } catch (NotExistingVariableException e) {
            e.printStackTrace();
        }
        backgroundTileCatalog = new BackgroundTileCatalog(backgroundTileCount, standardColor);
    }

    private void initMoves() {
        moves = 1;
        try {
            moves = Launcher.getGameCfg().getIntegerValueOf("standardMaxMoves");
        } catch (NotExistingVariableException e) {
            e.printStackTrace();
        }
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
                if (backgroundTileCatalog.size() == 0) {
                    nextBoard();
                } else {
                    //Placeholder until the required
                    //game state functionality is in place.
                    System.out.println("Game over!");
                    System.out.println("Your score is: " + scoreCounter.getScore());
                    System.exit(0);
                }
            }
        }
    }

    /**
     * Continues to the next board.
     */
    private void nextBoard() {
        board = bFactory.createRandomBoard();
        initMoves();
        initBTCatalog();
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
