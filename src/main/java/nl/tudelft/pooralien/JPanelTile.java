package nl.tudelft.pooralien;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Component;

/**
 * Created by mostafa on 9-9-17.
 */
public class JPanelTile extends JPanel{
    private JLabel imageIcon = null;
    private int gridX;
    private int gridY;

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

    public JLabel getImageIcon() {
        return imageIcon;
    }

    public int getGridX() {
        return gridX;
    }

    public int getGridY() {
        return gridY;
    }
}
