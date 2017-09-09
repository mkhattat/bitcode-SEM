package nl.tudelft.pooralien.Controller;
import nl.tudelft.pooralien.Controller.ScoreCounter;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Created by Ivo Schols on 8-9-2017.
 */
public class ScoreCounterTest {

    ScoreCounter scoreCounter;

    @Before
    void setup() {
        scoreCounter = new ScoreCounter(0);
    }

    @Test
    void negativeScoreUpdateNoChange() {
        int currentScore = scoreCounter.getScore();
        scoreCounter.updateScore(-10);
        assertEquals(currentScore, scoreCounter.getScore());
    }

    @Test
    void positiveScoreUpdateChange() {
        int currentScore = scoreCounter.getScore() + (scoreCounter.getScorePerTile() * 10);
        scoreCounter.updateScore(10);
        assertEquals(currentScore, scoreCounter.getScore());
    }

    @Test
    void negativeSetScoreNoChange() {
        int currentScore = scoreCounter.getScore();
        scoreCounter.setScore(-1);
        assertEquals(currentScore, scoreCounter.getScore());
    }

    @Test
    void positiveSetScoreToOne() {
        int currentScore = 1;
        scoreCounter.setScore(1);
        assertEquals(currentScore, scoreCounter.getScore());
    }

    @Test
    void negativeSetScorePerTileNoChange() {
        int currentScorePerTile = scoreCounter.getScorePerTile();
        scoreCounter.setScorePerTile(-2);
        assertEquals(currentScorePerTile, scoreCounter.getScorePerTile());
    }

    @Test
    void positiveSetScorePerTileToTwo() {
        int currentScorePerTile = 2;
        scoreCounter.setScorePerTile(2);
        assertEquals(currentScorePerTile, scoreCounter.getScorePerTile());
    }

}
