/**
 *
 */
package ru.aafanasiev.util.copier.converter.clocaldate;

import static org.testng.Assert.assertEquals;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.aafanasiev.util.copier.converter.base.ConverterKey;

/**
 * @author aafanasyev
 */
@Test(groups = {"CopyConverterLocalDate2OffsetDateTimeTest", "UTIL"}, ignoreMissingDependencies = true)
public class CopyConverterLocalDate2OffsetDateTimeTest extends CopyConverterLocalDateBase {
    @BeforeMethod
    public void intitialize() {
        convertor = new CopyConverterLocalDate2OffsetDateTime();
        init();
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.clocaldate.CopyConverterLocalDate2LocalDateTime#convert(ru.aafanasiev.util.copier.converter.base.CopyEntry, java.lang.Object)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testConvert() throws Exception {
        LocalDate now = LocalDate.now();
        OffsetDateTime res = (OffsetDateTime) convertor.convert(entry, now);
        assertEquals(res.toLocalDate(), now);
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.clocaldate.CopyConverterLocalDate2LocalDateTime#listConvertedPairs()}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testListConvertedPairs() throws Exception {
        ConverterKey[] array = convertor.listConvertedPairs();
        assertEquals(array.length, 1);
        assertEquals(array[0].getFromClazz(), LocalDate.class);
        assertEquals(array[0].getToClazz(), OffsetDateTime.class);
    }

}
