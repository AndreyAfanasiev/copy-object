/**
 *
 */
package ru.aafanasiev;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import ru.aafanasiev.util.UtilTestSuite;
import ru.aafanasiev.util.copier.UtilCopierTestSuite;

/**
 * @author aafanasyev
 */
@Test
public class GlobalTestSuite {

    @Factory
    public Object[] settingsSuite() {
        Object[] res = new Object[] {
                new UtilTestSuite(),
                new UtilCopierTestSuite(),
                new UtilTestSuite()
        };

        return res;
    }
}
