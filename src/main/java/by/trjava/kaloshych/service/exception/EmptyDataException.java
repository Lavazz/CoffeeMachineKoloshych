package by.trjava.kaloshych.service.exception;

public class EmptyDataException extends ServiceException {
    public EmptyDataException() {
    }

    public EmptyDataException(String message) {
        super(message);
    }

    public EmptyDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyDataException(Throwable cause) {
        super(cause);
    }
}
