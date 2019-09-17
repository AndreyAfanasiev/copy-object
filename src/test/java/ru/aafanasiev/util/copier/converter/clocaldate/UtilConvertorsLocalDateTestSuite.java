/**
 *
 */
package ru.aafanasiev.util.copier.converter.clocaldate;

import org.testng.annotations.Factory;

public class UtilConvertorsLocalDateTestSuite {
    @Factory
    public Object[] settingsSuite() {
        Object[] res = new Object[] {
                new CopyConverterUDate2LocalDateTest(),
                new CopyConverterUDate2LocalDateTimeTest(),
                new CopyConverterUDate2LocalTimeTest(),
                new CopyConverterUDate2OffsetDateTimeTest(),
                new CopyConverterUDate2OffsetTimeTest(),

                new CopyConverterSDate2LocalDateTest(),
                new CopyConverterSDate2LocalDateTimeTest(),
                new CopyConverterSDate2LocalTimeTest(),
                new CopyConverterSDate2OffsetDateTimeTest(),
                new CopyConverterSDate2OffsetTimeTest(),

                new CopyConverterOffsetDateTime2LocalDateTest(),
                new CopyConverterOffsetDateTime2LocalDateTimeTest(),
                new CopyConverterOffsetDateTime2LocalTimeTest(),
                new CopyConverterOffsetDateTime2OffsetTimeTest(),
                new CopyConverterOffsetDateTime2SDateTest(),
                new CopyConverterOffsetDateTime2UDateTest(),

                new CopyConverterLocalDateTime2LocalDateTest(),
                new CopyConverterLocalDateTime2LocalTimeTest(),
                new CopyConverterLocalDateTime2OffsetDateTimeTest(),
                new CopyConverterLocalDateTime2OffsetTimeTest(),
                new CopyConverterLocalDateTime2SDateTest(),
                new CopyConverterLocalDateTime2UDateTest(),

                new CopyConverterLocalDate2LocalDateTimeTest(),
                new CopyConverterLocalDate2OffsetDateTimeTest(),
                new CopyConverterLocalDate2SDateTest(),
                new CopyConverterLocalDate2UDateTest()
        };

        return res;
    }
}
