import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Map;

public class Statistics extends JFrame implements ActionListener {
    private JButton backButton;
    private JTable statsTable;
    private DefaultTableModel tableModel;
    private boolean init = false;
    public Statistics() {
    	super("Statistics");
    	if(!init) {
    		LocationCounter.loadCount();
    		Incident.loadData();    
    	}
    	setSize(1920, 1080);
        initTable();
        initBackButton();
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }
    private void initTable() {
        // Define the column names for the table
        String[] columnNames = {"Statistic", "Value"};
        
        // Initialize the table model and set the column identifiers
        tableModel = new DefaultTableModel(columnNames, 0);

        // Create the table with the model
        statsTable = new JTable(tableModel);

        // Add data
        tableModel.addRow(new Object[]{"Number of Incidents", Incident.getNumIncidents()});
        tableModel.addRow(new Object[]{"Most frequent location", LocationCounter.findMostFrequentLocation()});
        tableModel.addRow(new Object[]{"Incidents with low severity", Incident.getLoCount()});
        tableModel.addRow(new Object[]{"Incidents with medium severity", Incident.getMedCount()});
        tableModel.addRow(new Object[]{"Incidents with high severity", Incident.getHiCount()});
        tableModel.addRow(new Object[]{"Most recent incident: ", Incident.getMostRecentIncident()});
        for (Map.Entry<String,Integer> entry : LocationCounter.getLocationCounts().entrySet()) {
			
			tableModel.addRow(new Object[] {"Incidents in " + entry.getKey(), entry.getValue()});
			
		}
        // Wrap the table in a scroll pane
        JScrollPane scrollPane = new JScrollPane(statsTable);
        
        // Add the scroll pane to the frame
        add(scrollPane);
    }

    // Additional methods to manipulate the table data can be added here
    private void initBackButton() {
        backButton = new JButton("Back");
        backButton.setBounds(10, getHeight() - 70, 100, 30); // Positioning at the bottom left
        backButton.addActionListener(this);
        add(backButton, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            // Action to perform when 'Back' button is clicked
            // For example: close this window or navigate back to the previous screen
            try {
            	dispose();
				new MainMenu();
			} catch (FontFormatException | IOException | LineUnavailableException | UnsupportedAudioFileException e1) {
				e1.printStackTrace();
			}
        }
    }
}