/**
 *
 */
package ru.aafanasiev.util.copier.test;

import ru.aafanasiev.util.copier.compilator.CopyNameCalculator;
import ru.aafanasiev.util.copier.converter.base.CopyEntry;

/**
 * @author aafanasyev
 */
public class TestNameCalculator2 implements CopyNameCalculator {

    /*
     * (non-Javadoc)
     * @see
     * ru.aafanasiev.util.copier.compilator.CopyNameCalculator#calculateToName(ru.aafanasiev.util.copier.converter.base.
     * CopyEntry)
     */
    @Override
    public boolean calculateToName(CopyEntry entry) {
        return false;
    }

    /*
     * (non-Javadoc)
     * @see ru.aafanasiev.util.copier.compilator.CopyNameCalculator#listRootMethods()
     */
    @Override
    public Class<?>[] returnClasses() {
        return null;
    }

}
