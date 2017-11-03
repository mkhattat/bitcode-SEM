package nl.tudelft.pooralien.Controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import nl.tu.delft.defpro.exception.NotExistingVariableException;
import nl.tudelft.pooralien.Controller.HighScore.ScoreCounter;
import nl.tudelft.pooralien.Launcher;
import nl.tudelft.pooralien.Observer;
import nl.tudelft.pooralien.Subject;
import nl.tudelft.pooralien.ui.HighScoreTable.HighScoreEnterNameDialog;
import nl.tudelft.pooralien.ui.HighScoreTable.HighScoreTableTopX;

import javax.swing.JTable;


/**
 * class for controlling the flow of the game.
 */
public final class Game implements Subject {
    private static Game game;
    private static BoardFactory bFactory = new StandardBoardFactory();
    private Board board;
    private BackgroundTileCatalog backgroundTileCatalog;
    private ScoreCounter scoreCounter;
    private boolean multiplayer;
    private boolean gameIsRunning;
    private ArrayList<Observer> observers;
    private int moves;
    private HighScoreTableTopX highScoreTableTopX;
    private static int difficulty = 1;


    /**
     * Initialise the singleton Game object.
     */
    private Game() {
        gameIsRunning = true;
        observers = new ArrayList<>();
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
     * @return the topX score table being used.
     */
    public JTable getHighScoreTableTopX() {
        if (highScoreTableTopX == null) {
            highScoreTableTopX = new HighScoreTableTopX();
        }
        return highScoreTableTopX.getTable();
    }

    /**
     * Initializes the backgroundtilecatalog.
     */
    private void initBTCatalog() {
        int backgroundTileCount = -1;
        Color standardColor = Color.MAGENTA;
        try {
            backgroundTileCount = Launcher.getGameCfg().getIntegerValueOf("backgroundTileCount");
            List<Integer> rgb = Launcher.getGameCfg().getListIntValueOf("colorBackgroundTile");
            standardColor = new Color(rgb.get(0), rgb.get(1), rgb.get(2));
        } catch (NotExistingVariableException e) {
            e.printStackTrace();
        }
        backgroundTileCatalog = new BackgroundTileCatalog(backgroundTileCount, standardColor);
    }

    /**
     * Initializes the amount of moves.
     */
    private void initMoves() {
        moves = 1;
        try {
            moves = Launcher.getGameCfg().getIntegerValueOf("maxMoves" + difficulty);
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
                //Enter user input into
                new HighScoreEnterNameDialog(true, game.scoreCounter.getScore());

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

    /**
     * Check multiplayer mode.
     *
     * @return if the game is in multiplayer mode.
     */
    public boolean multiplayerMode() {
        return multiplayer;
    }

    /**
     * Check if the game is in pause mode.
     *
     * @return true if the game is playable.
     */
    public boolean gameIsRunning() {
        return gameIsRunning;
    }

    /**
     * change the multiplayer mode.
     *
     * @param b the state of multiplayer mode.
     */
    public void setMultiplayer(boolean b) {
        multiplayer = b;
        notifyObservers();
    }

    /**
     * Pause the game.
     *
     */
    public void pauseGame() {
        gameIsRunning = false;
        notifyObservers();
    }

    /**
     * Resume the game.
     *
     */
    public void resumeGame() {
        gameIsRunning = true;
        notifyObservers();
    }

    /**
     * Change the board of the game.
     *
     * @param board the new board to replace the old one.
     */
    public void setBoard(Board board) {
        if (board != null) {
            this.board = board;
        }
    }

    /**
     * Change the background tile catalog.
     *
     * @param btc is the new backgroundTileCatalog object.
     */
    public void setBackgroundTileCatalog(BackgroundTileCatalog btc) {
        if (btc != null) {
            this.backgroundTileCatalog = btc;
        }
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer item : observers) {
            item.update(this);
        }
    }

    /**
     * Sets the game to hard mode.
     */
    public static void setHardMode() {
        if (difficulty != 2) {
            difficulty = 2;
            bFactory = new HardBoardFactory();
        }
    }

    /**
     * Sets the game to standard mode.
     */
    public static void setStandardMode() {
        if (difficulty != 1) {
            difficulty = 1;
            bFactory = new StandardBoardFactory();
        }
    }

    /**
     * Sets the game to easy mode.
     */
    public static void setEasyMode() {
        if (difficulty != 0) {
            difficulty = 0;
            bFactory = new EasyBoardFactory();
        }
    }

    /**
     * Retrieves the int representation of the current difficulty.
     * 0 - Easy
     * 1 - Standard
     * 2 - Hard
     * @return An int representing the current difficulty.
     */
    public static int getDifficulty() {
        return difficulty;
    }
}
