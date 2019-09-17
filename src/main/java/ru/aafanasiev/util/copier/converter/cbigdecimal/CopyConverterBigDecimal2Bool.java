/**
 *
 */
package ru.aafanasiev.util.copier.converter.cbigdecimal;

import java.math.BigDecimal;

import ru.aafanasiev.annotations.Converter;
import ru.aafanasiev.util.copier.converter.base.ConverterKey;
import ru.aafanasiev.util.copier.converter.base.CopyConverter;
import ru.aafanasiev.util.copier.converter.base.CopyEntry;

/**
 * Convert from BigDecimal to Boolean
 *
 * @author <a href="mailto:aafanasyev@umail.ru">Andrey Afanasiev</a>
 */
@Converter
public class CopyConverterBigDecimal2Bool implements CopyConverter {

    /*
     * (non-Javadoc)
     * @see ru.aafanasiev.util.copier.CopyConverter#convert(ru.aafanasiev.util.copier. CopyEntry, java.lang.Object)
     */
    @Override
    public Object convert(CopyEntry entry, Object value) {
        if(null != value && BigDecimal.class.equals(value.getClass())) {
            return 0 == ((BigDecimal) value).intValue() ? Boolean.FALSE : Boolean.TRUE;
        }

        return null;
    }

    /*
     * (non-Javadoc)
     * @see ru.aafanasiev.util.copier.CopyConverter#listConvertedPairs()
     */
    @Override
    public ConverterKey[] listConvertedPairs() {
        return new ConverterKey[] {new ConverterKey(BigDecimal.class, Boolean.class)};
    }
}
