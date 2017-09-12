package nl.tudelft.pooralien;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by mostafa on 9-9-17.
 */
public class MyMouseAdapter extends MouseAdapter {
    private MainScreen mainScreen;

    private DragAnimation dragAnimation;

    public MyMouseAdapter(MainScreen mainScreen) {
        super();
        this.mainScreen = mainScreen;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point p = e.getPoint();
        if (dragAnimation != null) {
            dragAnimation.update(p);
            return;
        }
        dragAnimation = new DragAnimation(mainScreen, DragAnimation.Direction.Top_To_Bottom);
        dragAnimation.start(p);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        dragAnimation = null;
    }

}
