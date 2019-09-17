/**
 *
 */
package ru.aafanasiev.util.copier.complex;

import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertSame;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.aafanasiev.util.copier.Copier;
import ru.aafanasiev.util.copier.CopierBuilder;

/**
 * @author aafanasyev
 */
@Test(groups = {"UTILS"}, ignoreMissingDependencies = true)
public class CopyObjTest {
    private Copier copier;

    @BeforeMethod
    public void setup() {
        copier = CopierBuilder.builder().build();
    }

    /**
     * Test method for Integer copy
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testCopyInt2OtherObjects() throws Exception {
        Custom1 custom1 = new Custom1(10);
        Fobj1 fobj1 = new Fobj1(custom1);
        Custom1 custom1a = new Custom1(5);
        Fobj1 fobj1a = new Fobj1(custom1a);

        Custom2 custom2 = new Custom2(5);
        Fobj2 fobj2 = new Fobj2(custom2);

        copier.copy(fobj1, fobj1a);
        assertSame(fobj1a.getF(), fobj1.getF());

        copier.copy(fobj1, fobj2);
        assertNull(fobj2.getF());
    }
}
