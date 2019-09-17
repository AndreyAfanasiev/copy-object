/**
 *
 */
package ru.aafanasiev.util.copier.converter.cdate;

import org.testng.annotations.Factory;

public class UtilConvertorsDateTestSuite {
    @Factory
    public Object[] settingsSuite() {
        Object[] res = new Object[] {
                // Date
                new CopyConverterSDate2SDateTest(),
                new CopyConverterSDate2UDateTest(),
                new CopyConverterUDate2SDateTest(),
                new CopyConverterUDate2UDateTest()
        };

        return res;
    }
}
