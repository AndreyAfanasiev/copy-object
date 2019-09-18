/**
 *
 */
package ru.aafanasiev.util.copier.converter.cint;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.aafanasiev.util.copier.converter.base.ConverterKey;
import ru.aafanasiev.util.copier.converter.cdouble.CopyConverterDoubleBase;

/**
 * @author aafanasyev
 */
@Test(groups = {"UTIL"}, ignoreMissingDependencies = true)
public class CopyConverterInt2BoolTest extends CopyConverterDoubleBase {
    @BeforeMethod
    public void intitialize() {
        convertor = new CopyConverterInt2Bool();
        init();
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.bool.CopyConverterBool2Int#convert(ru.aafanasiev.util.copier.converter.base.CopyEntry, java.lang.Object)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testConvert() throws Exception {
        Boolean result = (Boolean) convertor.convert(entry, Integer.valueOf(0));
        assertEquals(result, Boolean.FALSE);

        result = (Boolean) convertor.convert(entry, Integer.valueOf(1));
        assertEquals(result, Boolean.TRUE);

        result = (Boolean) convertor.convert(entry, Integer.valueOf(2));
        assertEquals(result, Boolean.TRUE);
    }

    /**
     * Test method for {@link ru.aafanasiev.util.copier.converter.bool.CopyConverterBool2Int#listConvertedPairs()}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testListConvertedPairs() throws Exception {
        ConverterKey[] array = convertor.listConvertedPairs();
        assertEquals(array.length, 1);
        assertEquals(array[0].getFromClazz(), Integer.class);
        assertEquals(array[0].getToClazz(), Boolean.class);
    }

}
