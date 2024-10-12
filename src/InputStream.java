/*
https://www.bls.gov/productivity/tables/
labor-productivity-major-sectors.xlsx downloaded from website
Data released September 5, 2024

Job types being measured: Non-farm business sector, business sector, Non-financial corporate sector,
Manufacturing sector, Durable manufacturing sector, and Non-durable manufacturing sector

I will be graphing data on the measurement: Employment in millions from the spreadsheet.
Planned three filters: Manufacturing jobs, business jobs, corporate jobs
 */

import java.io.*;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;



public class InputStream {

    public InputStream(){

    }

    //public void Streamer(String file) {
    public static void main(String[] args) {
        String file1 = "data.csv";   //file object
        String line;
        String delimiter = ","; //for acknowledging the separation between columns

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file1))) {
            while ((line = bufferedReader.readLine()) != null) { //repeats while there are still lines to read
                String[] columns = line.split(delimiter); // The whole line is segmented based on comma locations. Allows each cell to be separately tracked.
                String sector = columns[0]; // different job types I'll be graphing
                String measure = columns[2]; //"Employment" within csv file
                String units = columns[3]; // for "Millions of jobs"
                String yearMillions = columns[80]; //tracks the column that holds millions of employees, for year 2023.
                if ("Employment".equals(measure) && "Millions of jobs".equals(units)) {
                    System.out.println("Sector: "+sector);
                    System.out.println("National Employees (in millions): "+yearMillions);
                }
            }

        } catch (IOException e) { // used in case the program cannot find the file; results in a thrown error
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }
}
