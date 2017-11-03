package nl.tudelft.pooralien.Controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nl.tudelft.pooralien.Controller.GameStates.GameControllerMachine;
import nl.tudelft.pooralien.Controller.HighScore.ScoreCounter;
import nl.tudelft.pooralien.Observer;
import nl.tudelft.pooralien.Subject;
import nl.tudelft.pooralien.ui.HighScoreTable.HighScoreTableTopX;
import nl.tudelft.pooralien.ui.MainScreen;

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
    private ArrayList<Observer> observers;
    private int moves;
    private static int difficulty = 1;
    private GameControllerMachine gameControllerMachine;
    private MainScreen mainScreen = null;

    /**
     * Initialise the singleton Game object.
     */
    private Game() {
        observers = new ArrayList<>();
        board = bFactory.createRandomBoard();
        initMoves();
        initBackgroundTileCatalog();
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
     * @return gameControllerMachine, used to alter states of the state machine.
     */
    public GameControllerMachine getGameControllerMachine() {
        if (gameControllerMachine == null) {
            gameControllerMachine = new GameControllerMachine();
        }
        return gameControllerMachine;
    }

    /**
     * @return a new topX score table object with new data.
     */
    public JTable getHighScoreTableTopX() {
        return new HighScoreTableTopX().getTable();
    }

    /**
     * Initializes the amount of moves.
     */
    private void initMoves() {
        final int minStandardMaxMoves = 1;
        final int maxStandardMaxMoves = 100;
        final int defaultStandardMaxMoves = 14 - difficulty * 2;

        moves = GameConfig.getInteger("maxMoves" + difficulty, minStandardMaxMoves,
                maxStandardMaxMoves, defaultStandardMaxMoves);
    }

    private void initBackgroundTileCatalog() {
        final int minBackgroundTileCount = 0;
        final int maxBackgroundTileCount = 20;
        final int defaultBackgroundTileCount = 10;

        int backgroundTileCount = GameConfig.getInteger("backgroundTileCount",
                minBackgroundTileCount, maxBackgroundTileCount, defaultBackgroundTileCount);

        final int minRGBLength = 3;
        final int maxRGBLength = 3;
        final List<Integer> minRGBValue = Arrays.asList(0, 0, 0);
        final List<Integer> maxRGBValue = Arrays.asList(255, 255, 255);
        final List<Integer> defaultRGBValue = Arrays.asList(255, 0, 255);

        List<Integer> rgb = GameConfig.getIntegerList("colorBackgroundTile",
                minRGBLength, maxRGBLength, minRGBValue, maxRGBValue, defaultRGBValue);

        Color standardColor = new Color(rgb.get(0), rgb.get(1), rgb.get(2));

        backgroundTileCatalog = new BackgroundTileCatalog(backgroundTileCount, standardColor);
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
                getGame().gameControllerMachine.setState(gameControllerMachine.getGameEndedState());
                Game.getGame().gameControllerMachine.endGame();
            }
        }
    }

    /**
     * Continues to the next board.
     */
    public void nextBoard() {
        board = bFactory.createRandomBoard();
        initMoves();
        initBackgroundTileCatalog();
        //Refreshes the GUI board
        mainScreen.refreshBoard();
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
        return getGameControllerMachine()
                .equalsCurrentState(gameControllerMachine.getGamePlayState());
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
     * Reset the game.
     *
     */
    public void reset() {
        game = null;
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

    /**
     * Used to pass the MainScreen object to the Game object.
     * @param mainScreen sets the mainScreen object variable.
     */
    public void setMainScreen(MainScreen mainScreen) {
        this.mainScreen = mainScreen;
    }

    /**
     * Get main screen.
     * @return the main screen.
     */
    public MainScreen getMainScreen() {
        return mainScreen;
    }
}
