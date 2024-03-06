import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LocationCounter {
    private static HashMap<String, Integer> locationCounts = new HashMap<>();

    public LocationCounter() {
        locationCounts = new HashMap<>();
        locationCounts.put("North Parking Lot", 0);
        locationCounts.put("South Parking Lot", 0);
        locationCounts.put("Exit11-12 Hallway", 0);
        locationCounts.put("C-Hall", 0);
        locationCounts.put("English Hallway", 0);
        locationCounts.put("Swimming Pool / Health Room Hall", 0);
        locationCounts.put("Glass Hallway", 0);
        locationCounts.put("Music Hall", 0);
        locationCounts.put("S Hall", 0);
        locationCounts.put("Gym 1", 0);
        locationCounts.put("Boy's Single Gym", 0);
        locationCounts.put("Girl's Single Gym", 0);
        locationCounts.put("Front Foyer", 0);
        locationCounts.put("Math Hall", 0);
        locationCounts.put("Science Hall", 0);
        locationCounts.put("Third Floor", 0);
    }

    public static void incrementLocationCount(String location) {
        if (locationCounts.containsKey(location)) {
            locationCounts.merge(location, 1, Integer::sum);
        } else {
            System.out.println("Location not found.");
        }
    }
    
    public static void loadCount() {
    	
            BufferedReader in = null;
    		try {
    			in = new BufferedReader(new FileReader("incidents.txt"));
    		} catch (FileNotFoundException e) {
    			e.printStackTrace();
    		}
            String location = "";
            
            int n = 0;
    		try {
    			n = Integer.parseInt(in.readLine());
    		} catch (NumberFormatException | IOException e) {
    			e.printStackTrace();
    		}
            if(n != 0) {
            	for(int i = 0; i < n; i++) {
            		try {
    					location = in.readLine();
    					in.readLine();
    	        		in.readLine();
    	        		in.readLine();
    	        		in.readLine();
    	        		in.readLine();
    	        		in.readLine();
    	        		in.readLine();
    				} catch (IOException e) {
    					
    					e.printStackTrace();
    				}
            		locationCounts.merge(location, 1, Integer::sum);
            	}
            }
            try {
    			in.close();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    }

	public static String findMostFrequentLocation() {
		try {
			Incident.saveData();
			loadCount();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		int greatest = 0; String loc = "none";
		for (Map.Entry<String,Integer> entry : locationCounts.entrySet()) {
			if(entry.getValue() > greatest) {
				greatest = entry.getValue();
				loc = entry.getKey();
			}
		}
		return loc;
	}
	
	public static HashMap<String, Integer> getLocationCounts(){
		return locationCounts;
	}
    
}

