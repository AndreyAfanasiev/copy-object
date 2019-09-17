/**
 *
 */
package ru.aafanasiev.util.copier.test;

import ru.aafanasiev.util.copier.converter.base.ConverterKey;
import ru.aafanasiev.util.copier.converter.base.CopyConverter;
import ru.aafanasiev.util.copier.converter.base.CopyEntry;

/**
 * @author aafanasyev
 */

public class TestCopyConverter2 implements CopyConverter {

    @Override
    public Object convert(CopyEntry entry, Object value) {
        return null;
    }

    @Override
    public ConverterKey[] listConvertedPairs() {
        return null;
    }
}
