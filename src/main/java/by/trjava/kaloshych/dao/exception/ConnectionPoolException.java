package by.trjava.kaloshych.dao.exception;

    public class ConnectionPoolException extends Exception {

        public ConnectionPoolException(String message) {
            super(message);
        }

        public ConnectionPoolException(String message, Throwable e) {
            super(message, e);
        }

    }