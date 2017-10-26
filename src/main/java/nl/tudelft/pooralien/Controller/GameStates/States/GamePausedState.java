package nl.tudelft.pooralien.Controller.GameStates.States;

import nl.tudelft.pooralien.Controller.Game;
import nl.tudelft.pooralien.Controller.GameStates.GameControllerMachine;
import nl.tudelft.pooralien.Controller.GameStates.State;

public class GamePausedState implements State {

    GameControllerMachine gameControllerMachine;

    public GamePausedState(GameControllerMachine gameControllerMachine) {
        this.gameControllerMachine = gameControllerMachine;
    }

    @Override
    public void MainMenu() {

    }

    @Override
    public void startGame() {

    }

    @Override
    public void pauseGame() {
        Game.getGame().pauseGame();
    }

    @Override
    public void resumeGame() {
        Game.getGame().resumeGame();
        gameControllerMachine.setState(gameControllerMachine.getGamePlayState());
    }

    @Override
    public void endGame() {

    }

    @Override
    public void loadGame() {

    }

    @Override
    public void saveGame() {

    }

    @Override
    public void exit() {

    }

    @Override
    public void dragAnimation() {

    }

}
