import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StatsPanel extends JPanel {

    /*ArrayList<DataAggregate> sectorInformationAggregate;
    ArrayList<String> sectors;
    ArrayList<Double> averageWeeklyHours;
    ArrayList<Double> employmentPercentChange;
    ArrayList<Double> averageDollarsPerHour;*/


    public StatsPanel(ArrayList<DataAggregate> sectorInformationAggregate/*, ArrayList<String> sectors, ArrayList<Double> averageWeeklyHours, ArrayList<Double> employmentPercentChange
            ,ArrayList<Double> averageDollarsPerHour*/) {

        /*this.sectorInformationAggregate = sectorInformationAggregate;
        this.sectors = sectors;
        this.averageWeeklyHours = averageWeeklyHours;
        this.employmentPercentChange = employmentPercentChange;
        this.averageDollarsPerHour = averageDollarsPerHour;*/

        setLayout(new GridLayout(6, 3)); // layout for panel - 4 columns include Sector, and three different calculated data points

        updateStats(sectorInformationAggregate/*, sectors, averageWeeklyHours, employmentPercentChange, averageDollarsPerHour*/);




    }
    public void updateStats(ArrayList<DataAggregate> sectorInformationAggregate/*, ArrayList<String> sectors, ArrayList<Double> averageWeeklyHours, ArrayList<Double> employmentPercentChange
            ,ArrayList<Double> averageDollarsPerHour*/){

        removeAll();

        /*Below will be the formulas for the different stats I am including.
          They are as follows:
          Previous year (2022) employment number = Employment # - Employment #(%change)
          Total Employee payout on one hour of work = Employment #(current $ per hour worked)
          Average $ per worker per week = Average weekly hours (current $ per hour worked)
         */
        //Header labels for data columns
        add(new JLabel ("Sector: "));
        add(new JLabel("Previous Year (2022) Employment Number (in millions): "));
        add(new JLabel("Total Sector Employee Payout on one hour of work (in millions): "));
        add(new JLabel("Average Dollars per worker per week: "));

        for(int i = 0; i < 4; i++) {
            DataAggregate data = sectorInformationAggregate.get(i);
            double weeklyHours = data.getAverageWeeklyHours();
            double employmentPercent = data.getEmploymentPercentChange();
            double averageDollars = data.getAverageDollarsPerHour();
            double employmentCount = data.getYearMillions();
            String sectorName = data.getSector();

            double previousEmploymentCount = employmentCount - (employmentCount * (employmentPercent / 100));
            double oneHourEmployeePayout = employmentCount * averageDollars;
            double averageDollarPerWorkerPerWeek = weeklyHours * averageDollars;

            add(new JButton(sectorName));
            add(new JLabel("" + previousEmploymentCount));
            add(new JLabel("" + oneHourEmployeePayout));
            add(new JLabel("" + averageDollarPerWorkerPerWeek));

            revalidate();
            repaint();

        }
    }


}
