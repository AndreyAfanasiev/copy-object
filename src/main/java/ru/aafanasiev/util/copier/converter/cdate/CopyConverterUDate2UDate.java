/**
 *
 */
package ru.aafanasiev.util.copier.converter.cdate;

import java.util.Date;

import ru.aafanasiev.annotations.Converter;
import ru.aafanasiev.util.copier.converter.base.ConverterKey;

/**
 * Convert from java.util.Date to java.util.Date
 *
 * @author aafanasyev
 */
@Converter
public class CopyConverterUDate2UDate extends CopyConverterDate2DateOne<java.util.Date> {

    /*
     * (non-Javadoc)
     * @see ru.aafanasiev.util.copier.CopyConverter#listConvertedPairs()
     */
    @Override
    public ConverterKey[] listConvertedPairs() {
        return new ConverterKey[] {new ConverterKey(java.util.Date.class, java.util.Date.class)};
    }

    @Override
    protected boolean compare(Object value) {
        return java.util.Date.class.equals(value.getClass());
    }

    @Override
    protected Date create(Object value) {
        return new java.util.Date(((Date) value).getTime());
    }
}
