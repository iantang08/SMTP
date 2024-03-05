import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

public class Incident {
	private static int loCount = 0, medCount = 0, hiCount = 0;
    private String location;
    private String time;
    private String date;
    private String description;
    private String severity;
    private int floor;
    private int x;
    private int y;
    private static ArrayList<Incident> incidents = new ArrayList<Incident>();
    private static boolean initialized = false;
    public Incident(String location, String time, String date, String description, String severity, int floor, int x, int y) {
        this.location = location;
        this.time = time;
        this.date = date;
        this.description = description;
        this.severity = severity;
        this.floor = floor;
        this.x = x;
        this.y = y;
    }
    
	public static ArrayList<Incident> getIncidents() {
		return incidents;
	}
	public String getDate() {
		return date;
	}
	public static ArrayList<Incident> convertIncidentList(Incident[] in) {
		ArrayList<Incident> convertedList = new ArrayList<>();
		for(Incident i : in) {
			convertedList.add(i);
		}
		return convertedList;
	}
	
	public static Incident[] convertIncidentArray(ArrayList<Incident> in) {
		int n = in.size();
		Incident[] convertedArray = new Incident[n];
		for(int i = 0; i < n; i++) {
			convertedArray[i] = in.get(i);
		}
		return convertedArray;
	}
	
	public static ArrayList<Incident> sortByDate() {
		IncidentMergeSort sorter = new IncidentMergeSort();
		Incident[] incidentArray = convertIncidentArray(incidents);
        try {
			sorter.sortIncidents(incidentArray);
			incidents = convertIncidentList(incidentArray);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        return incidents;
		
	}

	public static void loadData(){
        BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader("incidents.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        String location = "";
        String time = "";
        String date = "";
        String description = "";
        String severity = "";
        int floor = 0, x = 0, y = 0;
        int n = 0;
		try {
			n = Integer.parseInt(in.readLine());
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
        if(n != 0 && !initialized) {
        	for(int i = 0; i < n; i++) {
        		try {
					location = in.readLine();
					time = in.readLine();
	        		date = in.readLine();
	        		description = in.readLine();
	        		severity = in.readLine();
	        		floor = Integer.parseInt(in.readLine());
	        		x = Integer.parseInt(in.readLine());
	        		y = Integer.parseInt(in.readLine());
				} catch (IOException e) {
					
					e.printStackTrace();
				}
        		
        		ImagePanel.addMarker(floor, x, y);
        		incidents.add(new Incident(location, time, date, description, severity, floor, x, y));
        	}
        	if(!initialized)loadSevCount();
        	initialized = true;
        }
        try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	public String toString() {
		return date + " " + time + " in the " + location + " " + description + " with a " + severity + " severity." ;
		
	}
	public static int getNumIncidents() {
		return incidents.size();
	}
	public static void saveData() throws IOException {
        incidents = sortByDate();
		BufferedWriter writer = new BufferedWriter(new FileWriter("incidents.txt", false));
        int numIncidents = incidents.size();
        writer.write(numIncidents + "\n");
        for (Incident incident : incidents) {
            writer.write(incident.location + "\n");
            writer.write(incident.time + "\n");
            writer.write(incident.date + "\n");
            writer.write(incident.description + "\n");
            writer.write(incident.severity + "\n");
            writer.write(incident.floor + "\n");
            writer.write(incident.x + "\n");
            writer.write(incident.y + "\n");
        }
        writer.close();
    }
	
	private static void loadSevCount() {
		loCount=0;medCount=0;hiCount=0;
		for(Incident i : incidents) {
			if(i.severity.equals("Low"))loCount++;
			else if(i.severity.equals("Medium"))medCount++;
			else
				hiCount++;
		}
	}
	
	public static int getLoCount() {
		loadSevCount();
		return loCount;
	}
	public static int getMedCount() {
		return medCount;
	}
	public static int getHiCount() {
		return hiCount;
	}

	public static String getMostRecentIncident() {
		try {
			saveData();
			loadData();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(incidents.size()!=0)return incidents.get(incidents.size()-1).toString();
		else
			return "no incidents";
	}

	
	
	

    
    
}