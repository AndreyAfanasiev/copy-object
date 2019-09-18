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
public class CopyConverterFloat2StrTest extends CopyConverterFloatBase {
    @BeforeMethod
    public void intitialize() {
        convertor = new CopyConverterFloat2Str();
        init();
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.cint.CopyConverterInt2Long#convert(ru.aafanasiev.util.copier.converter.base.CopyEntry, java.lang.Object)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testConvertOk() throws Exception {
        String result = (String) convertor.convert(entry, Float.valueOf(1.0F));
        assertEquals(result, Float.valueOf(1.0F).toString());

        result = (String) convertor.convert(entry, Float.NaN);
        assertEquals(result, Float.valueOf(Float.NaN).toString());

        result = (String) convertor.convert(entry, Float.POSITIVE_INFINITY);
        assertEquals(result, Float.valueOf(Float.POSITIVE_INFINITY).toString());

        result = (String) convertor.convert(entry, Float.NEGATIVE_INFINITY);
        assertEquals(result, Float.valueOf(Float.NEGATIVE_INFINITY).toString());

        result = (String) convertor.convert(entry, Float.MAX_VALUE);
        assertEquals(result, Float.valueOf(Float.MAX_VALUE).toString());

        result = (String) convertor.convert(entry, Float.MAX_VALUE);
        assertEquals(result, Float.valueOf(Float.MAX_VALUE).toString());
    }

    /**
     * Test method for {@link ru.aafanasiev.util.copier.converter.cfloat.CopyConverterFloat2Str#listConvertedPairs()
     * listConvertedPairs}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testListConvertedPairs() throws Exception {
        ConverterKey[] array = convertor.listConvertedPairs();
        assertEquals(array.length, 1);
        assertEquals(array[0].getFromClazz(), Float.class);
        assertEquals(array[0].getToClazz(), String.class);
    }
}
