/**
 *
 */
package ru.aafanasiev.util.copier.converter;

import org.testng.annotations.Factory;

import ru.aafanasiev.util.copier.converter.cbigdecimal.UtilConvertorsBigDecimalTestSuite;
import ru.aafanasiev.util.copier.converter.cbiginteger.UtilConvertorsBigIntegerTestSuite;
import ru.aafanasiev.util.copier.converter.cboolean.UtilConvertorsBooleanTestSuite;
import ru.aafanasiev.util.copier.converter.cdate.UtilConvertorsDateTestSuite;
import ru.aafanasiev.util.copier.converter.cdouble.UtilConvertorsDoubleTestSuite;
import ru.aafanasiev.util.copier.converter.cfloat.UtilConvertorsFloatTestSuite;
import ru.aafanasiev.util.copier.converter.cint.UtilConvertorsIntegerTestSuite;
import ru.aafanasiev.util.copier.converter.clocaldate.UtilConvertorsLocalDateTestSuite;
import ru.aafanasiev.util.copier.converter.clong.UtilConvertorsLongTestSuite;
import ru.aafanasiev.util.copier.converter.cstring.UtilConvertorsStringTestSuite;

public class UtilConvertorsTestSuite {
    @Factory
    public Object[] settingsSuite() {
        Object[] res = new Object[] {
                // Suctom converter cache
                new CustomConverterCacheTest(),
                // Simple copy
                new CopyConverterSimpleTest(),
                // Integer
                new UtilConvertorsIntegerTestSuite(),
                // Long
                new UtilConvertorsLongTestSuite(),
                // Float
                new UtilConvertorsFloatTestSuite(),
                // Double
                new UtilConvertorsDoubleTestSuite(),
                // String
                new UtilConvertorsStringTestSuite(),
                // Date
                new UtilConvertorsDateTestSuite(),
                // Boolean
                new UtilConvertorsBooleanTestSuite(),
                // BigDecimal
                new UtilConvertorsBigDecimalTestSuite(),
                // BigInteger
                new UtilConvertorsBigIntegerTestSuite(),
                // LocalDate
                new UtilConvertorsLocalDateTestSuite(),
                // Object
                new CopyConverterObj2ObjTest()
        };

        return res;
    }
}
