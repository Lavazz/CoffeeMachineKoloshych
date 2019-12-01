package by.trjava.kaloshych.service.exception;

public class InsufficientPortionException extends ServiceException {
    public InsufficientPortionException() {
    }

    public InsufficientPortionException(String message) {
        super(message);
    }

    public InsufficientPortionException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsufficientPortionException(Throwable cause) {
        super(cause);
    }
}
