package nl.tudelft.pooralien.Controller;
import nl.tudelft.item.Item;
import nl.tudelft.item.ItemFactory;

import java.util.ArrayList;


/**
 * The Board class.
 */
public class Board {
    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;
    private static final int MIN_REQUIRED_ITEMS = 3;

    private Item[][] items = new Item[WIDTH][HEIGHT];


    /**
     * Constructor which builds the data structure with the random data.
     */
    public Board() {
        // initialise the board with random items.
        createRandom();
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
        if (x < WIDTH && x >= 0 && y < HEIGHT && y >= 0) {
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
        if (x >= WIDTH || x < 0) {
            throw new IllegalArgumentException(
                    "The x should be greater or equal than 0 and less than "
                    + WIDTH + "."
            );
        }
        if (y >= HEIGHT || y < 0) {
            throw new IllegalArgumentException(
                    "The y should be greater or equal than 0 and less than "
                    + HEIGHT + "."
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
            if (y + i < HEIGHT && items[x][y].getSprite().equals(items[x][y + i].getSprite())) {
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
        if (founded.size() < MIN_REQUIRED_ITEMS) {
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
            if (i + x < WIDTH && items[x][y].getSprite().equals(items[x + i][y].getSprite())) {
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
        if (founded.size() < MIN_REQUIRED_ITEMS) {
            return new ArrayList<Integer>();
        }
        return founded;
    }

    /**
     * Remove an item from data structure, shift other items in the same column down and
     * add a new random item to the top.
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
    }

    private void createRandom() {
        ItemFactory itemFactory = new ItemFactory();
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                items[x][y] = itemFactory.createRandomItem();
            }
        }
    }

    private void createFromFile(String fileName) {
        //TODO: create a new board from file
    }
}
