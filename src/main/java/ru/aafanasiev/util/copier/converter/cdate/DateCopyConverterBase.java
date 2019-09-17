/**
 *
 */
package ru.aafanasiev.util.copier.converter.cdate;

import ru.aafanasiev.annotations.CopyImmutable;
import ru.aafanasiev.util.copier.converter.base.CopyConverter;
import ru.aafanasiev.util.copier.converter.base.CopyEntry;

/**
 * @author aafanasyev
 */
public abstract class DateCopyConverterBase implements CopyConverter {

    /*
     * (non-Javadoc)
     * @see
     * ru.aafanasiev.util.copier.CopyConverter#getParameters(ru.aafanasiev.util.copier.
     * CopyEntry)
     */
    @Override
    public Object getParams(CopyEntry entry) {
        /** Date parameters object */
        DateConverterParameters result = new DateConverterParameters();

        if(entry.getFromMethod().isAnnotationPresent(CopyImmutable.class)) {
            result.setImmutable(true);
        }

        return result;
    }
}
