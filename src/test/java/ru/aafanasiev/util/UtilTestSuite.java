/**
 *
 */
package ru.aafanasiev.util;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import ru.aafanasiev.util.copier.util.DateTimeUtilsTest;
import ru.aafanasiev.util.copier.util.DigestUtilsTest;
import ru.aafanasiev.util.copier.util.ExceptionUtilsTest;
import ru.aafanasiev.util.copier.util.MessagesBaseTest;
import ru.aafanasiev.util.copier.util.NumericUtilsTest;
import ru.aafanasiev.util.copier.util.ReflectionUtilsTest;

/**
 * @author aafanasyev
 */
@Test
public class UtilTestSuite {

    @Factory
    public Object[] settingsSuite() {
        Object[] res = new Object[] {
                new DateTimeUtilsTest(),
                new DigestUtilsTest(),
                new ExceptionUtilsTest(),
                new MessagesBaseTest(),
                new NumericUtilsTest(),
                new ReflectionUtilsTest()
        };

        return res;
    }
}
