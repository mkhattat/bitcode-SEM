package nl.tudelft.pooralien.Controller.HighScore;

import nl.tu.delft.defpro.exception.NotExistingVariableException;
import nl.tudelft.pooralien.Launcher;

import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * ScoreManager class, handles adding/retrieving/saving/loading of scores.
 */
public class ScoreManager {

    private ArrayList<Score> scores;
    private static final String SCORE_FILE = "scores.bin";
    private int topXScores;

    /**
     * Initialize the score manager, load the scores from the save file or generate a new save file.
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public ScoreManager() {
        scores = new ArrayList<>();

        try {
            //If high score files does not exist then create one.
            new File(SCORE_FILE).createNewFile();
            scores = getScores();
            //CONFIG FILE
            topXScores = Launcher.getGameCfg().getIntegerValueOf("topXScores");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NotExistingVariableException e) {
            topXScores = 2 + 2 + 2 + 2 + 2;
            e.printStackTrace();
        }

    }

    /**
     * @return ArrayList of sorted scores (highest first).
     */
    public ArrayList<Score> getScores() {
        try {
            loadScores();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sortScores();
        return scores;
    }

    private void sortScores() {
        ScoreComparator scoreComparator = new ScoreComparator();
        scores.sort(scoreComparator);
    }

    /**
     * Add score to scores ArrayList and save to file.
     * @param name String of the player's name to be added.
     * @param score int of the player's score  to be added.
     */
    public void addScore(String name, int score) {
        try {
            loadScores();
            scores.add(new Score(name, score));
            saveScores();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * !-- SHOULD NEVER BE USED OUTSIDE OF TESTING PURPOSES,
     * !-- AS WRONG SCORES WITH SAME NAME AND SCORE COULD BE DELETED.
     * Delete score from the scores ArrayList and save to file.
     * @param name String of the player's name to be added.
     * @param score int of the player's score  to be added.
     */
    @Deprecated
    public void deleteScore(String name, int score) {
        try {
            loadScores();

            for (Score s : scores) {
                if (s.getName().equals(name) && s.getScore() == score) {
                    scores.remove(s);
                    break;
                }
            }
            saveScores();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads all the current highscores into the scores ArrayList.
     * @throws IOException Signals that an I/O exception of some sort has occurred.
     */
    private void loadScores() throws IOException {
        String tempName;
        int tempScore;
        boolean endOfFile = false;
        scores = new ArrayList<>();

        try {
            FileInputStream scoreFile = new FileInputStream(SCORE_FILE);
            DataInputStream scoreStream = new DataInputStream(scoreFile);

            while (!endOfFile) {
                try {
                    tempName = scoreStream.readUTF();
                    tempScore = scoreStream.readInt();
                    scores.add(new Score(tempName, tempScore));
                } catch (EOFException e) {
                    endOfFile = true;
                }
            }
            scoreStream.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("ScoreManager, there are currently no scores saved: "
                    + e.getMessage());
        } catch (IOException e) {
            throw new IOException(("ScoreManager, there was a problem reading the file: "
                    + e.getMessage()));
        }
    }

    /**
     * Saves the current scores ArrayList as an object to scoreFile.
     * @throws IOException Signals that an I/O exception of some sort has occurred.
     */
    private void saveScores() throws IOException {
        try {
            FileOutputStream scoreFile = new FileOutputStream(SCORE_FILE, false);
            DataOutputStream scoreWriter = new DataOutputStream(scoreFile);

            for (Score score : scores) {
                scoreWriter.writeUTF(score.getName());
                scoreWriter.writeInt(score.getScore());
            }
            scoreWriter.close();
        } catch (IOException e) {
            throw new IOException("ScoreManager there was a problem writing to the file: "
                    + e.getMessage());
        }
    }

    /**
     * Returns the @topXScores saved in the save file.
     * @return ArrayList of Score objects.
     */
    public ArrayList<Score> getTopXScores() {
        ArrayList<Score> topXArrayListScores = new ArrayList<>();

        topXScores = getTopXScoreCount();
        scores = getScores();

        for (int i = 0; i < topXScores; i++) {
            topXArrayListScores.add(scores.get(i));
        }

        return topXArrayListScores;
    }

    /**
     * @return the lowest score in the top x or zero if there are no highscores in the save file.
     */
    public int getLowestScoreInTopX() {
        ArrayList<Score> topXArrayListScores = getTopXScores();

        if (topXArrayListScores.size() < 1) {
            return 0;
        }
        return topXArrayListScores.get(topXArrayListScores.size() - 1).getScore();
    }

    /**
     * @return LINE_COUNT, amount of scores saved.
     */
    public int getSCORE_COUNT() {
        return scores.size();
    }

    /**
     * @return Returns TopX config int except,
     * if there are fewer scores saved then TopX then the amount of those scores is returned.
     */
    public int getTopXScoreCount() {
        if (scores.size() <= topXScores) {
            topXScores = scores.size();
        }
        return topXScores;
    }
}
