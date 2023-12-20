package gr.aueb.cf4.orderapp.service.exceptions;

public class OrderItemNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public OrderItemNotFoundException(String message) {
        super(message);
    }

    public OrderItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
