package nl.tudelft.pooralien.Controller;
import nl.tu.delft.defpro.exception.NotExistingVariableException;
import nl.tudelft.item.Item;
import nl.tudelft.item.ItemFactory;

import java.util.ArrayList;
import nl.tudelft.pooralien.Launcher;



/**
 * The Board class.
 */
public class Board {
    private int width;
    private int height;
    private int minRequiredItems;

    private Item[][] items;


    /**
     * Constructor which builds the data structure with the random data.
     */
    public Board() {
        // initialise the board with random items.
        width = getMaxWidth();
        height = getMaxHeight();
        minRequiredItems = getMinRequiredItems();
        items = new Item[width][height];

        createRandom();
    }

    /**
     * Get the width of the board from config file.
     * @return width of the board.
     */
    public static int getMaxWidth() {
        try {
            return Launcher.getGameCfg().getIntegerValueOf("maxBoardWidth");
        } catch (NotExistingVariableException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Get the height of the board from config file.
     * @return height of the board.
     */
    public static int getMaxHeight() {
        try {
            return Launcher.getGameCfg().getIntegerValueOf("maxBoardHeight");
        } catch (NotExistingVariableException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Get the height of the board from config file.
     * @return height of the board.
     */
    public static int getMinWidth() {
        try {
            return Launcher.getGameCfg().getIntegerValueOf("minBoardWidth");
        } catch (NotExistingVariableException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Get the height of the board from config file.
     * @return height of the board.
     */
    public static int getMinHeight() {
        try {
            return Launcher.getGameCfg().getIntegerValueOf("minBoardHeight");
        } catch (NotExistingVariableException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Min required items to be in on row (or column) to score.
     * @return min required item in a row from config file.
     */
    private int getMinRequiredItems() {
        try {
            return Launcher.getGameCfg().getIntegerValueOf("minItemsInRow");
        } catch (NotExistingVariableException e) {
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * Constructor which builds the data structure with the given file.
     * @param fileName path of the file containing the preset data.
     */
    public Board(String fileName) {
        // initialise the board by reading from file
        createFromFile(fileName);
    }

    /**
     * Replace the current board structure with new random data.
     */
    public void newBoard() {
        createRandom();
    }

    /**
     * replace the current board structure by reading from file.
     * @param fileName path of the file containing the preset data.
     */
    public void newBoard(String fileName) {
        createFromFile(fileName);
    }

    /**
     * set a particular item on the board with the given item.
     * @param cell is the new item to be replaced on the board.
     * @param x is the X position on the board.
     * @param y is the Y position on the board.
     * @return true if it was successful
     */
    public boolean setItem(Item cell, int x, int y) {
        if (x < width && x >= 0 && y < height && y >= 0) {
            this.items[x][y] = cell;
            return true;
        }
        return false;
    }

    /**
     *
     * get a particular item at the given position.
     * @param x is the X coordinate on the board.
     * @param y is the Y coordinate on the board.
     * @return the item if the given position is valid otherwise return null.
     * @throws IllegalArgumentException if the x and y are out of the board raneg.
     */
    public Item getItem(int x, int y) throws IllegalArgumentException {
        if (x >= width || x < 0) {
            throw new IllegalArgumentException(
                    "The x should be greater or equal than 0 and less than "
                    + width + "."
            );
        }
        if (y >= height || y < 0) {
            throw new IllegalArgumentException(
                    "The y should be greater or equal than 0 and less than "
                    + height + "."
            );
        }

        return items[x][y];
    }


    /**
     * check the row X if there are 3 or more of the same items
     * at the neighbor of the given coordinate.
     * @param x the X coordinate of the item on the board
     * @param y the Y coordinate of the item on the board
     * @return returns an array of Y position of items on the board.
     */
    public ArrayList<Integer> findHSimilaresAt(int x, int y) {
        ArrayList<Integer> founded = new ArrayList<>();
        founded.add(y);
        boolean flag = true;
        int i = 1;
        while (flag) { // checking the right side
            if (y + i < height && items[x][y].getSprite().equals(items[x][y + i].getSprite())) {
                founded.add(y + i);
            } else {
                flag = false;
            }
            i++;
        }
        i = 1;
        flag = true;
        while (flag) { //checking the left side
            if (y - i >= 0 && items[x][y].getSprite().equals(items[x][y - i].getSprite())) {
                founded.add(y - i);
            } else {
                flag = false;
            }
            i++;
        }
        // if nothing has been founded or the number of founded items is less than
        // minimum required for the game return an empty array list.
        if (founded.size() < minRequiredItems) {
            return new ArrayList<Integer>();
        }
        return founded;
    }

    /**
     * check the column Y if there are 3 or more of the same items
     * at the neighbor of the given coordinate.
     * @param x the X coordinate of the item on the board
     * @param y the Y coordinate of the item on the board
     * @return returns an array of X position of items on the board.
     */
    public ArrayList<Integer> findVSimilaresAt(int x, int y) {
        ArrayList<Integer> founded = new ArrayList<>();
        founded.add(x);
        boolean flag = true;
        int i = 1;
        while (flag) { // checking the bottom side
            if (i + x < width && items[x][y].getSprite().equals(items[x + i][y].getSprite())) {
                founded.add(x + i);
            } else {
                flag = false;
            }
            i++;
        }
        flag = true;
        i = 1;
        while (flag) { //checking the top side
            if (x - i >= 0 && items[x][y].getSprite().equals(items[x - i][y].getSprite())) {
                founded.add(x - i);
            } else {
                flag = false;
            }
            i++;
        }
        // if nothing has been founded or the number of founded items is less than
        // minimum required for the game return an empty array list.
        if (founded.size() < minRequiredItems) {
            return new ArrayList<Integer>();
        }
        return founded;
    }

    /**
     * Remove an item from data structure, shift other items in the same column down and
     * add a new random item to the top.
     * If this position has the same coordinates of that of a item in the BackgroundTileCatalog,
     * than that item is also removed.
     * @param x the X position of the item
     * @param y the Y position of the item
     */
    public void remove(int x, int y) {
        for (int i = x; i >= 0; i--) {
            if (i == 0) {
                ItemFactory itemFactory = new ItemFactory();
                setItem(itemFactory.createRandomItem(), i, y);
            } else {
                setItem(getItem(i - 1, y), i, y);
            }
        }
        if (Game.getGame().getBackgroundTileCatalog().contains(x, y)) {
            Game.getGame().getBackgroundTileCatalog().remove(x, y);
            Game.getGame().getScoreCounter().updateScoreBackgroundTileRemoved();
        }
    }

    private void createRandom() {
        ItemFactory itemFactory = new ItemFactory();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                items[x][y] = itemFactory.createRandomItem();
            }
        }
    }

    private void createFromFile(String fileName) {
        //TODO: create a new board from file
    }
}
