package nl.tudelft.pooralien.ui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import nl.tudelft.pooralien.Observer;
import nl.tudelft.pooralien.Subject;
import nl.tudelft.pooralien.Controller.Listener;
import nl.tudelft.pooralien.Controller.Server;

/**
 * A user interface to see the connections.
 *
 */
public class ConnectionScreen implements Observer {
    private JLabel headerLabel;
    private JTextArea textArea;
    private JPanel controlPanel;
    private Listener listener;
    private MainScreen mainScreen;
    private JDialog dialog;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;

    /**
     * constructor used to create this GUI.
     *
     * @param listener
     *            is the listener class.
     * @param mainScreen
     *          is the main screen of the game.
     */
    public ConnectionScreen(Listener listener, MainScreen mainScreen) {
        this.listener = listener;
        this.mainScreen = mainScreen;
        this.dialog = new JDialog();
        prepareGUI();
    }

    /**
     * Close this screen.
     *
     */
    private void closeFrame() {
        Server.getServer().removeObserver(this);
        dialog.dispose();
    }

    /**
     * Prepare GUI.
     *
     */
    private void prepareGUI() {
        dialog.setTitle("Creating a network");
        dialog.setSize(WIDTH, HEIGHT);
        dialog.setLayout(new GridLayout(2 + 1, 1));
        try {
            this.headerLabel = new JLabel("The server is listening on "
                    + InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            this.headerLabel = new JLabel("I can not get the server address!");
        }
        dialog.add(headerLabel);
        this.textArea = new JTextArea();
        textArea.setEditable(false);
        dialog.add(textArea);
        this.controlPanel = new JPanel(new FlowLayout());
        JButton startButton = new JButton("Start");
        JButton cancelButton = new JButton("Cancel");
        controlPanel.add(startButton);
        controlPanel.add(cancelButton);
        dialog.add(controlPanel);
        dialog.pack();
        dialog.setVisible(true);
        cancelButton.addActionListener(new CancelButtonListener());
        startButton.addActionListener(new StartButtonListener());

    }

    /**
     * An listener for cancel button.
     */
    private class CancelButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Server.getServer().sendToAll("ServerIsDying;");
            listener.terminate();
            Server.getServer().destroy();
            StartupScreen startupScreen = new StartupScreen();
            startupScreen.show();
            closeFrame();
        }
    }

    /**
     * An listener for start button.
     */
    private class StartButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            mainScreen.launch();
            listener.terminate();
            Server.getServer().startMultiPlayerGame();
            closeFrame();
        }
    }

    @Override
    public void update(Subject subject) {
        if (!(subject instanceof Server)) {
            return;
        }
        textArea.setText(Server.getServer().getSize() + " Player(s) joined!\n");
    }

}
