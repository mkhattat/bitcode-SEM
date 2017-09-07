package nl.tudelft.pooralien;

class ScoreCounter {

    private int score;
    private int scorePerTile = 1;

    /**
     * Standard initialization
     * @param score
     */
    void ScoreCounter(int score) {
        this.score = score;
    }

    /**
     * Pre: tilesDestroyed is bigger than 0
     * Updates the score by multiplying the tilesDestroyed by scorePerTile
     * @param tilesDestroyed
     */
    public void updateScore(int tilesDestroyed) {
        if(tilesDestroyed > 0) {
            this.score = this.score + (tilesDestroyed * this.scorePerTile);
        }
    }

    /**
     * Returns the current score of the player
     * @return
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Pre: newScore is bigger or equal to 0
     * Sets score to newScore
     * @param newScore
     */
    public void setScore(int newScore) {
        if(newScore >= 0) {
            this.score = newScore;
        }
    }

    /**
     * Pre: scorePerTile is bigger than 0
     * Sets scorePerTile to scorePerTile
     * @param scorePerTile
     */
    public void setScorePerTile(int scorePerTile) {
        if(scorePerTile > 0) {
            this.scorePerTile = scorePerTile;
        }
    }
}