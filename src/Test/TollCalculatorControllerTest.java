package Test;

import Controller.TollCalculatorController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TollCalculatorControllerTest {
    TollCalculatorController tollCalculatorController;

    @Test
    public void testMain() {

        final double TOLL_RATE = 0.05;

        assertEquals(197.2, tollCalculatorController.calculateTollCost(TOLL_RATE, 3943.5), 0.1);
    }

    @Test
    public void testCalculateDistance() {
        double lat1 = 43.484361; //Neyagawa Blvd. latitude
        double lng1 = -79.766037; //Neyagawa Blvd. longitude
        double lat2 = 43.508828; //Trafalgar Road latitude
        double lng2 = -79.747886; //Trafalgar Road longitude

        double expectedDistance = 3.0895471948509643; //Distance in km between Neyagawa Blvd. and Trafalgar Road
        double delta = 0.1; //Max difference between expected and actual distance

        double actualDistance = tollCalculatorController.calculateDistance(lat1, lng1, lat2, lng2);

        assertEquals(expectedDistance, actualDistance, delta);
    }

    @Test
    void testCalculateTollCost() {
        double cost = 0.25;
        double distance = 100;
        double expectedCost = 25;

        double actualCost = TollCalculatorController.calculateTollCost(cost, distance);

        Assertions.assertEquals(expectedCost, actualCost);
    }
}