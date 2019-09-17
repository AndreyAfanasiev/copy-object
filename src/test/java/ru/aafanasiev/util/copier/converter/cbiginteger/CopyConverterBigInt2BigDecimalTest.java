/**
 *
 */
package ru.aafanasiev.util.copier.converter.cbiginteger;

import static org.testng.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.aafanasiev.util.copier.converter.base.ConverterKey;
import ru.aafanasiev.util.copier.converter.base.CopyEntry;

/**
 * @author aafanasyev
 */
@Test(groups = {"UTIL"}, ignoreMissingDependencies = true)
public class CopyConverterBigInt2BigDecimalTest extends CopyConverterBigIntBase {
    @BeforeMethod
    public void intitialize() {
        convertor = new CopyConverterBigInt2BigDecimal();
        entry = new CopyEntry();
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.cbigdecimal.CopyConverterBigDecimal2BigInt#convert(ru.aafanasiev.util.copier.converter.base.CopyEntry, java.lang.Object)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testConvert() throws Exception {
        BigDecimal result = (BigDecimal) convertor.convert(entry, BigInteger.valueOf(0));
        assertEquals(result, BigDecimal.valueOf(0));

        result = (BigDecimal) convertor.convert(entry, BigInteger.valueOf(1));
        assertEquals(result, BigDecimal.valueOf(1));

        result = (BigDecimal) convertor.convert(entry, BigInteger.valueOf(-1));
        assertEquals(result, BigDecimal.valueOf(-1));

        result = (BigDecimal) convertor.convert(entry, BigInteger.valueOf(Long.MAX_VALUE));
        assertEquals(result, BigDecimal.valueOf(Long.MAX_VALUE));

        result = (BigDecimal) convertor.convert(entry, BigInteger.valueOf(Long.MIN_VALUE));
        assertEquals(result, BigDecimal.valueOf(Long.MIN_VALUE));
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.cbigdecimal.CopyConverterBigDecimal2BigInt#listConvertedPairs()}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testListConvertedPairs() throws Exception {
        ConverterKey[] array = convertor.listConvertedPairs();
        assertEquals(array.length, 1);
        assertEquals(array[0].getFromClazz(), BigInteger.class);
        assertEquals(array[0].getToClazz(), BigDecimal.class);
    }
}
