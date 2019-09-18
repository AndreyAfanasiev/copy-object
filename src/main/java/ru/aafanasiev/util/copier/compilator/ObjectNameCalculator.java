/**
 *
 */
package ru.aafanasiev.util.copier.compilator;

import ru.aafanasiev.annotations.NameCalculator;
import ru.aafanasiev.util.copier.converter.base.CopyEntry;
import ru.aafanasiev.util.copier.util.ReflectionUtils;

/**
 * Calculate destination method name for standard objects
 *
 * @author <a href="mailto:aafanasyev@umail.ru">Andrey Afanasiev</a>
 */
@NameCalculator(0)
public class ObjectNameCalculator extends NameCalculatorBase {

    /*
     * (non-Javadoc)
     * @see
     * ru.aafanasiev.util.copier.compilator.NameCalculatorBase#calculateToName(ru.aafanasiev.util.copier.converter.base.
     * CopyEntry)
     */
    @Override
    public boolean calculateToName(CopyEntry entry) {
        if(null == entry || null == entry.getFromMethod()) {
            return false;
        }

        if(!checkNoCopy(entry)) {
            return false;
        }

        String propertyName = getPropertyName(entry);
        if(null == propertyName) {
            return false;
        }

        entry.setToName(ReflectionUtils.getSetterName(propertyName));
        return true;
    }

    /*
     * (non-Javadoc)
     * @see ru.aafanasiev.util.copier.CopyNameCalculator#listRootMethods()
     */
    @Override
    public Class<?>[] returnClasses() {
        return new Class<?>[] {AllObjectsAndInterfaces.class};
    }

}
