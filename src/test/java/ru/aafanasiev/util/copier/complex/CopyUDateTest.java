/**
 *
 */
package ru.aafanasiev.util.copier.complex;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotSame;
import static org.testng.Assert.assertSame;

import java.util.Date;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.aafanasiev.util.copier.Copier;
import ru.aafanasiev.util.copier.CopierBuilder;

/**
 * @author aafanasyev
 */
@Test(groups = {"UTILS"}, ignoreMissingDependencies = true)
public class CopyUDateTest {
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
        Date now = new Date();
        Date old = new Date(1000);
        Fudate fudate = new Fudate(now);

        Fudate fudate2 = new Fudate(old);
        assertEquals(fudate2.getF(), old);
        copier.copy(fudate, fudate2);
        assertEquals(fudate2.getF(), now);
        assertSame(fudate.getF(), fudate2.getF());

        FudateImmutable fuimm = new FudateImmutable(now);
        fudate2 = new Fudate(old);
        assertEquals(fudate2.getF(), old);
        copier.copy(fuimm, fudate2);
        assertEquals(fudate2.getF(), now);
        assertNotSame(fudate.getF(), fudate2.getF());

        Fsdate fsdate2 = new Fsdate(new java.sql.Date(old.getTime()));
        assertEquals(fsdate2.getF(), new java.sql.Date(old.getTime()));
        copier.copy(fudate, fsdate2);
        assertEquals(fsdate2.getF(), new java.sql.Date(now.getTime()));
    }
}
