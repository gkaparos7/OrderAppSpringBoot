package gr.aueb.cf4.orderapp.service.exceptions;

public class NoCurrentOrderException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NoCurrentOrderException(String message) {
        super(message);
    }

}
