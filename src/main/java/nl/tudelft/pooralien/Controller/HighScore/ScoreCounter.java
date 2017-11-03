package nl.tudelft.pooralien.Controller.HighScore;

import nl.tudelft.pooralien.Controller.Game;
import nl.tudelft.pooralien.Controller.GameConfig;

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
    public ScoreCounter(int score) {
        this.score = score;

        final int minScorePerTile = 1;
        final int maxScorePerTile = Integer.MAX_VALUE - minScorePerTile;
        final int defaultScorePerTile = 1;
        final int minScorePerBackgroundTile = 2;
        final int maxScorePerBackgroundTile = Integer.MAX_VALUE;
        final int defaultScorePerBackgroundTile = 6;

        this.scorePerTile = GameConfig.getInteger("scorePerTile", minScorePerTile,
                maxScorePerTile, defaultScorePerTile);
        this.scorePerBackgroundTile = GameConfig.getInteger("scorePerBackgroundTile",
                minScorePerBackgroundTile, maxScorePerBackgroundTile,
                defaultScorePerBackgroundTile);
    }

    /**
     * Pre: tilesDestroyed is bigger than zero.
     * Updates the score by multiplying the tilesDestroyed by,
     * up rounded square root of scorePerTile.
     * @param tilesRemoved is the amount of tiles the player has destroyed, in one single move.
     */
    public void updateScoreTilesRemoved(int tilesRemoved) {
        if (!(Game.getGame().gameIsRunning())) {
            return;
        }
        if (tilesRemoved > 0) {
            this.score = this.score
                    + (tilesRemoved * (int) (Math.round(Math.sqrt((double) scorePerTile))));
        }
    }

    /**
     * Updates score with one backgroundTile removed.
     */
    public void updateScoreBackgroundTileRemoved() {
        if (!(Game.getGame().gameIsRunning())) {
            return;
        }
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
