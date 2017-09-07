package nl.tudelft.board;

/**
 * Created by DexterQuintin on 9/7/2017.
 */
public class Item {
    private int id;

    /**
     * Constructor for Item objects.
     * @param id The id of the item.
     */
    public Item(int id) {
        this.id = id;
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
    public void setId(int newId) {
       id = newId;
    }

    /**
     * Equals function for Item objects.
     * @param other The object to compare to.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
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
