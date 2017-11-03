package nl.tudelft.pooralien.Controller;

import nl.tudelft.item.ItemFactory;

/**
 * Factory used to create easy boards.
 */
public class EasyBoardFactory implements BoardFactory {

    /**
     * Creates an EasyBoard using the provided layout.
     * @param layout A string representation of the layout.
     * @return An EasyBoard based on the provided layout.
     * @throws IllegalArgumentException If the provided layout
     * cannot be used to create an EasyBoard.
     */
    @Override
    public EasyBoard createBoard(String layout) throws IllegalArgumentException {
        EasyBoard res = new EasyBoard();
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
     * Creates an EasyBoard with a random layout.
     * @return A randomly filled EasyBoard.
     */
    @Override
    public EasyBoard createRandomBoard() {
        EasyBoard res = new EasyBoard();
        res.createRandom();
        return res;
    }
}
