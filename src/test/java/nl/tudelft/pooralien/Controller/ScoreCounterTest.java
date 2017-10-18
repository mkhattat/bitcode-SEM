package nl.tudelft.pooralien.Controller;

import nl.tudelft.pooralien.Launcher;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Created by Ivo Schols on 8-9-2017.
 */
public class ScoreCounterTest {

    private Launcher launcher;
    private ScoreCounter scoreCounter;

    @Before
    public void setup() throws Exception {
        launcher = new Launcher();
        scoreCounter = new ScoreCounter(0);
    }

    @Test
    public void negativeTenTilesScoreUpdateNoChange() {
        int currentScore = scoreCounter.getScore();
        scoreCounter.updateScoreTilesRemoved(-10);
        assertEquals(currentScore, scoreCounter.getScore());
    }

    @Test
    public void positiveTenTilesScoreUpdateChange() {
        int currentScore = scoreCounter.getScore() + (scoreCounter.getScorePerTile() * 10);
        scoreCounter.updateScoreTilesRemoved(10);
        assertEquals(currentScore, scoreCounter.getScore());
    }

    @Test
    public void BackgroundTilesScoreUpdateChange() {
        int currentScore = scoreCounter.getScore() + scoreCounter.getScorePerTile()
                * scoreCounter.getScorePerBackgroundTile();
        scoreCounter.updateScoreBackgroundTileRemoved();
        assertEquals(currentScore, scoreCounter.getScore());
    }

    @Test
    public void negativeSetScoreNoChange() {
        int currentScore = scoreCounter.getScore();
        scoreCounter.setScore(-1);
        assertEquals(currentScore, scoreCounter.getScore());
    }

    @Test
    public void positiveSetScoreToOne() {
        int currentScore = 1;
        scoreCounter.setScore(1);
        assertEquals(currentScore, scoreCounter.getScore());
    }

    @Test
    public void negativeSetScorePerTileNoChange() {
        int currentScorePerTile = scoreCounter.getScorePerTile();
        scoreCounter.setScorePerTile(-2);
        assertEquals(currentScorePerTile, scoreCounter.getScorePerTile());
    }

    @Test
    public void positiveSetScorePerTileToTwo() {
        int currentScorePerTile = 2;
        scoreCounter.setScorePerTile(2);
        assertEquals(currentScorePerTile, scoreCounter.getScorePerTile());
    }

}
