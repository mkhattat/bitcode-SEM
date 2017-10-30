package nl.tudelft.pooralien.ui.HighScoreTable;

import nl.tudelft.pooralien.Controller.Game;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;

/**
 * HighScoreFrame Class.
 */
public class HighScoreFrame {

    /**
     * Constructs an JTable Dialog.
     */
    public HighScoreFrame() {
        JTable highScoreBoard = Game.getGame().getHighScoreTableTopX();
        JScrollPane pane = new JScrollPane(highScoreBoard);
        //pane.setWheelScrollingEnabled(true);



        //Custom button text
        Object[] buttonText = {"New Game", "Exit To Main Menu", "Exit To Desktop"};

        int optionSelected = JOptionPane.showOptionDialog(null,
                pane,
                "HighScore",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null, buttonText, 0);

        selectedOption(optionSelected);


    }

    /**
     * Decides what to do with the selected option.
     * @param optionSelected from the buttonText array in the constructor.
     */
    private void selectedOption(int optionSelected) {
        switch (optionSelected) {
            case (0):
                //game controller new board
                break;
            case(1):
                //game controller main menu
                break;
            case(2):
                break;
            default:
                //game controller exit
                break;
        }
    }

}
