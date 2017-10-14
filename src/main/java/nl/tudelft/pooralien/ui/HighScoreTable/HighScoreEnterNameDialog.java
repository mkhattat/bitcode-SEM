package nl.tudelft.pooralien.ui.HighScoreTable;

import javax.swing.*;


public class HighScoreEnterNameDialog {

    public HighScoreEnterNameDialog() {
        JFrame frame = new JFrame("InputDialog Example");
        String code = JOptionPane.showInputDialog(
                frame,
                "Enter the name to be added to the highscore",
                "You have beat the top 10 players",
                JOptionPane.INFORMATION_MESSAGE
        );
        // if the user presses Cancel, this will be null
        System.out.printf("The player's name is '%s'.\n", code);
        System.exit(0);
    }

}