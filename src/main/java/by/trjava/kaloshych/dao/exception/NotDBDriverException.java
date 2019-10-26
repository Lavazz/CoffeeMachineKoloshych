package by.trjava.kaloshych.dao.exception;

public class NotDBDriverException extends RuntimeException {

        public NotDBDriverException() {
            super();
        }

        public NotDBDriverException(String message) {
            super(message);
        }

        public NotDBDriverException(Exception e) {
            super(e);
        }

        public NotDBDriverException(String message, Exception e) {
            super(message, e);
             }
}
