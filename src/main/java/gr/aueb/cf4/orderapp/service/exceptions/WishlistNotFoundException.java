package gr.aueb.cf4.orderapp.service.exceptions;

public class WishlistNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public WishlistNotFoundException(String message) {
        super(message);
    }

    public WishlistNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
