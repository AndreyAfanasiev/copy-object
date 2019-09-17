/**
 *
 */
package ru.aafanasiev.util.copier.converter;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import org.testng.annotations.Test;

import ru.aafanasiev.util.copier.converter.base.CopyEntry;

/**
 * @author aafanasyev
 */
@Test(groups = {"UTIL"}, ignoreMissingDependencies = true)
public class CopyConverterSimpleTest {

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.CopyConverterSimple#convert(ru.aafanasiev.util.copier.converter.base.CopyEntry, java.lang.Object)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testConvert() throws Exception {
        CopyConverterSimple convertor = new CopyConverterSimple();
        CopyEntry entry = new CopyEntry();

        Integer intRes = (Integer) convertor.convert(entry, 0);
        assertEquals(intRes.longValue(), 0L);

        String sRes = (String) convertor.convert(entry, "A");
        assertEquals(sRes, "A");

        Object oRes = convertor.convert(entry, null);
        assertNull(oRes);
        ;
    }

}
