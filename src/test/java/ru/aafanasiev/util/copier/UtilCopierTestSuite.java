/**
 *
 */
package ru.aafanasiev.util.copier;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import ru.aafanasiev.util.copier.compilator.UtilCompilatorTestSuite;
import ru.aafanasiev.util.copier.complex.CopyBigDecimalTest;
import ru.aafanasiev.util.copier.complex.CopyBigIntTest;
import ru.aafanasiev.util.copier.complex.CopyBoolTest;
import ru.aafanasiev.util.copier.complex.CopyDoubleTest;
import ru.aafanasiev.util.copier.complex.CopyFloatTest;
import ru.aafanasiev.util.copier.complex.CopyIntTest;
import ru.aafanasiev.util.copier.complex.CopyLongTest;
import ru.aafanasiev.util.copier.complex.CopySDateTest;
import ru.aafanasiev.util.copier.complex.CopyStringTest;
import ru.aafanasiev.util.copier.complex.CopyUDateTest;
import ru.aafanasiev.util.copier.converter.UtilConvertorsTestSuite;
import ru.aafanasiev.util.copier.converter.base.ConverterKeyTest;

/**
 * @author aafanasyev
 */
@Test
public class UtilCopierTestSuite {

    @Factory
    public Object[] settingsSuite() {
        Object[] res = new Object[] {
                new CopierOptionsTest(),
                new ConverterKeyTest(),
                new CopierTest(),
                new CopierAnalyzeTest(),
                new CopierCompilatorTest(),
                new CopierBuilderTest(),
                /* Compilators */
                new UtilCompilatorTestSuite(),
                /* Convertors */
                new UtilConvertorsTestSuite(),
                /* Complex tests */
                new CopyIntTest(),
                new CopyLongTest(),
                new CopyFloatTest(),
                new CopyDoubleTest(),
                new CopyBigIntTest(),
                new CopyBigDecimalTest(),
                new CopyBoolTest(),
                new CopySDateTest(),
                new CopyUDateTest(),
                new CopyStringTest()
        };

        return res;
    }
}
