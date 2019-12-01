package by.trjava.kaloshych.service.exception;

public class InsufficientMoneyException extends ServiceException {
    public InsufficientMoneyException() {
    }

    public InsufficientMoneyException(String message) {
        super(message);
    }

    public InsufficientMoneyException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsufficientMoneyException(Throwable cause) {
        super(cause);
    }
}
