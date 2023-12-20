package gr.aueb.cf4.orderapp.service.exceptions;

public class ProductNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
