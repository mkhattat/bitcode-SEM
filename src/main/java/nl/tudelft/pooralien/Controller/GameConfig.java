package nl.tudelft.pooralien.Controller;

import nl.tu.delft.defpro.api.IDefProAPI;
import nl.tu.delft.defpro.exception.NotExistingVariableException;

import java.util.List;

import static nl.tu.delft.defpro.api.APIProvider.getAPI;

/**
 * GameConfig class, used to read values from the config file.
 */
public final class GameConfig {
    private static String cfgPath;
    private static IDefProAPI cfg;

    static {
        try {
            cfgPath = GameConfig.class.getResource("/config.txt").toURI()
                    .getPath().replaceFirst("^/(.:/)", "$1");
            cfg = getAPI(cfgPath);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Constructor for GameConfig.
     */
    private GameConfig() { }

    /**
     * Retrieves a Boolean value from the config.
     * Specifically, the Boolean value corresponding
     * to the provided name.
     * @param name The name corresponding to the requested Boolean.
     * @return The Boolean corresponding to the provided name.
     */
    public static Boolean getBoolean(String name) {
        try {
            return cfg.getBooleanValueOf(name);
        } catch (NotExistingVariableException e) {
            System.out.println("Boolean " + name + " not found.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Retrieves a Boolean List from the config.
     * Specifically, the Boolean List corresponding
     * to the provided name.
     * @param name The name corresponding to the requested Boolean List.
     * @return The Boolean List corresponding to the provided name.
     */
    @SuppressWarnings("unchecked")
    public static List<Boolean> getBooleanList(String name) {
        try {
            return (List<Boolean>) cfg.getListBoolValueOf(name);
        } catch (NotExistingVariableException e) {
            System.out.println("Boolean List " + name + " not found.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Retrieves an Integer value from the config.
     * Specifically, the Integer value corresponding
     * to the provided name.
     * @param name The name corresponding to the requested Integer.
     * @return The Integer corresponding to the provided name.
     */
    public static Integer getInteger(String name) {
        try {
            return cfg.getIntegerValueOf(name);
        } catch (NotExistingVariableException e) {
            System.out.println("Integer " + name + " not found.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Retrieves an Integer List from the config.
     * Specifically, the Integer List corresponding
     * to the provided name.
     * @param name The name corresponding to the requested Integer List.
     * @return The Integer List corresponding to the provided name.
     */
    @SuppressWarnings("unchecked")
    public static List<Integer> getIntegerList(String name) {
        try {
            return (List<Integer>) cfg.getListIntValueOf(name);
        } catch (NotExistingVariableException e) {
            System.out.println("Integer List " + name + " not found.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Retrieves a Real (Double) value from the config.
     * Specifically, the Real value corresponding
     * to the provided name.
     * @param name The name corresponding to the requested Real.
     * @return The Real corresponding to the provided name.
     */
    public static Double getReal(String name) {
        try {
            return cfg.getRealValueOf(name);
        } catch (NotExistingVariableException e) {
            System.out.println("Real " + name + " not found.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Retrieves a Real (Double) List from the config.
     * Specifically, the Real List corresponding
     * to the provided name.
     * @param name The name corresponding to the requested Real List.
     * @return The Real List corresponding to the provided name.
     */
    @SuppressWarnings("unchecked")
    public static List<Double> getRealList(String name) {
        try {
            return (List<Double>) cfg.getListRealValueOf(name);
        } catch (NotExistingVariableException e) {
            System.out.println("Real List " + name + " not found.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Retrieves a String value from the config.
     * Specifically, the String value corresponding
     * to the provided name.
     * @param name The name corresponding to the requested String.
     * @return The String corresponding to the provided name.
     */
    public static String getString(String name) {
        try {
            return cfg.getStringValueOf(name);
        } catch (NotExistingVariableException e) {
            System.out.println("String " + name + " not found.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Retrieves a String List from the config.
     * Specifically, the String List corresponding
     * to the provided name.
     * @param name The name corresponding to the requested String List.
     * @return The String List corresponding to the provided name.
     */
    @SuppressWarnings("unchecked")
    public static List<String> getStringList(String name) {
        try {
            return (List<String>) cfg.getListStringValueOf(name);
        } catch (NotExistingVariableException e) {
            System.out.println("String List " + name + " not found.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Sets the config that the values are retrieved from,
     * iff the new config is not null.
     * @param newConfig The new config.
     */
    public static void setConfig(IDefProAPI newConfig) {
        if (newConfig != null) {
            cfg = newConfig;
        }
    }

    /**
     * Resets the config that values are retrieved from to the default config.
     */
    public static void resetConfig() {
        try {
            cfg = getAPI(cfgPath);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
