package nl.tudelft.pooralien.ui.HighScoreTable;

import nl.tudelft.pooralien.Controller.Game;
import nl.tudelft.pooralien.Controller.GameStates.GameControllerMachine;

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

        //Custom button text
        Object[] buttonText = {"Exit To Main Menu"};

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
    //TODO: THIS METHOD WILL BE IMPLEMENTED VIA THE GAMESTATE BRANCH
    private void selectedOption(int optionSelected) {
        GameControllerMachine gameControllerMachine = Game.getGame().getGameControllerMachine();
        switch (optionSelected) {
            case (0):
                //game controller new board
                gameControllerMachine.setState(gameControllerMachine.getGameInitState());
                gameControllerMachine.initGame();
                break;
            case(1):
                //game controller main menu
                gameControllerMachine.setState(gameControllerMachine.getMainMenuState());
                gameControllerMachine.mainMenu();
                break;
            default:
                break;
        }
    }

}
