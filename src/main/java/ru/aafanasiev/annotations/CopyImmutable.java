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
 * <p>
 * The annotation states that the value of the getter before the assignment must be copied to an object of the same
 * type.
 * </p>
 * <p>
 * Applies mainly to the Date fields (both java.util and java.sql) and, in future, Collectiosn type (Collections and
 * Map)
 * </p>
 * <p>
 * Not yet implemented for other classes. In the future, it is possible to apply for all Mutable classes.
 * </p>
 *
 * @author <a href="mailto:aafanasyev@umail.ru">Andrey Afanasiev</a>
 */
@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface CopyImmutable {

}
