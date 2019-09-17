/**
 *
 */
package ru.aafanasiev.util.copier.complex;

import static org.testng.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.aafanasiev.util.copier.Copier;
import ru.aafanasiev.util.copier.CopierBuilder;

/**
 * @author aafanasyev
 */
@Test(groups = {"UTILS"}, ignoreMissingDependencies = true)
public class CopyBigIntTest {
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
        FbigInt fbigInt = new FbigInt(0);

        Fint fint2 = new Fint(1);
        assertEquals(fint2.getF(), 1l);
        copier.copy(fbigInt, fint2);
        assertEquals(fint2.getF(), 0l);

        Flong flong2 = new Flong(1);
        copier.copy(fbigInt, flong2);
        assertEquals(flong2.getF(), 0l);

        Ffloat ffloat2 = new Ffloat(1.0f);
        copier.copy(fbigInt, ffloat2);
        assertEquals(ffloat2.getF(), 0f);

        Fdouble fdouble2 = new Fdouble(1.0d);
        copier.copy(fbigInt, fdouble2);
        assertEquals(fdouble2.getF(), 0d);

        FbigInt fbigInt2 = new FbigInt(1l);
        copier.copy(fbigInt, fbigInt2);
        assertEquals(fbigInt2.getF(), BigInteger.valueOf(0l));

        FbigDecimal fbigDecimal2 = new FbigDecimal(1l);
        copier.copy(fbigInt, fbigDecimal2);
        assertEquals(fbigDecimal2.getF(), BigDecimal.valueOf(0l));

        Fbool fbool2 = new Fbool(true);
        copier.copy(fbigInt, fbool2);
        assertEquals(fbool2.getF(), false);

        Fstring fstring2 = new Fstring("1");
        copier.copy(fbigInt, fstring2);
        assertEquals(fstring2.getF(), "0");
    }
}
