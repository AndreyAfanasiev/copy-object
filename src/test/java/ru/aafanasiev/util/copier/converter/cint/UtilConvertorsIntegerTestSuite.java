/**
 *
 */
package ru.aafanasiev.util.copier.converter.cint;

import org.testng.annotations.Factory;

public class UtilConvertorsIntegerTestSuite {
    @Factory
    public Object[] settingsSuite() {
        Object[] res = new Object[] {
                // Integer
                new CopyConverterInt2StrTest(),
                new CopyConverterInt2LongTest(),
                new CopyConverterInt2BigDecimalTest(),
                new CopyConverterInt2BigIntTest(),
                new CopyConverterInt2BoolTest(),
                new CopyConverterInt2FloatTest(),
                new CopyConverterInt2DoubleTest()
        };

        return res;
    }
}
