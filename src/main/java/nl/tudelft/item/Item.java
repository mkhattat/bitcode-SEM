package nl.tudelft.item;

/**
 * Item interface, these are the items that will be placed on the item.
 */
public interface Item {

    /**
     * Retrieves the location of the sprite that matches the item.
     * @return The location of the sprite that matches the item as a String.
     */
    String getSprite();
}
