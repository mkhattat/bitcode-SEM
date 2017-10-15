package nl.tudelft.pooralien.Controller.HighScore;

import javax.swing.table.AbstractTableModel;

public class Top10TableModel extends AbstractTableModel {

    String[] columnNames = {
            "No.",
            "Name",
            "Score"
    };

    ScoreManager scoreManager;

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
        if(scoreManager.getSCORE_COUNT() < 10) {
            return scoreManager.getSCORE_COUNT();
        }
        return 10;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(rowIndex < 0 || columnIndex < 0) {
            return null;
        }
        if(columnIndex > getColumnCount()) {
            return null;
        }
        // -1 Because index != line count
        if(rowIndex > getRowCount()-1) {
            return null;
        }

        for(int i = 0; i < getRowCount(); i++) {
            switch (columnIndex) {
                case(0):
                    //Index starts at 0, score No. does not.
                    return rowIndex+1;
                case(1):
                    return scoreManager.getTopTenScores().get(rowIndex).getName();
                case(2):
                    return scoreManager.getTopTenScores().get(rowIndex).getScore();
            }
        }
        return null;
    }
}
