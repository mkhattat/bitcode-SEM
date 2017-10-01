package nl.tudelft.pooralien.Controller;

import nl.tudelft.pooralien.Controller.HighScore.ScoreCounter;
import nl.tudelft.pooralien.Launcher;
import nl.tudelft.pooralien.Observer;
import nl.tudelft.pooralien.ui.HighScoreTable.HighScoreTableTopX;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Sam on 10/1/2017.
 */
public class GameTest {

    private Launcher launcher;
    private static Game game;
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
        game = Game.getGame();
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
        JTable oldHs = game.getHighScoreTableTopX();
        assertNotNull(oldHs);
    }

    @Test
    public void getMoves() throws Exception {
        game.useMove();
        int mov = game.getMoves();
        assertTrue("move is not zero.", mov > 0);
    }

    @Test
    public void useMove() throws Exception {
        int move1 = game.getMoves();
        game.useMove();
        int move2 = game.getMoves();
        assertTrue(move1 == (move2 + 1));
    }

    @Test
    public void getBackgroundTileCatalog() throws Exception {
        BackgroundTileCatalog oldBc = game.getBackgroundTileCatalog();
        game.setBackgroundTileCatalog(new BackgroundTileCatalog());
        assertFalse(game.getBackgroundTileCatalog().equals(oldBc));
    }

    @Test
    public void getScoreCounter() throws Exception {
        game.getScoreCounter().setScore(10);
        int sc1 = game.getScoreCounter().getScore();

        game.setBoard(new StandardBoard());
        game.getScoreCounter().setScore(20);
        int sc2 = game.getScoreCounter().getScore();
        System.out.println(sc1);
        System.out.println(sc2);
        assertFalse(sc1 == sc2);
        /*game.getScoreCounter().setScore(10);
        int sc2 = game.getScoreCounter().getScore();
        assertFalse(sc1 == sc2);*/
    }

    @Test
    public void multiplayerMode() throws Exception {
        game.setMultiplayer(true);
        assertTrue(game.multiplayerMode());
    }

    @Test
    public void gameIsRunning() throws Exception {
        Game.getGame().pauseGame();
        assertFalse(game.gameIsRunning());
    }

    @Test
    public void setMultiplayer() throws Exception {
        game.setMultiplayer(false);
        assertFalse(game.multiplayerMode());
    }

    @Test
    public void pauseGame() throws Exception {
        game.pauseGame();
        assertFalse(game.gameIsRunning());
    }

    @Test
    public void resumeGame() throws Exception {
        game.pauseGame();
        game.resumeGame();
        assertTrue(game.gameIsRunning());
    }

    @Test
    public void setBoard() throws Exception {
        Board oldBoard = game.getBoard();
        game.setBoard(new StandardBoard());
        assertFalse(game.getBoard().equals(oldBoard));
    }

    @Test
    public void setBackgroundTileCatalog() throws Exception {
        BackgroundTileCatalog oldBc = game.getBackgroundTileCatalog();
        game.setBackgroundTileCatalog(new BackgroundTileCatalog());
        assertFalse(game.getBackgroundTileCatalog().equals(oldBc));
    }
}