package nl.tudelft.pooralien.ui.HighScoreTable;

import nl.tudelft.pooralien.Controller.Game;
import nl.tudelft.pooralien.Controller.HighScore.ScoreManager;
import nl.tudelft.pooralien.ui.MainScreen;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Initialize the object to automatically handle the score input.
 */
public class HighScoreEnterNameDialog {

    //SHOULD BE ADDDED TO CONFIG?
    private static final int MAX_INPUT_CHARACTERS = 20;
    private ScoreManager scoreManager;

    /**
     * Shows an input dialog if the user has scored in the top ten.
     * @param firstTry if this is the first highscore dialog. (standard = true).
     * @param score is the user's score after finishing a game.
     */
    public HighScoreEnterNameDialog(boolean firstTry, int score) {
        scoreManager = new ScoreManager();

        if (checkScoreEligibleForHighScore(score)) {
            String userInput = constructDialog(firstTry);

            handleUserInput(userInput, score);
        }

        if (Game.getGame().getBackgroundTileCatalog().size() > 0) {
            JOptionPane.showMessageDialog(new JFrame(),
                    "Your score is: " + Game.getGame().getScoreCounter().getScore(),
                    "GAME OVER",
                    JOptionPane.ERROR_MESSAGE);
            Game.getGame().reset();
            MainScreen mainScreen = Game.getGame().getMainScreen();
            if (mainScreen != null) {
                mainScreen.refreshBoard();
            }
        }
    }

    /**
     *
     * @param firstTry After the user has not entered a name or the name was longer than
     *                 20 characters this becomes false (automatically),
     *                 so that a different dialog gets returned.
     * @return Returns a normal dialog or another one with instructions if
     *          the user has entered wrong input.
     */
    private String constructDialog(boolean firstTry) {
        if (firstTry) {
            return constructNormalDialog();
        } else {
            return constructRetryDialog();
        }
    }

    /**
     * @param score to be checked.
     * @return True , if the amount of scores saved is smaller than the highScore count configured,
     * or if the score is higher than the lowest score in the topX.
     */
    private boolean checkScoreEligibleForHighScore(int score) {
        return scoreManager.getSCORE_COUNT() < scoreManager.getTopXScoreCount()
                || score >= scoreManager.getLowestScoreInTopX();
    }

    /**
     * Constructs a dialog window. This is called after a player finishes a game.
     * @return String with the value null if the user presses cancel else it is set.
     */
    private String constructNormalDialog() {
        JFrame frame = new JFrame("inputDialog");
        return JOptionPane.showInputDialog(
                frame,
                "Enter the name to be added to the highscore",
                "You have beat the top 10 players",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Constructs a dialog window. This is called after a player finishes a game.
     * @return String with the value null if the user presses cancel else it is set.
     */
    private String constructRetryDialog() {
        JFrame frame = new JFrame("inputRetryDialog");
        return JOptionPane.showInputDialog(
                frame,
                "Please enter a different name (must be under 20 characters).",
                "Please retry...",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Handles the dialog return values. Creates new dialogs if needed or saves the score.
     * @param userInput is the name that the user entered for the highscore.
     * @param score is the score that the user scored in the game.
     */
    private void handleUserInput(String userInput, int score) {
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
        Game.getGame().getHighScoreTableTopX().repaint();
    }


    /**
     * If the user pressed cancel on the dialog, print message to console.
     */
    private void userPressedCancel() {
        System.out.println("Player pressed cancel -> return to main menu / high score.");
    }

    /**
     * If the user has provided no input or input that is longer than 20 characters,
     * then construct a new and other dialog.
     * @param score that the user has scored in the game.
     */
    private void incorrectUserInput(int score) {
        new HighScoreEnterNameDialog(false, score);
    }
}