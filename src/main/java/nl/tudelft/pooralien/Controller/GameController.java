package nl.tudelft.pooralien.Controller;

/**
 * Created by Ivo Schols on 11-9-2017.
 */
public class GameController {

    private BackgroundTileCatalog backgroundTileCatalog;
    private Boolean inProgress;
    private int movesLeft;


    public GameController(BackgroundTileCatalog backgroundTileCatalog, int movesLeft) {
        this.backgroundTileCatalog = backgroundTileCatalog;
        this.movesLeft = movesLeft;
    }

    /**
     * @return True if the player has no moves left or no background tiles are left.
     */
    public boolean hasGameEnded() {
        if (movesLeft > 0) {
            return false;
        }
        if (backgroundTileCatalog.size() > 0) {
            return false;
        }
        return true;
    }

    public void endGame() {

    }

    public void setInProgress(Boolean inProgress) {
        this.inProgress = inProgress;
    }

    public Boolean getInProgress() {
        return inProgress;
    }
}
