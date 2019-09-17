/**
 *
 */
package ru.aafanasiev.util.copier.converter.cstring;

import org.testng.annotations.Factory;

public class UtilConvertorsStringTestSuite {
    @Factory
    public Object[] settingsSuite() {
        Object[] res = new Object[] {
                // String
                new CopyConverterStr2IntTest(),
                new CopyConverterStr2LongTest(),
                new CopyConverterStr2FloatTest(),
                new CopyConverterStr2DoubleTest(),
                new CopyConverterStr2BigDecimalTest(),
                new CopyConverterStr2BigIntTest(),
                new CopyConverterStr2BoolTest()
        };

        return res;
    }
}
