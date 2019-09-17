/**
 *
 */
package ru.aafanasiev.util.copier.converter.clong;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.aafanasiev.util.copier.converter.base.ConverterKey;
import ru.aafanasiev.util.copier.converter.base.CopyEntry;
import ru.aafanasiev.util.copier.converter.base.NumberConvertException;
import ru.aafanasiev.util.copier.converter.clong.CopyConverterLong2Int;

/**
 * @author aafanasyev
 */
@Test(groups = {"UTIL"}, ignoreMissingDependencies = true)
public class CopyConverterLong2IntTest extends CopyConverterLongBase {
    @BeforeMethod
    public void intitialize() {
        convertor = new CopyConverterLong2Int();
        entry = new CopyEntry();
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.cint.CopyConverterInt2Long#convert(ru.aafanasiev.util.copier.converter.base.CopyEntry, java.lang.Object)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testConvertOk() throws Exception {
        Integer result = (Integer) convertor.convert(entry, 0L);
        assertEquals(result.intValue(), 0L);

        result = (Integer) convertor.convert(entry, Long.valueOf(1));
        assertEquals(result.intValue(), 1L);

        result = (Integer) convertor.convert(entry, Long.valueOf(Integer.MAX_VALUE));
        assertEquals(result.intValue(), Integer.MAX_VALUE);

        result = (Integer) convertor.convert(entry, Long.valueOf(Integer.MIN_VALUE));
        assertEquals(result.intValue(), Integer.MIN_VALUE);
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.cint.CopyConverterInt2Long#convert(ru.aafanasiev.util.copier.converter.base.CopyEntry, java.lang.Object)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true, expectedExceptions = {
            NumberConvertException.class})
    public void testConvertBad02() throws Exception {
        convertor.convert(entry, Long.valueOf(Long.MAX_VALUE));
        assertFalse(true);
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.cint.CopyConverterInt2Long#convert(ru.aafanasiev.util.copier.converter.base.CopyEntry, java.lang.Object)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true, expectedExceptions = {
            NumberConvertException.class})
    public void testConvertBad03() throws Exception {
        convertor.convert(entry, Long.valueOf(Long.MIN_VALUE));
        assertFalse(true);
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
        assertEquals(array[0].getToClazz(), Integer.class);
    }
}
