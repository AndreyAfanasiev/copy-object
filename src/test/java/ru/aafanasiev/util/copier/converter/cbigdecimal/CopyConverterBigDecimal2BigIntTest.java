/**
 *
 */
package ru.aafanasiev.util.copier.converter.cbigdecimal;

import static org.junit.Assert.assertNotNull;
import static org.testng.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.aafanasiev.util.copier.converter.base.ConverterKey;
import ru.aafanasiev.util.copier.converter.base.CopyEntryImpl;

/**
 * @author aafanasyev
 */
@Test(groups = {"UTIL"}, ignoreMissingDependencies = true)
public class CopyConverterBigDecimal2BigIntTest extends CopyConverterBigDecimalBase {
    @BeforeMethod
    public void intitialize() {
        convertor = new CopyConverterBigDecimal2BigInt();
        entry = new CopyEntryImpl();
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.cbigdecimal.CopyConverterBigDecimal2BigInt#convert(ru.aafanasiev.util.copier.converter.base.CopyEntry, java.lang.Object)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testConvert() throws Exception {
        BigInteger result = (BigInteger) convertor.convert(entry, new BigDecimal(0));
        assertNotNull(result);
        assertEquals(result, BigInteger.valueOf(0));

        result = (BigInteger) convertor.convert(entry, new BigDecimal(1));
        assertEquals(result, BigInteger.valueOf(1));

        result = (BigInteger) convertor.convert(entry, new BigDecimal(-1));
        assertEquals(result, BigInteger.valueOf(-1));

        result = (BigInteger) convertor.convert(entry, new BigDecimal(1.2d));
        assertEquals(result, BigInteger.valueOf(1));

        result = (BigInteger) convertor.convert(entry, new BigDecimal((Long.MAX_VALUE)));
        assertEquals(result, BigInteger.valueOf(Long.MAX_VALUE));

        result = (BigInteger) convertor.convert(entry, new BigDecimal((Long.MIN_VALUE)));
        assertEquals(result, BigInteger.valueOf(Long.MIN_VALUE));
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.cbigdecimal.CopyConverterBigDecimal2BigInt#listConvertedPairs()}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testListConvertedPairs() throws Exception {
        ConverterKey[] array = convertor.listConvertedPairs();
        assertEquals(array.length, 1);
        assertEquals(array[0].getFromClazz(), BigDecimal.class);
        assertEquals(array[0].getToClazz(), BigInteger.class);
    }
}
