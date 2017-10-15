package nl.tudelft.pooralien.ui.HighScoreTable;

import nl.tudelft.pooralien.Controller.HighScore.Score;
import nl.tudelft.pooralien.Controller.HighScore.ScoreManager;
import nl.tudelft.pooralien.Controller.HighScore.Top10TableModel;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.ArrayList;

public class HighScoreTable extends  JPanel{

    public HighScoreTable() {
        super(new GridLayout(1,0));

        final JTable table = new JTable(new Top10TableModel());
        table.setCellSelectionEnabled(false);
        // Disable column header repositioning.
        table.getTableHeader().setReorderingAllowed(false);

        //Resize columns
        table.getColumnModel().getColumn(0).setMaxWidth(30);
        //Disable column resizing
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setResizable(false);

        }

        table.setPreferredScrollableViewportSize(new Dimension(500, 400));
        table.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
    }





}
