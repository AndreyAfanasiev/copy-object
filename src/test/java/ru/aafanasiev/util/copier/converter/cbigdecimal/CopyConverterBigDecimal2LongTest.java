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

/**
 * @author aafanasyev
 */
@Test(groups = {"UTIL"}, ignoreMissingDependencies = true)
public class CopyConverterBigDecimal2LongTest extends CopyConverterBigDecimalBase {
    @BeforeMethod
    public void intitialize() {
        convertor = new CopyConverterBigDecimal2Long();
        init();
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.cbigdecimal.CopyConverterBigDecimal2Long#convert(ru.aafanasiev.util.copier.converter.base.CopyEntry, java.lang.Object)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testConvert() throws Exception {
        Long result = (Long) convertor.convert(entry, new BigDecimal(0));
        assertNotNull(result);
        assertEquals(result, Long.valueOf(0));

        result = (Long) convertor.convert(entry, new BigDecimal(1));
        assertEquals(result, Long.valueOf(1));

        result = (Long) convertor.convert(entry, new BigDecimal(-1));
        assertEquals(result, Long.valueOf(-1));

        result = (Long) convertor.convert(entry, new BigDecimal(1.2d));
        assertEquals(result, Long.valueOf(1));

        result = (Long) convertor.convert(entry, new BigDecimal((Long.MAX_VALUE)));
        assertEquals(result, Long.valueOf(Long.MAX_VALUE));

        result = (Long) convertor.convert(entry, new BigDecimal((Long.MIN_VALUE)));
        assertEquals(result, Long.valueOf(Long.MIN_VALUE));
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.cbigdecimal.CopyConverterBigDecimal2Long#listConvertedPairs()}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testListConvertedPairs() throws Exception {
        ConverterKey[] array = convertor.listConvertedPairs();
        assertEquals(array.length, 1);
        assertEquals(array[0].getFromClazz(), BigDecimal.class);
        assertEquals(array[0].getToClazz(), Long.class);
    }
}
