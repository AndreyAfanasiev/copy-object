/**
 *
 */
package ru.aafanasiev.util.copier.converter;

import ru.aafanasiev.util.copier.converter.base.CopyConverter;
import ru.aafanasiev.util.copier.converter.base.CopyEntry;
import ru.aafanasiev.util.copier.converter.base.CopyEntryImpl;

/**
 * Common Test Converter class
 * 
 * @author <a href="mailto:aafanasiev@umail.ru">Andrey Afanasiev</a>
 */
public class CopyConverterBase {
    protected CopyConverter convertor;
    protected CopyEntry entry;

    protected void init() {
        entry = new CopyEntryImpl();
    }
}
