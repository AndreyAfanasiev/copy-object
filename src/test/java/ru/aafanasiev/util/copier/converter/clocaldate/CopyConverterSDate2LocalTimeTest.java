/**
 *
 */
package ru.aafanasiev.util.copier.converter.clocaldate;

import static org.testng.Assert.assertEquals;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.aafanasiev.util.copier.converter.base.ConverterKey;
import ru.aafanasiev.util.copier.converter.base.CopyEntry;

/**
 * @author aafanasyev
 */
@Test(groups = {"CopyConverterSDate2LocalTimeTest", "UTIL"}, ignoreMissingDependencies = true)
public class CopyConverterSDate2LocalTimeTest extends CopyConverterLocalDateBase {
    @BeforeMethod
    public void intitialize() {
        convertor = new CopyConverterSDate2LocalTime();
        entry = new CopyEntry();
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.clocaldate.CopyConverterLocalDate2LocalDateTime#convert(ru.aafanasiev.util.copier.converter.base.CopyEntry, java.lang.Object)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testConvert() throws Exception {
        Date now = new Date(new java.util.Date().getTime());
        LocalTime res = (LocalTime) convertor.convert(entry, now);
        assertEquals(res, Instant.ofEpochMilli(now.getTime()).atZone(ZoneId.systemDefault()).toLocalTime());
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.clocaldate.CopyConverterLocalDate2LocalDateTime#listConvertedPairs()}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testListConvertedPairs() throws Exception {
        ConverterKey[] array = convertor.listConvertedPairs();
        assertEquals(array.length, 1);
        assertEquals(array[0].getFromClazz(), Date.class);
        assertEquals(array[0].getToClazz(), LocalTime.class);
    }

}
