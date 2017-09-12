package nl.tudelft.pooralien.ui;

import nl.tudelft.pooralien.Launcher;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Component;
import java.awt.Point;
import java.util.LinkedList;

/**
 * DragAnimation class responsible to update the screen
 * when dragging images on the screen.
 */
public class DragAnimation {
    private MainScreen mainScreen;
    private Direction direction;

    private Point previousCoordinate;
    private Point originalGridPosition;
    private Point delta;

    private LinkedList<JLabel> selectedItems = new LinkedList<>();
    private LinkedList<JLabel> originalItems = new LinkedList<>();

    private static final int MARGIN = 5;
    private static final double GAP = 1.22;

    /**
     * Possible direction of dragging animation.
     */
    public enum Direction {
        Top_To_Bottom, Left_To_Right
    }

    /**
     * Constructor of the class.
     * @param screen is the main screen of the gui.
     * @param dir the direction in which the items should move.
     */
    public DragAnimation(MainScreen screen, Direction dir) {
        this.mainScreen = screen;
        this.direction = dir;
    }

    /**
     * Initializing the drag move.
     * @param p is the point where mouse is pressed.
     */
    public void start(Point p) {
        previousCoordinate = p;

        Component c = mainScreen.getGridBoard().getComponentAt(p);
        if (c instanceof JPanelTile) {
            JPanelTile tile = (JPanelTile) c;
            if (tile.getImageIcon() == null) {
                return;
            }

            originalGridPosition = new Point(tile.getGridX(), tile.getGridY());
            int x = (SwingUtilities.convertPoint(tile.getImageIcon(), tile.getImageIcon().getX(),
                    tile.getImageIcon().getY(), mainScreen.getGridBoard())).x;
            int y = (SwingUtilities.convertPoint(tile.getImageIcon(), tile.getImageIcon().getX(),
                    tile.getImageIcon().getY(), mainScreen.getGridBoard())).y;
            delta = new Point(x - MARGIN, y + 2 * MARGIN);

            for (int i = 0; i < Launcher.BOARD_HEIGHT; i++) {
                JLabel label = null;
                if (direction == Direction.Top_To_Bottom) {
                    label = mainScreen.getItem(i, originalGridPosition.y).getImageIcon();
                    mainScreen.removeItem(i, originalGridPosition.y);
                } else {
                    label = mainScreen.getItem(originalGridPosition.x, i).getImageIcon();
                    mainScreen.removeItem(originalGridPosition.x, i);
                }
                selectedItems.addLast(label);
                originalItems.addLast(label);
                mainScreen.add(label, JLayeredPane.DRAG_LAYER);
                repaint(selectedItems);
            }
        }
    }

    /**
     * updating of the screen when mouse is dragged.
     * @param p the mouse coordinate of the new point.
     */
    public void update(Point p) {
        if (selectedItems.size() == 0) {
            return;
        }
        if (direction == Direction.Top_To_Bottom) {
            updateVertical(p);
        } else {
            updateHorizontal(p);
        }
    }

    public void end() {
        for (JLabel label : selectedItems) {
            mainScreen.remove(label);
        }

        // TODO: check the board for similare items in a row.

        int i = 0;
        for (JLabel label : originalItems) {
            if (direction == Direction.Top_To_Bottom) {
                mainScreen.replaceItem(i, originalGridPosition.y, label);
            } else {
                mainScreen.replaceItem(originalGridPosition.x, i, label);
            }
            i++;
        }
        mainScreen.repaint();
    }

    private void repaint(LinkedList<JLabel> list) {
        int i = 0;
        for (JLabel label : list) {
            if (direction == Direction.Top_To_Bottom) {
                int yCoordinate = (int) ((i * label.getHeight() * GAP)
                        + (mainScreen.getGridBoard().getY()));
                label.setLocation(delta.x, yCoordinate);
            } else {
                int xCoordinate = (int) ((i * label.getWidth() * GAP) + MARGIN);
                label.setLocation(xCoordinate, delta.y);
            }
            mainScreen.revalidate();
            mainScreen.repaint();
            i++;
        }
    }

    private void updateVertical(Point p) {
        JPanel gridBoard = mainScreen.getGridBoard();
        if (p.y > gridBoard.getY() && p.y < gridBoard.getHeight()) {
            int deltaP = p.y - previousCoordinate.y;
            if (deltaP > 0 && deltaP > selectedItems.get(0).getHeight()) {
                selectedItems.addFirst(selectedItems.removeLast());
                repaint(selectedItems);
                previousCoordinate = p;
            } else if (deltaP < 0 && deltaP * -1 > selectedItems.get(0).getHeight()) {
                selectedItems.addLast(selectedItems.removeFirst());
                repaint(selectedItems);
                previousCoordinate = p;
            }
        }
    }

    private void updateHorizontal(Point p) {
        JPanel gridBoard = mainScreen.getGridBoard();
        if (p.x > gridBoard.getX() && p.x < gridBoard.getWidth()) {
            int deltaP = p.x - previousCoordinate.x;
            if (deltaP > 0 && deltaP > selectedItems.get(0).getWidth()) {
                selectedItems.addFirst(selectedItems.removeLast());
                repaint(selectedItems);
                previousCoordinate = p;
            } else if (deltaP < 0 && deltaP * -1 > selectedItems.get(0).getWidth()) {
                selectedItems.addLast(selectedItems.removeFirst());
                repaint(selectedItems);
                previousCoordinate = p;
            }
        }
    }

}