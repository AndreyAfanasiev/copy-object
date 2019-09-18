/**
 *
 */
package ru.aafanasiev.util.copier.converter.cbiginteger;

import static org.testng.Assert.assertEquals;

import java.math.BigInteger;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.aafanasiev.util.copier.converter.base.ConverterKey;

/**
 * @author aafanasyev
 */
@Test(groups = {"UTIL"}, ignoreMissingDependencies = true)
public class CopyConverterBigInt2BoolTest extends CopyConverterBigIntBase {
    @BeforeMethod
    public void intitialize() {
        convertor = new CopyConverterBigInt2Bool();
        init();
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.cbigdecimal.CopyConverterBigDecimal2Bool#convert(ru.aafanasiev.util.copier.converter.base.CopyEntry, java.lang.Object)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testConvert() throws Exception {
        Boolean result = (Boolean) convertor.convert(entry, BigInteger.valueOf(0));
        assertEquals(result, Boolean.FALSE);

        result = (Boolean) convertor.convert(entry, BigInteger.valueOf(1));
        assertEquals(result, Boolean.TRUE);

        result = (Boolean) convertor.convert(entry, BigInteger.valueOf(-1));
        assertEquals(result, Boolean.TRUE);
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.cbigdecimal.CopyConverterBigDecimal2Bool#listConvertedPairs()}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testListConvertedPairs() throws Exception {
        ConverterKey[] array = convertor.listConvertedPairs();
        assertEquals(array.length, 1);
        assertEquals(array[0].getFromClazz(), BigInteger.class);
        assertEquals(array[0].getToClazz(), Boolean.class);
    }
}
