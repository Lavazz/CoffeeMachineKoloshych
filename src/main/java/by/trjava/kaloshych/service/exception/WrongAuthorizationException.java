package by.trjava.kaloshych.service.exception;

public class WrongAuthorizationException extends ServiceException {
    public WrongAuthorizationException() {
    }

    public WrongAuthorizationException(String message) {
        super(message);
    }

    public WrongAuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongAuthorizationException(Throwable cause) {
        super(cause);
    }
}
