/**
 *
 */
package ru.aafanasiev.util.copier.converter.cdouble;

import static org.testng.Assert.assertEquals;

import java.math.BigDecimal;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.aafanasiev.util.copier.converter.base.ConverterKey;

/**
 * @author aafanasyev
 */
@Test(groups = {"UTIL"}, ignoreMissingDependencies = true)
public class CopyConverterDouble2BigDecimalTest extends CopyConverterDoubleBase {
    @BeforeMethod
    public void intitialize() {
        convertor = new CopyConverterDouble2BigDecimal();
        init();
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.cint.CopyConverterInt2Long#convert(ru.aafanasiev.util.copier.converter.base.CopyEntry, java.lang.Object)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testConvert() throws Exception {
        BigDecimal result = (BigDecimal) convertor.convert(entry, 0.0D);
        assertEquals(result, BigDecimal.valueOf(0.0D));

        result = (BigDecimal) convertor.convert(entry, Double.valueOf(1.0D));
        assertEquals(result, BigDecimal.valueOf(1.0D));

    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.cdouble.CopyConverterDouble2BigDecimal#listConvertedPairs()
     * listConvertedPairs}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testListConvertedPairs() throws Exception {
        ConverterKey[] array = convertor.listConvertedPairs();
        assertEquals(array.length, 1);
        assertEquals(array[0].getFromClazz(), Double.class);
        assertEquals(array[0].getToClazz(), BigDecimal.class);
    }
}
