package nl.tudelft.pooralien.Controller;

import nl.tudelft.item.Item;
import nl.tudelft.item.ItemFactory;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Abstract board class which implements standard board functionality.
 */
public abstract class Board {
    private int width;
    private int height;
    private int minGroupSize;
    private ItemFactory iFactory;
    private Item[][] items;

    /**
     * Board constructor.
     */
    public Board() {
        width = initWidth();
        height = initHeight();
        minGroupSize = initMinGroupSize();
        iFactory = initItemFactory();
        items = new Item[width][height];
    }

    /**
     * Initializes the width of the board.
     * @return The initial width of the board.
     */
    protected abstract int initWidth();

    /**
     * Initializes the height of the board.
     * @return The initial height of the board.
     */
    protected abstract int initHeight();

    /**
     * Initializes the minimum group size.
     * @return The initial minimum group size.
     */
    protected abstract int initMinGroupSize();

    /**
     * Initializes the ItemFactory.
     * @return The initial ItemFactory.
     */
    protected abstract ItemFactory initItemFactory();

    /**
     * Retrieves the width of the board.
     * @return The width of the board.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Retrieves the height of the board.
     * @return The height of the board.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Retrieves the minimum amount of items required in a group
     * for that group to be removed.
     * @return The minimum group size.
     */
    public int getMinGroupSize() {
        return minGroupSize;
    }

    /**
     * Retrieves the Item Factory of the board.
     * @return The Item Factory of the board.
     */
    public ItemFactory getItemFactory() {
        return iFactory;
    }

    /**
     * Set the item at the specified position to the provided item.
     * @param newItem The new item.
     * @param x The X-Coordinate on the board.
     * @param y The Y-Coordinate on the board.
     * @return True iff the set succeeded.
     */
    public boolean setItem(Item newItem, int x, int y) {
        if (x < width && x >= 0 && y < height && y >= 0) {
            this.items[x][y] = newItem;
            return true;
        }
        return false;
    }

    /**
     * Retrieve the item at the specified position on the board.
     * @param x The X-Coordinate on the board.
     * @param y The Y-Coordinate on the board.
     * @return The item at the provided position.
     * @throws IllegalArgumentException Iff the position is outside the board's bounds.
     */
    public Item getItem(int x, int y) throws IllegalArgumentException {
        if (x >= width || x < 0) {
            throw new IllegalArgumentException(
                    "The provided X-coordinate " + x
                            + " is outside of the board's bounds [0-"
                            + width + "]."
            );
        }
        if (y >= height || y < 0) {
            throw new IllegalArgumentException(
                    "The provided Y-coordinate " + x
                            + " is outside of the board's bounds [0-"
                            + height + "]."
            );
        }

        return items[x][y];
    }

    /**
     * Remove an item from data structure, shift other items in the same column down and
     * add a new random item to the top.
     * If this position has the same coordinates of that of a item in the BackgroundTileCatalog,
     * than that item is also removed.
     * @param x the X-Coordinate of the item
     * @param y the Y-Coordinate of the item
     */
    public void remove(int x, int y) {
        for (int i = x; i >= 0; i--) {
            if (i == 0) {
                setItem(iFactory.createRandomItem(), i, y);
            } else {
                setItem(getItem(i - 1, y), i, y);
            }
        }
        if (Game.getGame().getBackgroundTileCatalog().contains(x, y)) {
            Game.getGame().getBackgroundTileCatalog().remove(x, y);
        }
    }

    /**
     * Fills the board with randomly generated items.
     */
    protected void createRandom() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                items[x][y] = iFactory.createRandomItem();
            }
        }
        removeGroups(true);
    }

    /**
     * Checks the board for any groups that should be removed,
     * and removed those groups iff they exist.
     * @return True iff the board has changed.
     */
    public boolean removeGroups() {
        return removeGroups(false);
    }

    /**
     * Checks the board for any groups that should be removed,
     * and removed those groups iff they exist.
     * @param init Specifies whether the call to the removeGroups
     *             method was made during initialization or not.
     * @return True iff the board has changed.
     */
    private boolean removeGroups(Boolean init) {
        boolean changedBoard = false;

        boolean removed = true;
        while (removed) {
            removed = false;
            for (int i = 0; i < height; i++) {
                for (int j = i % 2; j < width; j += 2) {
                    ArrayList<Point> group = findGroup(j, i);
                    if (group.size() >= minGroupSize) {
                        removed = true;
                        changedBoard = true;
                        if (init) {
                            removeInit(group);
                        } else {
                            removeGroup(group);
                        }
                        break;
                    }
                }
                if (removed) {
                    break;
                }
            }
        }

        return changedBoard;
    }

    /**
     * Finds the largest possible group of identical items,
     * containing the item at the provided location.
     * @param x The X-coordinate of the group's origin.
     * @param y The Y-coordinate of the group's origin.
     * @return  group.
     */
    public ArrayList<Point> findGroup(int x, int y) {
        ArrayList<Point> group = new ArrayList<>();

        Queue<Point> pQueue = new LinkedList<>();
        pQueue.offer(new Point(x, y));
        while (!pQueue.isEmpty()) {
            Point currentP = pQueue.poll();
            if (!group.contains(currentP) && items[x][y].getSprite()
                    .equals(items[currentP.x][currentP.y].getSprite())) {
                group.add(currentP);
                if (currentP.x > 0) {
                    pQueue.offer(new Point(currentP.x - 1, currentP.y));
                }
                if (currentP.x < width - 1) {
                    pQueue.offer(new Point(currentP.x + 1, currentP.y));
                }
                if (currentP.y > 0) {
                    pQueue.offer(new Point(currentP.x, currentP.y - 1));
                }
                if (currentP.y < height - 1) {
                    pQueue.offer(new Point(currentP.x, currentP.y + 1));
                }
            }
        }
        return group;
    }

    /**
     * Removes the items at the provided locations.
     * @param group The locations of the items.
     */
    public void removeGroup(ArrayList<Point> group) {
        Collections.sort(group, new Comparator<Point>() {
            public int compare(Point p1, Point p2) {
                return Integer.compare(p1.x, p2.x);
            }
        });

        for (Point p : group) {
            remove(p.x, p.y);
        }
    }

    /**
     * Remove method used specifically when initializing a randomly filled board.
     * This makes sure none of the background tiles are removed when removing any
     * groups during initialization.
     * @param group The coordinates of the items that need to be removed.
     */
    private void removeInit(ArrayList<Point> group) {
        for (Point p : group) {
            setItem(iFactory.createRandomItem(), p.x, p.y);
        }
    }
}
