package nl.tudelft.pooralien.Controller;

import nl.tu.delft.defpro.exception.NotExistingVariableException;
import nl.tudelft.pooralien.Launcher;

/**
 * ScoreCounter keeps track of the score of the player.
 * The score should be reset every level.
 */
public class ScoreCounter {

    private int score;
    private int scorePerTile;
    private int scorePerBackgroundTile;

    /**
     * Standard initialization of the class.
     * @param score will be the value that the player starts with.
     */
    ScoreCounter(int score) {
        this.score = score;
        try {
            this.scorePerTile = Launcher.getGameCfg().getIntegerValueOf("scorePerTile");
            this.scorePerBackgroundTile =
                    Launcher.getGameCfg().getIntegerValueOf("scorePerBackgroundTile");
        } catch (NotExistingVariableException e) {
            e.printStackTrace();
            this.scorePerTile = 1;
            this.scorePerBackgroundTile = 2 + 2 + 2 + 2;
        }
    }

    /**
     * Pre: tilesDestroyed is bigger than zero.
     * Updates the score by multiplying the tilesDestroyed by,
     * up rounded square root of scorePerTile.
     * @param tilesRemoved is the amount of tiles the player has destroyed, in one single move.
     */
    public void updateScoreTilesRemoved(int tilesRemoved) {
        if (tilesRemoved > 0) {
            this.score = this.score
                    + (tilesRemoved * (int) (Math.round(Math.sqrt((double) scorePerTile))));
        }
    }

    /**
     * Updates score with one backgroundTile removed.
     */
    public void updateScoreBackgroundTileRemoved() {
        this.score = this.score + (scorePerTile * scorePerBackgroundTile);
    }

    /**
     * @return Returns the current score of the player.
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Pre: newScore is bigger or equal to zero.
     * Sets score to newScore.
     * @param newScore will replace the value of the integer score of the ScoreCounter object.
     */
    public void setScore(int newScore) {
        if (newScore >= 0) {
            this.score = newScore;
        }
    }

    /**
     * Pre: scorePerTile is bigger than zero.
     * Sets scorePerTile to scorePerTile.
     * @param scorePerTile will set the multiplier with which the destroyed tiles are multiplied.
     */
    public void setScorePerTile(int scorePerTile) {
        if (scorePerTile > 0) {
            this.scorePerTile = scorePerTile;
        }
    }

    /**
     * @return scorePerTile which is used to multiply the amount of tiles destroyed for the score.
     */
    public int getScorePerTile() {
        return scorePerTile;
    }

    /**
     * @return scorePerBackgroundTile which is used to calculate the score.
     */
    public int getScorePerBackgroundTile() {
        return scorePerBackgroundTile;
    }
}