/**
 *
 */
package ru.aafanasiev.util.copier.converter.base;

import java.lang.reflect.Method;

/**
 * Custom convertion interface. <br>
 * Custom converter can not register in conveter list.
 *
 * @author <a href="mailto:aafanasyev@umail.ru">Andrey Afanasiev</a>
 */
public interface CopyCustomConverter {
    /**
     * Converting source data object to destination data object
     *
     * @param source Source method describe
     * @param destination Destination method describe
     * @param params Parameters for copy method
     * @param value Source object value
     * @return Destination object
     */
    Object convert(Method source, Method destination, Object params, Object value);

    /**
     * Get Parameters entry for this copy step.
     *
     * @param source Source method describe
     * @return Parametes object or <code>null</code>
     */
    Object getParameters(Method source);
}
