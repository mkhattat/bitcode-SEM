package nl.tudelft.pooralien.Controller;
import nl.tu.delft.defpro.exception.NotExistingVariableException;
import nl.tudelft.item.ItemFactory;
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
        try {
            return Launcher.getGameCfg().getIntegerValueOf("maxBoardWidth");
        } catch (NotExistingVariableException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Initializes the height of the board using the config file.
     * @return The initial height of the board from the config file.
     */
    protected int initHeight() {
        try {
            return Launcher.getGameCfg().getIntegerValueOf("maxBoardHeight");
        } catch (NotExistingVariableException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Initializes the minimum group size using the config file.
     * @return The minimum group size from the config file.
     */
    protected int initMinGroupSize() {
        try {
            return Launcher.getGameCfg().getIntegerValueOf("minItemsInRow");
        } catch (NotExistingVariableException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Initializes the StandardItemFactory.
     * @return The initial StandardItemFactory.
     */
    protected StandardItemFactory initItemFactory() {
        return new StandardItemFactory();
    }
}
