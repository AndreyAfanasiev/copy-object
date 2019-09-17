/**
 *
 */
package ru.aafanasiev.util.copier.converter.cdate;

import ru.aafanasiev.annotations.Converter;
import ru.aafanasiev.util.copier.converter.base.ConverterKey;
import ru.aafanasiev.util.copier.converter.base.CopyConverter;

/**
 * Convert from java.util.Date to java.sql.Date
 *
 * @author aafanasyev
 */
@Converter
public class CopyConverterUDate2SDate extends CopyConverterDate2DateTwo<java.sql.Date>
        implements CopyConverter {
    /*
     * (non-Javadoc)
     * @see ru.aafanasiev.util.copier.CopyConverter#listConvertedPairs()
     */
    @Override
    public ConverterKey[] listConvertedPairs() {
        return new ConverterKey[] {new ConverterKey(java.util.Date.class, java.sql.Date.class)};
    }

    /*
     * (non-Javadoc)
     * @see ru.aafanasiev.util.copier.converter.CopyConverterDate2DateOne#create(java.lang. Object)
     */
    @Override
    protected java.sql.Date create(Object value) {
        return new java.sql.Date(((java.util.Date) value).getTime());
    }

    /*
     * (non-Javadoc)
     * @see ru.aafanasiev.util.copier.converter.CopyConverterDate2DateOne#compare(java.lang. Object)
     */
    @Override
    protected boolean compare(Object value) {
        return java.util.Date.class.equals(value.getClass());
    }
}
