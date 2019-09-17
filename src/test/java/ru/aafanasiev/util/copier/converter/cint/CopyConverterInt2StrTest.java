/**
 *
 */
package ru.aafanasiev.util.copier.converter.cint;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.aafanasiev.util.copier.converter.base.ConverterKey;
import ru.aafanasiev.util.copier.converter.base.CopyEntry;

/**
 * @author aafanasyev
 */
@Test(groups = {"UTIL"}, ignoreMissingDependencies = true)
public class CopyConverterInt2StrTest extends CopyConverterIntBase {
    @BeforeMethod
    public void intitialize() {
        convertor = new CopyConverterInt2Str();
        entry = new CopyEntry();
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.cint.CopyConverterInt2Long#convert(ru.aafanasiev.util.copier.converter.base.CopyEntry, java.lang.Object)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testConvertOk() throws Exception {
        String result = (String) convertor.convert(entry, 0);
        assertEquals(result, "0");

        result = (String) convertor.convert(entry, Integer.valueOf(1));
        assertEquals(result, "1");

        result = (String) convertor.convert(entry, Integer.valueOf(Integer.MAX_VALUE));
        assertEquals(result, String.valueOf(Integer.MAX_VALUE));

        result = (String) convertor.convert(entry, Integer.valueOf(Integer.MIN_VALUE));
        assertEquals(result, String.valueOf(Integer.MIN_VALUE));
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.cint.CopyConverterInt2Long#listConvertedPairs()}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testListConvertedPairs() throws Exception {
        ConverterKey[] array = convertor.listConvertedPairs();
        assertEquals(array.length, 1);
        assertEquals(array[0].getFromClazz(), Integer.class);
        assertEquals(array[0].getToClazz(), String.class);
    }
}
