package by.trjava.kaloshych.service.exception;

public class LoginUsedException extends ServiceException {
    public LoginUsedException() {
    }

    public LoginUsedException(String message) {
        super(message);
    }

    public LoginUsedException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginUsedException(Throwable cause) {
        super(cause);
    }
}
