package com.bookmystay.rooms;

/**
 * Represents a standard single room.
 */
public class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 200, 80.00);
    }

    @Override
    public void displayRoomDetails() {
        System.out.println(roomType + " - Beds: " + beds + ", Size: " + size + " sqft, Price: $" + price + "/night");
    }
}
