package nl.tudelft.pooralien.ui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Component;

/**
 * JPanelTile holds image icons on the GridBoard on the screen.
 */
public class JPanelTile extends JPanel {
    private JLabel imageIcon = null;
    private int gridX;
    private int gridY;

    /**
     * Constructor of JPanelTile.
     * @param x is the X position of the tile on the GridBoard.
     * @param y is the Y position of the tile on the GridBoard.
     */
    public JPanelTile(int x, int y) {
        super();
        this.gridX = x;
        this.gridY = y;
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
     * @return the X position.
     */
    public int getGridX() {
        return gridX;
    }

    /**
     * Get the position of this item on the GridBoard.
     * @return the Y position.
     */
    public int getGridY() {
        return gridY;
    }
}
