package nl.tudelft.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Factory used to create the items that are placed on the board.
 */
public class StandardItemFactory implements ItemFactory {

    /**
     * Random used to generate random items by generating random ints.
     * These ints will be between the lower bound (inclusive) and the upper bound (exclusive).
     */
    private Random intGen;

    /**
     * Array containing the itemnames this factory can use.
     */
    private ArrayList<String> itemNames;

    /**
     * HashMap containing the items this factory can possibly create.
     */
    private HashMap<String, Item> itemHashMap;

    /**
     * Constructor for StandardItemFactory objects.
     */
    public StandardItemFactory() {
        itemHashMap = new HashMap<>();
        itemHashMap.put("axe", new Axe());
        itemHashMap.put("bone", new Bone());
        itemHashMap.put("eye", new Eye());
        itemHashMap.put("leaf", new Leaf());
        itemHashMap.put("mask", new Mask());
        itemHashMap.put("mouth", new Mouth());
        itemHashMap.put("sun", new Sun());

        itemNames = new ArrayList<>();
        itemNames.add("axe");
        itemNames.add("bone");
        itemNames.add("eye");
        itemNames.add("leaf");
        itemNames.add("mask");
        itemNames.add("mouth");
        itemNames.add("sun");

        intGen = new Random();
    }

    /**
     * Retrieves the amount of different items.
     * @return The amount of different items.
     */
    public int getItemCount() {
        return itemHashMap.size();
    }

    /**
     * Randomly creates one of the following items:
     * Axe, Bone, Eye, Leaf, Mask, Mouth or Sun.
     * @return A random item.
     */
    public Item createRandomItem() {
        int r = intGen.nextInt(getItemCount());
        return itemHashMap.get(itemNames.get(r));
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
        Item res = itemHashMap.get(itemName.toLowerCase());
        if (res == null) {
            throw new IllegalArgumentException("The provided item name: " + itemName
                    + "\nDoes not match any of the following: "
                    + "axe, bone, eye, leaf, mask, mouth or sun.");
        }
        return res;
    }
}
