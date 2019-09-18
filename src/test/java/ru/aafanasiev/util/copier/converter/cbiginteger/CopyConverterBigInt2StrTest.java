/**
 *
 */
package ru.aafanasiev.util.copier.converter.cbiginteger;

import static org.testng.Assert.assertEquals;

import java.math.BigInteger;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.aafanasiev.util.copier.converter.base.ConverterKey;
import ru.aafanasiev.util.copier.converter.clong.CopyConverterLongBase;

/**
 * @author aafanasyev
 */
@Test(groups = {"UTIL"}, ignoreMissingDependencies = true)
public class CopyConverterBigInt2StrTest extends CopyConverterLongBase {
    @BeforeMethod
    public void intitialize() {
        convertor = new CopyConverterBigInt2Str();
        init();
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.cint.CopyConverterBigDecimal2Str#convert(ru.aafanasiev.util.copier.converter.base.CopyEntry, java.lang.Object)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testConvertOk() throws Exception {
        String result = (String) convertor.convert(entry, BigInteger.valueOf(0L));
        assertEquals(result, "0");

        result = (String) convertor.convert(entry, BigInteger.valueOf(1));
        assertEquals(result, "1");

        result = (String) convertor.convert(entry, BigInteger.valueOf(Long.MAX_VALUE));
        assertEquals(result, String.valueOf(Long.MAX_VALUE));

        result = (String) convertor.convert(entry, BigInteger.valueOf(Long.MIN_VALUE));
        assertEquals(result, String.valueOf(Long.MIN_VALUE));
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.cint.CopyConverterBigDecimal2Str#listConvertedPairs()}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testListConvertedPairs() throws Exception {
        ConverterKey[] array = convertor.listConvertedPairs();
        assertEquals(array.length, 1);
        assertEquals(array[0].getFromClazz(), BigInteger.class);
        assertEquals(array[0].getToClazz(), String.class);
    }
}
