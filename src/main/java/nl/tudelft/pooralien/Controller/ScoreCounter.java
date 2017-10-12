package nl.tudelft.pooralien.Controller;

import nl.tu.delft.defpro.exception.NotExistingVariableException;
import nl.tudelft.pooralien.Launcher;

import java.util.ArrayList;

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
            this.scorePerBackgroundTile = Launcher.getGameCfg().getIntegerValueOf("scorePerBackgroundTile");
        } catch (NotExistingVariableException e) {
            e.printStackTrace();
            this.scorePerTile = 1;
            this.scorePerBackgroundTile = 2+2+2+2;
        }
    }
    /**
     * Update the scoreCounter object with the amount of tiles & background tiles that are removed.
     * @param foundedItems ArrayList containing the x coordinate of the tiles.
     * @param x the int of the column where items were removed.
     */
    public void calculateTilesRemovedX(ArrayList<Integer> foundedItems, int x) {
        // Find amount of backgroundTiles destroyed
        int backgroundTilesDestroyed = 0;
        for (Integer index : foundedItems) {
            if (Game.getGame().getBackgroundTileCatalog().contains(x, index)) {
                backgroundTilesDestroyed++;
            }
        }
        // Update the score
        this.updateScore(foundedItems.size(), backgroundTilesDestroyed);
    }

    /**
     * Update the scoreCounter object with the amount of tiles & background tiles that are removed.
     * @param foundedItems ArrayList containing the x coordinate of the tiles.
     * @param y the int of the column where items were removed.
     */
    public void calculateTilesRemovedY(ArrayList<Integer> foundedItems, int y) {
        // Find amount of backgroundTiles destroyed
        int backgroundTilesDestroyed = 0;
        for (Integer index : foundedItems) {
            if (Game.getGame().getBackgroundTileCatalog().contains(index, y)) {
                backgroundTilesDestroyed++;
            }
        }
        // Update the score
        updateScore(foundedItems.size(), backgroundTilesDestroyed);
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
                    + (backgroundTilesDestroyed * scorePerTile * scorePerBackgroundTile);
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