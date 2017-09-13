package nl.tudelft.item;

/**
 * The sun item.
 */
public class Sun implements Item {

    /**
     * Constructor for Sun items.
     */
    Sun() { };

    /**
     * Retrieves the location of the Sun sprite.
     * @return The location of the sun sprite as a String.
     */
    public String getSprite() {
        return "SUN";
    }
}
