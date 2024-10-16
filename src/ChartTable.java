import org.jfree.chart.*;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.*;
import org.jfree.chart.swing.ChartPanel;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;

public class ChartTable extends JFrame {
    JFrame chart;
    public ChartTable(ArrayList<DataAggregate> sectorInformationAggregate/*, ArrayList<String> sectors, ArrayList<Double> averageWeeklyHours, ArrayList<Double> employmentPercentChange
            ,ArrayList<Double> averageDollarsPerHour*/) {
        setTitle("Employee Count (in millions) by Sector");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (DataAggregate dataAggregate : sectorInformationAggregate) {
            String sector = dataAggregate.getSector();
            Double employeesInMillions = dataAggregate.getYearMillions();
            dataset.addValue(employeesInMillions,sector, "Employment"); // employees in millions is y axis, sectors are x axis
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Employee Count by Sector", // title of chart
                "Sector", // x axis
                "Millions of Jobs", // y axis
                dataset, // created dataset using JFree
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        setLayout(new BorderLayout());
        add(chartPanel, BorderLayout.NORTH);

        StatsPanel statsPanel = new StatsPanel(sectorInformationAggregate/*, sectors, averageWeeklyHours, employmentPercentChange, averageDollarsPerHour*/);
        add(statsPanel, BorderLayout.CENTER);

        setSize(800, 800);
        setVisible(true);





    }
}
