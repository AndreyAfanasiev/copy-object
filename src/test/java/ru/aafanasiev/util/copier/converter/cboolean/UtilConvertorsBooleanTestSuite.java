/**
 *
 */
package ru.aafanasiev.util.copier.converter.cboolean;

import org.testng.annotations.Factory;

public class UtilConvertorsBooleanTestSuite {
    @Factory
    public Object[] settingsSuite() {
        Object[] res = new Object[] {
                // Boolean
                new CopyConverterBool2IntTest(),
                new CopyConverterBool2LongTest(),
                new CopyConverterBool2BigIntTest(),
                new CopyConverterBool2BigDecimalTest(),
                new CopyConverterBool2StrTest()
        };

        return res;
    }
}
