/**
 *
 */
package ru.aafanasiev.util.copier.mapper;

/**
 * Common operation
 *
 * @author <a href="mailto:aafanasiev@umail.ru">Andrey Afanasiev</a>
 */
public interface Operation {
    /**
     * Calculate operation: Copy value from object <code>source</code> to object <code>target</code>
     *
     * @param <F> Source object type
     * @param <T> Target object type
     * @param source Source object
     * @param target Target object
     */
    public <F extends Object, T extends Object> void calculate(F source, T target);
}
