package gr.aueb.cf4.orderapp.service.exceptions;

public class SubcategoryNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public SubcategoryNotFoundException(String message) {
        super(message);
    }

    public SubcategoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
