package by.trjava.kaloshych.service.exception;

public class WrongConfirmPasswordException extends ServiceException {
    public WrongConfirmPasswordException() {
    }

    public WrongConfirmPasswordException(String message) {
        super(message);
    }

    public WrongConfirmPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongConfirmPasswordException(Throwable cause) {
        super(cause);
    }
}
