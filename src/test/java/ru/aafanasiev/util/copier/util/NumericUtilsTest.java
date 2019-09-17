/**
 *
 */
package ru.aafanasiev.util.copier.util;

import static org.testng.Assert.assertEquals;

import java.math.BigDecimal;

import org.testng.annotations.Test;

/**
 * @author aafanasyev
 */
@Test(groups = {"NumericUtilTest", "UTILS"}, ignoreMissingDependencies = true)
public class NumericUtilsTest {
    /**
     * Test method for {@link com.NumericUtils.bux.util.NumericUtil#getCurrency(java.lang.String)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testGetCurrency() throws Exception {
        assertEquals(NumericUtils.getCurrency("0"), BigDecimal.ZERO);
        assertEquals(NumericUtils.getCurrency("1"), BigDecimal.ONE);
        assertEquals(NumericUtils.getCurrency("1,25"), new BigDecimal("1.25"));
        assertEquals(NumericUtils.getCurrency("-1"), BigDecimal.ONE.negate());
        assertEquals(NumericUtils.getCurrency("1024"), new BigDecimal("1024"));
        assertEquals(NumericUtils.getCurrency("8192,36"), new BigDecimal("8192.36"));

        assertEquals(NumericUtils.getCurrency("1.25"), new BigDecimal("1"));
        assertEquals(NumericUtils.getCurrency("8192.36"), new BigDecimal("8192"));
        assertEquals(NumericUtils.getCurrency("8 192.36"), new BigDecimal("8"));
    }

    /**
     * Test method for {@link com.NumericUtils.bux.util.NumericUtil#getGroupingCurrency(java.lang.String)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testGetGroupingCurrency() throws Exception {
        assertEquals(NumericUtils.getGroupingCurrency("0"), BigDecimal.ZERO);
        assertEquals(NumericUtils.getGroupingCurrency("1"), BigDecimal.ONE);
        assertEquals(NumericUtils.getGroupingCurrency("1,25"), new BigDecimal("1.25"));
        assertEquals(NumericUtils.getGroupingCurrency("-1"), BigDecimal.ONE.negate());
        assertEquals(NumericUtils.getGroupingCurrency("1 024"), new BigDecimal("1024"));
        assertEquals(NumericUtils.getGroupingCurrency("8 192,36"), new BigDecimal("8192.36"));
    }

}
