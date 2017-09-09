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

    public class MouseHandler implements MouseListener, MouseMotionListener {

        private MouseAction mouseAction;

        public MouseHandler(Launcher window) {
            window.addMouseListener(this);
            window.addMouseMotionListener(this);
        }

        public void mousePressed(MouseEvent e) {
            System.out.println("Create MouseAction Object");
            mouseAction = new MouseAction(e.getX(), e.getY());
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
            mouseAction.setPosition(e.getX(), e.getY());
            if (mouseAction.getMouseActionType() != MouseAction.CLICK_ACTION){

                if (mouseAction.getMouseActionType() == MouseAction.HORIZONTAL_DRAG_ACTION) {
                    System.out.println("Animate DRAG Horizontal x:" + e.getX() + ", y:" + e.getY());
                } else if (mouseAction.getMouseActionType() == MouseAction.VERICAL_DRAG_ACTION) {
                    System.out.println("Animate DRAG Vertical x:" + e.getX() + ", y:" + e.getY());
                }
            }
        }

        public class MouseAction {
            public static final int CLICK_ACTION = 1;
            public static final int HORIZONTAL_DRAG_ACTION = 2;
            public static final int VERICAL_DRAG_ACTION = 3;

            private static final int DRAG_TRIGGER_VALUE = 5;

            private int xPosStart, yPosStart;
            private int xPosCurrent, yPosCurrent;
            public int mouseActionType;

            public MouseAction(int xPos, int yPos) {
                xPosStart = xPos;
                yPosStart = yPos;
                mouseActionType = CLICK_ACTION;
            }

            private void determineDragDirection(int xPos, int yPos) {
                if (Math.abs(xPosStart - xPos) >= DRAG_TRIGGER_VALUE) {
                    mouseActionType = HORIZONTAL_DRAG_ACTION;
                } else if (Math.abs(yPosStart - yPos) >= DRAG_TRIGGER_VALUE) {
                    mouseActionType = VERICAL_DRAG_ACTION;
                } else {
                    mouseActionType = CLICK_ACTION;
                }
            }

            public void setPosition(int xPos, int yPos){
                if (mouseActionType == CLICK_ACTION) {
                    determineDragDirection(xPos, yPos);
                }
                xPosCurrent = xPos;
                yPosCurrent = yPos;
            }

            public int[] getPosition(){
                int[] positionArray = {xPosCurrent, yPosCurrent};
                return positionArray;
            }

            public int getMouseActionType(){
                return mouseActionType;
            }
        }

    }


}
