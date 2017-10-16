package nl.tudelft.pooralien.Controller;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Client implements Runnable {
  private JFrame mainFrame;
  private JTextField tf = new JTextField();
  private JTextArea ta = new JTextArea();

  // The socket connecting us to the server
  private Socket socket;

  // the streams we communicate to the server; these come
  // from the socket.
  private DataOutputStream out;
  private DataInputStream in;

  public Client(String host, int port) {
    // setup the interface
    prepareGUI();

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
      new Thread(this).start();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    String serverAddress = JOptionPane.showInputDialog(
        "Enter IP Address of a machine that is \n"
        + "running the server on port 9090:");
    new Client(serverAddress, 9090);
  }

  private void prepareGUI() {
    mainFrame = new JFrame();
    GridBagConstraints gb = new GridBagConstraints();
    mainFrame.setLayout(new GridBagLayout());
    gb.gridx = 0;
    gb.gridy = 0;
    gb.weightx = 1;
    tf.setPreferredSize(new Dimension(300 , 100));
    mainFrame.add(tf, gb);
    gb.gridx = 0;
    gb.gridy = 1;
    gb.weightx = 1;
    ta.setPreferredSize(new Dimension(300, 100));
    mainFrame.add(ta, gb);
    // we want to receive messages when someone types a line
    // and hits return, using an anonymous class as a callback
    tf.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        processMessage(e.getActionCommand());
      }
    } );
    mainFrame.pack();
    mainFrame.setVisible(true);
  }

  private void processMessage(String message) {
    try {
      // send it to the server
      out.writeUTF(message);

      // clear out text input field
      tf.setText("");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void run() {
    try {
      // receive messages one-by-one
      while (true) {
        // Get the next message
        String message = in.readUTF();

        // print it to the window
        ta.append(message + "\n");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
