package com.bookmystay.app;

import com.bookmystay.rooms.Room;
import com.bookmystay.rooms.SingleRoom;
import com.bookmystay.rooms.DoubleRoom;
import com.bookmystay.rooms.SuiteRoom;

/**
 * Main application entry point for the Book My Stay Hotel Booking System.
 * Demonstrates linear execution, string literals, and method invocation.
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

        // Static availability variables for UC2
        int singleRoomsAvailable = 5;
        int doubleRoomsAvailable = 3;
        int suiteRoomsAvailable = 2;

        System.out.println("--- Room Availability ---");
        System.out.println("Single Rooms: " + singleRoomsAvailable);
        System.out.println("Double Rooms: " + doubleRoomsAvailable);
        System.out.println("Suite Rooms: " + suiteRoomsAvailable);
    }
}
