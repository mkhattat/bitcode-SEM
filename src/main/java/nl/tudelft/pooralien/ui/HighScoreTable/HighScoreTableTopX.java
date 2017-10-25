package nl.tudelft.pooralien.ui.HighScoreTable;

import nl.tudelft.pooralien.Controller.HighScore.TopXTableModel;

import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.Dimension;

/**
 * High Score Table based on the Top10 Table Model.
 */
public class HighScoreTableTopX extends JPanel {

    private final int tableViewportWidth = 500;
    private final int tableViewportHeight = 160;
    private final int columnIndexWidth = 30;

    private JTable table;

    /**
     * Initializing the top 10 high score table.
     */
    public HighScoreTableTopX() {
        super(new GridLayout(1, 0));

        table = new JTable(new TopXTableModel());
        table.setCellSelectionEnabled(false);
        // Disable column header repositioning.
        table.getTableHeader().setReorderingAllowed(false);

        //Resize columns
        table.getColumnModel().getColumn(0).setMaxWidth(columnIndexWidth);
        //Disable column resizing
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setResizable(false);

        }

        table.setPreferredScrollableViewportSize(new Dimension(tableViewportWidth,
                tableViewportHeight));
        table.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
    }

    /**
     * @return JTable being used.
     */
    public JTable getTable() {
        return table;
    }





}
