/**
 *
 */
package ru.aafanasiev.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Marker for user converter in converters package
 *
 * @author <a href="mailto:aafanasyev@umail.ru">Andrey Afanasiev</a>
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface Converter {

}
