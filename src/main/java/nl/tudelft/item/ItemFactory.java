package nl.tudelft.item;

/**
 * Interface for ItemFactories.
 */
public interface ItemFactory {

    /**
     * Retrieves the amount of different items.
     * @return The amount of different items.
     */
    int getItemCount();

    /**
     * Randomly creates an item.
     * @return A random item.
     */
    Item createRandomItem();

    /**
     * Creates a new item based on the provided string (ignoring case).
     * @param itemName The string representing the desired item.
     * @return The item corresponding to the input string.
     * @throws IllegalArgumentException In case the provided string does not match any item name.
     */
    Item createItem(String itemName) throws IllegalArgumentException;
}
