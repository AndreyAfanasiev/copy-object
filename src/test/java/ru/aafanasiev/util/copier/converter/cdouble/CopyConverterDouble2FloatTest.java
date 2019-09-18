/**
 *
 */
package ru.aafanasiev.util.copier.converter.cdouble;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.aafanasiev.util.copier.converter.base.ConverterKey;

/**
 * @author aafanasyev
 */
@Test(groups = {"UTIL"}, ignoreMissingDependencies = true)
public class CopyConverterDouble2FloatTest extends CopyConverterDoubleBase {
    @BeforeMethod
    public void intitialize() {
        convertor = new CopyConverterDouble2Float();
        init();
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.cint.CopyConverterInt2Long#convert(ru.aafanasiev.util.copier.converter.base.CopyEntry, java.lang.Object)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testConvertOk() throws Exception {
        Float result = (Float) convertor.convert(entry, 0.0D);
        assertEquals(result, Float.valueOf(0.0F));

        result = (Float) convertor.convert(entry, Double.valueOf(1.0D));
        assertEquals(result, Float.valueOf(1.0F));

        result = (Float) convertor.convert(entry, Double.NaN);
        assertEquals(result, Float.NaN);

        result = (Float) convertor.convert(entry, Double.POSITIVE_INFINITY);
        assertEquals(result, Float.POSITIVE_INFINITY);

        result = (Float) convertor.convert(entry, Double.NEGATIVE_INFINITY);
        assertEquals(result, Float.NEGATIVE_INFINITY);

        result = (Float) convertor.convert(entry, Double.MAX_VALUE);
        assertEquals(result, Float.valueOf(Float.POSITIVE_INFINITY));

        result = (Float) convertor.convert(entry, Double.MIN_VALUE);
        assertEquals(result, Float.valueOf(0.0F));

        result = (Float) convertor.convert(entry, Double.MIN_NORMAL);
        assertEquals(result, Float.valueOf(0.0F));
    }

    /**
     * Test method for {@link ru.aafanasiev.util.copier.converter.cdouble.CopyConverterDouble2Float#listConvertedPairs()
     * listConvertedPairs}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testListConvertedPairs() throws Exception {
        ConverterKey[] array = convertor.listConvertedPairs();
        assertEquals(array.length, 1);
        assertEquals(array[0].getFromClazz(), Double.class);
        assertEquals(array[0].getToClazz(), Float.class);
    }
}
