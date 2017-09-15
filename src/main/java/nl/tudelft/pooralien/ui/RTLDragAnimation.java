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
 * An implementation for the Right To Left drag animation.
 */
public class RTLDragAnimation implements Animation {
    private MainScreen mainScreen;

    private Point previousCoordinate;
    private int originalXGridPosition;
    private int originalYScreenPosition;

    private LinkedList<JLabel> selectedItems = new LinkedList<>();
    private LinkedList<JLabel> originalItems = new LinkedList<>();

    private static final int MARGIN = 10;
    private static final double GAP = 1.22;

    /**
     * Constructor of the class.
     * @param screen is the main screen of the gui.
     */
    public RTLDragAnimation(MainScreen screen) {
        this.mainScreen = screen;
        previousCoordinate = new Point(0, 0);
        originalXGridPosition = 0;
        originalYScreenPosition = 0;
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

        originalXGridPosition = tile.getGridPosition().x;
        originalYScreenPosition = tile.getY() + 2 * MARGIN;
        for (int i = 0; i < Board.WIDTH; i++) {
            JLabel label = mainScreen.getItem(originalXGridPosition, i).getImageIcon();
            String name = Game.getGame().getBoard().getItem(originalXGridPosition, i).getSprite();

            mainScreen.removeItem(originalXGridPosition, i);
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
        if (p.x > gridBoard.getX() && p.x < gridBoard.getWidth()) {
            int deltaP = p.x - previousCoordinate.x;
            if (deltaP > 0 && deltaP > selectedItems.get(0).getWidth()) {
                selectedItems.addFirst(selectedItems.removeLast());
                drawItems(selectedItems);
                previousCoordinate = p;
            } else if (deltaP < 0 && deltaP * -1 > selectedItems.get(0).getWidth()) {
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
        boolean founded = false;
        // check for similar items
        int y = 0;
        for (JLabel image : selectedItems) {
            Board board = Game.getGame().getBoard();
            ArrayList<Integer> foundedItems = new ArrayList<>();
            //update the board with changed data (by mouse drag)
            ItemFactory itemFactory = new ItemFactory();
            board.setItem(itemFactory.createItem(image.getName()), originalXGridPosition, y);
            //search the board
            foundedItems.addAll(board.findVSimilaresAt(originalXGridPosition, y));
            if (foundedItems.size() > 0) {
                founded = true;
            }
            Collections.sort(foundedItems);
            for (Integer index : foundedItems) { // remove founden items and add new random ones.
                board.remove(index, y);
            }
            y++;
        }
        if (!founded) {
            restoreScreen();
            return;
        }
        mainScreen.refreshBoard();
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
                    itemFactory.createItem(label.getName()), originalXGridPosition, i);
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
            int xCoordinate = (int) ((i * label.getWidth() * GAP) + MARGIN / 2);
            label.setLocation(xCoordinate, originalYScreenPosition);
            mainScreen.revalidate();
            mainScreen.repaint();
            i++;
        }
    }
}
