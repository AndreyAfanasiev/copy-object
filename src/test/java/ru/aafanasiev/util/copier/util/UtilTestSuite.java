/**
 *
 */
package ru.aafanasiev.util.copier.util;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/**
 * @author aafanasyev
 */
@Test
public class UtilTestSuite {

    @Factory
    public Object[] settingsSuite() {
        Object[] res = new Object[] {
                new MessagesBaseTest(),
                new ExceptionUtilsTest(),
                new DateTimeUtilsTest(),
                new DigestUtilsTest(),
                new NumericUtilsTest(),
                new ReflectionUtilsTest()
        };

        return res;
    }
}
