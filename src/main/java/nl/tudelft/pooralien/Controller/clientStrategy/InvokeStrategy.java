package nl.tudelft.pooralien.Controller.clientStrategy;

import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * Handle mapping the command to the right interface.
 *
 */
public class InvokeStrategy {
    private HashMap<String, Strategy> strategyMap;

    /**
     * The constructor of this class.
     *
     * @see Object#InvokeStrategy()
     */
    public InvokeStrategy() {
        this.strategyMap = new HashMap<>();
        init();
    }

    /**
     * initialize the hash map with default strategy commands.
     *
     */
    private void init() {
        strategyMap.put("Ready", new Ready());
        strategyMap.put("Play", new Play());
        strategyMap.put("Wait", new Wait());
        strategyMap.put("ServerIsDying", new ClosedServer());
        strategyMap.put("NewBoard", new NewBoard());
        strategyMap.put("NewBackgroundCatalog", new NewBackgroundCatalog());
        strategyMap.put("StartAnimation", new StartAnimation());
        strategyMap.put("UpdateAnimation", new UpdateAnimation());
        strategyMap.put("StopAnimation", new StopAnimation());
    }

    /**
     * Get the right strategy function based on the key.
     *
     * @param key is the command to map to the right interface
     * @return an strategy based on the key
     *
     * @throws NoSuchElementException if the key doesn't recognized.
     */
    public Strategy getStrategy(String key) throws NoSuchElementException {
        if (strategyMap.containsKey(key)) {
            return strategyMap.get(key);
        }
        throw new NoSuchElementException("The key " + key 
                + " doesn't exist");
    }

    /**
     * Add a new strategy to the database.
     *
     * @param key is the name of the command.
     * @param strategy is an implementation for the command based on the
     *              Strategy interface.
     */
    public void addStrategy(String key, Strategy strategy) {
        if (strategyMap.containsKey(key)) {
            return;
        }
        if (key == null || strategy == null) {
            return;
        }
        strategyMap.put(key, strategy);
    }
}
