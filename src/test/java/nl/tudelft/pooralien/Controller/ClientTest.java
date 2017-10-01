package nl.tudelft.pooralien.Controller;

import nl.tudelft.pooralien.MouseEventHandler;
import org.junit.After;
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
    private volatile boolean running;
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private MouseEventHandler subject;
    private boolean updateAnimation;
    private String host;
    private int port;

    @Before
    public void setUp() throws Exception {
        socket = new Socket(host, port);
        Client cl = new Client( host,  port,  subject);
    }

    @Test
    public void start() throws Exception {
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
       // assertTrue()


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