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
import java.util.Collections;

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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NotExistingVariableException e) {
            topXScores = 2 + 2 + 2 + 2 + 2;
            e.printStackTrace();
        }

    }

    /**
     * @return ArrayList of sorted scores (highest first).
     * @throws IOException Input/Output exception.
     * @throws ClassNotFoundException Class not found exception.
     */
    public ArrayList<Score> getScores() throws IOException, ClassNotFoundException {
        loadScores();
        sortScores();
        return scores;
    }

    private void sortScores() {
        ScoreComparator scoreComparator = new ScoreComparator();
        Collections.sort(scores, scoreComparator);
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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads all the current highscores into the scores ArrayList.
     * @throws IOException Signals that an I/O exception of some sort has occurred.
     * @throws ClassNotFoundException Signals that an class was not found.
     */
    private void loadScores() throws IOException, ClassNotFoundException {
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
            new File(SCORE_FILE).delete();
            new File(SCORE_FILE).createNewFile();

            FileOutputStream scoreFile = new FileOutputStream(SCORE_FILE);
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

        try {
            scores = getScores();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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
