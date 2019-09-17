/**
 *
 */
package ru.aafanasiev.util.copier.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class for operations with exceptions
 * 
 * @author <a href="mailto:aafanasyev@umail.ru">Andrey Afanasiev</a>
 * @version 1.0 2017-03-30
 */
public class ExceptionUtils {
    /** Logger */
    private static final Logger log = LoggerFactory.getLogger(ExceptionUtils.class);

    /**
     * Private constructor for utility class
     */
    private ExceptionUtils() {
    }

    /**
     * Create string with exception trace stack.
     *
     * @param ex Source {@code Throwable}
     * @return Exception stack trace as string
     */
    public static String getExceptionStack(Throwable ex) {
        String result = null;

        try(ByteArrayOutputStream os = new ByteArrayOutputStream(8192);
                PrintStream ps = new PrintStream(os, true, StandardCharsets.UTF_8.name())) {
            ex.printStackTrace(ps);
            result = new String(os.toByteArray(), StandardCharsets.UTF_8);
        } catch(IOException ex1) {
            log.error("Close ByteArrayOutputStream close", ex1);
        }

        return result;
    }

}
