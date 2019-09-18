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
public class CopyConverterDouble2StrTest extends CopyConverterDoubleBase {
    @BeforeMethod
    public void intitialize() {
        convertor = new CopyConverterDouble2Str();
        init();
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.cint.CopyConverterInt2Long#convert(ru.aafanasiev.util.copier.converter.base.CopyEntry, java.lang.Object)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testConvertOk() throws Exception {
        String result = (String) convertor.convert(entry, Double.valueOf(1.0F));
        assertEquals(result, Double.valueOf(1.0F).toString());

        result = (String) convertor.convert(entry, Double.NaN);
        assertEquals(result, Double.valueOf(Double.NaN).toString());

        result = (String) convertor.convert(entry, Double.POSITIVE_INFINITY);
        assertEquals(result, Double.valueOf(Double.POSITIVE_INFINITY).toString());

        result = (String) convertor.convert(entry, Double.NEGATIVE_INFINITY);
        assertEquals(result, Double.valueOf(Double.NEGATIVE_INFINITY).toString());

        result = (String) convertor.convert(entry, Double.MAX_VALUE);
        assertEquals(result, Double.valueOf(Double.MAX_VALUE).toString());

        result = (String) convertor.convert(entry, Double.MAX_VALUE);
        assertEquals(result, Double.valueOf(Double.MAX_VALUE).toString());
    }

    /**
     * Test method for {@link ru.aafanasiev.util.copier.converter.cdouble.CopyConverterDouble2Str#listConvertedPairs()
     * listConvertedPairs}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testListConvertedPairs() throws Exception {
        ConverterKey[] array = convertor.listConvertedPairs();
        assertEquals(array.length, 1);
        assertEquals(array[0].getFromClazz(), Double.class);
        assertEquals(array[0].getToClazz(), String.class);
    }
}
