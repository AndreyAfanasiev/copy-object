/**
 *
 */
package ru.aafanasiev.util.copier;

import org.testng.annotations.Test;

/**
 * @author aafanasyev
 */
@Test(groups = {"UTILS"}, ignoreMissingDependencies = true, suiteName = "CopyObject")
public class CopierOptionsTest {

    /**
     * Test method for {@link ru.aafanasiev.util.copier.CopierOptions#getCopyConverters()}.
     */
    @Test(enabled = false, dependsOnMethods = {"testGetInstance"}, ignoreMissingDependencies = true)
    public void testGetCopyConverters() throws Exception {
        // TODO
    }

    /**
     * Test method for {@link ru.aafanasiev.util.copier.CopierOptions#getNameCalculators()}.
     */
    @Test(enabled = false, dependsOnMethods = {"testGetInstance"}, ignoreMissingDependencies = true)
    public void testGetNameCalculators() throws Exception {
        // TODO
    }

}
