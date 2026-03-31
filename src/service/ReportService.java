package service;

import core.BookingHistory;
import model.Reservation;
import model.Room;

import java.util.List;
import java.util.Map;

/**
 * Service to generate reports and summaries of bookings. (UC8)
 */
public class ReportService {
    private BookingHistory history;
    private AddOnServiceManager addOnManager;
    private Map<String, Room> roomPrototypes;

    public ReportService(BookingHistory history, AddOnServiceManager addOnManager, Map<String, Room> roomPrototypes) {
        this.history = history;
        this.addOnManager = addOnManager;
        this.roomPrototypes = roomPrototypes;
    }

    /**
     * Shows all bookings in the system.
     */
    public void showAllBookings() {
        System.out.println("\n--- Booking History ---");
        List<Reservation> allReservations = history.getAllReservations();
        if (allReservations.isEmpty()) {
            System.out.println("No bookings recorded.");
            return;
        }

        for (Reservation res : allReservations) {
            System.out.println(res);
        }
    }

    /**
     * Generates a summary including total bookings and estimated revenue.
     */
    public void generateSummaryReport() {
        System.out.println("\n--- Summary Report ---");
        List<Reservation> allReservations = history.getAllReservations();
        int totalBookings = allReservations.size();
        int confirmedBookings = 0;
        int cancelledBookings = 0;
        double totalRevenue = 0.0;

        for (Reservation res : allReservations) {
            if ("CONFIRMED".equals(res.getStatus())) {
                confirmedBookings++;
                Room room = roomPrototypes.get(res.getRoomType());
                if (room != null) {
                    totalRevenue += room.getPrice();
                }
                // Add extra cost
                totalRevenue += addOnManager.calculateAdditionalCost(res.getReservationId());
                
            } else if ("CANCELLED".equals(res.getStatus())) {
                cancelledBookings++;
            }
        }

        System.out.println("Total Booking Requests: " + totalBookings);
        System.out.println("Confirmed Bookings: " + confirmedBookings);
        System.out.println("Cancelled Bookings: " + cancelledBookings);
        System.out.println("Estimated Revenue: $" + String.format("%.2f", totalRevenue));
    }
}
