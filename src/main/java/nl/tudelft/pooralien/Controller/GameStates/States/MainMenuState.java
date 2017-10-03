package nl.tudelft.pooralien.Controller.GameStates.States;

import nl.tudelft.pooralien.Controller.Game;
import nl.tudelft.pooralien.Controller.GameStates.GameControllerMachine;
import nl.tudelft.pooralien.Controller.GameStates.State;
import nl.tudelft.pooralien.Launcher;
import nl.tudelft.pooralien.ui.MainScreen;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


/**
 * The MainMenuState class.
 */
public class MainMenuState implements State {

    private GameControllerMachine gameControllerMachine;

    /**
     * The Main Menu state of the state machine.
     * @param gameControllerMachine object, used to alter behavior.
     */
    public MainMenuState(GameControllerMachine gameControllerMachine) {
        this.gameControllerMachine = gameControllerMachine;
    }

    @Override
    public void mainMenu() {
        // Already in main menu. -> OR should this method be called in the MainMenuState constructor
        System.out.println("CurrentState: MainMenuState, ");


        try {
            JFrame mainWindow = new JFrame(Launcher.getGameCfg().getStringValueOf("gameTitle"));
            MainScreen mainScreen = new MainScreen();
            mainWindow.setSize(0, 0);
            mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            JPanel gameHolder = new JPanel();
            gameHolder.add(mainScreen);

            mainWindow.getContentPane().add(gameHolder);

            mainWindow.pack();
            Game.getGame().registerObserver(mainScreen);
            Game.getGame().setMultiplayer(false);
            if (!Launcher.getGameCfg().getBooleanValueOf("multiLevel")) {
                mainWindow.setVisible(true);
            }
            mainWindow.repaint();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        gameControllerMachine.setState(gameControllerMachine.getGameInitState());
        gameControllerMachine.initGame();
    }

    @Override
    public void initGame() {
        throw new IllegalStateException("CurrentState: MainMenuState, " +
                "initGame() is not possible.");
    }

    @Override
    public void startGame() {
        throw new IllegalStateException("CurrentState: MainMenuState, "
                + "startGame() is not possible.");
    }

    @Override
    public void pauseGame() {
        throw new IllegalStateException("CurrentState: MainMenuState, "
                + "pauseGame() is not possible.");
    }

    @Override
    public void resumeGame() {
        throw new IllegalStateException("CurrentState: MainMenuState, "
                + "resumeGame() is not possible.");
    }

    @Override
    public void endGame() {
        throw new IllegalStateException("CurrentState: MainMenuState, " +
                "endGame() is not possible.");
    }

    @Override
    public void loadGame() {
        //NOT YET IMPLEMENTED.
    }

    @Override
    public void saveGame() {
        throw new IllegalStateException("CurrentState: MainMenuState, " +
                "saveGame() is not possible.");
    }

}
