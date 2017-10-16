package nl.tudelft.pooralien.Controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class ServerThread extends Thread {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private volatile boolean running;

    public ServerThread(Socket socket) {
        this.socket = socket;
        try {
            // Create a DataInputStream for communication; the player
            // is using a DataOutputStream to write to us.
            in = new DataInputStream(socket.getInputStream());
            // we use this to write back to the player
            out = new DataOutputStream(socket.getOutputStream());
            // run the thread.
            running = true;
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean sendMessage(String message) {
        boolean result = false;
        try {
            out.writeUTF(message);
            result = true;
        } catch (IOException e) {
            System.out.println("Could not sent a message to the player!");
            System.out.println(e);
        }
        return result;
    }

    public void terminate() {
        try {
            socket.close();
            in.close();
        } catch (IOException e) {
            System.out.println("Could not terminate server thread");
            System.out.println(e);
        }
        running = false;
    }

    @Override
    public void run() {
        try {
            // over and over, forever (!)
            while (running) {
                // read the next message we get from client
                String message = in.readUTF();

                // Handle the messages you get from the player.
                // ... and have the server send it to all players
                String[] whole = message.split(";");
                String command = whole[0];
                if (command.equals("StopAnimation")) {
                    Server.getServer().nextTurn();
                }
                Server.getServer().sendToOthers(message, this);
            }
        } catch (EOFException ie) {
            System.out.println(ie);
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            // the connection is closed for one reason or another,
            // so have the server dealing with it
            System.out.println("Removing the client from server...");
            Server.getServer().removeConnection(this);
            running = false;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ServerThread) {
            ServerThread other = (ServerThread) obj;
            return socket.equals(other.socket);
        }
        return false;
    }

}
