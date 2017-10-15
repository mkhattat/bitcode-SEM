package nl.tudelft.pooralien.Controller.HighScore;

import java.util.Comparator;

/**
 * ScoreComparator, which allows easy use of the Comparator object/class.
 */
public class ScoreComparator implements Comparator<Score> {

    @Override
    public int compare(Score o1, Score o2) {
        int firstScore = o1.getScore();
        int secondScore = o2.getScore();
        //Returns: the value 0 if Score1 == Score2;
        // a value less than 0 if Score1 < Score2;
        // and a value greater than 0 if Score1 > Score2
        return Integer.compare(secondScore, firstScore);
    }
}
