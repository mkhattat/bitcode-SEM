package nl.tudelft.pooralien.Controller.HighScore;

/**
 * Score class which is used for the high score.
 */
class Score {

    private String name;
    private int score;

    /**
     * Construct the Score object.
     * @param name of the player.
     * @param score of the player.
     */
    Score(String name, int score) {
        if (name == null) {
            throw new IllegalArgumentException("name should be a String");
        }

        this.name = name;
        this.score = score;
    }

    /**
     * @return String of the player's name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Integer of the player's score.
     */
    public int getScore() {
        return score;
    }
}
