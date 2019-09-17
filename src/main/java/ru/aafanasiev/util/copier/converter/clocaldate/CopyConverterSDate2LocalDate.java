/**
 *
 */
package ru.aafanasiev.util.copier.converter.clocaldate;

import java.sql.Date;
import java.time.LocalDate;

import ru.aafanasiev.annotations.Converter;
import ru.aafanasiev.util.copier.converter.base.ConverterKey;
import ru.aafanasiev.util.copier.converter.base.CopyConverter;
import ru.aafanasiev.util.copier.converter.base.CopyEntry;

/**
 * @author aafanasyev
 */
@Converter
public class CopyConverterSDate2LocalDate implements CopyConverter {

    /*
     * (non-Javadoc)
     * @see ru.aafanasiev.util.copier.converter.base.CopyConverter#convert(ru.aafanasiev.util.copier.converter.base.
     * CopyEntry, java.lang.Object)
     */
    @Override
    public Object convert(CopyEntry entry, Object value) {
        if(null != value && Date.class.equals(value.getClass())) {
            return ((Date) value).toLocalDate();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * @see ru.aafanasiev.util.copier.converter.base.CopyConverter#listConvertedPairs()
     */
    @Override
    public ConverterKey[] listConvertedPairs() {
        return new ConverterKey[] {new ConverterKey(Date.class, LocalDate.class)};
    }

}
