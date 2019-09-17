/**
 *
 */
package ru.aafanasiev.util.copier.converter.clong;

import static org.testng.Assert.assertEquals;

import java.math.BigInteger;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.aafanasiev.util.copier.converter.base.ConverterKey;
import ru.aafanasiev.util.copier.converter.base.CopyEntry;
import ru.aafanasiev.util.copier.converter.clong.CopyConverterLong2BigInt;

/**
 * @author aafanasyev
 */
@Test(groups = {"UTIL"}, ignoreMissingDependencies = true)
public class CopyConverterLong2BigIntTest extends CopyConverterLongBase {
    @BeforeMethod
    public void intitialize() {
        convertor = new CopyConverterLong2BigInt();
        entry = new CopyEntry();
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.cint.CopyConverterInt2Long#convert(ru.aafanasiev.util.copier.converter.base.CopyEntry, java.lang.Object)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testConvert() throws Exception {
        BigInteger result = (BigInteger) convertor.convert(entry, 0L);
        assertEquals(result, BigInteger.valueOf(0));

        result = (BigInteger) convertor.convert(entry, Long.valueOf(1L));
        assertEquals(result, BigInteger.valueOf(1));

        result = (BigInteger) convertor.convert(entry, Long.valueOf(Long.MAX_VALUE));
        assertEquals(result, BigInteger.valueOf(Long.MAX_VALUE));

        result = (BigInteger) convertor.convert(entry, Long.valueOf(Long.MIN_VALUE));
        assertEquals(result, BigInteger.valueOf(Long.MIN_VALUE));
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.cint.CopyConverterInt2Long#listConvertedPairs()}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testListConvertedPairs() throws Exception {
        ConverterKey[] array = convertor.listConvertedPairs();
        assertEquals(array.length, 1);
        assertEquals(array[0].getFromClazz(), Long.class);
        assertEquals(array[0].getToClazz(), BigInteger.class);
    }
}
