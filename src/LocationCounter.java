import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LocationCounter {
	private static HashMap<String, Integer> locationCounts = new HashMap<String, Integer>() {{
        put("North Parking Lot", 0);
        put("South Parking Lot", 0);
        put("Exit11-12 Hallway", 0);
        put("C-Hall", 0);
        put("English Hallway", 0);
        put("Swimming Pool / Health Room Hall", 0);
        put("Glass Hallway", 0);
        put("Music Hall", 0);
        put("S Hall", 0);
        put("Gym 1", 0);
        put("Boy's Single Gym", 0);
        put("Girl's Single Gym", 0);
        put("Front Foyer", 0);
        put("Math Hall", 0);
        put("Science Hall", 0);
        put("Third Floor", 0);
    }};
    private static boolean dataLoaded = false;
    public LocationCounter() {
    	
    }

    public static void incrementLocationCount(String location) {
        if (locationCounts.containsKey(location)) {
            locationCounts.put(location, locationCounts.get(location)+1);
        } else {
            System.out.println("Location not found.");
        }
    }
    
    public static void loadCount() {
    	if(!dataLoaded) {
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
            		System.out.println(location);
            		int val = locationCounts.get(location);
            		locationCounts.put(location, val+1);
            	}
            }
            try {
    			in.close();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    	dataLoaded = true;
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

