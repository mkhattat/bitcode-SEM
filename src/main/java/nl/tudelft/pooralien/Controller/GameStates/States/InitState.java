package nl.tudelft.pooralien.Controller.GameStates.States;

import nl.tu.delft.defpro.api.IDefProAPI;
import nl.tudelft.pooralien.Controller.GameStates.GameController;
import nl.tudelft.pooralien.Controller.GameStates.State;

import static nl.tu.delft.defpro.api.APIProvider.getAPI;

public class InitState implements State {
    
    @Override
    public void goToMainMenu() {
        //NOT POSSIBLE.
    }

    @Override
    public void initGame(GameController gameController) {

    }

    @Override
    public void startGame(GameController gameController) {

    }

    @Override
    public void pauseGame(GameController gameController) {
        //NOT POSSIBLE.
    }

    @Override
    public void endGame(GameController gameController) {
        // NOT POSSIBLE.
    }

    @Override
    public void exit() {

    }
}
