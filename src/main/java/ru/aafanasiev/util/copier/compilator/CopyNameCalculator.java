/**
 *
 */
package ru.aafanasiev.util.copier.compilator;

import ru.aafanasiev.util.copier.converter.base.CopyEntry;

/**
 * Calculate setter name to method for getter name. <br>
 * This method defines root classes for calculation.
 *
 * @author <a href="mailto:aafanasyev@umail.ru">Andrey Afanasiev</a>
 */
public interface CopyNameCalculator {
    /**
     * Calculate to name and set it to entry<br>
     * This method must to save setter method into toMethod field in CopyEntry
     *
     * @param entry Copy operation definition
     * @return <code>true</code> - process this method<br>
     *         <code>false</code> - skip this method
     */
    boolean calculateToName(CopyEntry entry);

    /**
     * List of root methods fo this calculator
     *
     * @return Classes list
     */
    Class<?>[] returnClasses();
}
