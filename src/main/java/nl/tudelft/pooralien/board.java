package nl.tudelft.pooralien;

/**
 * Created by Sam on 9/7/2017.
 */
public class board {
    private int LENGTH= 10;
    private int WIDTH = 10;
    private Item[][] items = new Item[LENGTH][WIDTH];


    /**
     * Constructor which builds the data structure with the given items.
     * @param items data structure of the items on the board
     */
    public board (Item [][] items){
        this.items = items;
    }


    /**
     * set a particular item on the board with the given item.
     * @param cell is the new item to be replaced on the board.
     * @param x is the X position on the board.
     * @param y is the Y position on the board.
     * @return true if it was successful
     */
    public boolean setItems(Item cell, int x, int y) {
        if (x < WIDTH && x > 0 && y < LENGTH && y > 0) {
            this.items[x][y] = cell;
            return true;
        }
        return false;
    }


    /**
     * get a particular item at the given position.
     * @param x is the X coordinate on the board.
     * @param y is the Y coordinate on the board.
     * @return the item if the given position is valid otherwise return null
     */
    public Item getItem(int x, int y) {
        if (x < WIDTH && x > 0 && y < LENGTH && y > 0) {
            return items[x][y];
        }
        return null;
    }


    

}