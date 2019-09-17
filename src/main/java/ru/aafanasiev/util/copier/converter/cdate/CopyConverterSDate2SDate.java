/**
 *
 */
package ru.aafanasiev.util.copier.converter.cdate;

import java.sql.Date;

import ru.aafanasiev.annotations.Converter;
import ru.aafanasiev.util.copier.converter.base.ConverterKey;

/**
 * Convert from java.sql.Date to java.sql.Date
 *
 * @author aafanasyev
 */
@Converter
public class CopyConverterSDate2SDate extends CopyConverterDate2DateOne<Date> {
    /*
     * (non-Javadoc)
     * @see ru.aafanasiev.util.copier.CopyConverter#listConvertedPairs()
     */
    @Override
    public ConverterKey[] listConvertedPairs() {
        return new ConverterKey[] {new ConverterKey(java.sql.Date.class, java.sql.Date.class)};
    }

    @Override
    protected boolean compare(Object value) {
        return java.sql.Date.class.equals(value.getClass());
    }

    @Override
    protected Date create(Object value) {
        return new java.sql.Date(((Date) value).getTime());
    }
}
