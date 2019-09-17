/**
 *
 */
package ru.aafanasiev.util.copier.converter.clong;

import org.testng.annotations.Factory;

public class UtilConvertorsLongTestSuite {
    @Factory
    public Object[] settingsSuite() {
        Object[] res = new Object[] {
                // Long
                new CopyConverterLong2StrTest(),
                new CopyConverterLong2IntTest(),
                new CopyConverterLong2BigDecimalTest(),
                new CopyConverterLong2BigIntTest(),
                new CopyConverterLong2BoolTest(),
                new CopyConverterLong2FloatTest(),
                new CopyConverterLong2DoubleTest(),
        };

        return res;
    }
}
