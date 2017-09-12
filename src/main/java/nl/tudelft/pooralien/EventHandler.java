package nl.tudelft.pooralien;

import javax.swing.JFrame;

/**
 * Listen for events, capures them and perform actions.
 */
public class EventHandler {

    /**
     * Initiate event handlers on a given JFrame.
     *
     * @param  window the JFrame where where to listen for events
     */
    public EventHandler(JFrame window) {
        new MouseEventHandler(window);
    }
}
