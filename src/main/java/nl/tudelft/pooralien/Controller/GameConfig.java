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
     * to the provided name. If an exception occurs the
     * default value is returned.
     * @param name The name corresponding to the requested Boolean.
     * @param standard The default value.
     * @return The Boolean corresponding to the provided name.
     */
    public static Boolean getBoolean(String name, Boolean standard) {
        try {
            return cfg.getBooleanValueOf(name);
        } catch (NotExistingVariableException e) {
            System.out.println("Boolean " + name + " not found.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return standard;
    }

    /**
     * Retrieves a Boolean List from the config.
     * Specifically, the Boolean List corresponding
     * to the provided name. If an exception occurs or the
     * number of element is out of bounds, the default list is returned
     * @param name The name corresponding to the requested Boolean List.
     * @param minListLength The minimum number of elements in the Boolean List.
     * @param maxListLength The maximum number of elements in the Boolean List.
     * @param standard The default Boolean List.
     * @return The Boolean List corresponding to the provided name.
     */
    @SuppressWarnings("unchecked")
    public static List<Boolean> getBooleanList(String name, Integer minListLength, Integer maxListLength ,
                                               List<Boolean> standard) {
        try {
            List<Boolean> booleanList = cfg.getListBoolValueOf(name);
            if (booleanList.size() < minListLength | booleanList.size() > maxListLength) {
                return standard;
            }
            else {
                return booleanList;
            }
        } catch (NotExistingVariableException e) {
            System.out.println("Boolean List " + name + " not found.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return standard;
    }

    /**
     * Retrieves an Integer value from the config.
     * Specifically, the Integer value corresponding
     * to the provided name. If an exception occurs or the
     * value is out of bounds, the default value is returned.
     * @param name The name corresponding to the requested Integer.
     * @param min The minimum valid value.
     * @param max The maximum valid value.
     * @param standard The default value.
     * @return The Integer corresponding to the provided name.
     */
    public static Integer getInteger(String name, Integer min, Integer max, Integer standard) {
        try {
            Integer integer = cfg.getIntegerValueOf(name);
            if (integer < min | integer > max) {
                return standard;
            }
            else {
                return integer;
            }
        } catch (NotExistingVariableException e) {
            System.out.println("Integer " + name + " not found.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return standard;
    }

    // TODO: Implement check boundaries logic
    /**
     * Retrieves an Integer List from the config.
     * Specifically, the Integer List corresponding
     * to the provided name.
     * @param name The name corresponding to the requested Integer List.
     * @param maxListLength The maximum number of elements in the Integer List.
     * @param minValues The Integer List of minimum valid values.
     * @param maxValues The Integer List of maximum valid values.
     * @param standard The default Integer List
     * @return The Integer List corresponding to the provided name.
     */
    @SuppressWarnings("unchecked")
    public static List<Integer> getIntegerList(String name, Integer maxListLength, List<Integer> minValues,
                                               List<Integer> maxValues, List<Integer> standard) {
        try {
            return (List<Integer>) cfg.getListIntValueOf(name);
        } catch (NotExistingVariableException e) {
            System.out.println("Integer List " + name + " not found.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    // TODO: Implement check boundaries logic
    /**
     * Retrieves a Real (Double) value from the config.
     * Specifically, the Real value corresponding
     * to the provided name.
     * @param name The name corresponding to the requested Real.
     * @param min The minimum valid value.
     * @param max the maximum valid value.
     * @param standard The default value.
     * @return The Real corresponding to the provided name.
     */
    public static Double getReal(String name, Double min, Double max, Double standard) {
        try {
            return cfg.getRealValueOf(name);
        } catch (NotExistingVariableException e) {
            System.out.println("Real " + name + " not found.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    // TODO: Implement check boundaries logic
    /**
     * Retrieves a Real (Double) List from the config.
     * Specifically, the Real List corresponding
     * to the provided name.
     * @param name The name corresponding to the requested Real List.
     * @param maxListLength The maximum number of elements in the Double List.
     * @param minValues The Double List of minimum valid values.
     * @param maxValues The Double List of maximum valid values.
     * @param standard The default value.
     * @return The Real List corresponding to the provided name.
     */
    @SuppressWarnings("unchecked")
    public static List<Double> getRealList(String name, int maxListLength, List<Double> minValues,
                                           List<Double> maxValues, List<Double> standard) {
        try {
            return (List<Double>) cfg.getListRealValueOf(name);
        } catch (NotExistingVariableException e) {
            System.out.println("Real List " + name + " not found.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    // TODO: Implement check boundaries logic
    /**
     * Retrieves a String value from the config.
     * Specifically, the String value corresponding
     * to the provided name.
     * @param name The name corresponding to the requested String.
     * @param maxLength The maximum number of characters in the String
     * @param standard The default String.
     * @return The String corresponding to the provided name.
     */
    public static String getString(String name, int maxLength, String standard) {
        try {
            return cfg.getStringValueOf(name);
        } catch (NotExistingVariableException e) {
            System.out.println("String " + name + " not found.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    // TODO: Implement check boundaries logic
    /**
     * Retrieves a String List from the config.
     * Specifically, the String List corresponding
     * to the provided name.
     * @param name The name corresponding to the requested String List.
     * @param maxListLength The maximum number of elements in the Integer List.
     * @param maxStringLengths The ordered list of the maximum number of characters in
     *                         the String from the ordered String List.
     * @param standard The default String List.
     * @return The String List corresponding to the provided name.
     */
    @SuppressWarnings("unchecked")
    public static List<String> getStringList(String name, int maxListLength, List<Integer> maxStringLengths,
                                             String standard) {
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
