package nl.tudelft.pooralien.Controller.HighScore;

import javax.swing.table.AbstractTableModel;

/**
 * TopXTableModel which gives structure and adds rules to the table.
 */
public class TopXTableModel extends AbstractTableModel {

    private final String[] columnNames = {
        "No.",
        "Name",
        "Score"
    };

    private int topXScores;
    private ScoreManager scoreManager;

    /**
     * Initialize the TopXTableModel with the scoreManager and topXScores.
     */
    public TopXTableModel() {
        scoreManager = new ScoreManager();
        topXScores = scoreManager.getTopXScoreCount();
    }


    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public String getColumnName(int column) {
        if (column > -1 && column < columnNames.length) {
            return columnNames[column];
        }
        return null;
    }

    @Override
    public int getRowCount() {
        topXScores = scoreManager.getTopXScoreCount();
        if (scoreManager.getSCORE_COUNT() < topXScores) {
            return scoreManager.getSCORE_COUNT();
        }
        return topXScores;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex < 0 || columnIndex < 0) {
            return null;
        }
        if (columnIndex > getColumnCount()) {
            return null;
        }
        // -1 Because index != line count
        if (rowIndex > getRowCount() - 1) {
            return null;
        }

        switch (columnIndex) {
            case(0):
                //Index starts at 0, score No. does not.
                return rowIndex + 1;
            case(1):
                return scoreManager.getTopXScores().get(rowIndex).getName();
            case(2):
                return scoreManager.getTopXScores().get(rowIndex).getScore();
            default: return null;
        }
    }


}
