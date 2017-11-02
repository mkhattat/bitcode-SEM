package nl.tudelft.pooralien.Controller.HighScore;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class ScoreTest {

    @Test(expected = IllegalArgumentException.class)
    public void nullScoreName() {
        Score score = new Score(null , 0);

    }

    @Test
    public void nameEquals() {
        Score score = new Score("testName", 0);
        assertEquals("Expected name to be equal to testName",
                "testName", score.getName());
    }

    @Test
    public void scoreEquals() {
        int scoreInt = 10;
        Score score = new Score("testName", scoreInt);
        assertEquals("Expected score to be equal to " + scoreInt,
                score.getScore(), scoreInt);
    }

}
