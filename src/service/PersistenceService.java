package service;

import core.BookingHistory;
import core.Inventory;

import java.io.*;

/**
 * Service to handle serialization and deserialization of the system state. (UC12)
 */
public class PersistenceService {
    private static final String FILE_NAME = "data.ser";

    /**
     * Saves Inventory and BookingHistory to file.
     */
    public void saveData(Inventory inventory, BookingHistory history) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(inventory);
            oos.writeObject(history);
            System.out.println("Application state successfully saved to " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    /**
     * Loads Inventory data from file.
     */
    public Inventory loadInventory() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            Inventory inventory = (Inventory) ois.readObject();
            return inventory;
        } catch (FileNotFoundException e) {
            System.out.println("No existing save file found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Save file is corrupt. Starting fresh.");
        }
        return null; // Return null to indicate starting fresh
    }

    /**
     * Loads BookingHistory data from file.
     */
    public BookingHistory loadBookingHistory() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            ois.readObject(); // Skip Inventory
            BookingHistory history = (BookingHistory) ois.readObject();
            return history;
        } catch (Exception e) {
            // Error already logged during loadInventory or file doesn't exist
        }
        return null;
    }
}
