/**
 *
 */
package ru.aafanasiev.util.copier.compilator;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/**
 * @author aafanasyev
 */
@Test
public class UtilCompilatorTestSuite {

    @Factory
    public Object[] settingsSuite() {
        Object[] res = new Object[] {
                new ObjectNameCalculatorTest(),
                new CollectionsParams()
        };

        return res;
    }
}
