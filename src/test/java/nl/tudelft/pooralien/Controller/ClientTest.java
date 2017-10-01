package nl.tudelft.pooralien.Controller;

import nl.tudelft.pooralien.MouseEventHandler;
import nl.tudelft.pooralien.ui.MainScreen;
import org.junit.Before;
import org.junit.Test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import static org.junit.Assert.*;

/**
 * Created by Sam on 10/1/2017.
 */
public class ClientTest {
    private Server server;
    private volatile boolean running = false;
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private MouseEventHandler subject;
    private boolean updateAnimation;
    private String host = "localhost";
    private int port = 9090;
    Client clt;

    @Before
    public void setUp() throws Exception {
        server = Server.getServer();

        clt = new Client(host ,port, new MouseEventHandler(new MainScreen()));
        socket = new Socket(host, port);
        in = new DataInputStream(socket.getInputStream());
        out  = new DataOutputStream(socket.getOutputStream());
        clt.start();
    }

    @Test
    public void start() throws Exception {
        boolean started  = clt.start();
        assertTrue(started);
    }

    @Test
    public void terminate() throws Exception {
    }

    @Test
    public void run() throws Exception {
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void update1() throws Exception {
    }

    @Test
    public void stopAnimation() throws Exception {
    }

    @Test
    public void updateAnimation() throws Exception {
    }

    @Test
    public void startAnimation() throws Exception {
    }

}