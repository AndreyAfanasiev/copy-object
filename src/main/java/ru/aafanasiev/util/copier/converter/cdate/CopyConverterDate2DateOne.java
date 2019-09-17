/**
 *
 */
package ru.aafanasiev.util.copier.converter.cdate;

import ru.aafanasiev.util.copier.converter.base.CopyConverter;
import ru.aafanasiev.util.copier.converter.base.CopyEntry;

/**
 * l Base class for Date to Date conversion. Convert one Date type.
 *
 * @author aafanasyev
 */
public abstract class CopyConverterDate2DateOne<T extends java.util.Date>
        extends DateCopyConverterBase
        implements CopyConverter {

    /*
     * (non-Javadoc)
     * @see ru.aafanasiev.util.copier.CopyConverter#convert(ru.aafanasiev.util.copier.
     * CopyEntry, java.lang.Object)
     */
    @Override
    public Object convert(CopyEntry entry, Object value) {
        if(null != value && compare(value)) {
            if(((DateConverterParameters) entry.getParameters()).isImmutable()) {
                return create(value);
            } else {
                return value;
            }
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
