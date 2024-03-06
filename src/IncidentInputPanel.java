import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class IncidentInputPanel extends JFrame implements ActionListener {
    private JTextField timeField, descriptionField, dateField;
    private JComboBox<String> locationField, severityComboBox;
    private JButton submitButton;
    private ArrayList<Incident> incidents = Incident.getIncidents();
    private int x = 0, y = 0;
    public IncidentInputPanel(ArrayList<Incident> incidents, int x, int y) {
    	this.x = x; this.y = y;
        this.incidents = incidents;
        setLayout(new GridLayout(6, 2)); // Simple grid layout

        // Initialize components
        if(Map.getCurrentFloor()==1) {
        	locationField = new JComboBox<>(new String[]{"North Parking Lot", "South Parking Lot", "Exit11-12 Hallway", "C-Hall", "English Hallway", "Swimming Pool / Health Room Hall", "Glass Hallway", "Music Hall", "S Hallway", "Gym 1", "Boy's Single Gym", "Girl's Single Gym", "Front Foyer"});
        } else {
        	locationField = new JComboBox<>(new String[]{"Math Hall", "Science Hall", "Third Floor"});
        }
        timeField = new JTextField();
        descriptionField = new JTextField();
        dateField = new JTextField();
        severityComboBox = new JComboBox<>(new String[]{"Low", "Medium", "High"});
        submitButton = new JButton("Submit");

        // Add components to panel
        add(new JLabel("Location:"));
        add(locationField);
        add(new JLabel("Time (HH:MM):"));
        add(timeField);
        add(new JLabel("Date (MM/DD):"));
        add(dateField);
        add(new JLabel("Description:"));
        add(descriptionField);
        add(new JLabel("Severity:"));
        add(severityComboBox);
        add(submitButton);
        setSize(300, 300);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        

        
        submitButton.addActionListener(this);
    }

    
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == submitButton) {
			String location = (String) locationField.getSelectedItem();
			System.out.println("location: " + location);
			String time = timeField.getText();
	        String date = dateField.getText();
	        String description = descriptionField.getText();
	        String severity = (String) severityComboBox.getSelectedItem();
	        if(time.equals("") || date.equals("") || description.equals("")) {
	        	JOptionPane.showMessageDialog(null, "Please fill in the information!!");
	        } else if (!validTime(time)){
	        	JOptionPane.showMessageDialog(null, "Invalid time inputted.");
	        } else if (!validDate(date)) {
	        	JOptionPane.showMessageDialog(null, "Invalid date inputted.");
	        } else {
	        	Incident incident = new Incident(location, time, date, description, severity, Map.getCurrentFloor(), x, y);
		        incidents.add(incident);
		        LocationCounter.incrementLocationCount(location);
		        try {
					Incident.saveData();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

		        JOptionPane.showMessageDialog(this, "Incident added successfully!");
		        
		        // clear the fields after submission
		        locationField.setSelectedIndex(0);
		        timeField.setText("");
		        descriptionField.setText("");
		        severityComboBox.setSelectedIndex(0); // Reset to first option
		        dispose();
	        }
	        
		}
	}


	private boolean validTime(String time) {
		if(time.length() != 5 || !time.contains(":"))return false;
		int hours = Integer.parseInt(time.split(":")[0]);
		int minutes = Integer.parseInt(time.split(":")[1]);
		if(hours >= 0 && hours <= 24 && minutes >= 0 && minutes <= 59)return true; 
		return false;
	}
	
	private boolean validDate(String date) {
		if(date.length() != 5 || !date.contains("/"))return false;
		int month = Integer.parseInt(date.split("/")[0]);
		int day = Integer.parseInt(date.split("/")[1]);
		if(month >= 1 && month <= 12 && day >= 1 && day <= 31)return true; 
		return false;
	}
}