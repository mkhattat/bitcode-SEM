package nl.tudelft.pooralien.ui.HighScoreTable;

import nl.tudelft.pooralien.Controller.HighScore.TopXTableModel;

import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Component;

/**
 * High Score Table based on the Top10 Table Model.
 */
public class HighScoreTableTopX extends JPanel {

    private final JTable table;

    /**
     * Initializing the top 10 high score table.
     */
    public HighScoreTableTopX() {
        super(new GridLayout(1, 0));
        final int tableViewportWidth = 500;
        final int tableViewportHeight = 160;
        final int columnIndexWidth = 30;

        table = new JTable(new TopXTableModel());
        table.setShowGrid(false);
        // Disable column header repositioning.
        table.getTableHeader().setReorderingAllowed(false);
        //Disable the row selection background, grid & focus border
        table.setRowSelectionAllowed(false);
        table.setShowGrid(false);
        table.setDefaultRenderer(Object.class, new HighScoreTableRenderer());
        //Resize columns
        table.getColumnModel().getColumn(0).setMaxWidth(columnIndexWidth);
        //Disable column resizing
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setResizable(false);

        }
        table.setPreferredScrollableViewportSize(new Dimension(tableViewportWidth,
                tableViewportHeight));
        table.setFillsViewportHeight(true);
    }

    /**
     * @return JTable being used.
     */
    public JTable getTable() {
        return table;
    }

    /**
     * HighScoreTableRenderer disables cell border on-selection.
     */
    static class HighScoreTableRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table,
                                                       Object value,
                                                       boolean isSelected,
                                                       boolean hasFocus,
                                                       int row,
                                                       int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            setBorder(noFocusBorder);
            return this;
        }
    }


}
