/**
 *
 */
package ru.aafanasiev.util.copier.converter.clong;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.aafanasiev.util.copier.converter.base.ConverterKey;
import ru.aafanasiev.util.copier.converter.base.CopyEntry;
import ru.aafanasiev.util.copier.converter.clong.CopyConverterLong2Str;

/**
 * @author aafanasyev
 */
@Test(groups = {"UTIL"}, ignoreMissingDependencies = true)
public class CopyConverterLong2StrTest extends CopyConverterLongBase {
    @BeforeMethod
    public void intitialize() {
        convertor = new CopyConverterLong2Str();
        entry = new CopyEntry();
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.cint.CopyConverterInt2Long#convert(ru.aafanasiev.util.copier.converter.base.CopyEntry, java.lang.Object)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testConvertOk() throws Exception {
        String result = (String) convertor.convert(entry, 0L);
        assertEquals(result, "0");

        result = (String) convertor.convert(entry, Long.valueOf(1));
        assertEquals(result, "1");

        result = (String) convertor.convert(entry, Long.valueOf(Long.MAX_VALUE));
        assertEquals(result, String.valueOf(Long.MAX_VALUE));

        result = (String) convertor.convert(entry, Long.valueOf(Long.MIN_VALUE));
        assertEquals(result, String.valueOf(Long.MIN_VALUE));
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.cint.CopyConverterInt2Long#listConvertedPairs()}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testListConvertedPairs() throws Exception {
        ConverterKey[] array = convertor.listConvertedPairs();
        assertEquals(array.length, 1);
        assertEquals(array[0].getFromClazz(), Long.class);
        assertEquals(array[0].getToClazz(), String.class);
    }
}
