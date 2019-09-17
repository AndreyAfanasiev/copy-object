/**
 *
 */
package ru.aafanasiev.util.copier.converter.cdate;

import ru.aafanasiev.annotations.Converter;
import ru.aafanasiev.util.copier.converter.base.ConverterKey;
import ru.aafanasiev.util.copier.converter.base.CopyConverter;

/**
 * Convert from java.sql.Date to java.util.Date
 *
 * @author aafanasyev
 */
@Converter
public class CopyConverterSDate2UDate extends CopyConverterDate2DateTwo<java.util.Date>
        implements CopyConverter {

    /*
     * (non-Javadoc)
     * @see ru.aafanasiev.util.copier.CopyConverter#listConvertedPairs()
     */
    @Override
    public ConverterKey[] listConvertedPairs() {
        return new ConverterKey[] {new ConverterKey(java.sql.Date.class, java.util.Date.class)};
    }

    @Override
    protected boolean compare(Object value) {
        return java.sql.Date.class.equals(value.getClass());
    }

    @Override
    protected java.util.Date create(Object value) {
        return new java.util.Date(((java.sql.Date) value).getTime());
    }
}
