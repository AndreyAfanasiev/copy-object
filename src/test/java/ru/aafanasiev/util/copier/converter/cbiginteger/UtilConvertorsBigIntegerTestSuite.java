/**
 *
 */
package ru.aafanasiev.util.copier.converter.cbiginteger;

import org.testng.annotations.Factory;

public class UtilConvertorsBigIntegerTestSuite {
    @Factory
    public Object[] settingsSuite() {
        Object[] res = new Object[] {
                // BigInteger
                new CopyConverterBigInt2BigDecimalTest(),
                new CopyConverterBigInt2BoolTest(),
                new CopyConverterBigInt2DoubleTest(),
                new CopyConverterBigInt2FloatTest(),
                new CopyConverterBigInt2IntTest(),
                new CopyConverterBigInt2LongTest(),
                new CopyConverterBigInt2StrTest()
        };

        return res;
    }
}
