package by.trjava.kaloshych.service.exception;

public class WrongPortionException extends ServiceException {
    public WrongPortionException() {
    }

    public WrongPortionException(String message) {
        super(message);
    }

    public WrongPortionException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongPortionException(Throwable cause) {
        super(cause);
    }
}
