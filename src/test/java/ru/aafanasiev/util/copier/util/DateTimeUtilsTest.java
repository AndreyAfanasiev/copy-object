/**
 *
 */
package ru.aafanasiev.util.copier.util;

import static org.testng.Assert.assertEquals;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Date;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author aafanasyev
 */
@Test(groups = {"DateTimeUtilTest", "UTILS"}, ignoreMissingDependencies = true)
public class DateTimeUtilsTest {
    @DataProvider(name = "badTime")
    private Object[][] badTime() {
        return new Object[][] {
                {"00.00.00"},
                {"00:00:70"},
                {"00:65:00"},
                {"25:00:00"},
                {"aa:00:00"},
                {"00:bb:00"},
                {"00:00:cc"},
                {"5:00:00"}
        };
    }

    @DataProvider(name = "badDdMmYyyy")
    private Object[][] badDdMmYyyy() {
        return new Object[][] {
                {"32.01.2018"},
                {"01.14.2018"}
        };
    }

    @DataProvider(name = "badDdMmYy")
    private Object[][] badDdMmYy() {
        return new Object[][] {
                {"32.01.18"},
                {"01.14.18"}
        };
    }

    @DataProvider(name = "badYyMmDd")
    private Object[][] badYyMmDd() {
        return new Object[][] {
                {"18.01.32"},
                {"18.14.01"}
        };
    }

