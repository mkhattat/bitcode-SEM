package nl.tudelft.pooralien.Controller;

import nl.tudelft.item.ItemFactory;

/**
 * Factory class used to create hard boards.
 */
public class HardBoardFactory implements BoardFactory {

    /**
     * Creates a HardBoard using the provided layout.
     * @param layout A string representation of the layout.
     * @return A HardBoard based on the provided layout.
     * @throws IllegalArgumentException If the provided layout
     * cannot be used to create a HardBoard.
     */
    @Override
    public HardBoard createBoard(String layout) throws IllegalArgumentException {
        HardBoard res = new HardBoard();
        int width = res.getWidth();
        int height = res.getHeight();
        String[] items = layout.split("\\s");

        if (items.length != width * height) {
            throw new IllegalArgumentException(
                    "The provided board layout contained "
                            + items.length + " items, but should contain "
                            + width * height + " items."
            );
        }

        ItemFactory iFactory = res.getItemFactory();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                res.setItem(iFactory.createItem(items[x * width + y]), x, y);
            }
        }
        return res;
    }

    /**
     * Creates a HardBoard with a random layout.
     * @return A randomly filled HardBoard.
     */
    @Override
    public HardBoard createRandomBoard() {
        HardBoard res = new HardBoard();
        res.createRandom();
        return res;
    }
}
