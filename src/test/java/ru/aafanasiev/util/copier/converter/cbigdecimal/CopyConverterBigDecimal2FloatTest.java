/**
 *
 */
package ru.aafanasiev.util.copier.converter.cbigdecimal;

import static org.junit.Assert.assertNotNull;
import static org.testng.Assert.assertEquals;

import java.math.BigDecimal;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.aafanasiev.util.copier.converter.base.ConverterKey;
import ru.aafanasiev.util.copier.converter.base.CopyEntry;

/**
 * @author aafanasyev
 */
@Test(groups = {"UTIL"}, ignoreMissingDependencies = true)
public class CopyConverterBigDecimal2FloatTest extends CopyConverterBigDecimalBase {
    @BeforeMethod
    public void intitialize() {
        convertor = new CopyConverterBigDecimal2Float();
        entry = new CopyEntry();
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.cbigdecimal.CopyConverterBigDecimal2Float#convert(ru.aafanasiev.util.copier.converter.base.CopyEntry, java.lang.Object)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testConvert() throws Exception {
        Float result = (Float) convertor.convert(entry, new BigDecimal(0));
        assertNotNull(result);
        assertEquals(result, Float.valueOf(0));

        result = (Float) convertor.convert(entry, new BigDecimal(1));
        assertEquals(result, Float.valueOf(1));

        result = (Float) convertor.convert(entry, new BigDecimal(-1));
        assertEquals(result, Float.valueOf(-1));

        result = (Float) convertor.convert(entry, new BigDecimal(1.2f));
        assertEquals(result, Float.valueOf(1.2f));

        result = (Float) convertor.convert(entry, new BigDecimal((Long.MAX_VALUE)));
        assertEquals(result, Float.valueOf(Long.MAX_VALUE));

        result = (Float) convertor.convert(entry, new BigDecimal((Long.MIN_VALUE)));
        assertEquals(result, Float.valueOf(Long.MIN_VALUE));
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.cbigdecimal.CopyConverterBigDecimal2Float#listConvertedPairs()}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testListConvertedPairs() throws Exception {
        ConverterKey[] array = convertor.listConvertedPairs();
        assertEquals(array.length, 1);
        assertEquals(array[0].getFromClazz(), BigDecimal.class);
        assertEquals(array[0].getToClazz(), Float.class);
    }
}