    /**
     * Test method for {@link com.DateTimeUtils.bux.util.DateTimeUtil#getIsoLocalTime(java.lang.String)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testGetIsoLocalTimeOk() throws Exception {
        assertEquals(DateTimeUtils.getIsoLocalTime("00:00:00"), LocalTime.of(0, 0, 0));
        assertEquals(DateTimeUtils.getIsoLocalTime("00:00:01"), LocalTime.of(0, 0, 1));
        assertEquals(DateTimeUtils.getIsoLocalTime("23:59:59"), LocalTime.of(23, 59, 59));
        assertEquals(DateTimeUtils.getIsoLocalTime("13:30:15"), LocalTime.of(13, 30, 15));
    }

    /**
     * Test method for {@link com.DateTimeUtils.bux.util.DateTimeUtil#getIsoLocalTime(java.lang.String)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true,
            dataProvider = "badTime", expectedExceptions = {DateTimeParseException.class})
    public void testGetIsoLocalTimeBad(String timeStr) throws Exception {
        DateTimeUtils.getIsoLocalTime(timeStr);
    }

    /**
     * Test method for {@link com.DateTimeUtils.bux.util.DateTimeUtil#getLocalDate4DdMmYyyy(java.lang.String)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testGetLocalDate4DdMmYyyyOk() throws Exception {
        assertEquals(DateTimeUtils.getLocalDate4DdMmYyyy("01.01.2018"), LocalDate.of(2018, 1, 1));
        assertEquals(DateTimeUtils.getLocalDate4DdMmYyyy("01.12.2018"), LocalDate.of(2018, 12, 01));
        assertEquals(DateTimeUtils.getLocalDate4DdMmYyyy("31.12.2018"), LocalDate.of(2018, 12, 31));
        assertEquals(DateTimeUtils.getLocalDate4DdMmYyyy("15.03.2018"), LocalDate.of(2018, 3, 15));
        assertEquals(DateTimeUtils.getLocalDate4DdMmYyyy("28.02.2018"), LocalDate.of(2018, 2, 28));
        assertEquals(DateTimeUtils.getLocalDate4DdMmYyyy("29.02.2016"), LocalDate.of(2016, 2, 29));
        assertEquals(DateTimeUtils.getLocalDate4DdMmYyyy("29.02.2018"), LocalDate.of(2018, 2, 28));
    }

    /**
     * Test method for {@link com.DateTimeUtils.bux.util.DateTimeUtil#getLocalDate4DdMmYyyy(java.lang.String)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true, dataProvider = "badDdMmYyyy",
            expectedExceptions = {DateTimeParseException.class, DateTimeException.class})
    public void testGetLocalDate4DdMmYyyyBad(String dataStr) throws Exception {
        DateTimeUtils.getLocalDate4DdMmYyyy(dataStr);
    }

    /**
     * Test method for {@link com.DateTimeUtils.bux.util.DateTimeUtil#getLocalDate4DdMmYy(java.lang.String)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testGetLocalDate4DdMmYyOk() throws Exception {
        assertEquals(DateTimeUtils.getLocalDate4DdMmYy("01.01.18"), LocalDate.of(2018, 1, 1));
        assertEquals(DateTimeUtils.getLocalDate4DdMmYy("01.12.18"), LocalDate.of(2018, 12, 01));
        assertEquals(DateTimeUtils.getLocalDate4DdMmYy("31.12.18"), LocalDate.of(2018, 12, 31));
        assertEquals(DateTimeUtils.getLocalDate4DdMmYy("15.03.18"), LocalDate.of(2018, 3, 15));
        assertEquals(DateTimeUtils.getLocalDate4DdMmYy("28.02.18"), LocalDate.of(2018, 2, 28));
        assertEquals(DateTimeUtils.getLocalDate4DdMmYy("29.02.16"), LocalDate.of(2016, 2, 29));
        assertEquals(DateTimeUtils.getLocalDate4DdMmYy("29.02.18"), LocalDate.of(2018, 2, 28));
    }

    /**
     * Test method for {@link com.DateTimeUtils.bux.util.DateTimeUtil#getLocalDate4DdMmYyyy(java.lang.String)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true, dataProvider = "badDdMmYy",
            expectedExceptions = {DateTimeParseException.class, DateTimeException.class})
    public void testGetLocalDate4DdMmYyBad(String dataStr) throws Exception {
        DateTimeUtils.getLocalDate4DdMmYy(dataStr);
    }

    /**
     * Test method for {@link com.DateTimeUtils.bux.util.DateTimeUtil#getLocalDate4DdMmYy(java.lang.String)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testGetLocalDate4YyMmDdOk() throws Exception {
        assertEquals(DateTimeUtils.getLocalDate4YyMmDd("18.01.01"), LocalDate.of(2018, 1, 1));
        assertEquals(DateTimeUtils.getLocalDate4YyMmDd("18.12.01"), LocalDate.of(2018, 12, 01));
        assertEquals(DateTimeUtils.getLocalDate4YyMmDd("18.12.31"), LocalDate.of(2018, 12, 31));
        assertEquals(DateTimeUtils.getLocalDate4YyMmDd("18.03.15"), LocalDate.of(2018, 3, 15));
        assertEquals(DateTimeUtils.getLocalDate4YyMmDd("18.02.28"), LocalDate.of(2018, 2, 28));
        assertEquals(DateTimeUtils.getLocalDate4YyMmDd("16.02.29"), LocalDate.of(2016, 2, 29));
        assertEquals(DateTimeUtils.getLocalDate4YyMmDd("18.02.29"), LocalDate.of(2018, 2, 28));
    }

    /**
     * Test method for {@link com.DateTimeUtils.bux.util.DateTimeUtil#getLocalDate4DdMmYyyy(java.lang.String)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true, dataProvider = "badYyMmDd",
            expectedExceptions = {DateTimeParseException.class, DateTimeException.class})
    public void testGetLocalDate4YyMmDdBad(String dataStr) throws Exception {
        DateTimeUtils.getLocalDate4YyMmDd(dataStr);
    }

    /**
     * Test method for {@link ru.aafanasiev.util.DateTimeUtils#convert2Date(java.time.LocalDate)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testConvert2Date() throws Exception {
        LocalDate now = LocalDate.now();
        Date res = DateTimeUtils.convert2Date(now);
        assertEquals(new java.sql.Date(res.getTime()).toLocalDate(), now);
    }

    /**
     * Test method for {@link ru.aafanasiev.util.DateTimeUtils#convert2LocalDate(java.util.Date)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testConvert2LocalDate() throws Exception {
        Date now = new Date();
        LocalDate res = DateTimeUtils.convert2LocalDate(now);
        assertEquals(res, new java.sql.Date(now.getTime()).toLocalDate());
    }
}
