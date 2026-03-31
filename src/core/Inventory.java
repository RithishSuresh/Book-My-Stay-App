package core;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages the hotel's room inventory using centralized state.
 * Demonstrates the use of HashMap for O(1) lookup and clean separation of concerns.
 */
public class Inventory implements Serializable {
    private Map<String, Integer> roomAvailability;

    public Inventory() {
        this.roomAvailability = new HashMap<>();
    }

    /**
     * Registers a given room type into the system.
     */
    public synchronized void registerRoomType(String roomType, int totalRooms) {
        roomAvailability.put(roomType, totalRooms);
    }

    /**
     * Gets the availability of a requested room type.
     */
    public synchronized int getAvailability(String roomType) {
        return roomAvailability.getOrDefault(roomType, 0);
    }

    /**
     * Adjusts the availability by adding (or subtracting) the specified amount.
     */
    public synchronized void updateAvailability(String roomType, int amountChange) {
        if (roomAvailability.containsKey(roomType)) {
            int currentAvailability = roomAvailability.get(roomType);
            roomAvailability.put(roomType, currentAvailability + amountChange);
        } else {
            System.out.println("Warning: " + roomType + " is not registered in inventory.");
        }
    }

    /**
     * Prints the entire inventory state to the console.
     */
    public synchronized void displayInventory() {
        for (String roomType : roomAvailability.keySet()) {
            System.out.println("- " + roomType + ": " + roomAvailability.get(roomType) + " available");
        }
    }
}
