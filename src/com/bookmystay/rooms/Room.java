package com.bookmystay.rooms;

/**
 * Abstract class representing a generic hotel room.
 * Demonstrates abstraction, encapsulation, and class inheritance preparation.
 * 
 * @author BookMyStay Developer
 * @version 1.0
 */
public abstract class Room {
    protected String roomType;
    protected int beds;
    protected int size;
    protected double price;

    public Room(String roomType, int beds, int size, double price) {
        this.roomType = roomType;
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    /**
     * Abstract method to output room specifics.
     * Demonstrates polymorphism across subclasses.
     */
    public abstract void displayRoomDetails();
}
