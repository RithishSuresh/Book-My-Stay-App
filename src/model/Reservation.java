package model;

import java.io.Serializable;

/**
 * Represents a booking request/reservation in the system.
 */
public class Reservation implements Serializable {
    private String reservationId;
    private String customerName;
    private String roomType;
    private String allocatedRoomId;
    private String status;

    public Reservation(String reservationId, String customerName, String roomType) {
        this.reservationId = reservationId;
        this.customerName = customerName;
        this.roomType = roomType;
        this.status = "PENDING";
    }

    public String getReservationId() { return reservationId; }
    public String getCustomerName() { return customerName; }
    public String getRoomType() { return roomType; }
    
    public String getAllocatedRoomId() { return allocatedRoomId; }
    public void setAllocatedRoomId(String allocatedRoomId) { this.allocatedRoomId = allocatedRoomId; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Reservation [ID=" + reservationId + ", Customer=" + customerName +
               ", RoomType=" + roomType + ", Status=" + status + 
               (allocatedRoomId != null ? ", AllocatedRoom=" + allocatedRoomId : "") + "]";
    }
}
