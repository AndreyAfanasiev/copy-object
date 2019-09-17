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
public class CopyConverterBigDecimal2IntTest extends CopyConverterBigDecimalBase {
    @BeforeMethod
    public void intitialize() {
        convertor = new CopyConverterBigDecimal2Int();
        entry = new CopyEntry();
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.cbigdecimal.CopyConverterBigDecimal2Int#convert(ru.aafanasiev.util.copier.converter.base.CopyEntry, java.lang.Object)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testConvert() throws Exception {
        Integer result = (Integer) convertor.convert(entry, new BigDecimal(0));
        assertNotNull(result);
        assertEquals(result, Integer.valueOf(0));

        result = (Integer) convertor.convert(entry, new BigDecimal(1));
        assertEquals(result, Integer.valueOf(1));

        result = (Integer) convertor.convert(entry, new BigDecimal(-1));
        assertEquals(result, Integer.valueOf(-1));

        result = (Integer) convertor.convert(entry, new BigDecimal(1.2d));
        assertEquals(result, Integer.valueOf(1));

        result = (Integer) convertor.convert(entry, new BigDecimal((Integer.MAX_VALUE)));
        assertEquals(result, Integer.valueOf(Integer.MAX_VALUE));

        result = (Integer) convertor.convert(entry, new BigDecimal((Integer.MIN_VALUE)));
        assertEquals(result, Integer.valueOf(Integer.MIN_VALUE));
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.cbigdecimal.CopyConverterBigDecimal2Int#listConvertedPairs()}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testListConvertedPairs() throws Exception {
        ConverterKey[] array = convertor.listConvertedPairs();
        assertEquals(array.length, 1);
        assertEquals(array[0].getFromClazz(), BigDecimal.class);
        assertEquals(array[0].getToClazz(), Integer.class);
    }
}
