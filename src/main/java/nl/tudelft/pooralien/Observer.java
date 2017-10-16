package nl.tudelft.pooralien;

/**
 * Observer interface used for the observer design pattern.
 */
public interface Observer {

    /**
     * Update method of an observer is called when data is
     * changed from the Subject object.
     *
     * @param subject the observed subject object
     */
    void update(Subject subject);
}
