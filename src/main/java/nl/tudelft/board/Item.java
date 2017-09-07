package nl.tudelft.board;

/**
 * Item class, these are the items that will be placed on the board.
 */
public class Item {
    /**
     * The id of the item, this indicates the type of item,
     * and also the sprite that matches the item.
     */
    private int id;

    /**
     * Constructor for Item objects.
     * @param itemID The id of the item.
     */
    Item(final int itemID) {
        id = itemID;
    }

    /**
     * Retrieves the id of the Item.
     * @return The id of the item.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of the item.
     * @param newId The new id of the item.
     */
    public void setId(final int newId) {
       id = newId;
    }

    /**
     * Equals function for Item objects.
     * @param other The object to compare to.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(final Object other) {
        if (other instanceof Item) {
            Item that = (Item) other;
            return that.id == this.id;
        }
        return false;
    }

    /**
     * HashCode function for Item objects.
     * @return The HashCode of the Item.
     */
    @Override
    public int hashCode() {
        return id;
    }
}
