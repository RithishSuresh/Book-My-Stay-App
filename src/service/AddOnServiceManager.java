package service;

import model.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages add-on services for specific reservations. (UC7)
 */
public class AddOnServiceManager {
    // Maps Reservation ID to a list of requested Add-on Services
    private Map<String, List<Service>> addOnServices;

    public AddOnServiceManager() {
        this.addOnServices = new HashMap<>();
    }

    /**
     * Adds an extra service to an existing reservation.
     */
    public synchronized void addService(String reservationId, Service service) {
        addOnServices.computeIfAbsent(reservationId, k -> new ArrayList<>()).add(service);
        System.out.println("Service '" + service.getName() + "' added to Reservation ID: " + reservationId);
    }

    /**
     * Retrieves all requested services for a reservation.
     */
    public synchronized List<Service> getServicesForReservation(String reservationId) {
        return addOnServices.getOrDefault(reservationId, new ArrayList<>());
    }

    /**
     * Calculates the total additional cost for a given reservation.
     */
    public synchronized double calculateAdditionalCost(String reservationId) {
        List<Service> services = getServicesForReservation(reservationId);
        double total = 0.0;
        for (Service s : services) {
            total += s.getPrice();
        }
        return total;
    }
}
