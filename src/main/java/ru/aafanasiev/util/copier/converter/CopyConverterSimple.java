/**
 *
 */
package ru.aafanasiev.util.copier.converter;

import java.math.BigDecimal;
import java.math.BigInteger;

import ru.aafanasiev.annotations.Converter;
import ru.aafanasiev.util.copier.converter.base.ConverterKey;
import ru.aafanasiev.util.copier.converter.base.CopyConverter;
import ru.aafanasiev.util.copier.converter.base.CopyEntry;

/**
 * Converter for primitive types and wrap classes
 *
 * @author <a href="mailto:aafanasyev@umail.ru">Andrey Afanasiev</a>
 */
@Converter
public class CopyConverterSimple implements CopyConverter {

    /*
     * (non-Javadoc)
     * @see ru.aafanasiev.util.copier.CopyConverter#convert(ru.aafanasiev.util.copier. CopyEntry, java.lang.Object)
     */
    @Override
    public Object convert(CopyEntry entry, Object value) {
        return value;
    }

    /*
     * (non-Javadoc)
     * @see ru.aafanasiev.util.copier.CopyConverter#listConvertedPairs()
     */
    @Override
    public ConverterKey[] listConvertedPairs() {
        return new ConverterKey[] {
                new ConverterKey(String.class, String.class),
                new ConverterKey(Boolean.class, Boolean.class),
                new ConverterKey(Integer.class, Integer.class),
                new ConverterKey(Long.class, Long.class),
                new ConverterKey(Long.class, Comparable.class), // Заглушка для нормального тестирования bux
                new ConverterKey(Float.class, Float.class),
                new ConverterKey(Double.class, Double.class),
                new ConverterKey(BigDecimal.class, BigDecimal.class),
                new ConverterKey(BigInteger.class, BigInteger.class),
                new ConverterKey(Comparable.class, Comparable.class),
                new ConverterKey(String.class, Comparable.class),
                new ConverterKey(Comparable.class, String.class)
        };
    }
}
