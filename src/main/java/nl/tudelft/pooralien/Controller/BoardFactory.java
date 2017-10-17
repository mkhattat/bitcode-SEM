package nl.tudelft.pooralien.Controller;

import java.io.File;

/**
 * Interface for BoardFactories.
 */
public interface BoardFactory {
    /**
     * Creates a board using the provided board layout.
     * @param layout A string representation of the board layout.
     * @return A board based on the provided layout.
     */
    Board createBoard(String layout);

    /**
     * Creates a board using the layout in the provided file.
     * @param boardF A file containing the board's desired layout.
     * @return A board based on the provided layout.
     */
    Board createBoard(File boardF);

    /**
     * Creates a board with a random layout.
     * @return A randomly filled board.
     */
    Board createRandom();
}
