package nl.tudelft.item;

import java.util.Random;

/**
 * Factory used to create the items that are placed on the item.
 */
public class ItemFactory {

    /**
     * Constructor for ItemFactory objects.
     */
    public ItemFactory() {};

    /**
     * Creates a new item based on the provided string,
     * ignoring case;
     * "Axe" results in an axe item
     * "Bone" results in a bone item
     * "Eye" results in an eye item
     * "Leaf" results in a leaf item
     * "Mask" results in a mask item
     * "Mouth" results in a mouth item
     * "Sun" results in a sun item
     * Any other string results in a random item
     * @param itemName The string representing the desired item.
     * @return The item corresponding to the input string.
     */
    public Item createItem(final String itemName) {
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
        return createRandomItem();
    }

    /**
     * Ranomly creates one of the following items:
     * Axe, Bone, Eye, Leaf, Mask, Mouth or Sun.
     * @return A random item.
     */
    public Item createRandomItem() {
        switch (new Random().nextInt(7)) {
            case 0: return new Axe();
            case 1: return new Bone();
            case 2: return new Eye();
            case 2 + 1: return new Leaf();
            case 2 + 2: return new Mask();
            case 2 + 2 + 1: return new Mouth();
        }
        return new Sun();
    }
}
