/**
 *
 */
package ru.aafanasiev.util.copier.converter.cfloat;

import org.testng.annotations.Factory;

public class UtilConvertorsFloatTestSuite {
    @Factory
    public Object[] settingsSuite() {
        Object[] res = new Object[] {
                // Float
                new CopyConverterFloat2DoubleTest(),
                new CopyConverterFloat2BigDecimalTest(),
                new CopyConverterFloat2StrTest()
        };

        return res;
    }
}
