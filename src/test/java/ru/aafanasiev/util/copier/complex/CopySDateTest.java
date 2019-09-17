/**
 *
 */
package ru.aafanasiev.util.copier.complex;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotSame;
import static org.testng.Assert.assertSame;

import java.sql.Date;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.aafanasiev.util.copier.Copier;
import ru.aafanasiev.util.copier.CopierBuilder;

/**
 * @author aafanasyev
 */
@Test(groups = {"UTILS"}, ignoreMissingDependencies = true)
public class CopySDateTest {
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
        Date now = new Date((new java.util.Date()).getTime());
        Date old = new Date(1000);
        Fsdate fsdate = new Fsdate(now);

        Fudate fudate2 = new Fudate(new java.util.Date(old.getTime()));
        assertEquals(fudate2.getF(), new java.util.Date(old.getTime()));
        copier.copy(fsdate, fudate2);
        assertEquals(fudate2.getF(), new java.util.Date(now.getTime()));

        Fsdate fsdate2 = new Fsdate(old);
        assertEquals(fsdate2.getF(), old);
        copier.copy(fsdate, fsdate2);
        assertEquals(fsdate2.getF(), now);
        assertSame(fsdate.getF(), fsdate2.getF());

        FsdateImmutable fsimm = new FsdateImmutable(now);
        fsdate2 = new Fsdate(old);
        assertEquals(fsdate2.getF(), old);
        copier.copy(fsimm, fsdate2);
        assertEquals(fsdate2.getF(), now);
        assertNotSame(fsdate2.getF(), fsimm.getF());
    }
}
