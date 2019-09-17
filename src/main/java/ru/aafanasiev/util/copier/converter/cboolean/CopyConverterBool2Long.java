/**
 *
 */
package ru.aafanasiev.util.copier.converter.cboolean;

import ru.aafanasiev.annotations.Converter;
import ru.aafanasiev.util.copier.converter.base.ConverterKey;
import ru.aafanasiev.util.copier.converter.base.CopyConverter;
import ru.aafanasiev.util.copier.converter.base.CopyEntry;

/**
 * Convert from Long to Integer
 *
 * @author aafanasyev
 */
@Converter
public class CopyConverterBool2Long implements CopyConverter {
    private static final Long TRUE_VALUE = Long.valueOf(1);
    private static final Long FALSE_VALUE = Long.valueOf(0);

    /*
     * (non-Javadoc)
     * @see ru.aafanasiev.util.copier.CopyConverter#convert(ru.aafanasiev.util.copier. CopyEntry, java.lang.Object)
     */
    @Override
    public Object convert(CopyEntry entry, Object value) {
        if(null != value && Boolean.class.equals(value.getClass())) {
            if(((Boolean) value).booleanValue()) {
                return TRUE_VALUE;
            } else {
                return FALSE_VALUE;
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
        return new ConverterKey[] {new ConverterKey(Boolean.class, Long.class)};
    }
}
