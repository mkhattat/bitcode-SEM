package nl.tudelft.pooralien.Controller;
import nl.tu.delft.defpro.exception.NotExistingVariableException;
import nl.tudelft.item.StandardItemFactory;
import nl.tudelft.pooralien.Launcher;



/**
 * The StandardBoard class.
 */
public class StandardBoard extends Board {

    /**
     * Initializes the width of the board using the config file.
     * @return The initial width of the board from the config file.
     */
    protected int initWidth() {
        return GameConfig.getInteger("maxBoardWidth", 5, 20, 10);
    }

    /**
     * Initializes the height of the board using the config file.
     * @return The initial height of the board from the config file.
     */
    protected int initHeight() {
        return GameConfig.getInteger("maxBoardHeight", 5, 20, 10);
    }

    /**
     * Initializes the minimum group size using the config file.
     * @return The minimum group size from the config file.
     */
    protected int initMinGroupSize() {
        return GameConfig.getInteger("minItemsInRow", 2, this.initWidth(), 3);
    }

    /**
     * Initializes the StandardItemFactory.
     * @return The initial StandardItemFactory.
     */
    protected StandardItemFactory initItemFactory() {
        return new StandardItemFactory();
    }
}
