package nl.tudelft.pooralien;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by mostafa on 9-9-17.
 */
public class MyMouseAdapter extends MouseAdapter {
    private JLabel selectedItem;
    private Point delta;
    private JPanel gridBoard;
    private JLayeredPane rootPane;

    public MyMouseAdapter(JPanel gridBoard, JLayeredPane rootPane) {
        super();
        this.gridBoard = gridBoard;
        this.rootPane = rootPane;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point p = e.getPoint();
        Component c = gridBoard.getComponentAt(p);
        if (c instanceof JPanelTile) {
            JPanelTile tile = (JPanelTile) c;
            selectedItem = tile.getImageIcon();
            if (selectedItem == null) return;

            int x2 = (SwingUtilities.convertPoint(selectedItem, selectedItem.getX(), selectedItem.getY(), gridBoard)).x;
            delta = new Point(x2 - 5, 0);
            rootPane.add(selectedItem, JLayeredPane.DRAG_LAYER);
            selectedItem.setLocation(delta.x, p.y);
            gridBoard.revalidate();
            rootPane.repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (selectedItem == null) return;
        Point p = e.getPoint();
        if (p.y > gridBoard.getY() && p.y < gridBoard.getHeight()-gridBoard.getY()) {
            selectedItem.setLocation(delta.x, p.y);
            rootPane.revalidate();
            rootPane.repaint();
        }
    }

}
