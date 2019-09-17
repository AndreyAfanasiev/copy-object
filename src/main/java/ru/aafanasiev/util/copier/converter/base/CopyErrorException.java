/**
 *
 */
package ru.aafanasiev.util.copier.converter.base;

/**
 * Copy error.
 *
 * @author <a href="mailto:aafanasyev@umail.ru">Andrey Afanasiev</a>
 */
public class CopyErrorException extends RuntimeException {
    /** Serialization identifier */
    private static final long serialVersionUID = -1697792783528398250L;

    /**
     * Copy class to class error<br>
     * Default exception constructor
     */
    public CopyErrorException() {
        super();
    }

    /**
     * Create copy class to class error with message
     *
     * @param message the detail message.
     */
    public CopyErrorException(String message) {
        super(message);
    }

    /**
     * Create copy class to class error with previouse exception (with exception stack)
     *
     * @param cause the cause. (A {@code null} value is permitted, and indicates that the cause is nonexistent or
     *            unknown.)
     */
    public CopyErrorException(Throwable cause) {
        super(cause);
    }

    /**
     * Create copy class to class error with message and previouse exception (with exception stack)
     *
     * @param message the detail message.
     * @param cause the cause. (A {@code null} value is permitted, and indicates that the cause is nonexistent or
     *            unknown.)
     */
    public CopyErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Create copy class to class error with full parameters
     *
     * @param message the detail message.
     * @param cause the cause. (A {@code null} value is permitted, and indicates that the cause is nonexistent or
     *            unknown.)
     * @param enableSuppression whether or not suppression is enabled or disabled
     * @param writableStackTrace whether or not the stack trace should be writable
     */
    public CopyErrorException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
