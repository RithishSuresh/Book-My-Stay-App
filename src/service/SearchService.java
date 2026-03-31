package service;

import core.Inventory;
import model.Room;

import java.util.List;

/**
 * Service for searching rooms and checking availability.
 * Read-only operations. (UC4)
 */
public class SearchService {
    private Inventory inventory;
    private List<Room> displayRooms;

    public SearchService(Inventory inventory, List<Room> displayRooms) {
        this.inventory = inventory;
        this.displayRooms = displayRooms;
    }

    /**
     * Filters and displays rooms with availability > 0.
     */
    public void searchAvailableRooms() {
        System.out.println("--- Available Rooms ---");
        boolean found = false;
        for (Room room : displayRooms) {
            int available = inventory.getAvailability(room.getRoomType());
            if (available > 0) {
                System.out.print("[" + available + " available] ");
                room.displayRoomDetails();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No rooms are currently available.");
        }
    }
}
