package sk.stuba.fei.uim.oop.assignment3.Exceptions;

public class AmountLimitException extends Exception{
    public AmountLimitException() {
        super();
    }

    public AmountLimitException(String message) {
        super(message);
    }

    public AmountLimitException(String message, Throwable cause) {
        super(message, cause);
    }

    public AmountLimitException(Throwable cause) {
        super(cause);
    }

    public AmountLimitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
