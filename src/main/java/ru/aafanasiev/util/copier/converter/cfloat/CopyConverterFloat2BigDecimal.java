/**
 *
 */
package ru.aafanasiev.util.copier.converter.cfloat;

import java.math.BigDecimal;

import ru.aafanasiev.annotations.Converter;
import ru.aafanasiev.util.copier.converter.base.ConverterKey;
import ru.aafanasiev.util.copier.converter.base.CopyConverter;
import ru.aafanasiev.util.copier.converter.base.CopyEntry;

/**
 * Convert from Float to BigDecimal
 *
 * @author aafanasyev
 */
@Converter
public class CopyConverterFloat2BigDecimal implements CopyConverter {

    /*
     * (non-Javadoc)
     * @see ru.aafanasiev.util.copier.CopyConverter#convert(ru.aafanasiev.util.copier. CopyEntry, java.lang.Object)
     */
    @Override
    public Object convert(CopyEntry entry, Object value) {
        if(null != value && Float.class.equals(value.getClass())) {
            return BigDecimal.valueOf(((Float) value).doubleValue());
        }

        return null;
    }

    /*
     * (non-Javadoc)
     * @see ru.aafanasiev.util.copier.CopyConverter#listConvertedPairs()
     */
    @Override
    public ConverterKey[] listConvertedPairs() {
        return new ConverterKey[] {new ConverterKey(Float.class, BigDecimal.class)};
    }

}
