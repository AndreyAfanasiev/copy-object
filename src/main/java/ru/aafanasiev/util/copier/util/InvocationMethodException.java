/**
 *
 */
package ru.aafanasiev.util.copier.util;

/**
 * <p>
 * Invocation method with Exceptions
 * </p>
 * Exception is stored in cause exceptions field.
 *
 * @author <a href="mailto:aafanasyev@umail.ru">Andrey Afanasiev</a>
 */
public class InvocationMethodException extends RuntimeException {
    private static final long serialVersionUID = 2931573768592259045L;

    /**
     * Create Runtime exception
     */
    public InvocationMethodException() {
        super();
    }

    /**
     * Create Runtime exception with message and previos exception
     *
     * @param message Message
     * @param cause Previos (cause) exception
     */
    public InvocationMethodException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Create Runtime exception with message
     *
     * @param message Message
     */
    public InvocationMethodException(String message) {
        super(message);
    }

    /**
     * Create Runtime exception with message and previos exception
     *
     * @param cause Previos (cause) exception
     */
    public InvocationMethodException(Throwable cause) {
        super(cause);
    }

}
