package nl.tudelft.pooralien.ui;

import nl.tudelft.item.ItemFactory;
import nl.tudelft.pooralien.Controller.Board;
import nl.tudelft.pooralien.Controller.Game;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * An implementation for the Top To Bottom drag animation.
 */
public class TTBDragAnimation implements Animation {
    private MainScreen mainScreen;

    private Point previousCoordinate;
    private int originalYGridPosition;
    private int originalXScreenPosition;

    private LinkedList<JLabel> selectedItems = new LinkedList<>();
    private LinkedList<JLabel> originalItems = new LinkedList<>();

    private static final int MARGIN = 5;
    private static final double GAP = 1.22;

    /**
     * Constructor of the class.
     * @param screen is the main screen of the gui.
     */
    public TTBDragAnimation(MainScreen screen) {
        this.mainScreen = screen;
        previousCoordinate = new Point(0, 0);
        originalYGridPosition = 0;
        originalXScreenPosition = 0;
    }

    @Override
    public void start(Point p) {
        previousCoordinate = p;
        Component c = mainScreen.getGridBoard().getComponentAt(p);
        if (!(c instanceof  JPanelTile)) {
            return;
        }
        JPanelTile tile = (JPanelTile) c;
        if (tile.getImageIcon() == null) {
            return;
        }
        originalYGridPosition = tile.getGridPosition().y;
        originalXScreenPosition = tile.getX() +  MARGIN;

        for (int i = 0; i < Board.HEIGHT; i++) {
            JLabel label;
            String name;

            label = mainScreen.getItem(i, originalYGridPosition).getImageIcon();
            name = Game.getGame().getBoard().getItem(i, originalYGridPosition).getSprite();
            mainScreen.removeItem(i, originalYGridPosition);

            label.setName(name);
            selectedItems.addLast(label);
            originalItems.addLast(label);
            mainScreen.add(label, JLayeredPane.DRAG_LAYER);
            drawItems(selectedItems);
        }
    }

    @Override
    public void update(Point p) {
        if (selectedItems.size() == 0) {
            return;
        }
        JPanel gridBoard = mainScreen.getGridBoard();
        if (p.y > gridBoard.getY() && p.y < gridBoard.getHeight()) {
            int deltaP = p.y - previousCoordinate.y;
            if (deltaP > 0 && deltaP > selectedItems.get(0).getHeight()) {
                selectedItems.addFirst(selectedItems.removeLast());
                drawItems(selectedItems);
                previousCoordinate = p;
            } else if (deltaP < 0 && deltaP * -1 > selectedItems.get(0).getHeight()) {
                selectedItems.addLast(selectedItems.removeFirst());
                drawItems(selectedItems);
                previousCoordinate = p;
            }
        }
    }

    @Override
    public void end() {
        for (JLabel label : selectedItems) {
            mainScreen.remove(label);
        }
        boolean founded = false; int x = 0;
        // check for similar items
        for (JLabel image : selectedItems) {
            Board board = Game.getGame().getBoard();
            ArrayList<Integer> foundedItems = new ArrayList<>();
            //update the board with changed data (by mouse drag)
            ItemFactory itemFactory = new ItemFactory();
            board.setItem(itemFactory.createItem(image.getName()), x, originalYGridPosition);
            //search the board
            foundedItems.addAll(board.findHSimilaresAt(x, originalYGridPosition));
            if (foundedItems.size() > 0) {
                founded = true;
            }
            updateScore(foundedItems, x);

            Collections.sort(foundedItems);
            for (Integer index : foundedItems) { //remove founded items and add random ones.
                board.remove(x, index);
            }
            x++;
        }
        if (!founded) {
            restoreScreen(); return;
        }
        mainScreen.refreshBoard();
    }

    /**
     * Update the scoreCounter object with the amount of tiles & background tiles that are removed.
     * @param foundedItems ArrayList containing the x coordinate of the tiles.
     * @param x the int of the column where items were removed.
     */
    private void updateScore(ArrayList<Integer> foundedItems, int x) {
        // Find amount of backgroundTiles destroyed
        int backgroundTilesDestroyed = 0;
        for (Integer index : foundedItems) {
            if (Game.getGame().getBackgroundTileCatalog().contains(x, index)) {
                backgroundTilesDestroyed++;
            }
        }
        // Update the score
        Game.getGame().getScoreCounter().updateScore(
                foundedItems.size(), backgroundTilesDestroyed);
    }

    /**
     * restore the dragged items to their original state
     * and also update the board data structure to apply the changes.
     */
    private void restoreScreen() {
        int i = 0;
        for (JLabel label : originalItems) {
            // restore the board data structure
            ItemFactory itemFactory = new ItemFactory();
            Game.getGame().getBoard().setItem(
                    itemFactory.createItem(label.getName()), i, originalYGridPosition);
            i++;
        }
        mainScreen.refreshBoard();
    }

    /**
     * Draw images in the list on the screen.
     * @param list the list containing Labels (=images) which is going to be shown on the screen.
     */
    private void drawItems(LinkedList<JLabel> list) {
        int i = 0;
        for (JLabel label : list) {
            int yCoordinate = (int) ((i * label.getHeight() * GAP)
                    + (mainScreen.getGridBoard().getY()));
            label.setLocation(originalXScreenPosition, yCoordinate);
            mainScreen.revalidate();
            mainScreen.repaint();
            i++;
        }
    }
}
