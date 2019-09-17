/**
 *
 */
package ru.aafanasiev.util.copier.complex;

import static org.testng.Assert.assertEquals;

import java.math.BigDecimal;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.aafanasiev.util.copier.Copier;
import ru.aafanasiev.util.copier.CopierBuilder;

/**
 * @author aafanasyev
 */
@Test(groups = {"UTILS"}, ignoreMissingDependencies = true)
public class CopyFloatTest {
    private Copier copier;

    @BeforeMethod
    public void setup() {
        copier = CopierBuilder.builder().build();
    }

    /**
     * Test method for Integer copy
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testCopyLong2OtherObjects() throws Exception {
        Ffloat ffloat = new Ffloat(0);

        Ffloat ffloat2 = new Ffloat(1.0f);
        copier.copy(ffloat, ffloat2);
        assertEquals(ffloat2.getF(), 0f);

        Fdouble fdouble2 = new Fdouble(1.0d);
        copier.copy(ffloat, fdouble2);
        assertEquals(fdouble2.getF(), 0d);

        FbigDecimal fbigDecimal2 = new FbigDecimal(1l);
        copier.copy(ffloat, fbigDecimal2);
        assertEquals(fbigDecimal2.getF(), BigDecimal.valueOf(0f));

        Fstring fstring2 = new Fstring("1");
        copier.copy(ffloat, fstring2);
        assertEquals(fstring2.getF(), "0.0");
    }
}
