package nl.tudelft.item;

import java.util.Random;

/**
 * Factory used to create the items that are placed on the board.
 */
public class StandardItemFactory implements ItemFactory {
    /**
     * The amount of different types of items.
     */
    private final int itemCount;

    /**
     * The lower bound used for randomly generating items.
     */
    private int lowerBound;

    /**
     * The upper bound used for randomly generating items.
     */
    private int upperBound;

    /**
     * Random used to generate random items by generating random ints.
     * These ints will be between the lower bound (inclusive) and the upper bound (exclusive).
     */
    private Random intGen;

    /**
     * Constructor for StandardItemFactory objects.
     */
    public StandardItemFactory() {
        itemCount = 2 + 2 + 2 + 1;
        resetRandom();
    }

    /**
     * Resets the upper and lower bound to the default values.
     * Also re-initializes the intGen.
     */
    public void resetRandom() {
        lowerBound = 0;
        upperBound = itemCount;
        intGen = new Random();
    }

    /**
     * Retrieves the amount of different items.
     * @return The amount of different items.
     */
    public int getItemCount() {
        return itemCount;
    }

    /**
     * Retrieves the lower bound used for generating random items.
     * @return The lower bound used for generating random items.
     */
    public int getLowerBound() {
        return lowerBound;
    }

    /**
     * Sets the lower bound used for generating random items.
     * @param newLowerBound The new lower bound used for generating random items.
     * @throws IllegalArgumentException Is thrown if the new lower bound is larger than
     * or equal to the current upper bound, or if the new lower bound is negative.
     */
    public void setLowerBound(int newLowerBound) throws IllegalArgumentException {
        if (newLowerBound >= upperBound) {
            throw new IllegalArgumentException(
                    "The lower bound may not be larger than or equal to the upper bound."
                            + "\nCurrent upper bound: " + upperBound
                            + ", new lower bound: " + newLowerBound + ".");
        }
        if (newLowerBound < 0) {
            throw new IllegalArgumentException("The lower bound may not be negative."
                    + "\nThe new lower bound: " + newLowerBound + ".");
        }
        lowerBound = newLowerBound;
    }

    /**
     * Retrieves the upper bound used for generating random items.
     * @return The upper bound used for generating random items.
     */
    public int getUpperBound() {
        return upperBound;
    }

    /**
     * Sets the upper bound used for generating random items.
     * @param newUpperBound The new upper bound used for generating random items.
     * @throws IllegalArgumentException Is thrown if the new upper bound is smaller than
     * or equal to the current lower bound, or if the new upper bound is negative.
     */
    public void setUpperBound(int newUpperBound) throws IllegalArgumentException {
        if (newUpperBound < 0) {
            throw new IllegalArgumentException("The upper bound may not be negative."
                    + "\nThe new upper bound: " + newUpperBound + ".");
        }
        if (newUpperBound <= lowerBound) {
            throw new IllegalArgumentException(
                    "The upper bound may not be smaller than or equal to the lower bound."
                            + "\nNew upper bound: " + newUpperBound
                            + ", current lower bound: " + lowerBound + ".");
        }
        upperBound = newUpperBound;
    }

    /**
     * Retrieves the random int generator used for generating random items.
     * @return The random int generator used for generating random items.
     */
    public Random getIntGen() {
        return intGen;
    }

    /**
     * Randomly creates one of the following items:
     * Axe, Bone, Eye, Leaf, Mask, Mouth or Sun.
     * @return A random item.
     */
    public Item createRandomItem() {
        int r = (intGen.nextInt(upperBound - lowerBound) + lowerBound) % itemCount;
        switch (r) {
            case 0: return new Axe();
            case 1: return new Bone();
            case 2: return new Eye();
            case 2 + 1: return new Leaf();
            case 2 + 2: return new Mask();
            case 2 + 2 + 1: return new Mouth();
            default: return new Sun();
        }
    }

    /**
     * Creates a new item based on the provided string (ignoring case).
     * "Axe" results in an axe item
     * "Bone" results in a bone item
     * "Eye" results in an eye item
     * "Leaf" results in a leaf item
     * "Mask" results in a mask item
     * "Mouth" results in a mouth item
     * "Sun" results in a sun item
     * @param itemName The string representing the desired item.
     * @return The item corresponding to the input string.
     * @throws IllegalArgumentException In case the provided string does not match any item name.
     */
    public Item createItem(final String itemName) throws IllegalArgumentException {
        if (itemName.equalsIgnoreCase("axe")) {
            return new Axe();
        } else if (itemName.equalsIgnoreCase("bone")) {
            return new Bone();
        } else if (itemName.equalsIgnoreCase("eye")) {
            return new Eye();
        } else if (itemName.equalsIgnoreCase("leaf")) {
            return new Leaf();
        } else if (itemName.equalsIgnoreCase("mask")) {
            return new Mask();
        } else if (itemName.equalsIgnoreCase("mouth")) {
            return new Mouth();
        } else if (itemName.equalsIgnoreCase("sun")) {
            return new Sun();
        }
        throw new IllegalArgumentException("The provided item name: " + itemName
                + "\nDoes not match any of the following: "
                + "axe, bone, eye, leaf, mask, mouth or sun.");
    }
}