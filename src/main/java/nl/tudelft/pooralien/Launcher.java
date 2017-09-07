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
        setDefaultCloseOperation();
    }

    /**
     * Entry point of the game.
     * @param args The program arguments.
     */
    public static void main(String[] args) {

        System.out.println("Hi");
        Launcher simple = new Launcher();
        simple.setVisible(true);

    }
}
