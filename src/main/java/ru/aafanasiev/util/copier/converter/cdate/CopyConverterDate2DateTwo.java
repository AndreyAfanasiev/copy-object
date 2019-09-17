/**
 *
 */
package ru.aafanasiev.util.copier.converter.cdate;

import ru.aafanasiev.util.copier.converter.base.CopyConverter;
import ru.aafanasiev.util.copier.converter.base.CopyEntry;

/**
 * Base class for Date to Date conversion. Convert source Date type to destination Date
 * type.
 *
 * @author aafanasyev
 */
public abstract class CopyConverterDate2DateTwo<T extends java.util.Date>
        implements CopyConverter {

    /*
     * (non-Javadoc)
     * @see ru.aafanasiev.util.copier.CopyConverter#convert(ru.aafanasiev.util.copier.
     * CopyEntry, java.lang.Object)
     */
    @Override
    public Object convert(CopyEntry entry, Object value) {
        if(null != value && compare(value)) {
            return create(value);
        }

        return null;
    }

    /**
     * Clone create method
     *
     * @param value Source object
     * @return Target object
     */
    protected abstract T create(Object value);

    /**
     * Compare object type
     *
     * @param value Source value
     * @return <code>true</code> - valid type<br>
     *         <code>true</code> - invalid type
     */
    protected abstract boolean compare(Object value);
}
