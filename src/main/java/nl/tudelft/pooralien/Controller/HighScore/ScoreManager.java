package nl.tudelft.pooralien.Controller.HighScore;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class ScoreManager {

    private ArrayList<Score> scores;
    private int SCORE_COUNT;
    private final static String SCORE_FILE = "scores.bin";


    public ScoreManager() {
        scores = new ArrayList<>();

        try {
            //If high score files does not exist then create one.
            new File(SCORE_FILE).createNewFile();
            scores = getScores();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * @return ArrayList of sorted scores (highest first).
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
     * Add score to ArrayList and save to file.
     */
    public void addScore(String name, int score) {
        try {
            loadScores();
            scores.add(new Score(name, score));
            SCORE_COUNT++;
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
            SCORE_COUNT = 0;

            while(!endOfFile) {
                try {
                    tempName = scoreStream.readUTF();
                    tempScore = scoreStream.readInt();
                    scores.add(new Score(tempName, tempScore));
                    SCORE_COUNT++;
                } catch (EOFException e) {
                    endOfFile = true;
                }
            }
            scoreStream.close();

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("ScoreManager, there are currently no scores saved: "
                    + e.getMessage());
        } catch(IOException e) {
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

            for(Score score : scores) {
                scoreWriter.writeUTF(score.getName());
                scoreWriter.writeInt(score.getScore());
            }
            scoreWriter.close();
        } catch (IOException e) {
            throw new IOException("ScoreManager there was a problem writing to the file: "
                    + e.getMessage());
        }
    }

    public ArrayList<Score> getTopTenScores() {
        ArrayList<Score> topTenScores = new ArrayList<>();
        int scoreCount = 10;

        if(SCORE_COUNT < scoreCount) {
            scoreCount = SCORE_COUNT;
        }

        try {
            scores = getScores();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < scoreCount; i++) {
            topTenScores.add(scores.get(i));
        }
        return topTenScores;
    }

    /**
     * @return LINE_COUNT, amount of scores saved.
     */
    public int getSCORE_COUNT() {
        return SCORE_COUNT;
    }
}
