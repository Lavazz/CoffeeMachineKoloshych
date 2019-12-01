package by.trjava.kaloshych.service.exception;

public class SmallAmountException extends ServiceException {
    public SmallAmountException() {
    }

    public SmallAmountException(String message) {
        super(message);
    }

    public SmallAmountException(String message, Throwable cause) {
        super(message, cause);
    }

    public SmallAmountException(Throwable cause) {
        super(cause);
    }
}
