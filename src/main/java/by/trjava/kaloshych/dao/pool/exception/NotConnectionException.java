package by.trjava.kaloshych.dao.pool.exception;

public class NotConnectionException extends RuntimeException {

        public NotConnectionException() {
            super();
        }

        public NotConnectionException(String message) {
            super(message);
        }

        public NotConnectionException(Exception e) {
            super(e);
        }

        public NotConnectionException(String message, Exception e) {
            super(message, e);
             }
}
