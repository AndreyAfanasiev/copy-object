/**
 *
 */
package ru.aafanasiev.util.copier.converter.cfloat;

import static org.testng.Assert.assertNull;

import org.testng.annotations.Test;

import ru.aafanasiev.util.copier.converter.base.CopyConverter;
import ru.aafanasiev.util.copier.converter.base.CopyEntry;
import ru.aafanasiev.util.copier.converter.base.CopyEntryImpl;

/**
 * @author aafanasyev
 */
public class CopyConverterFloatBase {
    protected CopyConverter convertor;
    protected CopyEntry entry;

    protected void init() {
        entry = new CopyEntryImpl();
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.cint.CopyConverterInt2Long#convert(ru.aafanasiev.util.copier.converter.base.CopyEntry, java.lang.Object)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testConvertBad() throws Exception {
        Object result = convertor.convert(entry, null);
        assertNull(result);

        result = convertor.convert(entry, Double.valueOf(1));
        assertNull(result);
    }
}
