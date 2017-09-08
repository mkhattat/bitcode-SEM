package nl.tudelft.item;

/**
 * The bone item.
 */
public class Bone implements Item {

    /**
     * Constructor for Bone items.
     */
    Bone() { };

    /**
     * Retrieves the location of the Bone sprite.
     * @return The location of the bone sprite as a String.
     */
    public String getSprite() {
        return "BONE";
    }
}
