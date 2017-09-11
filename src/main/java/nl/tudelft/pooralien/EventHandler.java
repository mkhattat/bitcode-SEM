package nl.tudelft.pooralien;

/**
 * Listen for events, capures them and perform actions.
 */
public class EventHandler {

    /**
     * Initiate event handlers on a given JFrame.
     *
     * @param  window the JFrame where where to listen for events
     */
    public EventHandler(Launcher window) {
        new MouseEventHandler(window);
    }
}
