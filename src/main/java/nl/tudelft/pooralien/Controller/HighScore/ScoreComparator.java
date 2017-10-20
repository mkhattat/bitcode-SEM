package nl.tudelft.pooralien.Controller.HighScore;

import java.util.Comparator;

public class ScoreComparator implements Comparator<Score> {


    @Override
    public int compare(Score o1, Score o2) {

        int Score1 = o1.getScore();
        int Score2 = o2.getScore();
        //Returns: the value 0 if Score1 == Score2;
        // a value less than 0 if Score1 < Score2;
        // and a value greater than 0 if Score1 > Score2
        return Integer.compare(Score2, Score1);
    }
}
