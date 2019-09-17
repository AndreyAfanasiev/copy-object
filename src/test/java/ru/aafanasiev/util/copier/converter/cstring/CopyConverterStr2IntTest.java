/**
 *
 */
package ru.aafanasiev.util.copier.converter.cstring;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.aafanasiev.util.copier.converter.base.ConverterKey;
import ru.aafanasiev.util.copier.converter.base.CopyEntry;
import ru.aafanasiev.util.copier.converter.cstring.CopyConverterStr2Int;

/**
 * @author aafanasyev
 */
@Test(groups = {"UTIL"}, ignoreMissingDependencies = true)
public class CopyConverterStr2IntTest extends CopyConverterStrBase {
    @BeforeMethod
    public void intitialize() {
        convertor = new CopyConverterStr2Int();
        entry = new CopyEntry();
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.cint.CopyConverterInt2Long#convert(ru.aafanasiev.util.copier.converter.base.CopyEntry, java.lang.Object)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true, expectedExceptions = {
            NumberFormatException.class})
    public void testConvertBad02() throws Exception {
        convertor.convert(entry, " 123_456");
        assertTrue(false);
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.cint.CopyConverterInt2Long#convert(ru.aafanasiev.util.copier.converter.base.CopyEntry, java.lang.Object)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true, expectedExceptions = {
            NumberFormatException.class})
    public void testConvertBad03() throws Exception {
        convertor.convert(entry, "123 456");
        assertTrue(false);
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.CopyConverterInt2Long#(ru.aafanasiev.util.copier.CopyEntry,
     * java.lang.Object)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testConvertOk() throws Exception {
        Integer result = (Integer) convertor.convert(entry, "0");
        assertEquals(result.intValue(), 0);

        result = (Integer) convertor.convert(entry, " 1 ");
        assertEquals(result.intValue(), 1);

        result = (Integer) convertor.convert(entry, String.valueOf(Integer.MAX_VALUE));
        assertEquals(result.intValue(), Integer.MAX_VALUE);

        result = (Integer) convertor.convert(entry, String.valueOf(Integer.MIN_VALUE));
        assertEquals(result.intValue(), Integer.MIN_VALUE);
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.cint.CopyConverterInt2Long#listConvertedPairs()}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testListConvertedPairs() throws Exception {
        ConverterKey[] array = convertor.listConvertedPairs();
        assertEquals(array.length, 1);
        assertEquals(array[0].getFromClazz(), String.class);
        assertEquals(array[0].getToClazz(), Integer.class);
    }
}
