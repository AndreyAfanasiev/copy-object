/**
 *
 */
package ru.aafanasiev.util.copier.converter.cbigdecimal;

import static org.testng.Assert.assertNull;

import org.testng.annotations.Test;

import ru.aafanasiev.util.copier.converter.base.CopyConverter;
import ru.aafanasiev.util.copier.converter.base.CopyEntry;

/**
 * @author aafanasyev
 */
public class CopyConverterBigDecimalBase {
    protected CopyConverter convertor;
    protected CopyEntry entry;

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.cint.CopyConverterInt2Long#convert(ru.aafanasiev.util.copier.converter.base.CopyEntry, java.lang.Object)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testConvertBad() throws Exception {
        Object result = convertor.convert(entry, null);
        assertNull(result);

        result = convertor.convert(entry, Long.valueOf(1));
        assertNull(result);
    }

}
