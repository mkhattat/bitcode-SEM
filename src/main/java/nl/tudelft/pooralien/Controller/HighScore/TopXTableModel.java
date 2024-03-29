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
    private final ScoreManager scoreManager;

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
        return columnNames.length + 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex < 0
                || columnIndex < 0
                || columnIndex > getColumnCount()
                // -1 Because index != line count
                || rowIndex > getRowCount() - 1) {
            return null;
        }

        return columnObject(columnIndex, rowIndex);
    }



    /**
     * Returns the object that corresponds with the column and row index.
     * @param columnIndex index of the column.
     * @param rowIndex index of the row.
     * @return No. (int), Name (String), Score (int) or null.
     */
    private Object columnObject(int columnIndex, int rowIndex) {
        switch (columnIndex) {
            case(0):
                //Index starts at 0, scoreNo. (Which is returned), does not.
                return rowIndex + 1;
            case(1):
                return scoreManager.getTopXScores().get(rowIndex).getName();
            case(2):
                return scoreManager.getTopXScores().get(rowIndex).getScore();
            default: return null;
        }
    }

    /**
     * @return StringArray with all the columnNames
     */
    public String[] getColumnNames() {
        return columnNames;
    }


}
