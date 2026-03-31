package service;

import core.BookingQueue;
import core.Inventory;
import model.Reservation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Service to handle confirmation and allocation of reservations. (UC6)
 */
public class BookingService {
    private BookingQueue queue;
    private Inventory inventory;

    // To prevent duplicate room IDs
    private Set<String> allAllocatedRoomIds;
    
    // Track allocated rooms per type
    private Map<String, Set<String>> allocatedRoomsPerType;

    public BookingService(BookingQueue queue, Inventory inventory) {
        this.queue = queue;
        this.inventory = inventory;
        this.allAllocatedRoomIds = new HashSet<>();
        this.allocatedRoomsPerType = new HashMap<>();
    }

    /**
     * Processes the booking queue by dequeuing requests and allocating rooms.
     */
    public synchronized void processBookings() {
        while (!queue.isEmpty()) {
            Reservation reservation = queue.dequeue();
            if (reservation == null) continue;

            System.out.println("Processing reservation request: " + reservation.getReservationId());
            String roomType = reservation.getRoomType();

            // Double Booking Prevention
            synchronized (inventory) {
                if (inventory.getAvailability(roomType) > 0) {
                    // Update inventory immediately
                    inventory.updateAvailability(roomType, -1);
                    
                    // Allocate room ID
                    String roomId = generateUniqueRoomId(roomType);
                    reservation.setAllocatedRoomId(roomId);
                    reservation.setStatus("CONFIRMED");
                    
                    // Track allocations
                    allAllocatedRoomIds.add(roomId);
                    allocatedRoomsPerType.computeIfAbsent(roomType, k -> new HashSet<>()).add(roomId);

                    System.out.println("Booking Confirmed: " + reservation.getReservationId() + " -> Room " + roomId);
                } else {
                    reservation.setStatus("FAILED (No Availability)");
                    System.out.println("Booking Failed: " + reservation.getReservationId() + " (No rooms available)");
                }
            }
        }
    }

    /**
     * Generates a guaranteed unique room ID.
     */
    private String generateUniqueRoomId(String roomType) {
        String baseType = roomType.substring(0, Math.min(3, roomType.length())).toUpperCase();
        String newId;
        do {
            newId = baseType + "-" + UUID.randomUUID().toString().substring(0, 5).toUpperCase();
        } while (allAllocatedRoomIds.contains(newId));
        return newId;
    }

    public Set<String> getAllAllocatedRoomIds() {
        return allAllocatedRoomIds;
    }

    public Map<String, Set<String>> getAllocatedRoomsPerType() {
        return allocatedRoomsPerType;
    }
}
