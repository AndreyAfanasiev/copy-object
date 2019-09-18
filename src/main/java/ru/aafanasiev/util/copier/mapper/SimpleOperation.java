/**
 *
 */
package ru.aafanasiev.util.copier.mapper;

import java.lang.reflect.Method;

/**
 * @author <a href="mailto:aafanasiev@umail.ru">Andrey Afanasiev</a>
 */
public interface SimpleOperation extends ReadOperation {
    /**
     * Get setter. It is used for value save
     *
     * @return Setter метод
     */
    Method getToMethod();
}
