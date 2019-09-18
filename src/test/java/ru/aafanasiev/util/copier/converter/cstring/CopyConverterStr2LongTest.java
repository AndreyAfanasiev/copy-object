/**
 *
 */
package ru.aafanasiev.util.copier.converter.cstring;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.aafanasiev.util.copier.converter.base.ConverterKey;

/**
 * @author aafanasyev
 */
@Test(groups = {"UTIL"}, ignoreMissingDependencies = true)
public class CopyConverterStr2LongTest extends CopyConverterStrBase {
    @BeforeMethod
    public void intitialize() {
        convertor = new CopyConverterStr2Long();
        init();
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.cint.CopyConverterInt2Long#convert(ru.aafanasiev.util.copier.converter.base.CopyEntry, java.lang.Object)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testConvertOk() throws Exception {
        Long result = (Long) convertor.convert(entry, "0");
        assertEquals(result.longValue(), 0);

        result = (Long) convertor.convert(entry, " 1 ");
        assertEquals(result.longValue(), 1);

        result = (Long) convertor.convert(entry, String.valueOf(Long.MAX_VALUE));
        assertEquals(result.longValue(), Long.MAX_VALUE);

        result = (Long) convertor.convert(entry, String.valueOf(Long.MIN_VALUE));
        assertEquals(result.longValue(), Long.MIN_VALUE);
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
     * {@link ru.aafanasiev.util.copier.converter.cint.CopyConverterInt2Long#listConvertedPairs()}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testListConvertedPairs() throws Exception {
        ConverterKey[] array = convertor.listConvertedPairs();
        assertEquals(array.length, 1);
        assertEquals(array[0].getFromClazz(), String.class);
        assertEquals(array[0].getToClazz(), Long.class);
    }
}
