package by.trjava.kaloshych.service.exception;

public class InvalidCurrentPasswordException extends ServiceException {
    public InvalidCurrentPasswordException() {
    }

    public InvalidCurrentPasswordException(String message) {
        super(message);
    }

    public InvalidCurrentPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCurrentPasswordException(Throwable cause) {
        super(cause);
    }
}
