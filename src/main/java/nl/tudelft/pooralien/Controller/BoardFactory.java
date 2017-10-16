package nl.tudelft.pooralien.Controller;

/**
 * Interface for BoardFactories.
 */
public interface BoardFactory {
    /**
     * Creates a board using the provided layout.
     * @param layout A string representation of the layout.
     * @return A board based on the provided layout.
     * @throws IllegalArgumentException If the provided layout
     * cannot be used to create a board.
     */
    Board createBoard(String layout) throws IllegalArgumentException;

    /**
     * Creates a board with a random layout.
     * @return A randomly filled board.
     */
    Board createRandomBoard();
}
