/*
https://www.bls.gov/productivity/tables/
labor-productivity-major-sectors.xlsx downloaded from website
Data released September 5, 2024

Job types being measured: Non-farm business sector, business sector, Non-financial corporate sector,
Manufacturing sector, Durable manufacturing sector, and Non-durable manufacturing sector

I will be graphing data on the measurement: Employment in millions from the spreadsheet.
Planned three filters: Manufacturing jobs, business jobs, corporate jobs
 */

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;




public class InputStream {
    String sector;
    String yearMillions;

    public InputStream() {

    }

    //public void Streamer(String file) {
    public static void main(String[] args) {
        String file1 = "data2.csv";   //file object
        String line;
        String delimiter = ","; //for acknowledging the separation between columns

        /*try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file1))) {
            while ((line = bufferedReader.readLine()) != null) { //repeats while there are still lines to read
                String[] columns = line.split(delimiter); // The whole line is segmented based on comma locations. Allows each cell to be separately tracked.
                String sector = columns[0]; // different job types I'll be graphing
                String digit = columns[3]; // the more the digit, the more precise the job is within the certain industry. For example - Mining: 2-digit, Support Activities for mining, 3-digit
                String measure = columns[5]; //"Employment" within csv file
                String units = columns[6]; // for "Thousands of jobs"
                String yearMillions = columns[43]; //tracks the column that holds millions of employees, for year 2023.
                if ("Employment".equals(measure) && "Thousands of jobs".equals(units) && "2-Digit".equals(digit)) {
                    System.out.println("Sector: " + sector);
                    System.out.println("National Employees (in thousands): " + yearMillions);
                }
            }

        } catch (IOException e) { // used in case the program cannot find the file; results in a thrown error
            e.printStackTrace();
            throw new RuntimeException(e);
        }*/

        file1 = "data.csv";

        ArrayList<DataAggregate> sectorInformationAggregate = new ArrayList<>(); //for storing data to put on TablePanel
        ArrayList<Double> employmentTotal = new ArrayList<>();
        ArrayList<Double> averageWeeklyHours = new ArrayList<>();
        ArrayList<Double> employmentPercentChange = new ArrayList<>();
        ArrayList<Double> averageDollarsPerHour = new ArrayList<>();
        ArrayList<String> sectors = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file1))) {
            while ((line = bufferedReader.readLine()) != null) { //repeats while there are still lines to read
                String[] columns = line.split(delimiter); // The whole line is segmented based on comma locations. Allows each cell to be separately tracked.
                String sector = columns[0]; // different job types I'll be graphing
                String basis = columns[1]; // basis of the data: can be either employees or all workers
                String measure = columns[2]; //String: "Employment" within csv file
                String units = columns[3]; // column holding string: "Millions of jobs"
                String yearMillions = columns[80]; //tracks the column that counts the millions of employees, for year 2023.
                if ("Employment".equals(measure) && "Millions of jobs".equals(units)) {
                    System.out.println("Sector: " + sector);
                    System.out.println("National Employees (in millions): " + yearMillions);
                    double employeesInMillions = Double.parseDouble(yearMillions);
                    DataAggregate data = new DataAggregate(sector, employeesInMillions, basis); // new data aggregate object
                    sectorInformationAggregate.add(data); //add the different data points to an array list for processing
                    sectors.add(columns[0]);
                    employmentTotal.add(Double.parseDouble(columns[80]));
                }
                if("Employment".equals(measure)&&"% Change from previous year".equals(units)) { // when column says Employment and other column says % change, add to array list
                    employmentPercentChange.add(Double.parseDouble(columns[80])); // 80th column statistic will represent 2023 value
                }
                if("Average weekly hours".equals(measure)&&"Hours worked per job per week".equals(units)) {
                    averageWeeklyHours.add(Double.parseDouble(columns[80]));
                }
                if("Hourly compensation".equals(measure)&&"Current dollars per hour worked".equals(units)) {
                    averageDollarsPerHour.add(Double.parseDouble(columns[80]));
                }
            }

        } catch (IOException e) { // used in case the program cannot find the file; results in a thrown error
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        //new table panel
        //new stats panel

        JFrame frame = new JFrame(); // First JFrame featuring TablePanel + filters and sorts
        frame.setTitle("Sector Employment Information");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setSize(600, 400);

        TablePanel tablePanel = new TablePanel(sectorInformationAggregate);
        frame.getContentPane().add(tablePanel, BorderLayout.CENTER);
        frame.setVisible(true);

        JFrame frame2 = new JFrame();
        frame2.setTitle("Charting and Calculated Stats");
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.getContentPane().setLayout(new BorderLayout());

        ChartTable chartPanel = new ChartTable(sectorInformationAggregate);
        chartPanel.setVisible(true);





    }
}
