package Bean;

import java.util.HashMap;
import java.util.Map;

public class LocationData {
    private Map<String, Location> locations = new HashMap<>();

    public Map<String, Location> getLocations() {
        return locations;
    }

    public void setLocations(Map<String, Location> locations) {
        this.locations = locations;
    }
}
