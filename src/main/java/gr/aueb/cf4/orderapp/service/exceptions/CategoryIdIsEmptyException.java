package gr.aueb.cf4.orderapp.service.exceptions;

public class CategoryIdIsEmptyException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public CategoryIdIsEmptyException(String message) {
        super(message);
    }
}
