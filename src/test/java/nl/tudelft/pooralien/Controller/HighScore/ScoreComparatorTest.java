package nl.tudelft.pooralien.Controller.HighScore;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ScoreComparatorTest {

    private ArrayList<Score> randomScoreArray;
    private ArrayList<Score> orderedScoreArray;
    private ScoreComparator scoreComparator;

    private Score score0;
    private Score score1;
    private Score score2;
    private Score score3;
    private Score score4;
    private Score score5;
    private Score score6;
    private Score score7;
    private Score score8;
    private Score score9;

    @Before
    public void setup() {
        randomScoreArray = new ArrayList<>();
        orderedScoreArray = new ArrayList<>();
        scoreComparator = new ScoreComparator();
        score0 = new Score("Name0", 0);
        score1 = new Score("Name1", 1);
        score2 = new Score("Name2", 2);
        score3 = new Score("Name3", 3);
        score4 = new Score("Name4", 4);
        score5 = new Score("Name5", 5);
        score6 = new Score("Name6", 6);
        score7 = new Score("Name7", 7);
        score8 = new Score("Name8", 8);
        score9 = new Score("Name9", 9);

        orderedScoreArray.add(score9);
        orderedScoreArray.add(score8);
        orderedScoreArray.add(score7);
        orderedScoreArray.add(score6);
        orderedScoreArray.add(score5);
        orderedScoreArray.add(score4);
        orderedScoreArray.add(score3);
        orderedScoreArray.add(score2);
        orderedScoreArray.add(score1);
        orderedScoreArray.add(score0);

    }

    @Test
    public void sortOrderedArrayList() {
        randomScoreArray.add(score0);
        randomScoreArray.add(score1);
        randomScoreArray.add(score2);
        randomScoreArray.add(score3);
        randomScoreArray.add(score4);
        randomScoreArray.add(score5);
        randomScoreArray.add(score6);
        randomScoreArray.add(score7);
        randomScoreArray.add(score8);
        randomScoreArray.add(score9);
        randomScoreArray.sort(scoreComparator);
        assertEquals("Expected to be sorted the same.", randomScoreArray, orderedScoreArray);
    }

    @Test
    public void sortFromNineToZeroSorted() {
        randomScoreArray.add(score0);
        randomScoreArray.add(score7);
        randomScoreArray.add(score5);
        randomScoreArray.add(score4);
        randomScoreArray.add(score8);
        randomScoreArray.add(score2);
        randomScoreArray.add(score1);
        randomScoreArray.add(score3);
        randomScoreArray.add(score6);
        randomScoreArray.add(score9);
        randomScoreArray.sort(scoreComparator);
        assertEquals("Expected to be equal, as the list has been sorted.",
                randomScoreArray, orderedScoreArray);
    }

    @Test
    public void sortFromNineToZeroUnSorted() {
        randomScoreArray.add(score0);
        randomScoreArray.add(score7);
        randomScoreArray.add(score5);
        randomScoreArray.add(score4);
        randomScoreArray.add(score8);
        randomScoreArray.add(score2);
        randomScoreArray.add(score1);
        randomScoreArray.add(score3);
        randomScoreArray.add(score6);
        randomScoreArray.add(score9);
        assertNotEquals("Expected not to be equal, as the list has not yet been sorted.",
                randomScoreArray, orderedScoreArray);
    }

    @Test
    public void sortEmptyList() {
        randomScoreArray.clear();
        orderedScoreArray.clear();
        randomScoreArray.sort(scoreComparator);
        assertEquals("Expected two empty ArrayLists, to be the same",
                randomScoreArray, orderedScoreArray);
    }
}
