package core;

import model.Reservation;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Manages the booking requests in a FIFO order.
 * Ensures fairness and decouples intake from processing. (UC5)
 */
public class BookingQueue {
    private Queue<Reservation> queue;

    public BookingQueue() {
        this.queue = new LinkedList<>();
    }

    /**
     * Adds a new booking request to the queue.
     */
    public synchronized void enqueue(Reservation reservation) {
        queue.add(reservation);
        System.out.println("Request added to queue: " + reservation.getReservationId());
    }

    /**
     * Removes and returns the next booking request to process.
     */
    public synchronized Reservation dequeue() {
        return queue.poll();
    }

    /**
     * Checks if the queue is empty.
     */
    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
    
    /**
     * Gets the current size of the queue.
     */
    public synchronized int size() {
        return queue.size();
    }
}
