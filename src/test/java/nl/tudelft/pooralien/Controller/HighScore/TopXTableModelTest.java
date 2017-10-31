package nl.tudelft.pooralien.Controller.HighScore;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class TopXTableModelTest {

    private TopXTableModel topXTableModel;

    @Before
    public void setup() {
        topXTableModel = new TopXTableModel();
    }

    @Test
    public void isCellEditableExpectFalse() {
        int row = topXTableModel.getRowCount();
        int column = topXTableModel.getColumnCount();

        for (int x = 0; x < row; x++) {
            for (int y = 0; y < column; y++) {
                assertFalse("Expected to be false. Row:" +
                        row + "Column: " + column + "." ,
                        topXTableModel.isCellEditable(row, column));
            }
        }
    }

    @Test
    public void getColumnName() {
        String[] columnNames = topXTableModel.getColumnNames();

        for (int i = 0; i < columnNames.length; i++) {
            assertEquals("Expected to be: " + columnNames[i] +
                    ", was: " + topXTableModel.getColumnName(i),
                    columnNames[i], topXTableModel.getColumnName(i));
        }
    }

    @Test
    public void getColumnNameExpectedToBeNullColumnTooLarge() {
        // Added one to be longer than can be possible.
        int columnNamesLength = topXTableModel.getColumnNames().length + 1;

        assertNull("Expected to be null.", topXTableModel.getColumnName(columnNamesLength));
    }

    @Test
    public void getColumnNameExpectedToBeNullColumnTooSmall() {
        int columnNamesLength = -1;

        assertNull("Expected to be null.", topXTableModel.getColumnName(columnNamesLength));
    }

    @Test
    public void getValueAtExpectedToBeNullTooSmall() {
        assertNull("Expected to be null.",
                topXTableModel.getValueAt(-1, -1));
    }

    @Test
    public void getValueAtExpectedToBeNullRowTooBig() {
        int rowIndexBig = topXTableModel.getRowCount();
        //Index is smaller than count.
        int columnIndex = topXTableModel.getColumnCount() -1 ;

        assertNull("Expected to be null.",
                topXTableModel.getValueAt(rowIndexBig, columnIndex));
    }

    @Test
    public void getValueAtExpectedToBeNullColumnTooBig() {
        //Index is smaller than count.
        int rowIndex = topXTableModel.getRowCount() -1 ;

        int columnIndexBig = topXTableModel.getColumnCount() + 1;

        assertNull("Expected to be null.",
                topXTableModel.getValueAt(rowIndex, columnIndexBig));
    }

    @Test
    public void getValueAtColumnIndexZero() {
        //Index is smaller than count.
        int rowIndex = topXTableModel.getRowCount() - 1;

        if (rowIndex < 0) rowIndex = 0;

        int columnIndex = 0;

        assertEquals("Expected to be one bigger than rowIndex",
                rowIndex + 1,
                topXTableModel.getValueAt(rowIndex, columnIndex));
    }

    @Test
    public void getValueAtColumnIndexOneExpectedString() {
        //Index is smaller than count.
        int rowIndex = topXTableModel.getRowCount() - 1;

        if (rowIndex < 0) rowIndex = 0;

        int columnIndex = 1;

        assertTrue("Expected to be a String",
                topXTableModel.getValueAt(rowIndex, columnIndex) instanceof String);
    }

    @Test
    public void getValueAtColumnIndexTwoExpectedInteger() {
        //Index is smaller than count.
        int rowIndex = topXTableModel.getRowCount() - 1;

        if (rowIndex < 0) rowIndex = 0;

        int columnIndex = 2;

        assertTrue("Expected to be an Integer",
                topXTableModel.getValueAt(rowIndex, columnIndex) instanceof Integer);
    }

}
