/**
 *
 */
package ru.aafanasiev.util.copier.converter.cstring;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.math.BigDecimal;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.aafanasiev.util.copier.converter.base.ConverterKey;
import ru.aafanasiev.util.copier.converter.base.CopyEntry;
import ru.aafanasiev.util.copier.converter.cstring.CopyConverterStr2BigDecimal;

/**
 * @author aafanasyev
 */
@Test(groups = {"UTIL"}, ignoreMissingDependencies = true)
public class CopyConverterStr2BigDecimalTest extends CopyConverterStrBase {
    @BeforeMethod
    public void intitialize() {
        convertor = new CopyConverterStr2BigDecimal();
        entry = new CopyEntry();
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.cint.CopyConverterInt2BigDecimal#convert(ru.aafanasiev.util.copier.converter.base.CopyEntry, java.lang.Object)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testConvertOk01() throws Exception {
        BigDecimal result = (BigDecimal) convertor.convert(entry, "0");
        assertEquals(result, BigDecimal.valueOf(0L));

        result = (BigDecimal) convertor.convert(entry, " 1 ");
        assertEquals(result, BigDecimal.valueOf(1L));

        result = (BigDecimal) convertor.convert(entry, String.valueOf(Long.MAX_VALUE));
        assertEquals(result, BigDecimal.valueOf(Long.MAX_VALUE));

        result = (BigDecimal) convertor.convert(entry, String.valueOf(Long.MIN_VALUE));
        assertEquals(result, BigDecimal.valueOf(Long.MIN_VALUE));
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.cint.CopyConverterInt2BigDecimal#convert(ru.aafanasiev.util.copier.converter.base.CopyEntry, java.lang.Object)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testConvertOk02() throws Exception {
        BigDecimal result = (BigDecimal) convertor.convert(entry, "0.254");
        assertEquals(result, new BigDecimal("0.254"));

        result = (BigDecimal) convertor.convert(entry, " 1.45 ");
        assertEquals(result, new BigDecimal("1.45"));
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.cint.CopyConverterInt2BigDecimal#convert(ru.aafanasiev.util.copier.converter.base.CopyEntry, java.lang.Object)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true, expectedExceptions = {
            NumberFormatException.class})
    public void testConvertBad02() throws Exception {
        convertor.convert(entry, " 123_456");
        assertTrue(false);
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.cint.CopyConverterInt2BigDecimal#convert(ru.aafanasiev.util.copier.converter.base.CopyEntry, java.lang.Object)}.
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
        assertEquals(array[0].getToClazz(), BigDecimal.class);
    }
}
