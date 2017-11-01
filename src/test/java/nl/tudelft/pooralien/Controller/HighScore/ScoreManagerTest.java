package nl.tudelft.pooralien.Controller.HighScore;

import org.junit.Before;
import org.junit.Test;


import static junit.framework.TestCase.assertEquals;

public class ScoreManagerTest {

    private ScoreManager scoreManager;

    @Before
    public void setup() {
        scoreManager = new ScoreManager();
    }

    @Test
    public void addScoreSuccessful() {
        String testName = "testName";
        int testScore = 9999;
        int expectedScoreCount = scoreManager.getScores().size() + 1;
        scoreManager.addScore(testName, testScore);
        assertEquals("Expected size not met.",
                expectedScoreCount, scoreManager.getScores().size());
        scoreManager.deleteScore(testName, testScore);
    }

    @Test
    public void deleteScoreSuccessful() {
        String testName = "testName";
        int testScore = 9999;
        scoreManager.addScore(testName, testScore);

        int expectedScoreCount = scoreManager.getScores().size() - 1;

        scoreManager.deleteScore(testName, testScore);

        assertEquals("Expected size not met.",
                expectedScoreCount, scoreManager.getScores().size());
    }


}
