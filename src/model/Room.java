package model;

import java.io.Serializable;

/**
 * Abstract class representing a generic hotel room.
 * Demonstrates abstraction, encapsulation, and class inheritance preparation.
 */
public abstract class Room implements Serializable {
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

    public String getRoomType() { return roomType; }
    public double getPrice() { return price; }

    /**
     * Abstract method to output room specifics.
     * Demonstrates polymorphism across subclasses.
     */
    public abstract void displayRoomDetails();
}
