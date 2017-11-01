package nl.tudelft.pooralien.Controller;

import nl.tu.delft.defpro.exception.NotExistingVariableException;
import nl.tudelft.item.EasyItemFactory;
import nl.tudelft.pooralien.Launcher;

/**
 * Board for easy mode.
 */
public class EasyBoard extends Board {
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
     * Initializes the EasyItemFactory.
     * @return The initial EasyItemFactory.
     */
    protected EasyItemFactory initItemFactory() {
        return new EasyItemFactory();
    }
}
