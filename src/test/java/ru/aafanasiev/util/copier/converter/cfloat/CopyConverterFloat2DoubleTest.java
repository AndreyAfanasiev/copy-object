/**
 *
 */
package ru.aafanasiev.util.copier.converter.cfloat;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.aafanasiev.util.copier.converter.base.ConverterKey;

/**
 * @author aafanasyev
 */
@Test(groups = {"UTIL"}, ignoreMissingDependencies = true)
public class CopyConverterFloat2DoubleTest extends CopyConverterFloatBase {
    @BeforeMethod
    public void intitialize() {
        convertor = new CopyConverterFloat2Double();
        init();
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.cint.CopyConverterInt2Long#convert(ru.aafanasiev.util.copier.converter.base.CopyEntry, java.lang.Object)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testConvertOk() throws Exception {
        Double result = (Double) convertor.convert(entry, 0.0F);
        assertEquals(result, Double.valueOf(0.0D));

        result = (Double) convertor.convert(entry, Float.valueOf(1.0F));
        assertEquals(result, Double.valueOf(1.0D));

        result = (Double) convertor.convert(entry, Float.NaN);
        assertEquals(result, Double.NaN);

        result = (Double) convertor.convert(entry, Float.POSITIVE_INFINITY);
        assertEquals(result, Double.POSITIVE_INFINITY);

        result = (Double) convertor.convert(entry, Float.NEGATIVE_INFINITY);
        assertEquals(result, Double.NEGATIVE_INFINITY);

        result = (Double) convertor.convert(entry, Float.MAX_VALUE);
        assertEquals(result, Double.valueOf(Float.MAX_VALUE));

        result = (Double) convertor.convert(entry, Float.MIN_NORMAL);
        assertEquals(result, Double.valueOf(Float.MIN_NORMAL));
    }

    /**
     * Test method for {@link ru.aafanasiev.util.copier.converter.cfloat.CopyConverterFloat2Double#listConvertedPairs()
     * listConvertedPairs}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testListConvertedPairs() throws Exception {
        ConverterKey[] array = convertor.listConvertedPairs();
        assertEquals(array.length, 1);
        assertEquals(array[0].getFromClazz(), Float.class);
        assertEquals(array[0].getToClazz(), Double.class);
    }
}
