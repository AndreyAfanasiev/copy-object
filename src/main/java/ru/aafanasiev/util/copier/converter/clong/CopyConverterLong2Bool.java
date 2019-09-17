/**
 *
 */
package ru.aafanasiev.util.copier.converter.clong;

import ru.aafanasiev.annotations.Converter;
import ru.aafanasiev.util.copier.converter.base.ConverterKey;
import ru.aafanasiev.util.copier.converter.base.CopyConverter;
import ru.aafanasiev.util.copier.converter.base.CopyEntry;

/**
 * Convert from Integer To Boolean
 *
 * @author aafanasyev
 */
@Converter
public class CopyConverterLong2Bool implements CopyConverter {
    /*
     * (non-Javadoc)
     * @see ru.aafanasiev.util.copier.CopyConverter#convert(ru.aafanasiev.util.copier. CopyEntry, java.lang.Object)
     */
    @Override
    public Object convert(CopyEntry entry, Object value) {
        if(null != value && Long.class.equals(value.getClass())) {
            if(0L == ((Long) value).longValue()) {
                return Boolean.FALSE;
            } else {
                return Boolean.TRUE;
            }
        } else {
            return null;
        }
    }

    /*
     * (non-Javadoc)
     * @see ru.aafanasiev.util.copier.CopyConverter#listConvertedPairs()
     */
    @Override
    public ConverterKey[] listConvertedPairs() {
        return new ConverterKey[] {new ConverterKey(Long.class, Boolean.class)};
    }
}
