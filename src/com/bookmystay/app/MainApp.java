package com.bookmystay.app;

import com.bookmystay.rooms.Room;
import com.bookmystay.rooms.SingleRoom;
import com.bookmystay.rooms.DoubleRoom;
import com.bookmystay.rooms.SuiteRoom;
import com.bookmystay.inventory.RoomInventory;

/**
 * Main application entry point for the Book My Stay Hotel Booking System.
 * Demonstrates linear execution, object modeling, and centralized inventory management.
 * 
 * @author BookMyStay Developer
 * @version 1.0
 */
public class MainApp {
    public static void main(String[] args) {
        // UC1: Welcome Message
        System.out.println("---------------------------------");
        System.out.println("Welcome to Book My Stay");
        System.out.println("Hotel Booking System v1.0");
        System.out.println("---------------------------------\n");

        // UC2: Room Modeling & Objects
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();
        
        System.out.println("--- Our Room Offerings ---");
        singleRoom.displayRoomDetails();
        System.out.println();
        doubleRoom.displayRoomDetails();
        System.out.println();
        suiteRoom.displayRoomDetails();
        System.out.println();

        // UC3: Centralized Room Inventory
        RoomInventory inventory = new RoomInventory();
        inventory.registerRoomType("SingleRoom", 5);
        inventory.registerRoomType("DoubleRoom", 3);
        inventory.registerRoomType("SuiteRoom", 2);

        System.out.println("--- Initial Room Availability ---");
        inventory.displayInventory();

        // Simulating some room bookings
        System.out.println("\n--- Processing Bookings ---");
        System.out.println("Booking 1 Single Room...");
        inventory.updateAvailability("SingleRoom", -1);
        System.out.println("Booking 2 Double Rooms...");
        inventory.updateAvailability("DoubleRoom", -2);

        System.out.println("\n--- Updated Room Availability ---");
        inventory.displayInventory();
    }
}
