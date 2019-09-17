package ru.aafanasiev.util.copier.converter.base;
/**
 *
 */

/**
 * Number convert error
 *
 * @author <a href="mailto:aafanasyev@umail.ru">Andrey Afanasiev</a>
 */
public class NumberConvertException extends RuntimeException {
    /** Идентификатор сериализации */
    private static final long serialVersionUID = 4443916293020763132L;

    /**
     * Default constructor fot this exception
     */
    public NumberConvertException() {
        super();
    }

    /**
     * Constrictor with detail message
     *
     * @param message the detail message.
     */
    public NumberConvertException(String message) {
        super(message);
    }

    /**
     * Create with previouse exception (with exception stack)
     *
     * @param cause the cause. (A {@code null} value is permitted, and indicates that the cause is nonexistent or
     *            unknown.)
     */
    public NumberConvertException(Throwable cause) {
        super(cause);
    }

    /**
     * Create with message and previouse exception (with exception stack)
     *
     * @param message the detail message.
     * @param cause the cause. (A {@code null} value is permitted, and indicates that the cause is nonexistent or
     *            unknown.)
     */
    public NumberConvertException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Factory method for making a {@code NumberConvertException} given the specified input which caused the error.
     *
     * @param s the input causing the error
     * @return The new NumberConvertException object
     */
    static NumberConvertException forInputString(String s) {
        return new NumberConvertException("For input string: \"" + s + "\"");
    }
}
