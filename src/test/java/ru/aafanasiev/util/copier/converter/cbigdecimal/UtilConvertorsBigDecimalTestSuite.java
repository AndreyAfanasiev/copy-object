/**
 *
 */
package ru.aafanasiev.util.copier.converter.cbigdecimal;

import org.testng.annotations.Factory;

public class UtilConvertorsBigDecimalTestSuite {
    @Factory
    public Object[] settingsSuite() {
        Object[] res = new Object[] {
                // BigDecimal
                new CopyConverterBigDecimal2BigIntTest(),
                new CopyConverterBigDecimal2BoolTest(),
                new CopyConverterBigDecimal2DoubleTest(),
                new CopyConverterBigDecimal2FloatTest(),
                new CopyConverterBigDecimal2IntTest(),
                new CopyConverterBigDecimal2LongTest(),
                new CopyConverterBigDecimal2StrTest()
        };

        return res;
    }
}
