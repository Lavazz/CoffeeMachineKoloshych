package by.trjava.kaloshych.service.exception;

public class DuplicateComponentException extends ServiceException {
    public DuplicateComponentException() {
    }

    public DuplicateComponentException(String message) {
        super(message);
    }

    public DuplicateComponentException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateComponentException(Throwable cause) {
        super(cause);
    }
}
