/*
This panel will contain the Employment field, the millions of jobs count, and
the basis.

There will be three check marks including corporate, business and manufacturing
for filtering.

There will be the option to sort by millions of jobs, or by name
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class TablePanel extends JPanel {
    JTable table; // summarized data points with employment titles
    DefaultTableModel model;
    JComboBox<String> dropDown; // for sorting by name or millions of jobs
    JCheckBox manufacturingCheckBox; // for filtering data
    JCheckBox businessCheckBox;// for filtering by business industries
    JCheckBox corporateCheckBox; // for filtering my corporate industries
    List <DataAggregate> dataPoints; // for storing
    List <DataAggregate> filteredDataPoints;



    public TablePanel(ArrayList<DataAggregate> sectorInformationAggregate) {
        this.dataPoints = sectorInformationAggregate; // initialization. Pulling arraylist containing information and saving it within current object.
        this.filteredDataPoints = new ArrayList<>(dataPoints); // new ArrayList object for later filtering

        setLayout(new BorderLayout());
        String[] columns = {"Sector", "Employee count (in millions)", "Basis"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        model.setRowCount(0); // clears the table
        //model.setColumnCount(0);
        for(DataAggregate d : dataPoints) { // sets points from the new data points list, populated from the Data Aggregate array list
            model.addRow(new Object[]{d.getSector(), d.getYearMillions(), d.getBasis()});
        }
        JFrame frame = new JFrame();
        add(table, BorderLayout.CENTER);

        dropDown = new JComboBox<>(new String[]{"Sort by Name", "Sort by Millions of Jobs"});
        manufacturingCheckBox = new JCheckBox("Manufacturing");
        businessCheckBox = new JCheckBox("Business");
        corporateCheckBox = new JCheckBox("Corporate");

        JPanel filter = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filter.add(manufacturingCheckBox);
        filter.add(businessCheckBox);
        filter.add(corporateCheckBox);







    }
}
