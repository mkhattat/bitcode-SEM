package nl.tudelft.pooralien.Controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Listener implements Runnable {
    private ServerSocket serverSocket;
    private int port;
    private Thread thread;
    private volatile boolean running;

    public Listener(int port) throws IOException {
        // All we have to do is listen
        this.port = port;
        thread = new Thread(this);
        running = true;
        thread.start();
    }

    public void terminate() {
        try {
            serverSocket.close();
            running = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        // loop until someone stops the server from listening
        try {
            serverSocket = new ServerSocket(port);
            System.out.println(
                    "The server address: " + InetAddress.getLocalHost().getHostAddress());
            // start listening on the port
            while (running) {
                // get a connection
                Socket player = serverSocket.accept();

                // Tell the world we got you.
                System.out.println("Connection from " + player);

                // Create a new thread for this connection, and then forget
                // about it.
                ServerThread serverThread = new ServerThread(player);

                // Save this stream so we don't need to make it again
                Server.getServer().add(serverThread);
            }
        } catch (IOException e) {
            running = false;
        }
        System.out.println("The server is not listening anymore.");
    }
}
