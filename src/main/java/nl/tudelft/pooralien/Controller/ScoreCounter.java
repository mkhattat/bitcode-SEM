package nl.tudelft.pooralien.Controller;

/**
 * ScoreCounter keeps track of the score of the player.
 * The score should be reset every level.
 */
public class ScoreCounter {

    private int score;
    private int scorePerTile = 1;

    /**
     * Standard initialization of the class.
     * @param score will be the value that the player starts with.
     */
    ScoreCounter(int score) {
        this.score = score;
    }

    /**
     * Pre: tilesDestroyed is bigger than zero.
     * Updates the score by multiplying the tilesDestroyed by,
     * up rounded square root of scorePerTile.
     * And gives a bonus for destroying background tiles.
     * @param tilesDestroyed is the amount of tiles the player has destroyed, in one single move.
     * @param backgroundTilesDestroyed is the amount of backgroundTiles the player has destroyed,
     *                                 in a single move.
     */
    public void updateScore(int tilesDestroyed, int backgroundTilesDestroyed) {
        if (tilesDestroyed > 0) {
            this.score = this.score
                    + (tilesDestroyed * (int) (Math.round(Math.sqrt((double) scorePerTile))));
        }
        if (backgroundTilesDestroyed > 0) {
            this.score = this.score
                    + (backgroundTilesDestroyed * scorePerTile * (2 + 2 + 2 + 2));
        }
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
}