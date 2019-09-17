/**
 *
 */
package ru.aafanasiev.util.copier.util;

import static org.testng.Assert.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.testng.annotations.Test;

/**
 * @author aafanasyev
 */
@Test(groups = {"ExceptionUtilTest", "UTILS"}, ignoreMissingDependencies = true)
public class ExceptionUtilsTest {
    /** Logger */
    private static final Logger log = LoggerFactory.getLogger(ExceptionUtilsTest.class);

    /**
     * Test method for {@link ru.aafanasiev.util.ExceptionUtils#getExceptionStack(java.lang.Throwable)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testGetExceptionStack() throws Exception {
        Throwable ex = new Exception("Test Exception");

        String res = ExceptionUtils.getExceptionStack(ex);
        log.debug("Exception stack:\n{}", res);
        Pattern pattern1 = Pattern.compile("^java\\.lang\\.Exception: Test Exception$", Pattern.MULTILINE);
        Matcher matcher1 = pattern1.matcher(res);
        log.trace("matcher1: matches={} lookingAt={} groups={}",
                matcher1.matches(), matcher1.lookingAt(), matcher1.groupCount());
        assertTrue(matcher1.lookingAt());
        assertTrue(res.indexOf(
                "at ru.aafanasiev.util.copier.util.ExceptionUtilsTest.testGetExceptionStack(ExceptionUtilsTest.java:") > -1);
    }

}
