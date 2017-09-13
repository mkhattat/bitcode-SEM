package nl.tudelft.pooralien.ui;

import nl.tudelft.item.ItemFactory;
import nl.tudelft.pooralien.Controller.Board;
import nl.tudelft.pooralien.Controller.Game;
import nl.tudelft.pooralien.Launcher;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
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
    private int deltaX;

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
        deltaX = 0;
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
        originalYGridPosition = tile.getGridY();
        int x = (SwingUtilities.convertPoint(tile.getImageIcon(), tile.getImageIcon().getX(),
                tile.getImageIcon().getY(), mainScreen.getGridBoard())).x;
        deltaX = x - MARGIN;

        for (int i = 0; i < Launcher.BOARD_HEIGHT; i++) {
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
        boolean founded = false;
        // check for similar items
        int x = 0;
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
            Collections.sort(foundedItems);
            for (Integer index : foundedItems) { //remove founded items and add random ones.
                board.remove(x, index);
            }
            x++;
        }
        if (!founded) {
            restoreScreen();
            return;
        }
        mainScreen.refreshBoard();
    }

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

    private void drawItems(LinkedList<JLabel> list) {
        int i = 0;
        for (JLabel label : list) {
            int yCoordinate = (int) ((i * label.getHeight() * GAP)
                    + (mainScreen.getGridBoard().getY()));
            label.setLocation(deltaX, yCoordinate);
            mainScreen.revalidate();
            mainScreen.repaint();
            i++;
        }
    }
}
