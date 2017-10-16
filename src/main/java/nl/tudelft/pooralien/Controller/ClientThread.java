package nl.tudelft.pooralien.Controller;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class ClientThread extends Thread {
  private Server server;
  private Socket socket;
  
  public ClientThread(Server server, Socket socket) {
    this.server = server;
    this.socket = socket;

    start();
  }

  @Override
  public void run() {
    try {
      // Create a DataInputStream for communication; the client
      // is using a DataOutputStream to write to us.
      DataInputStream in = new DataInputStream(socket.getInputStream());

      // over and over, forever (!)
      while (true) {
        // ... read the next message ...
        String message = in.readUTF();

        // ... tell the world ...
        System.out.println("Sending " + message);

        // Handle the messages you get from the client.
        // ... and have the server send it to all clients
        server.sendToOthers(message, socket);
      }
    } catch (EOFException ie) {
      // Im not gonna handle this.
    } catch (IOException e) {
      System.out.println(e);
    } finally {
      // the connection is closed for one reason or another,
      // so have the server dealing with it
      server.removeConnection(socket);
    }
  }
}
