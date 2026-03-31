package exception;

/**
 * Custom exception for invalid booking requests. (UC9)
 */
public class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}
