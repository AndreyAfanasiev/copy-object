/**
 *
 */
package ru.aafanasiev.util.copier.converter.cboolean;

import static org.testng.Assert.assertEquals;

import java.math.BigInteger;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.aafanasiev.util.copier.converter.base.ConverterKey;
import ru.aafanasiev.util.copier.converter.base.CopyEntry;
import ru.aafanasiev.util.copier.converter.cdouble.CopyConverterDoubleBase;

/**
 * @author aafanasyev
 */
@Test(groups = {"UTIL"}, ignoreMissingDependencies = true)
public class CopyConverterBool2BigIntTest extends CopyConverterDoubleBase {
    @BeforeMethod
    public void intitialize() {
        convertor = new CopyConverterBool2BigInt();
        entry = new CopyEntry();
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.bool.CopyConverterBool2Int#convert(ru.aafanasiev.util.copier.converter.base.CopyEntry, java.lang.Object)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testConvert() throws Exception {
        BigInteger result = (BigInteger) convertor.convert(entry, Boolean.FALSE);
        assertEquals(result.intValue(), 0);

        result = (BigInteger) convertor.convert(entry, Boolean.TRUE);
        assertEquals(result.intValue(), 1);
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.bool.CopyConverterBool2Int#listConvertedPairs()}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testListConvertedPairs() throws Exception {
        ConverterKey[] array = convertor.listConvertedPairs();
        assertEquals(array.length, 1);
        assertEquals(array[0].getFromClazz(), Boolean.class);
        assertEquals(array[0].getToClazz(), BigInteger.class);
    }

}
