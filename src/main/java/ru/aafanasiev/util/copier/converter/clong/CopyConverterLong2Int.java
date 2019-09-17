/**
 *
 */
package ru.aafanasiev.util.copier.converter.clong;

import ru.aafanasiev.annotations.Converter;
import ru.aafanasiev.util.copier.converter.base.ConverterKey;
import ru.aafanasiev.util.copier.converter.base.CopyConverter;
import ru.aafanasiev.util.copier.converter.base.CopyEntry;
import ru.aafanasiev.util.copier.converter.base.NumberConvertException;

/**
 * Convert from Long to Integer
 *
 * @author aafanasyev
 */
@Converter
public class CopyConverterLong2Int implements CopyConverter {

    /*
     * (non-Javadoc)
     * @see ru.aafanasiev.util.copier.CopyConverter#convert(ru.aafanasiev.util.copier. CopyEntry, java.lang.Object)
     */
    @Override
    public Object convert(CopyEntry entry, Object value) {
        if(null != value && Long.class.equals(value.getClass())) {
            if((Integer.MAX_VALUE < ((Long) value).longValue())
                    || (Integer.MIN_VALUE > ((Long) value).longValue())) {
                throw new NumberConvertException("Long can not convert to integer. Tooo big.");
            }
            return Integer.valueOf(((Long) value).intValue());
        }

        return null;
    }

    /*
     * (non-Javadoc)
     * @see ru.aafanasiev.util.copier.CopyConverter#listConvertedPairs()
     */
    @Override
    public ConverterKey[] listConvertedPairs() {
        return new ConverterKey[] {new ConverterKey(Long.class, Integer.class)};
    }
}
