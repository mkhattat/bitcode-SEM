package nl.tudelft.pooralien;

public class InputDefender {

    public static int integer(int in, int min, int max, int standard) {
        if (in < min | in > max) {
            return standard;
        }
        else {
            return in;
        }
    }

    public static String string(String in, int maxSize, String standard) {
        if (in.length() > maxSize) {
            return standard;
        }
        // preventing code injection
        if (!isAlpha(in)) {
            return standard;
        }
        else {
            return in;
        }
    }

    public static float real(float in, float min, float max, float standard) {
        if (in < min | in > max) {
            return standard;
        }
        else {
            return in;
        }
    }

    public static boolean bool(boolean in, boolean standard) {
        return in;
    }

    private static boolean isAlpha(String name) {
        char[] chars = name.toCharArray();

        for (char c : chars) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }

        return true;
    }
}
