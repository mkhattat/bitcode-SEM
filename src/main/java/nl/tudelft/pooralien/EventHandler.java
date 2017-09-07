package nl.tudelft.pooralien;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class EventHandler {

    public EventHandler(Launcher window) {

        ButtonHandler button = new ButtonHandler();
        MouseHandler mouse = new MouseHandler(window);

    }

    public class ButtonHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("OK")) {
                System.out.println("Ok Button clicked.");
            } else if (command.equals("Submit")) {
                System.out.println("Submit Button clicked.");
            } else {
                System.out.println("Cancel Button clicked.");
            }
        }
    }

    public class MouseHandler implements MouseListener, MouseMotionListener{

        public MouseHandler(Launcher window) {
            window.addMouseListener(this);
            window.addMouseMotionListener(this);
        }

        public void mousePressed(MouseEvent e) {
            saySomething("Mouse pressed; # of clicks: "
                    + e.getClickCount(), e);
        }

        public void mouseReleased(MouseEvent e) {
            saySomething("Mouse released; # of clicks: "
                    + e.getClickCount(), e);
        }

        public void mouseEntered(MouseEvent e) {
            saySomething("Mouse entered", e);
        }

        public void mouseExited(MouseEvent e) {
            saySomething("Mouse exited", e);
        }

        public void mouseClicked(MouseEvent e) {
            saySomething("Mouse clicked (# of clicks: "
                    + e.getClickCount() + ")", e);
        }

        void saySomething(String eventDescription, MouseEvent e) {
            System.out.println(eventDescription + " detected on "
                    + e.getComponent().getClass().getName()
                    + ".");
        }

        public void mouseMoved(MouseEvent e) {
            //System.out.println("X : " + e.getX());
            //System.out.println("Y : " + e.getY());
        }

        public void mouseDragged(MouseEvent e) {
            System.out.println("X : " + e.getX());
            System.out.println("Y : " + e.getY());
        }

    }

}
