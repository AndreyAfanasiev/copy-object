/**
 *
 */
package ru.aafanasiev.util.copier.mapper;

import java.lang.reflect.Method;

/**
 * @author <a href="mailto:aafanasiev@umail.ru">Andrey Afanasiev</a>
 */
public interface ReadOperation extends Operation {
    /**
     * Get Getter. It is used for one value calculate
     *
     * @return
     */
    Method getFromMethod();
}
