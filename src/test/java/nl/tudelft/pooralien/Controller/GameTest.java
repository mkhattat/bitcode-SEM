package nl.tudelft.pooralien.Controller;

import nl.tudelft.pooralien.Controller.HighScore.ScoreCounter;
import nl.tudelft.pooralien.Launcher;
import nl.tudelft.pooralien.Observer;
import nl.tudelft.pooralien.ui.HighScoreTable.HighScoreTableTopX;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Sam on 10/1/2017.
 */
public class GameTest {

    private Launcher launcher;
    private static Game game , game1;
    private BoardFactory bFactory;
    private Board board;
    private BackgroundTileCatalog backgroundTileCatalog;
    private ScoreCounter scoreCounter;
    private boolean multiplayer;
    private boolean gameIsRunning;
    private ArrayList<Observer> observers;
    private int moves;
    private HighScoreTableTopX highScoreTableTopX;

    @Before
    public void setUp() throws Exception {
        game =  Game.getGame();
    }

    @Test
    public void getGame() throws Exception {
        assertTrue(game.gameIsRunning());
    }

    @Test
    public void getBoard() throws Exception {
        Board oldBoard = game.getBoard();
        game.setBoard(new StandardBoard());
        assertFalse(game.getBoard().equals(oldBoard));

    }

    @Test
    public void getHighScoreTableTopX() throws Exception {
    }

    @Test
    public void getMoves() throws Exception {
    }

    @Test
    public void useMove() throws Exception {
    }

    @Test
    public void getBackgroundTileCatalog() throws Exception {
    }

    @Test
    public void getScoreCounter() throws Exception {
    }

    @Test
    public void multiplayerMode() throws Exception {
    }

    @Test
    public void gameIsRunning() throws Exception {
    }

    @Test
    public void setMultiplayer() throws Exception {
    }

    @Test
    public void pauseGame() throws Exception {
    }

    @Test
    public void resumeGame() throws Exception {
    }

    @Test
    public void setBoard() throws Exception {
    }

    @Test
    public void setBackgroundTileCatalog() throws Exception {
    }

    @Test
    public void registerObserver() throws Exception {
    }

    @Test
    public void removeObserver() throws Exception {
    }

    @Test
    public void notifyObservers() throws Exception {
    }

}