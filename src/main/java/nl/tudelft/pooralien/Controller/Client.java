package nl.tudelft.pooralien.Controller;

import java.awt.Color;
import java.awt.Point;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import nl.tudelft.pooralien.MouseActionObserver;
import nl.tudelft.pooralien.MouseEventHandler;
import nl.tudelft.pooralien.Subject;

/**
 * Client class represents a player.
 */
public class Client extends MouseActionObserver implements Runnable {
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

    /**
     * Constructor of the client class.
     *
     * @param host is the server address we want to connect to.
     * @param port is the port which server is running on.
     * @param subject is the class we want to observe.
     */
    public Client(String host, int port, MouseEventHandler subject) {
        this.subject = subject;
        this.updateAnimation = false;
        this.host = host;
        this.port = port;
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
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Send a message to the server.
     *
     * @param message message that is going to be send to the server.
     */
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

    /**
     * Parse the message we got from the server and decide what to do.
     *
     * @param command is the command.
     * @param args is the arguments of the command.
     */
    private void parseMessage(String command, String[] args) {
        if (command.equals("Ready")) {
            Game.getGame().setMultiplayer(true);
            Game.getGame().getScoreCounter().setScore(0);
        } else if (command.equals("Play")) {
            Game.getGame().resumeGame();
        } else if (command.equals("Wait")) {
            Game.getGame().pauseGame();
        } else if (command.equals("ServerIsDying")) {
            System.out.println("Server died!");
            this.terminate();
        } else if (command.equals("NewBoard")) {
            createNewBoard(args);
        } else if (command.equals("NewBackgroundCatalog")) {
            createNewBackgroundCatalog(args);
        } else if (command.equals("StartAnimation")) {
            startAnimationCommand(args);
        } else if (command.equals("UpdateAnimation")) {
            updateAnimationCommand(args);
        } else if (command.equals("StopAnimation")) {
            this.stopAnimation();
        }
    }

    private void createNewBoard(String[] args) {
        StandardBoardFactory bFactory = new StandardBoardFactory();
        StandardBoard newBoard = bFactory.createBoard(args[0]);
        Game.getGame().setBoard(newBoard);
        subject.getMainScreen().refreshBoard();
    }

    private void createNewBackgroundCatalog(String[] args) {
        Color c = new Color(Integer.parseInt(args[0]));
        BackgroundTileCatalog btc = new BackgroundTileCatalog();
        for (int i = 1; i < args.length; i++) {
            String[] coordinate = args[i].split("\\s");
            if (coordinate.length > 1) {
                btc.add(new BackgroundTile(
                            Integer.parseInt(coordinate[0]),
                            Integer.parseInt(coordinate[1]),
                            c));
            }
        }
        Game.getGame().setBackgroundTileCatalog(btc);
        subject.getMainScreen().refreshBoard();
    }

    private void startAnimationCommand(String[] args) {
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

    private void updateAnimationCommand(String[] args) {
        if (args.length == 2) {
            Point p = new Point(Integer.parseInt(args[0]),
                    Integer.parseInt(args[1]));
            this.updateAnimation(p);
        }
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
            if (updateAnimation) {
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
                updateAnimation = true;
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
        updateAnimation = false;
    }
}
