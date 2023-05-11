package Controller;

import java.io.IOException;

import Bean.Location;
import Bean.LocationData;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Scanner;

public class TollCalculatorController {
    private static final double TOLL_RATE = 0.25;

    public static void main(String[] args) throws IOException {
        //Create object mapper
        ObjectMapper mapper = new ObjectMapper();

        //map json data to the bean class
        LocationData locations = mapper.readValue(new File("yourdirectorypathforjsonfile\\scratch.json"), LocationData.class);

        // fetch location from json bean
        Location startLocation = null, endLocation = null;

        //take input from user
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter starting location name: ");
        String startLocationName = scanner.nextLine();
        System.out.print("Enter ending location name: ");
        String endLocationName = scanner.nextLine();

        //fetch location details based on input
        for (Location location : locations.getLocations().values()) {
            if (location.getName().equals(startLocationName)) {
                startLocation = location;
            }
            if (location.getName().equals(endLocationName)) {
                endLocation = location;
            }
        }

        //check if location not empty
        if (startLocation == null || endLocation == null) {
            System.out.println("Invalid location names.");
            return;
        }

        double distance = calculateDistance(startLocation.getLat(), startLocation.getLng(), endLocation.getLat(), endLocation.getLng());
        double tollCost = calculateTollCost(TOLL_RATE, distance);

        // Return the results
        System.out.println("Distance between " + startLocationName + " and " + endLocationName + " is " + distance + " km.");
        System.out.println("Toll cost is $" + tollCost);
    }

    public static double calculateDistance(double lat1, double lng1, double lat2, double lng2) {
        // Convert latitude and longitude coordinates to radians
        double lat1Rad = Math.toRadians(lat1);
        double lng1Rad = Math.toRadians(lng1);
        double lat2Rad = Math.toRadians(lat2);
        double lng2Rad = Math.toRadians(lng2);

        // Calculate the distance using the Haversine formula
        //used google reference to find distance
        double dLat = lat2Rad - lat1Rad;
        double dLng = lng2Rad - lng1Rad;
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(lat1Rad) * Math.cos(lat2Rad) * Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = 6371 * c;

        return distance;
    }

    // Calculating the toll cost
    public static double calculateTollCost(double cost, double distance) {
        return cost * distance;
    }
}
