package nl.tudelft.item;

/**
 * The leaf item.
 */
public class Leaf implements Item {

    /**
     * Constructor for Leaf items.
     */
    Leaf() {};

    /**
     * Retrieves the location of the Leaf sprite.
     * @return The location of the leaf sprite as a String.
     */
    public String getSprite() {
        return "LEAF";
    }
}
