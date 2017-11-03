package nl.tudelft.pooralien.Controller;

import nl.tu.delft.defpro.exception.NotExistingVariableException;
import nl.tudelft.item.StandardItemFactory;
import nl.tudelft.pooralien.Launcher;

/**
 * Board for hard mode.
 */
public class HardBoard extends Board {

    /**
     * Initializes the width of the board using the config file.
     * @return The initial width of the board from the config file.
     */
    protected int initWidth() {
        final int min = 5;
        final int max = 20;
        final int standard = 10;

        return GameConfig.getInteger("maxBoardWidth", min, max, standard);
    }

    /**
     * Initializes the height of the board using the config file.
     * @return The initial height of the board from the config file.
     */
    protected int initHeight() {
        final int min = 5;
        final int max = 20;
        final int standard = 10;

        return GameConfig.getInteger("maxBoardHeight", min, max, standard);
    }

    /**
     * Initializes the minimum group size using the config file.
     * @return The minimum group size from the config file.
     */
    protected int initMinGroupSize() {
        final int min = 2;
        final int max = this.initWidth() - min;
        final int standard = 4;

        return GameConfig.getInteger("minItemsInRowHard", min, max, standard);
    }

    /**
     * Initializes the ItemFactory.
     * @return The initial ItemFactory.
     */
    protected StandardItemFactory initItemFactory() {
        return new StandardItemFactory();
    }
}
