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
        System.out.println("Warning: exception in gameConfig.getBoolean. Using default value.");
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
                System.out.println("Warning: number of elements out of bounds in gameConfig.getBooleanList. Using default value.");
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
        System.out.println("Warning: exception in gameConfig.getBooleanList. Using default value.");
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
                System.out.println("Warning: value out of bounds in gameConfig.getInteger. Using default value.");
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
        System.out.println("Warning: exception in gameConfig.getInteger. Using default value.");
        return standard;
    }

    /**
     * Retrieves an Integer List from the config.
     * Specifically, the Integer List corresponding
     * to the provided name.
     * @param name The name corresponding to the requested Integer List.
     * @param minListLength The minimum number of elements in the Integer List.
     * @param maxListLength The maximum number of elements in the Integer List.
     * @param minValues The Integer List of minimum valid values.
     * @param maxValues The Integer List of maximum valid values.
     * @param standard The default Integer List
     * @return The Integer List corresponding to the provided name.
     */
    @SuppressWarnings("unchecked")
    public static List<Integer> getIntegerList(String name, Integer minListLength, Integer maxListLength,
                                               List<Integer> minValues, List<Integer> maxValues,
                                               List<Integer> standard) {
        try {
            List<Integer> integerList = cfg.getListIntValueOf(name);
            if (integerList.size() < minListLength | integerList.size() > maxListLength) {
                System.out.println("Warning: number of elements out of bounds in gameConfig.getIntegerList. Using default value.");
                return standard;
            }
            for (int i = 0; i < integerList.size(); i++) {
                if (integerList.get(i) < minValues.get(i) | integerList.get(i) > maxValues.get(i)) {
                    System.out.println("Warning: value on index " + i + " out of bounds in gameConfig.getIntegerList. Using default value.");
                    return standard;
                }
            }
            return integerList;
        } catch (NotExistingVariableException e) {
            System.out.println("Integer List " + name + " not found.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Warning: exception in gameConfig.getIntegerList. Using default value.");
        return standard;
    }

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
            Double real = cfg.getRealValueOf(name);
            if (real < min | real > max) {
                System.out.println("Warning: value out of bounds in gameConfig.getReal. Using default value.");
                return standard;
            }
            return real;
        } catch (NotExistingVariableException e) {
            System.out.println("Real " + name + " not found.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Warning: exception in gameConfig.getReal. Using default value.");
        return standard;
    }

    /**
     * Retrieves a Real (Double) List from the config.
     * Specifically, the Real List corresponding
     * to the provided name.
     * @param name The name corresponding to the requested Real List.
     * @param minListLength The minimum number of elements in the Double List.
     * @param maxListLength The maximum number of elements in the Double List.
     * @param minValues The Double List of minimum valid values.
     * @param maxValues The Double List of maximum valid values.
     * @param standard The default value.
     * @return The Real List corresponding to the provided name.
     */
    @SuppressWarnings("unchecked")
    public static List<Double> getRealList(String name, Integer minListLength, Integer maxListLength,
                                           List<Double> minValues, List<Double> maxValues,
                                           List<Double> standard) {
        try {
            List<Double> doubleList = cfg.getListRealValueOf(name);
            if (doubleList.size() < minListLength | doubleList.size() > maxListLength) {
                System.out.println("Warning: number of elements out of bounds in gameConfig.getRealList. Using default value.");
                return standard;
            }
            for (int i = 0; i < doubleList.size(); i++) {
                if (doubleList.get(i) < minValues.get(i) | doubleList.get(i) > maxValues.get(i)) {
                    System.out.println("Warning: value on index " + i + " out of bounds in gameConfig.getRealList. Using default value.");
                    return standard;
                }
            }
            return doubleList;
        } catch (NotExistingVariableException e) {
            System.out.println("Real List " + name + " not found.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Warning: exception in gameConfig.getRealList. Using default value.");
        return standard;
    }

    /**
     * Retrieves a String value from the config.
     * Specifically, the String value corresponding
     * to the provided name.
     * @param name The name corresponding to the requested String.
     * @param minLength The minimum number of characters in the String.
     * @param maxLength The maximum number of characters in the String.
     * @param standard The default String.
     * @return The String corresponding to the provided name.
     */
    public static String getString(String name, Integer minLength, Integer maxLength, String standard) {
        try {
            String string = cfg.getStringValueOf(name);
            if (string.length() < minLength | string.length() > maxLength) {
                System.out.println("Warning: number of characters in string out of bounds in gameConfig.getString. Using default value.");
                return standard;
            }
            if (!isAlpha(string)) {
                System.out.println("Warning: string is not alphabetic in gameConfig.getString. Using default value.");
                return standard;
            }
            return string;
        } catch (NotExistingVariableException e) {
            System.out.println("String " + name + " not found.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Warning: exception in gameConfig.getString. Using default value.");
        return standard;
    }

    /**
     * Retrieves a String List from the config.
     * Specifically, the String List corresponding
     * to the provided name.
     * @param name The name corresponding to the requested String List.
     * @param minListLength The minimum number of elements in the Integer List.
     * @param maxListLength The maximum number of elements in the Integer List.
     * @param minStringLengths The ordered list of the minimum number of characters in
     *                         the String from the ordered String List.
     * @param maxStringLengths The ordered list of the maximum number of characters in
     *                         the String from the ordered String List.
     * @param standard The default String List.
     * @return The String List corresponding to the provided name.
     */
    @SuppressWarnings("unchecked")
    public static List<String> getStringList(String name, Integer minListLength, Integer maxListLength,
                                             List<Integer> minStringLengths, List<Integer> maxStringLengths,
                                             List<String> standard) {
        try {
            List<String> stringList = cfg.getListStringValueOf(name);
            if (stringList.size() < minListLength | stringList.size() > maxListLength) {
                System.out.println("Warning: number of elements out of bounds in gameConfig.getStringList. Using default value.");
                return standard;
            }
            for (int i = 0; i < stringList.size(); i++) {
                if ((stringList.get(i).length() < minStringLengths.get(i))
                        | (stringList.get(i).length() > maxStringLengths.get(i))) {
                    System.out.println("Warning: string length on index " + i + " out of bounds in gameConfig.getRealList. Using default value.");
                    return standard;
                }
                if (!isAlpha(stringList.get(i))) {
                    System.out.println("Warning: string on index " + i + " is not alphabetic in gameConfig.getRealList. Using default value.");
                    return standard;
                }
            }
            return stringList;
        } catch (NotExistingVariableException e) {
            System.out.println("String List " + name + " not found.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Warning: exception in gameConfig.getStringList. Using default value.");
        return standard;
    }

    /**
     * Checks if a given string is alphabetic to
     * prevent code injection.
     * @param string The string to be checked.
     * @return True if string is alphabetic, else False.
     */
    private static boolean isAlpha(String string) {
        char[] chars = string.toCharArray();
        for (char c : chars) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
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
