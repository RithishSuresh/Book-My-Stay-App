package core;

import model.Reservation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Maintains a historical record of all processed bookings. (UC8)
 * Preserves insertion order.
 */
public class BookingHistory implements Serializable {
    private List<Reservation> history;

    public BookingHistory() {
        this.history = new ArrayList<>();
    }

    /**
     * Adds a reservation to the history.
     */
    public synchronized void addReservation(Reservation reservation) {
        history.add(reservation);
    }

    /**
     * Retrieves all reservations in the history.
     */
    public synchronized List<Reservation> getAllReservations() {
        return new ArrayList<>(history);
    }
}
