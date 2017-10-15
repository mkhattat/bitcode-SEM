package nl.tudelft.pooralien.Controller.HighScore;

import javax.swing.table.AbstractTableModel;

/**
 * Top10TableModel which gives structure and adds rules to the table.
 */
public class Top10TableModel extends AbstractTableModel {

    private String[] columnNames = {
        "No.",
        "Name",
        "Score"
    };

    //CONFIG FILE
    private final  int top10 = 10;

    private ScoreManager scoreManager;

    /**
     * Initialize the Top10TableModel with the scoreManager.
     */
    public Top10TableModel() {
        scoreManager = new ScoreManager();
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
        if (scoreManager.getSCORE_COUNT() < top10) {
            return scoreManager.getSCORE_COUNT();
        }
        return top10;
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
                return scoreManager.getTopTenScores().get(rowIndex).getName();
            case(2):
                return scoreManager.getTopTenScores().get(rowIndex).getScore();
            default: return null;
        }
    }
}
