/**
 *
 */
package ru.aafanasiev.util.copier.converter.cint;

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
public class CopyConverterInt2Bool implements CopyConverter {
    /*
     * (non-Javadoc)
     * @see ru.aafanasiev.util.copier.CopyConverter#convert(ru.aafanasiev.util.copier. CopyEntry, java.lang.Object)
     */
    @Override
    public Object convert(CopyEntry entry, Object value) {
        if(null != value && Integer.class.equals(value.getClass())) {
            if(0 == ((Integer) value).intValue()) {
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
        return new ConverterKey[] {new ConverterKey(Integer.class, Boolean.class)};
    }
}
