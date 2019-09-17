/**
 *
 */
package ru.aafanasiev.util.copier.test;

import ru.aafanasiev.annotations.Converter;
import ru.aafanasiev.util.copier.converter.base.ConverterKey;
import ru.aafanasiev.util.copier.converter.base.CopyEntry;

/**
 * @author aafanasyev
 */
@Converter
public class TestCopyConverter3 {

    public Object convert(CopyEntry entry, Object value) {
        return null;
    }

    public ConverterKey[] listConvertedPairs() {
        return null;
    }
}
