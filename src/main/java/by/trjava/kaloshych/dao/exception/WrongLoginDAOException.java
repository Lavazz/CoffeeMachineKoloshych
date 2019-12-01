package by.trjava.kaloshych.dao.exception;

public class WrongLoginDAOException extends Exception {
    public WrongLoginDAOException() {
    }

    public WrongLoginDAOException(String message) {
        super(message);
    }

    public WrongLoginDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongLoginDAOException(Throwable cause) {
        super(cause);
    }
}
