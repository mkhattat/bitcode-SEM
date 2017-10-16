package nl.tudelft.pooralien.Controller;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import java.util.Scanner;

public class Server implements Runnable {

  private Hashtable<Socket, DataOutputStream> outputStreams = 
    new Hashtable<>();
  private ServerSocket serverSocket;
  private int port;
  private Thread thread;
  private Scanner scanner;

  public Server(int port) throws IOException {
    // All we have to do is listen
    this.port = port;
    thread = new Thread(this);
    thread.start();
    // listen to input until get the stop signal
    listen();
  }

  public void terminate() {
    scanner.close();
  }

  private void listen() throws IOException {
    scanner = new Scanner(System.in);
    try {
      while (scanner.hasNext()) {
        String next = scanner.next();
        if (next.equals("stop")) {
          serverSocket.close();
        } else if (next.equals("exit")) {
          serverSocket.close();
          break;
        }
      }
    } catch (Exception e) { }
    scanner.close();
    System.out.println("The server has been stopped");
  }

  public void sendToOthers(String message, Socket currentClient) {
    // we synchronize on this because another thread might be calling
    // removeConnection so then we don't mees up the outputStreams
    synchronized(outputStreams) {
      for (Socket key : outputStreams.keySet()) {
        if (key.equals(currentClient)) {
          continue;
        }
        DataOutputStream out = outputStreams.get(key);
        try {
          out.writeUTF(message);
        } catch (IOException io) {
          System.out.println(io);
        }
      }
    }
  }

  /**
   * Remove a socket, and it's corresponding output from our list.
   *
   * @param socket is the client
   */
  public void removeConnection(Socket socket) {
    // Synchronize so we don't messup outputStreams, if another thread calls sendToAll method.
    synchronized(outputStreams) {
      // Tell the world
      System.out.println("Removing connection " + socket);
      // remove it from our hashtable
      outputStreams.remove(socket);
      // make sure it's closed 
      try {
        socket.close();
      } catch (IOException e) {
        System.out.println("Error closing the socket");  
        e.printStackTrace();
      }
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
      while (true) {
        // get a connection
        Socket client = serverSocket.accept();

        // Tell the world we got you.
        System.out.println("Connection from " + client);

        // Create a DataOutputStream for writing data to the other side
        DataOutputStream out = new DataOutputStream(client.getOutputStream());

        // Save this stream so we don't need to make it again
        outputStreams.put(client, out);

        // Create a new thread for this connection, and then forget
        // about it.
        new ClientThread(this, client);
      }
    } catch (IOException e) {
      System.out.println("The server is not listening anymore.");
    }
  }

  public static void main(String[] args) throws Exception {
    int port = 9090;

    // create a server object which automatically accepts connections.
    new Server(port);
  }
}
