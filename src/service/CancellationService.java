package service;

import core.BookingHistory;
import core.Inventory;
import model.Reservation;

import java.util.Stack;

/**
 * Service to handle booking cancellations and state rollback. (UC10)
 */
public class CancellationService {
    private BookingHistory history;
    private Inventory inventory;
    private BookingService bookingService;
    
    // History of cancelled reservation IDs
    private Stack<String> cancellationRollbackStack;

    public CancellationService(BookingHistory history, Inventory inventory, BookingService bookingService) {
        this.history = history;
        this.inventory = inventory;
        this.bookingService = bookingService;
        this.cancellationRollbackStack = new Stack<>();
    }

    /**
     * Cancels a reservation by ID.
     */
    public synchronized void cancelReservation(String reservationId) {
        Reservation target = null;
        for (Reservation res : history.getAllReservations()) {
            if (res.getReservationId().equals(reservationId)) {
                target = res;
                break;
            }
        }

        if (target == null) {
            System.out.println("Error: Reservation ID '" + reservationId + "' not found.");
            return;
        }

        if ("CANCELLED".equals(target.getStatus())) {
            System.out.println("Error: Reservation '" + reservationId + "' is already cancelled.");
            return;
        }

        if (!"CONFIRMED".equals(target.getStatus())) {
            System.out.println("Error: Only CONFIRMED reservations can be cancelled (Current Status: " + target.getStatus() + ").");
            return;
        }

        // Rollback operations
        String roomId = target.getAllocatedRoomId();
        String roomType = target.getRoomType();

        // 1. Remove room allocation
        bookingService.getAllAllocatedRoomIds().remove(roomId);
        if (bookingService.getAllocatedRoomsPerType().containsKey(roomType)) {
            bookingService.getAllocatedRoomsPerType().get(roomType).remove(roomId);
        }

        // 2. Increment inventory
        inventory.updateAvailability(roomType, 1);

        // 3. Update status
        target.setStatus("CANCELLED");
        target.setAllocatedRoomId(null);
        
        // Save to rollback stack
        cancellationRollbackStack.push(reservationId);

        System.out.println("Reservation '" + reservationId + "' has been successfully cancelled. Room " + roomId + " is now free.");
    }
}
