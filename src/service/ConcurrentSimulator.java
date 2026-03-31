package service;

import core.BookingQueue;
import core.Inventory;
import model.Reservation;

/**
 * Simulates concurrent booking to test thread safety. (UC11)
 */
public class ConcurrentSimulator {
    private BookingQueue queue;
    private BookingService bookingService;
    private Inventory inventory;

    public ConcurrentSimulator(BookingQueue queue, BookingService bookingService, Inventory inventory) {
        this.queue = queue;
        this.bookingService = bookingService;
        this.inventory = inventory;
    }

    /**
     * Simulates multiple users trying to book the same room type concurrently.
     */
    public void runSimulation() {
        System.out.println("Starting Concurrent Booking Simulation for 'SingleRoom' (5 available originally, maybe less now).");
        
        // Add 10 requests to the queue fast
        for (int i = 1; i <= 10; i++) {
            queue.enqueue(new Reservation("SIM-" + i, "User " + i, "SingleRoom"));
        }

        // Start multiple threads trying to process the bookings concurrently
        Thread t1 = new Thread(() -> bookingService.processBookings());
        Thread t2 = new Thread(() -> bookingService.processBookings());
        Thread t3 = new Thread(() -> bookingService.processBookings());

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            System.out.println("Concurrent Simulation Completed successfully.");
        } catch (InterruptedException e) {
            System.out.println("Simulation interrupted.");
            Thread.currentThread().interrupt();
        }
    }
}
