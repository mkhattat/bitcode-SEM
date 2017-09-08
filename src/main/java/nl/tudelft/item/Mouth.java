package nl.tudelft.item;

/**
 * The mouth item.
 */
public class Mouth implements Item {

    /**
     * Constructor for Mouth items.
     */
    Mouth() { };

    /**
     * Retrieves the location of the Mouth sprite.
     * @return The location of the mouth sprite as a String.
     */
    public String getSprite() {
        return "MOUTH";
    }
}
