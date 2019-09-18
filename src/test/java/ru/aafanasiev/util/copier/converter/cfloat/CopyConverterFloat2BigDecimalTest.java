/**
 *
 */
package ru.aafanasiev.util.copier.converter.cfloat;

import static org.testng.Assert.assertEquals;

import java.math.BigDecimal;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.aafanasiev.util.copier.converter.base.ConverterKey;

/**
 * @author aafanasyev
 */
@Test(groups = {"UTIL"}, ignoreMissingDependencies = true)
public class CopyConverterFloat2BigDecimalTest extends CopyConverterFloatBase {
    @BeforeMethod
    public void intitialize() {
        convertor = new CopyConverterFloat2BigDecimal();
        init();
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.cint.CopyConverterInt2Long#convert(ru.aafanasiev.util.copier.converter.base.CopyEntry, java.lang.Object)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testConvert() throws Exception {
        BigDecimal result = (BigDecimal) convertor.convert(entry, 0.0F);
        assertEquals(result, BigDecimal.valueOf(0.0F));

        result = (BigDecimal) convertor.convert(entry, Float.valueOf(1.0F));
        assertEquals(result, BigDecimal.valueOf(1.0F));

    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.cfloat.CopyConverterFloat2BigDecimal#listConvertedPairs()
     * listConvertedPairs}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testListConvertedPairs() throws Exception {
        ConverterKey[] array = convertor.listConvertedPairs();
        assertEquals(array.length, 1);
        assertEquals(array[0].getFromClazz(), Float.class);
        assertEquals(array[0].getToClazz(), BigDecimal.class);
    }
}
