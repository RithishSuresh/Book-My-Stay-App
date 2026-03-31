package main;

import core.BookingHistory;
import core.BookingQueue;
import core.Inventory;
import exception.InvalidBookingException;
import model.*;
import service.*;

import java.util.*;

/**
 * Main application entry point for the Book My Stay App. (Integrates UC1-UC12)
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("---------------------------------");
        System.out.println("Welcome to Book My Stay");
        System.out.println("Hotel Booking System v2.0");
        System.out.println("---------------------------------\n");

        // Dependencies Setup
        PersistenceService persistenceService = new PersistenceService();
        
        // UC12: Persistence & Recovery
        Inventory inventory = persistenceService.loadInventory();
        BookingHistory history = persistenceService.loadBookingHistory();

        if (inventory == null || history == null) {
            System.out.println("No saved data found (or corrupt). Initializing fresh system state...");
            inventory = new Inventory();
            inventory.registerRoomType("SingleRoom", 5);
            inventory.registerRoomType("DoubleRoom", 3);
            inventory.registerRoomType("SuiteRoom", 2);
            history = new BookingHistory();
        } else {
            System.out.println("Application state successfully restored from save file.");
        }

        // Initialize Services
        BookingQueue queue = new BookingQueue();
        BookingService bookingService = new BookingService(queue, inventory);
        AddOnServiceManager addOnManager = new AddOnServiceManager();
        
        List<Room> displayRooms = Arrays.asList(new SingleRoom(), new DoubleRoom(), new SuiteRoom());
        Map<String, Room> roomPrototypes = new HashMap<>();
        for (Room r : displayRooms) {
            roomPrototypes.put(r.getRoomType(), r);
        }

        SearchService searchService = new SearchService(inventory, displayRooms);
        ReportService reportService = new ReportService(history, addOnManager, roomPrototypes);
        CancellationService cancellationService = new CancellationService(history, inventory, bookingService);
        Validator validator = new Validator(inventory, roomPrototypes.keySet());
        ConcurrentSimulator simulator = new ConcurrentSimulator(queue, bookingService, inventory);

        boolean running = true;
        while (running) {
            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1. Search Rooms");
            System.out.println("2. Add Booking Request");
            System.out.println("3. Process Booking Queue");
            System.out.println("4. Add Services");
            System.out.println("5. View Booking History");
            System.out.println("6. Cancel Booking");
            System.out.println("7. Generate Report");
            System.out.println("8. Simulate Concurrent Booking (UC11)");
            System.out.println("9. Save & Exit");
            System.out.print("Select an option: ");

            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    searchService.searchAvailableRooms();
                    break;
                case 2:
                    System.out.print("Enter Customer Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Room Type (SingleRoom, DoubleRoom, SuiteRoom): ");
                    String roomType = scanner.nextLine();
                    
                    try {
                        // UC9: Validation (Fail-fast)
                        validator.validateBookingRequest(roomType);
                        String reservationId = "RES-" + UUID.randomUUID().toString().substring(0, 4).toUpperCase();
                        Reservation res = new Reservation(reservationId, name, roomType);
                        // Add to queue
                        queue.enqueue(res);
                        // Add to history
                        history.addReservation(res);
                    } catch (InvalidBookingException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    bookingService.processBookings();
                    break;
                case 4:
                    System.out.print("Enter Reservation ID: ");
                    String resId = scanner.nextLine();
                    System.out.print("Enter Service Name (e.g., Breakfast, Spa): ");
                    String serviceName = scanner.nextLine();
                    System.out.print("Enter Service Price: ");
                    try {
                        double price = Double.parseDouble(scanner.nextLine());
                        addOnManager.addService(resId, new Service(serviceName, price));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid price.");
                    }
                    break;
                case 5:
                    reportService.showAllBookings();
                    break;
                case 6:
                    System.out.print("Enter Reservation ID to Cancel: ");
                    String cancelId = scanner.nextLine();
                    cancellationService.cancelReservation(cancelId);
                    break;
                case 7:
                    reportService.generateSummaryReport();
                    break;
                case 8:
                    simulator.runSimulation();
                    break;
                case 9:
                    persistenceService.saveData(inventory, history);
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
        scanner.close();
        System.out.println("Exiting the application. Goodbye!");
    }
}
