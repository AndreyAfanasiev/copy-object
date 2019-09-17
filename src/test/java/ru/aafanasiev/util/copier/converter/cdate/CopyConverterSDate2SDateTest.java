/**
 *
 */
package ru.aafanasiev.util.copier.converter.cdate;

import static org.junit.Assert.assertNotNull;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotSame;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertTrue;

import java.sql.Date;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.aafanasiev.annotations.CopyImmutable;
import ru.aafanasiev.util.copier.util.ReflectionUtils;
import ru.aafanasiev.util.copier.converter.base.ConverterKey;
import ru.aafanasiev.util.copier.converter.base.CopyConverter;
import ru.aafanasiev.util.copier.converter.base.CopyEntry;

/**
 * @author aafanasyev
 */
@Test(groups = {"UTIL"}, ignoreMissingDependencies = true)
public class CopyConverterSDate2SDateTest {
    protected CopyConverter convertor;
    protected CopyEntry entry;

    @BeforeMethod
    public void intitializeMethod() {
        convertor = new CopyConverterSDate2SDate();
        entry = new CopyEntry();
        entry.setParameters(new DateConverterParameters());
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.cdate.CopyConverterUDate2UDate#convert(ru.aafanasiev.util.copier.converter.base.CopyEntry, java.lang.Object)
     * convert(CopyEntry, Object)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testConvert() throws Exception {
        Object result = convertor.convert(entry, null);
        assertNull(result);

        result = convertor.convert(entry, Long.valueOf(1));
        assertNull(result);

        Date dt = new Date(10);

        Date res = (Date) convertor.convert(entry, dt);
        assertEquals(res, dt);
        assertSame(res, dt);

        ((DateConverterParameters) entry.getParameters()).setImmutable(true);
        res = (Date) convertor.convert(entry, dt);
        assertEquals(res, dt);
        assertNotSame(res, dt);
    }

    /**
     * Test method for {@link ru.aafanasiev.util.copier.converter.cdate.CopyConverterUDate2UDate#listConvertedPairs()
     * listConvertedPairs()}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    @CopyImmutable
    public void testListConvertedPairs() throws Exception {
        ConverterKey[] array = convertor.listConvertedPairs();
        assertEquals(array.length, 1);
        assertEquals(array[0].getFromClazz(), Date.class);
        assertEquals(array[0].getToClazz(), Date.class);
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.cdate.DateCopyConverterBase#getParams(ru.aafanasiev.util.copier.converter.base.CopyEntry)
     * listConvertedPairs()}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testGetParameters() throws Exception {
        entry.setParameters(null);

        entry.setFromMethod(ReflectionUtils.getMethod(CopyConverterSDate2SDateTest.class,
                "testGetParameters"));
        Object result = convertor.getParams(entry);
        assertNotNull(result);
        assertEquals(result.getClass(), DateConverterParameters.class);
        assertFalse(((DateConverterParameters) result).isImmutable());

        entry.setFromMethod(ReflectionUtils.getMethod(CopyConverterSDate2SDateTest.class,
                "testListConvertedPairs"));
        result = convertor.getParams(entry);
        assertNotNull(result);
        assertEquals(result.getClass(), DateConverterParameters.class);
        assertTrue(((DateConverterParameters) result).isImmutable());
    }

}
