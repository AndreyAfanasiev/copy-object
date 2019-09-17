/**
 *
 */
package ru.aafanasiev.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import ru.aafanasiev.util.copier.converter.base.CopyCustomConverter;

/**
 * <p>
 * Set custom convertion for Object
 * </p>
 * This annnotation can not set for base types
 *
 * @author <a href="mailto:aafanasyev@umail.ru">Andrey Afanasiev</a>
 */
@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface CopyCustom {
    /**
     * Set converter class
     *
     * @return Converter class
     */
    Class<? extends CopyCustomConverter> value();
}
