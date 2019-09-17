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
 * Convert {@code String} value to {@code boolean} for string getter
 *
 * @author <a href="mailto:aafanasyev@umail.ru">Andrey Afanasiev</a>
 */
@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface CopyString2Boolean {
    /**
     * True value list<br>
     * Default value:
     * <ul>
     * <li>{@code true}</li>
     * <li>{@code t}</li>
     * <li>{@code yes}</li>
     * <li>{@code y}</li>
     * <li>{@code 1}</li>
     * </ul>
     *
     * @return List string representative for "true" value.
     */
    String[] trueValue() default {"true", "t", "yes", "y", "1"};

    /**
     * False value list<br>
     * Default value:
     * <ul>
     * <li>{@code false}</li>
     * <li>{@code f}</li>
     * <li>{@code no}</li>
     * <li>{@code n}</li>
     * <li>{@code 0}</li>
     * </ul>
     *
     * @return List string representative for "false" value.
     */
    String[] falseValue() default {"false", "f", "no", "n", "0"};

    /**
     * <p>
     * Case sensitive comparison
     * </p>
     * Value:
     * <ul>
     * <li><code>true</code> - case sensitive</li>
     * <li><code>false</code> - case insensitive</li>
     * </ul>
     *
     * @return
     *         <ul>
     *         <li>{@code true} - Case sensitive comparison</li>
     *         <li>{@code false} - Case unsensitive comparison</li>
     *         </ul>
     */
    boolean caseSensitive() default false;
}
