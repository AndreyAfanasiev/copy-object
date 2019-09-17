/**
 *
 */
package ru.aafanasiev.util.copier.converter.cfloat;

import ru.aafanasiev.annotations.Converter;
import ru.aafanasiev.util.copier.converter.base.ConverterKey;
import ru.aafanasiev.util.copier.converter.base.CopyConverter;
import ru.aafanasiev.util.copier.converter.base.CopyEntry;

/**
 * Convert from Float to Double
 *
 * @author aafanasyev
 */
@Converter
public class CopyConverterFloat2Double implements CopyConverter {

    /*
     * (non-Javadoc)
     * @see ru.aafanasiev.util.copier.CopyConverter#convert(ru.aafanasiev.util.copier.CopyEntry, java.lang.Object)
     */
    @Override
    public Object convert(final CopyEntry entry, final Object value) {
        if(null != value && Float.class.equals(value.getClass())) {
            return Double.valueOf((Float) value);
        }

        return null;
    }

    /*
     * (non-Javadoc)
     * @see ru.aafanasiev.util.copier.CopyConverter#listConvertedPairs()
     */
    @Override
    public ConverterKey[] listConvertedPairs() {
        return new ConverterKey[] {new ConverterKey(Float.class, Double.class)};
    }

}
