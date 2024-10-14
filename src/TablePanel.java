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
    List<DataAggregate> dataPoints; // for storing
    List<DataAggregate> filteredDataPoints;


    public TablePanel(ArrayList<DataAggregate> sectorInformationAggregate) {
        this.dataPoints = sectorInformationAggregate; // initialization. Pulling arraylist containing information and saving it within current object.
        this.filteredDataPoints = new ArrayList<>(dataPoints); // new ArrayList object for later filtering

        setLayout(new BorderLayout());
        String[] columns = {"Sector", "Employee count (in millions)", "Basis"};
        model = new DefaultTableModel(new Object[]{"Sector", "Employee Count (in millions)", "Basis"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { //overridden method keeps cells in table, containing info, from being edited
                return false;
            }
        };
        table = new JTable(model);
        table.getColumnModel().getColumn(0).setHeaderValue("Sector");           //these are not working. Test later to get column titles
        table.getColumnModel().getColumn(1).setHeaderValue("Employee Count");
        table.getColumnModel().getColumn(2).setHeaderValue("Basis");

        //table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        model.setRowCount(0); // clears the table
        for (DataAggregate d : dataPoints) { // sets points from the new data points list, populated from the Data Aggregate array list
            model.addRow(new Object[]{d.getSector(), d.getYearMillions(), d.getBasis()});
        }

        dropDown = new JComboBox<>(new String[]{"Sort by Name", "Sort by Millions of Jobs"});

        manufacturingCheckBox = new JCheckBox("MANUFACTURING");
        businessCheckBox = new JCheckBox("BUSINESS");
        corporateCheckBox = new JCheckBox("CORPORATE");

        JPanel filter = new JPanel(new FlowLayout(FlowLayout.CENTER));
        filter.add(manufacturingCheckBox);
        filter.add(businessCheckBox);
        filter.add(corporateCheckBox);

        manufacturingCheckBox.addActionListener(new FilterDataListener() {
        });
        businessCheckBox.addActionListener(new FilterDataListener() {
        });
        corporateCheckBox.addActionListener(new FilterDataListener() {
        });

        add(table, BorderLayout.CENTER);
        add(filter, BorderLayout.SOUTH);


    }

    private class FilterDataListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            filteredDataPoints.clear();
            for (DataAggregate d : dataPoints) {
                boolean found = false;
                if (manufacturingCheckBox.isSelected() && d.getSector().toUpperCase().contains("MANUFACTURING")) {
                    found = true;
                }
                if (businessCheckBox.isSelected() && d.getSector().toUpperCase().contains("BUSINESS")) {
                    found = true;
                }
                if (corporateCheckBox.isSelected() && d.getSector().toUpperCase().contains("CORPORATE")) {
                    found = true;
                }
                if (!corporateCheckBox.isSelected() && !manufacturingCheckBox.isSelected() && !businessCheckBox.isSelected()) {
                    found = true;
                }
                if (found) {
                    filteredDataPoints.add(d);
                }

            }

            model.setRowCount(0);
            for (DataAggregate d : filteredDataPoints) {
                model.addRow(new Object[]{d.getSector(), d.getYearMillions(), d.getBasis()});


            }
        }
    }
}
