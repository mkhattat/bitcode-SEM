package nl.tudelft.pooralien;


import javax.swing.JFrame;

/**
 * Created by mostafa on 7-9-17.
 */
final class Launcher extends JFrame {

    /**
     * To prevent checkstyle error.
     */
    private Launcher() {
        setSize(300, 200);
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
        EventHandler event = new EventHandler(window);
        window.setVisible(true);

    }
}
