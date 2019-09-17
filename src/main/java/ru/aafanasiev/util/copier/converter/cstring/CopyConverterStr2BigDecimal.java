/**
 *
 */
package ru.aafanasiev.util.copier.converter.cstring;

import java.math.BigDecimal;

import ru.aafanasiev.annotations.Converter;
import ru.aafanasiev.util.copier.converter.base.ConverterKey;
import ru.aafanasiev.util.copier.converter.base.CopyConverter;
import ru.aafanasiev.util.copier.converter.base.CopyEntry;

/**
 * Convert String to BigDecimal
 *
 * @author aafanasyev
 */
@Converter
public class CopyConverterStr2BigDecimal implements CopyConverter {

    /*
     * (non-Javadoc)
     * @see ru.aafanasiev.util.copier.CopyConverter#convert(ru.aafanasiev.util.copier. CopyEntry, java.lang.Object)
     */
    @Override
    public Object convert(CopyEntry entry, Object value) {
        if(null != value && String.class.equals(value.getClass())) {
            String work = ((String) value).trim();
            return new BigDecimal(work);
        }

        return null;
    }

    /*
     * (non-Javadoc)
     * @see ru.aafanasiev.util.copier.CopyConverter#listConvertedPairs()
     */
    @Override
    public ConverterKey[] listConvertedPairs() {
        return new ConverterKey[] {new ConverterKey(String.class, BigDecimal.class)};
    }
}
