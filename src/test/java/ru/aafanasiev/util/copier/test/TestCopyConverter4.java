/**
 *
 */
package ru.aafanasiev.util.copier.test;

import ru.aafanasiev.annotations.Converter;
import ru.aafanasiev.util.copier.converter.base.ConverterKey;
import ru.aafanasiev.util.copier.converter.base.CopyConverter;
import ru.aafanasiev.util.copier.converter.base.CopyEntry;

/**
 * @author aafanasyev
 */
@Converter
public class TestCopyConverter4 implements CopyConverter {
    private TestCopyConverter4() {
        super();
    }

    @Override
    public Object convert(CopyEntry entry, Object value) {
        return null;
    }

    @Override
    public ConverterKey[] listConvertedPairs() {
        return null;
    }
}
