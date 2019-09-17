/**
 *
 */
package ru.aafanasiev.util.copier.converter.cdouble;

import org.testng.annotations.Factory;

public class UtilConvertorsDoubleTestSuite {
    @Factory
    public Object[] settingsSuite() {
        Object[] res = new Object[] {
                // Double
                new CopyConverterDouble2FloatTest(),
                new CopyConverterDouble2BigDecimalTest(),
                new CopyConverterDouble2StrTest()
        };

        return res;
    }
}
