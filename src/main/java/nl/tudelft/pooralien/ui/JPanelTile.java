package nl.tudelft.pooralien.ui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Point;

/**
 * JPanelTile holds image icons on the GridBoard on the screen.
 */
public class JPanelTile extends JPanel {
    private JLabel imageIcon = null;
    private Point gridPosition;

    /**
     * Constructor of JPanelTile.
     * @param gridPosition is the position of this tile on the grid board.
     */
    public JPanelTile(Point gridPosition) {
        super();
        this.gridPosition = gridPosition;
    }

    @Override
    public Component add(Component comp) {
        imageIcon = (JLabel) comp;
        return super.add(comp);
    }

    @Override
    public void remove(Component comp) {
        if (comp.equals(imageIcon)) {
            imageIcon = null;
        }
        super.remove(comp);
    }

    @Override
    public void removeAll() {
        imageIcon = null;
        super.removeAll();
    }

    /**
     * Get the ImageIcon which is placed on a JLabel.
     * @return JLabel containing the Icon of this object.
     */
    public JLabel getImageIcon() {
        return imageIcon;
    }

    /**
     * Get the position of this item on the GridBoard.
     * @return the position of tile on the grid board.
     */
    public Point getGridPosition() {
        return gridPosition;
    }
}
