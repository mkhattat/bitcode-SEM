package nl.tudelft.pooralien.Controller;

import java.awt.Point;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import nl.tudelft.pooralien.MouseActionObserver;
import nl.tudelft.pooralien.MouseEventHandler;
import nl.tudelft.pooralien.Subject;
import nl.tudelft.pooralien.Controller.clientStrategy.InvokeStrategy;
import nl.tudelft.pooralien.Controller.clientStrategy.Strategy;
import nl.tudelft.pooralien.ui.MainScreen;

/**
 * Client class represents a player.
 */
public class Client extends MouseActionObserver implements Runnable {
    private volatile boolean running;
    private Socket socket;
    private DataOutputStream outputStream;
    private DataInputStream inputStream;
    private MouseEventHandler subject;
    private boolean isUpdatingAnimation;
    private String host;
    private int port;
    private InvokeStrategy invokeStrategy;

    /**
     * Constructor of the client class.
     *
     * @param host is the server address we want to connect to.
     * @param port is the port which server is running on.
     * @param subject is the class we want to observe.
     */
    public Client(String host, int port, MouseEventHandler subject) {
        this.subject = subject;
        this.isUpdatingAnimation = false;
        this.host = host;
        this.port = port;
        this.invokeStrategy = new InvokeStrategy();
    }

    /**
     * Start the thread.
     *
     * @return true if success.
     */
    public boolean start() {
        // Connect to the server
        try {
            // Initiate the connection.
            socket = new Socket(host, port);
            // We got a connection! Tell the world
            System.out.println("Connected to " + socket);
            // Let's grab the streams and create DataInput/Output streams
            // from them.
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
            // Start a background thread for receiving messages
            running = true;
            new Thread(this).start();
            // setup the interface
        } catch (IOException e) {
            //TODO: replace this with a graphical message.
            System.out.println("Could not connect to the network!");
            return false;
        }
        return true;
    }

    /**
     * Terminate the client thread running in the background and clean up.
     *
     */
    public void terminate() {
        running = false;
        Game.getGame().setMultiplayer(false);
        Game.getGame().resumeGame();
        subject.removeObserver(this);
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Send a message to the server.
     *
     * @param message message that is going to be send to the server.
     */
    public void sendMessageToServer(String message) {
        try {
            // send it to the server
            outputStream.writeUTF(message);
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
                String message = inputStream.readUTF();

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

    /**
     * Parse the message we got from the server and decide what to do.
     *
     * @param command is the command.
     * @param args is the arguments of the command.
     */
    private void parseMessage(String command, String[] args) {
        Strategy strategy = invokeStrategy.getStrategy(command);
        strategy.execute(args, this);
    }

    @Override
    public void update(Subject subject) {
        if (!checkUpdate(subject)) {
            return;
        }
        MouseEventHandler mouseEventHandler = (MouseEventHandler) subject;
        MouseEventHandler.MouseAction mouseAction = mouseEventHandler.getMouseAction();
        Point p = mouseAction.getPosition();
        if (mouseAction.getMouseActionType()
                != MouseEventHandler.MouseAction.CLICK_ACTION) {
            if (isUpdatingAnimation) {
                if (mouseAction.isActionReleased()) {
                    stopAnimationCommand();
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
                sendMessageToServer("StartAnimation;" + p.x + "," + p.y + "," + direction);
                isUpdatingAnimation = true;
            }
        }
    }

    private boolean checkUpdate(Subject subject) {
        boolean result = true;
        if (!(subject instanceof MouseEventHandler)) {
            result = false;
        }
        if (!Game.getGame().multiplayerMode()) {
            result = false;
        }
        if (!Game.getGame().gameIsRunning()) {
            result = false;
        }
        return result;
    }

    private void stopAnimationCommand() {
        sendMessageToServer("StopAnimation;");
        sendMessageToServer("NewBoard;" + Game.getGame().getBoard().toString());
        sendMessageToServer("NewBackgroundCatalog;"
                + Game.getGame().getBackgroundTileCatalog().toString());
        isUpdatingAnimation = false;
    }
    
    /**
     * Get the main screen of this object.
     *
     * @return the mainscreen with boards on it.
     */
    public MainScreen getMainScreen() {
        return subject.getMainScreen();
    }
}
