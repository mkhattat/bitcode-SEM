package nl.tudelft.item;

import java.util.Random;

/**
 * Interface for ItemFactories.
 */
public interface ItemFactory {

    /**
     * Resets the upper and lower bound to the default values.
     * Also re-initializes the intGen.
     */
    void resetRandom();

    /**
     * Retrieves the amount of different items.
     * @return The amount of different items.
     */
    int getItemCount();

    /**
     * Retrieves the lower bound used for generating random items.
     * @return The lower bound used for generating random items.
     */
    int getLowerBound();

    /**
     * Sets the lower bound used for generating random items.
     * @param newLowerBound The new lower bound used for generating random items.
     * @throws IllegalArgumentException Is thrown if the new lower bound is larger than
     * or equal to the current upper bound, or if the new lower bound is negative.
     */
    void setLowerBound(int newLowerBound) throws IllegalArgumentException;

    /**
     * Retrieves the upper bound used for generating random items.
     * @return The upper bound used for generating random items.
     */
    int getUpperBound();

    /**
     * Sets the upper bound used for generating random items.
     * @param newUpperBound The new upper bound used for generating random items.
     * @throws IllegalArgumentException Is thrown if the new upper bound is smaller than
     * or equal to the current lower bound, or if the new upper bound is negative.
     */
    void setUpperBound(int newUpperBound) throws IllegalArgumentException;

    /**
     * Retrieves the random int generator used for generating random items.
     * @return The random int generator used for generating random items.
     */
    Random getIntGen();

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
    Item createItem(String itemName);
}
