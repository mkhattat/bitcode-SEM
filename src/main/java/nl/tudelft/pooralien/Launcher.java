package nl.tudelft.pooralien;


import javax.swing.JFrame;

/**
 * Created by mostafa on 7-9-17.
 */
final class Launcher extends JFrame {

    private static final int FRAME_WIDTH = 300;
    private static final int FRAME_HEIGHT = 200;

    /**
     * To prevent checkstyle error.
     */
    private Launcher() {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setTitle("Simple");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Entry point of the game.
     * @param args The program arguments.
     */
    public static void main(String[] args) {

        System.out.println("Hi");
        Launcher window = new Launcher();
        new EventHandler(window);
        window.setVisible(true);

    }
}
