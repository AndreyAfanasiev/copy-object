/**
 *
 */
package ru.aafanasiev.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Convert {@code boolean} value to {@code String} for boolean getter
 *
 * @author <a href="mailto:aafanasyev@umail.ru">Andrey Afanasiev</a>
 */
@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface CopyBoolean2String {
    /**
     * <p>
     * True value
     * </p>
     * Default: <code>true</code>
     *
     * @return String for {@code true} boolean value
     */
    String trueValue() default "true";

    /**
     * <p>
     * False value
     * </p>
     * Default: <code>false</code>
     *
     * @return String for {@code false} boolean value
     */
    String falseValue() default "false";
}
