package nl.tudelft.pooralien.Controller;

import java.awt.Point;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import nl.tudelft.pooralien.MouseActionObserver;
import nl.tudelft.pooralien.MouseEventHandler;
import nl.tudelft.pooralien.Subject;

public class Client extends MouseActionObserver implements Runnable{
    private volatile boolean running;
    // The socket connecting us to the server
    private Socket socket;
    // the streams we communicate to the server; these come
    // from the socket.
    private DataOutputStream out;
    private DataInputStream in;
    // subject we are observing
    private MouseEventHandler subject;
    private boolean updateAnimation;
    private String host;
    private int port;

    public Client(String host, int port, MouseEventHandler subject) {
        this.subject = subject;
        this.updateAnimation = false;
        this.host = host;
        this.port = port;
    }

    public boolean start() {
        // Connect to the server
        try {
            // Initiate the connection.
            socket = new Socket(host, port);
            // We got a connection! Tell the world
            System.out.println("Connected to " + socket);
            // Let's grab the streams and create DataInput/Output streams
            // from them.
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            // Start a background thread for receiving messages
            running = true;
            new Thread(this).start();
            // setup the interface
        } catch (Exception e) {
            //TODO: replace this with a graphical message.
            System.out.println("Could not connect to the network!");
            return false;
        }
        return true;
    }

    public void terminate() {
        running = false;
        Game.getGame().setMultiplayer(false);
        Game.getGame().resumeGame();
        subject.removeObserver(this);
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessageToServer(String message) {
        try {
            // send it to the server
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            // receive messages one-by-one
            while (running) {
                // Get the next message
                String message = in.readUTF();

                String[] whole = message.split(";");
                String[] arguments = {""};
                String command = whole[0];
                if (whole.length > 1) {
                    arguments = whole[1].split(",");
                }
                parseMessage(command, arguments);
            }
        } catch (IOException e) {
            running = false;
        }
        System.out.println("Client is dead!");
    }

    private void parseMessage(String command, String[] args) {
        if (command.equals("Ready")) {
            Game.getGame().setMultiplayer(true);
        } else if (command.equals("Play")) {
            Game.getGame().resumeGame();
        } else if (command.equals("Wait")) {
            Game.getGame().pauseGame();
        } else if (command.equals("ServerIsDying")) {
            System.out.println("Server died!");
            this.terminate();
        } else if (command.equals("StartAnimation")) {
            if (args.length == 3) {
                Point p = new Point(Integer.parseInt(args[0]),
                        Integer.parseInt(args[1]));
                int type = 0;
                if (args[2].equals("HORIZONTAL")) {
                    type = MouseEventHandler.MouseAction.HORIZONTAL_DRAG_ACTION;
                } else if (args[2].equals("VERTICAL")) {
                    type = MouseEventHandler.MouseAction.VERTICAL_DRAG_ACTION;
                }
                this.startAnimation(p, type, subject.getMainScreen());
            }
        } else if (command.equals("UpdateAnimation")) {
            if (args.length == 2) {
                Point p = new Point(Integer.parseInt(args[0]),
                        Integer.parseInt(args[1]));
                this.updateAnimation(p);
            }
        } else if (command.equals("StopAnimation")) {
            this.stopAnimation();
        }
    }

    @Override
    public void update(Subject subject) {
        if (!(subject instanceof MouseEventHandler)) {
            return;
        }
        if (!Game.getGame().multiplayerMode()) {
            return;
        }
        if (!Game.getGame().gameIsRunning()) {
            return;
        }
        MouseEventHandler mouseEventHandler = (MouseEventHandler) subject;
        MouseEventHandler.MouseAction mouseAction = mouseEventHandler.getMouseAction();
        Point p = mouseAction.getPosition();
        if (mouseAction.getMouseActionType()
                != MouseEventHandler.MouseAction.CLICK_ACTION) {
            if (updateAnimation) {
                if (mouseAction.isActionReleased()) {
                    sendMessageToServer("StopAnimation;");
                    updateAnimation = false;
                } else {
                    sendMessageToServer("UpdateAnimation;" + p.x + "," + p.y);
                }
            } else {
                String direction = "";
                if (mouseAction.getMouseActionType()
                        == MouseEventHandler.MouseAction.HORIZONTAL_DRAG_ACTION) {
                    direction = "HORIZONTAL";
                }
                if (mouseAction.getMouseActionType()
                        == MouseEventHandler.MouseAction.VERTICAL_DRAG_ACTION) {
                    direction = "VERTICAL";
                }
                sendMessageToServer("StartAnimation;" + p.x + "," + p.y
                        + "," + direction);
                updateAnimation = true;
            }
        }
    }
}
