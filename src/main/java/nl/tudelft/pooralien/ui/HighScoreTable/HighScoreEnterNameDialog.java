package nl.tudelft.pooralien.ui.HighScoreTable;

import nl.tudelft.pooralien.Controller.Game;
import nl.tudelft.pooralien.Controller.HighScore.ScoreManager;
import nl.tudelft.pooralien.Controller.HighScore.TopXTableModel;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Initialize the object to automatically handle the score input.
 */
public class HighScoreEnterNameDialog {

    //SHOULD BE ADDDED TO CONFIG?
    private static final int MAX_INPUT_CHARACTERS = 20;


    /**
     * Shows an input dialog if the user has scored in the top ten.
     * @param firstTry if this is the first highscore dialog. (standard = true).
     * @param score is the user's score after finishing a game.
     */
    public HighScoreEnterNameDialog(boolean firstTry, int score) {
        ScoreManager scoreManager = new ScoreManager();

        // IF THE USER HAS NOT SCORED IN THE TOP TEN THEN RETURN BUT
        // IF THERE ARE LESS THEN TEN SCORES CONTINUE.
        if (score < scoreManager.getLowestScoreInTopX()
                && scoreManager.getSCORE_COUNT() < scoreManager.getTopXScoreCount()) {
            return;
        }

        String userInput = checkFirstTry(firstTry);

        //USER PRESSED CANCEL
        if (userInput == null) {
            userPressedCancel();
            return;
        }

        //USER DID NOT ENTER ANYTHING OR SURPASSED 20 CHARACTERS
        if (userInput.isEmpty() || userInput.length() > MAX_INPUT_CHARACTERS) {
            incorrectUserInput(score);
            return;
        }
        scoreManager.addScore(userInput, score);
        Game.getGame().getHighScoreTableTopX().getTable().repaint();



        System.out.printf("The player's name is '%s'.\n", userInput);
    }

    /**
     *
     * @param firstTry
     * @return
     */
    private String checkFirstTry(boolean firstTry) {
        if (firstTry) {
            return constructNormalDialog();
        } else {
            return constructRetryDialog();
        }
    }

    /**
     * Constructs a dialog window. This is called after a player finishes a game.
     * @return String with the value null if the user presses cancel else it is set.
     */
    private String constructNormalDialog() {
        JFrame frame = new JFrame("inputDialog");
        String input = JOptionPane.showInputDialog(
                frame,
                "Enter the name to be added to the highscore",
                "You have beat the top 10 players",
                JOptionPane.INFORMATION_MESSAGE
        );
        return input;
    }

    /**
     * Constructs a dialog window. This is called after a player finishes a game.
     * @return String with the value null if the user presses cancel else it is set.
     */
    private String constructRetryDialog() {
        JFrame frame = new JFrame("inputRetryDialog");
        String input = JOptionPane.showInputDialog(
                frame,
                "Please enter a different name (must be under 20 characters).",
                "Please retry...",
                JOptionPane.INFORMATION_MESSAGE
        );
        return input;
    }

    private void userPressedCancel() {
        System.out.println("Player pressed cancel -> return to main menu / high score.");
    }

    private void incorrectUserInput(int score) {
        HighScoreEnterNameDialog hSEND = new HighScoreEnterNameDialog(false, score);
    }
}