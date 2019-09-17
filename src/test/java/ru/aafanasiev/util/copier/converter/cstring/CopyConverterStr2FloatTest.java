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
import ru.aafanasiev.util.copier.converter.cstring.CopyConverterStr2Float;

/**
 * @author aafanasyev
 */
@Test(groups = {"UTIL"}, ignoreMissingDependencies = true)
public class CopyConverterStr2FloatTest extends CopyConverterStrBase {
    @BeforeMethod
    public void intitialize() {
        convertor = new CopyConverterStr2Float();
        entry = new CopyEntry();
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.cint.CopyConverterInt2Long#convert(ru.aafanasiev.util.copier.converter.base.CopyEntry, java.lang.Object)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testConvertOk() throws Exception {
        Float result = (Float) convertor.convert(entry, "0");
        assertEquals(result.floatValue(), 0F);

        result = (Float) convertor.convert(entry, " 1 ");
        assertEquals(result.floatValue(), 1F);

        result = (Float) convertor.convert(entry, String.valueOf(Float.MAX_VALUE));
        assertEquals(result.floatValue(), Float.MAX_VALUE);

        result = (Float) convertor.convert(entry, String.valueOf(Float.MIN_VALUE));
        assertEquals(result.floatValue(), Float.MIN_VALUE);

        result = (Float) convertor.convert(entry, "1e+2");
        assertEquals(result.floatValue(), 1.0e+2F);

        result = (Float) convertor.convert(entry, "4e-24");
        assertEquals(result.floatValue(), 4e-24F);

        result = (Float) convertor.convert(entry, "1.2f");
        assertEquals(result.floatValue(), 1.2F);

        result = (Float) convertor.convert(entry, "0x1p+10");
        assertEquals(result.floatValue(), 1024F);
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
     * {@link ru.aafanasiev.util.copier.converter.cint.CopyConverterInt2Long#convert(ru.aafanasiev.util.copier.converter.base.CopyEntry, java.lang.Object)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true, expectedExceptions = {
            NumberFormatException.class})
    public void testConvertBad04() throws Exception {
        convertor.convert(entry, "1p+10");
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
        assertEquals(array[0].getToClazz(), Float.class);
    }
}
