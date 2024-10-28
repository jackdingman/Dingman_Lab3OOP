import org.jfree.chart.*;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.*;
import org.jfree.chart.swing.ChartPanel;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;

public class ChartTable extends JFrame {
    JFrame frame;
    ChartPanel chartPanel;


    public ChartTable(ArrayList<DataAggregate> sectorInformationAggregate) {
        frame = new JFrame(); //initializes a new JFrame obhect
        frame.setSize(800, 600); //sets size
        frame.setLocationRelativeTo(null);//centers frame
        frame.setTitle("Employee Count (in millions) by Sector"); // title for frame
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        updateChart(sectorInformationAggregate); //method for updating chart data, passing sectorInformationAggregate containing Excel data.
    }
    public void updateChart(ArrayList<DataAggregate> sectorInformationAggregate) {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (DataAggregate dataAggregate : sectorInformationAggregate) {
            String sector = dataAggregate.getSector(); //retrieve sector
            Double employeesInMillions = dataAggregate.getYearMillions(); //retrieve millions of employees
            dataset.addValue(employeesInMillions, sector, "Employment"); // employees in millions is y axis, sectors are x axis
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Employee Count by Sector", // title of chart
                "Sector", // x axis
                "Millions of Jobs", // y axis
                dataset, // created dataset using JFree
                PlotOrientation.VERTICAL, //chart display structure
                true, //legend
                true, //tooltips
                false
        );

        if (chartPanel == null){ // checks if the chartPanel has been initialized
            chartPanel = new ChartPanel(barChart); //initialize
            chartPanel.setPreferredSize(new Dimension(800, 600));
            frame.getContentPane().add(chartPanel, BorderLayout.NORTH); //add chartPanel to frame
        } else {
            chartPanel.setChart(barChart);
        }


        StatsPanel statsPanel = new StatsPanel(sectorInformationAggregate/*, sectors, averageWeeklyHours, employmentPercentChange, averageDollarsPerHour*/);
        frame.getContentPane().add(statsPanel, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
    }
}
