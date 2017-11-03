package nl.tudelft.pooralien.ui;

import nl.tudelft.pooralien.Controller.Board;
import nl.tudelft.pooralien.Controller.Game;
import nl.tudelft.pooralien.Controller.GameConfig;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Point;
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

    private int margin;
    private double gap;

    /**
     * Constructor of the class.
     * @param screen is the main screen of the gui.
     */
    public TTBDragAnimation(MainScreen screen) {
        this.mainScreen = screen;
        previousCoordinate = new Point(0, 0);
        originalYGridPosition = 0;
        originalXScreenPosition = 0;

        final int minMargin = 1;
        final int maxMargin = 20;
        final int defaultMargin = 10;
        this.margin = GameConfig.getInteger("marginHDrag", minMargin, maxMargin, defaultMargin);

        final Double minGap = 0.0;
        final Double maxGap = 5.0;
        final Double defaultGap = 1.22;
        this.gap = GameConfig.getReal("gap", minGap, maxGap, defaultGap);
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
        originalXScreenPosition = tile.getX() + margin;

        for (int i = 0; i < Game.getGame().getBoard().getHeight(); i++) {
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
        int x = 0;
        Board board = Game.getGame().getBoard();
        for (JLabel image : selectedItems) {
            board.setItem(
                    board.getItemFactory().createItem(image.getName()),
                    x,
                    originalYGridPosition
            );
            x++;
        }
        if (!board.removeGroups()) {
            restoreScreen(); return;
        } else {
            //Update Score
            Game.getGame().getScoreCounter().updateScoreTilesRemoved(x);
            mainScreen.refreshBoard();
            Game.getGame().useMove();
        }
    }

    /**
     * restore the dragged items to their original state
     * and also update the board data structure to apply the changes.
     */
    private void restoreScreen() {
        int i = 0;
        Board board = Game.getGame().getBoard();
        for (JLabel label : originalItems) {
            board.setItem(
                    board.getItemFactory().createItem(label.getName()), i, originalYGridPosition);
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
            int yCoordinate = (int) ((i * label.getHeight() * gap)
                    + (mainScreen.getGridBoard().getY()));
            label.setLocation(originalXScreenPosition, yCoordinate);
            mainScreen.revalidate();
            mainScreen.repaint();
            i++;
        }
    }
}
