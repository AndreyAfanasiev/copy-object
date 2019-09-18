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
public class CopyConverterStr2DoubleTest extends CopyConverterStrBase {
    @BeforeMethod
    public void intitialize() {
        convertor = new CopyConverterStr2Double();
        init();
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.cint.CopyConverterInt2Long#convert(ru.aafanasiev.util.copier.converter.base.CopyEntry, java.lang.Object)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testConvertOk() throws Exception {
        Double result = (Double) convertor.convert(entry, "0");
        assertEquals(result.doubleValue(), 0.0D);

        result = (Double) convertor.convert(entry, " 1 ");
        assertEquals(result.doubleValue(), 1D);

        result = (Double) convertor.convert(entry, String.valueOf(Double.MAX_VALUE));
        assertEquals(result.doubleValue(), Double.MAX_VALUE);

        result = (Double) convertor.convert(entry, String.valueOf(Double.MIN_VALUE));
        assertEquals(result.doubleValue(), Double.MIN_VALUE);

        result = (Double) convertor.convert(entry, "1e+2");
        assertEquals(result.doubleValue(), 1.0e+2D);

        result = (Double) convertor.convert(entry, "4e-24");
        assertEquals(result.doubleValue(), 4e-24D);

        result = (Double) convertor.convert(entry, "1.2D");
        assertEquals(result.doubleValue(), 1.2D);

        result = (Double) convertor.convert(entry, "0x1p+10D");
        assertEquals(result.doubleValue(), 1024D);
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
     * Test method for {@link ru.aafanasiev.util.copier.converter.cint.CopyConverterInt2Long#listConvertedPairs()}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testListConvertedPairs() throws Exception {
        ConverterKey[] array = convertor.listConvertedPairs();
        assertEquals(array.length, 1);
        assertEquals(array[0].getFromClazz(), String.class);
        assertEquals(array[0].getToClazz(), Double.class);
    }
}
