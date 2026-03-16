package com.bookmystay.rooms;

/**
 * Represents a standard double room.
 */
public class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 350, 120.00);
    }

    @Override
    public void displayRoomDetails() {
        System.out.println(roomType + " - Beds: " + beds + ", Size: " + size + " sqft, Price: $" + price + "/night");
    }
}
