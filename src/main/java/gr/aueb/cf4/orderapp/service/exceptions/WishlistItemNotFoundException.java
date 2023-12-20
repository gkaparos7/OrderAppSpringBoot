package gr.aueb.cf4.orderapp.service.exceptions;

public class WishlistItemNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public WishlistItemNotFoundException(String message) {
        super(message);
    }

    public WishlistItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
