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
public class CopyBoolTest {
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
        Fbool fbool = new Fbool(false);

        Fint fint2 = new Fint(1);
        assertEquals(fint2.getF(), 1l);
        copier.copy(fbool, fint2);
        assertEquals(fint2.getF(), 0l);

        Flong flong2 = new Flong(1);
        copier.copy(fbool, flong2);
        assertEquals(flong2.getF(), 0l);

        FbigInt fbigInt2 = new FbigInt(1l);
        copier.copy(fbool, fbigInt2);
        assertEquals(fbigInt2.getF(), BigInteger.valueOf(0l));

        FbigDecimal fbigDecimal2 = new FbigDecimal(1l);
        copier.copy(fbool, fbigDecimal2);
        assertEquals(fbigDecimal2.getF(), BigDecimal.valueOf(0l));

        Fbool fbool2 = new Fbool(true);
        copier.copy(fbool, fbool2);
        assertEquals(fbool2.getF(), false);

        Fstring fstring2 = new Fstring("1");
        copier.copy(fbool, fstring2);
        assertEquals(fstring2.getF(), "false");
    }
}
