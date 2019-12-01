package by.trjava.kaloshych.service.exception;

public class IncorrectComponentInformationException extends ServiceException {
    public IncorrectComponentInformationException() {
    }

    public IncorrectComponentInformationException(String message) {
        super(message);
    }

    public IncorrectComponentInformationException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectComponentInformationException(Throwable cause) {
        super(cause);
    }
}
