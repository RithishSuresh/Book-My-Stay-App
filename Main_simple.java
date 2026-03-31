package main;

import model.Room;
import model.SingleRoom;
import model.DoubleRoom;
import model.SuiteRoom;
import core.Inventory;

/**
 * Main application entry point for the Book My Stay Hotel Booking System.
 */
public class Main {
    public static void main(String[] args) {
        // Basic app init for testing models
        System.out.println("---------------------------------");
        System.out.println("Welcome to Book My Stay");
        System.out.println("Hotel Booking System v1.0");
        System.out.println("---------------------------------\n");

        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();
        
        System.out.println("--- Our Room Offerings ---");
        singleRoom.displayRoomDetails();
        doubleRoom.displayRoomDetails();
        suiteRoom.displayRoomDetails();

        Inventory inventory = new Inventory();
        inventory.registerRoomType("SingleRoom", 5);
        inventory.registerRoomType("DoubleRoom", 3);
        inventory.registerRoomType("SuiteRoom", 2);

        System.out.println("\n--- Initial Room Availability ---");
        inventory.displayInventory();
    }
}
