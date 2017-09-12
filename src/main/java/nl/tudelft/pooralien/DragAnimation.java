package nl.tudelft.pooralien;

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

    private Point originalCoordinate;
    private Point originalGridPosition;
    private Point delta;

    private LinkedList<JLabel> selectedItems = new LinkedList<>();

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
        originalCoordinate = p;

        Component c = mainScreen.getGridBoard().getComponentAt(p);
        if (c instanceof JPanelTile) {
            JPanelTile tile = (JPanelTile) c;
            originalGridPosition = new Point(tile.getGridX(), tile.getGridY());

            if (tile.getImageIcon() == null) {
                return;
            }

            int x = (SwingUtilities.convertPoint(tile.getImageIcon(), tile.getImageIcon().getX(),
                    tile.getImageIcon().getY(), mainScreen.getGridBoard())).x;
            delta = new Point(x - MARGIN, 0);

            for (int i = 0; i < Launcher.BOARD_HEIGHT; i++) {
                JLabel label =
                        mainScreen.getGridBoardHolder()[i][originalGridPosition.y].getImageIcon();
                selectedItems.addLast(label);
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
        JPanel gridBoard = mainScreen.getGridBoard();
        if (p.y > gridBoard.getY() && p.y < gridBoard.getHeight()) {
            int deltaP = p.y - originalCoordinate.y;
            if (deltaP > 0 && deltaP > selectedItems.get(0).getHeight()) {
                selectedItems.addFirst(selectedItems.removeLast());
                repaint(selectedItems);
                originalCoordinate = p;
            } else if (deltaP < 0 && deltaP * -1 > selectedItems.get(0).getHeight()) {
                selectedItems.addLast(selectedItems.removeFirst());
                repaint(selectedItems);
                originalCoordinate = p;
            }
        }
    }

    private void repaint(LinkedList<JLabel> list) {
        int i = 0;
        for (JLabel label : list) {
            int yCoordinate = (int) ((i * label.getHeight() * GAP)
                    + (mainScreen.getGridBoard().getY()));
            label.setLocation(delta.x, yCoordinate);
            mainScreen.getGridBoard().revalidate();
            mainScreen.getGridBoard().repaint();
            i++;
        }
    }


}