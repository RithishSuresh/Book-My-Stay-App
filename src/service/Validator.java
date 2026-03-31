package service;

import core.Inventory;
import exception.InvalidBookingException;

import java.util.Set;

/**
 * Handles validation and error checking (Fail-fast approach). (UC9)
 */
public class Validator {
    private Inventory inventory;
    private Set<String> validRoomTypes;

    public Validator(Inventory inventory, Set<String> validRoomTypes) {
        this.inventory = inventory;
        this.validRoomTypes = validRoomTypes;
    }

    /**
     * Validates if a booking request is legally allowed.
     * @throws InvalidBookingException with clear error message if validation fails.
     */
    public void validateBookingRequest(String roomType) throws InvalidBookingException {
        // Check if room type exists
        if (!validRoomTypes.contains(roomType)) {
            throw new InvalidBookingException("Error: Room type '" + roomType + "' does not exist.");
        }

        // Check if availability is not negative
        int available = inventory.getAvailability(roomType);
        if (available < 0) {
            throw new InvalidBookingException("Error: System state invalid. Availability for '" + roomType + "' is negative.");
        }

        // Fail-fast if currently 0 but requested
        if (available == 0) {
            throw new InvalidBookingException("Error: No available rooms for type '" + roomType + "'.");
        }
    }
}
