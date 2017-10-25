package nl.tudelft.pooralien.ui;

import nl.tu.delft.defpro.exception.NotExistingVariableException;
import nl.tudelft.pooralien.Controller.Board;
import nl.tudelft.pooralien.Controller.Game;
import nl.tudelft.pooralien.Launcher;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Point;
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

    private int margin;
    private double gap;

    /**
     * Constructor of the class.
     * @param screen is the main screen of the gui.
     */
    public RTLDragAnimation(MainScreen screen) {
        this.mainScreen = screen;
        previousCoordinate = new Point(0, 0);
        originalXGridPosition = 0;
        originalYScreenPosition = 0;
        try {
            //TODO: Implement Config Boundries
            this.margin = Launcher.getGameCfg().getIntegerValueOf("marginHDrag");
            this.gap = Launcher.getGameCfg().getRealValueOf("gap");
        } catch (NotExistingVariableException e) {
            e.printStackTrace();
            this.margin = 0;
            this.gap = 0;
        }
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
        originalYScreenPosition = tile.getY() + 2 * margin;
        for (int i = 0; i < Game.getGame().getBoard().getWidth(); i++) {
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
        int y = 0;
        Board board = Game.getGame().getBoard();
        for (JLabel image : selectedItems) {
            board.setItem(
                    board.getItemFactory().createItem(image.getName()),
                    originalXGridPosition,
                    y
            );
            y++;
        }
        if (!board.removeGroups()) {
            restoreScreen(); return;
        } else {
            //Update score
            Game.getGame().getScoreCounter().updateScoreTilesRemoved(y);
            Game.getGame().useMove();
            mainScreen.refreshBoard();
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
                    board.getItemFactory().createItem(label.getName()), originalXGridPosition, i);
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
            int xCoordinate = (int) ((i * label.getWidth() * gap) + margin / 2.0);
            label.setLocation(xCoordinate, originalYScreenPosition);
            mainScreen.revalidate();
            mainScreen.repaint();
            i++;
        }
    }
}
