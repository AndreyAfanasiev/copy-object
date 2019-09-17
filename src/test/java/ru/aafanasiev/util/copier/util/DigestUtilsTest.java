/**
 *
 */
package ru.aafanasiev.util.copier.util;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:aafanasyev@luxoft.com">aafanasyev</a>
 * @version 1.0 2017-04-19
 */
@Test(groups = {"DigestUtilTest", "UTILS"}, ignoreMissingDependencies = true)
public class DigestUtilsTest {
    @DataProvider(name = "digest")
    private Object[][] calendarTime() {
        return new Object[][] {
                {"a", "CA978112CA1BBDCAFAC231B39A23DC4DA786EFF8147C4E72B9807785AFEE48BB"},
                {"aafanasyev", "69ECDD81CFE98B0AB30DB6F0EDE5DC0F61ED1CEA8E7389207FAD205249F0757C"},
                {"apassword", "739145A8634B184276559A2F3055353DB3B261109649EF78445149415F0B4DEE"},
                {null, null}
        };
    }

    /**
     * Test method for {@link com.DigestUtils.mobile.server.util.DigestUtil#stringDigestSHA256(java.lang.String)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true, dataProvider = "digest")
    public void testStringDigestSHA256(String source, String result) throws Exception {
        String res = DigestUtils.stringDigestSHA256(source);

        assertEquals(res, result);
    }

}
