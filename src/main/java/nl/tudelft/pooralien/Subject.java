package nl.tudelft.pooralien;

/**
 * Subject interface used for the observer design pattern.
 */
public interface Subject {

    /**
     * Method for registering the observers.
     *
     * @param observer the to be registered observer
     */
    void registerObserver(Observer observer);

    /**
     * Method for removing the registered observers.
     *
     * @param observer the to be removed observer
     */
    void removeObserver(Observer observer);

    /**
     * Method for notifying the registered observers.
     */
    void notifyObservers();
}

