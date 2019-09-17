/**
 *
 */
package ru.aafanasiev.util.copier.converter.cint;

import static org.testng.Assert.assertEquals;

import java.math.BigDecimal;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.aafanasiev.util.copier.converter.base.ConverterKey;
import ru.aafanasiev.util.copier.converter.base.CopyEntry;

/**
 * @author aafanasyev
 */
@Test(groups = {"UTIL"}, ignoreMissingDependencies = true)
public class CopyConverterInt2BigDecimalTest extends CopyConverterIntBase {
    @BeforeMethod
    public void intitialize() {
        convertor = new CopyConverterInt2BigDecimal();
        entry = new CopyEntry();
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.cint.CopyConverterInt2Long#convert(ru.aafanasiev.util.copier.converter.base.CopyEntry, java.lang.Object)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testConvert() throws Exception {
        BigDecimal result = (BigDecimal) convertor.convert(entry, 0);
        assertEquals(result, BigDecimal.valueOf(0));

        result = (BigDecimal) convertor.convert(entry, Integer.valueOf(1));
        assertEquals(result, BigDecimal.valueOf(1));

        result = (BigDecimal) convertor.convert(entry, Integer.valueOf(Integer.MAX_VALUE));
        assertEquals(result, BigDecimal.valueOf(Integer.MAX_VALUE));

        result = (BigDecimal) convertor.convert(entry, Integer.valueOf(Integer.MIN_VALUE));
        assertEquals(result, BigDecimal.valueOf(Integer.MIN_VALUE));
    }

    /**
     * Test method for {@link ru.aafanasiev.util.copier.converter.cint.CopyConverterInt2Long#listConvertedPairs()}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testListConvertedPairs() throws Exception {
        ConverterKey[] array = convertor.listConvertedPairs();
        assertEquals(array.length, 1);
        assertEquals(array[0].getFromClazz(), Integer.class);
        assertEquals(array[0].getToClazz(), BigDecimal.class);
    }
}
