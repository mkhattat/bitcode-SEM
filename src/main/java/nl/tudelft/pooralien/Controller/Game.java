package nl.tudelft.pooralien.Controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import nl.tu.delft.defpro.exception.NotExistingVariableException;
import nl.tudelft.pooralien.Launcher;
import nl.tudelft.pooralien.Observer;
import nl.tudelft.pooralien.Subject;


/**
 * class for controlling the flow of the game.
 */
public final class Game implements Subject {
    private static Game game;
    private BoardFactory bFactory;
    private Board board;
    private BackgroundTileCatalog backgroundTileCatalog;
    private ScoreCounter scoreCounter;
    private boolean multiplayer;
    private boolean gameIsRunning;
    private ArrayList<Observer> observers;

    /**
     * Initialise the singleton Game object.
     */
    private Game() {
        gameIsRunning = true;
        observers = new ArrayList<>();
        bFactory = new StandardBoardFactory();
        board = bFactory.createRandomBoard();
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
    public Board getBoard() {
        if (board == null) {
            board = bFactory.createRandomBoard();
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
}
