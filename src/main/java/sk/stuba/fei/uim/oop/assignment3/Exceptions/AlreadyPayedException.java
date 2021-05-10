package sk.stuba.fei.uim.oop.assignment3.Exceptions;

public class AlreadyPayedException extends Exception{
    public AlreadyPayedException() {
        super();
    }

    public AlreadyPayedException(String message) {
        super(message);
    }

    public AlreadyPayedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyPayedException(Throwable cause) {
        super(cause);
    }

    public AlreadyPayedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
