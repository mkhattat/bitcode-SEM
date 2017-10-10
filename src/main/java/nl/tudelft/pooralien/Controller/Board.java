package nl.tudelft.pooralien.Controller;

import nl.tudelft.item.Item;

public interface Board {
    /**
     * Retrieves the width of the board.
     * @return The width of the board.
     */
    int getWidth();

    /**
     * Retrieves the height of the board.
     * @return The height of the board.
     */
    int getHeight();

    /**
     * Retrieves the minimum amount of items required in a group
     * for that group to be removed.
     * @return The minimum group size.
     */
    int getMinGroupSize();

    /**
     * Set the item at the specified position to the provided item.
     * @param newItem The new item.
     * @param x The X-Coordinate on the board.
     * @param y The Y-Coordinate on the board.
     * @return True iff the set succeeded.
     */
    boolean setItem(Item newItem, int x, int y);

    /**
     * Retrieve the item at the specified position on the board.
     * @param x The X-Coordinate on the board.
     * @param y The Y-Coordinate on the board.
     * @return The item at the provided position.
     * @throws IllegalArgumentException Iff the position is outside the board's bounds.
     */
    Item getItem(int x, int y) throws IllegalArgumentException;

    /**
     * Checks the board for any groups that should be removed,
     * and removed those groups iff they exist.
     * @return True iff the board has changed.
     */
    boolean removeGroups();
}
