package model;

/**
 * Represents a luxury suite room.
 */
public class SuiteRoom extends Room {
    public SuiteRoom() {
        super("SuiteRoom", 2, 700, 300.00);
    }

    @Override
    public void displayRoomDetails() {
        System.out.println(roomType + " (Luxury) - Beds: " + beds + ", Size: " + size + " sqft, Price: $" + price + "/night");
    }
}
