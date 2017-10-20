package nl.tudelft.pooralien.ui.HighScoreTable;

import nl.tudelft.pooralien.Controller.HighScore.Score;
import nl.tudelft.pooralien.Controller.HighScore.ScoreManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class HighScoreTable extends  JPanel{

    public HighScoreTable() {
        super(new GridLayout(1,0));

        ScoreManager scoreManager = new ScoreManager();

        String[] columnNames = {
                "No.",
                "Name",
                "Score"
        };
        Object[][] data = scoreArrayListToData(scoreManager.getTopTenScores());

        final JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500, 400));
        table.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);

    }

    public Object[][] scoreArrayListToData(ArrayList<Score> scoreArrayList) {

        Object[][] data = new Object[scoreArrayList.size()][3];

        for(int i = 0; i < scoreArrayList.size(); i++) {
            data[i][0] = i;
            data[i][1] = scoreArrayList.get(i).getName();
            data[i][2] = scoreArrayList.get(i).getScore();
        }

        return data;
    }



}
