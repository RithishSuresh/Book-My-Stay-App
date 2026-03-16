package com.bookmystay.inventory;

import java.util.HashMap;

/**
 * Manages the hotel's room inventory using centralized state.
 * Demonstrates the use of HashMap for O(1) lookup and clean separation of concerns.
 * 
 * @author BookMyStay Developer
 * @version 1.0
 */
public class RoomInventory {
    private HashMap<String, Integer> roomAvailability;

    public RoomInventory() {
        this.roomAvailability = new HashMap<>();
    }

    /**
     * Registers a given room type into the system.
     */
    public void registerRoomType(String roomType, int totalRooms) {
        roomAvailability.put(roomType, totalRooms);
    }

    /**
     * Gets the availability of a requested room type.
     */
    public int getAvailability(String roomType) {
        return roomAvailability.getOrDefault(roomType, 0);
    }

    /**
     * Adjusts the availability by adding (or subtracting) the specified amount.
     */
    public void updateAvailability(String roomType, int amountChange) {
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
    public void displayInventory() {
        for (String roomType : roomAvailability.keySet()) {
            System.out.println("- " + roomType + ": " + roomAvailability.get(roomType) + " available");
        }
    }
}
