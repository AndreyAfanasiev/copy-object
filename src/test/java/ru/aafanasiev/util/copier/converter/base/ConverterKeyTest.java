/**
 *
 */
package ru.aafanasiev.util.copier.converter.base;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import ru.aafanasiev.util.copier.complex.Fint;
import ru.aafanasiev.util.copier.complex.Flong;

/**
 * @author aafanasyev
 */
@Test(groups = {"UTIL"}, ignoreMissingDependencies = true)
public class ConverterKeyTest {

    /**
     * Test method for {@link ru.aafanasiev.util.copier.converter.base.ConverterKey#hashCode()}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testHashCode() throws Exception {
        ConverterKey key = new ConverterKey(null, null);
        assertEquals(key.hashCode(), 0);

        key = new ConverterKey(Fint.class, Flong.class);
        assertFalse(key.isFromInstance());
        assertFalse(key.isToInstance());
        assertSame(key.getFromClazz(), Fint.class);
        assertSame(key.getToClazz(), Flong.class);
        assertEquals(key.hashCode(), 31 * Fint.class.hashCode() + Flong.class.hashCode());

        key = new ConverterKey(Fint.class, false, Flong.class, true);
        assertFalse(key.isFromInstance());
        assertTrue(key.isToInstance());
        assertSame(key.getFromClazz(), Fint.class);
        assertSame(key.getToClazz(), Flong.class);
        assertEquals(key.hashCode(),
                31 * 31 + 31 * Fint.class.hashCode() + Flong.class.hashCode());

        key = new ConverterKey(Fint.class, true, Flong.class, true);
        assertTrue(key.isFromInstance());
        assertTrue(key.isToInstance());
        assertEquals(key.hashCode(),
                31 * 31 * 31 + 31 * 31 + 31 * Fint.class.hashCode() + Flong.class.hashCode());
    }

    /**
     * Test method for {@link ru.aafanasiev.util.copier.converter.base.ConverterKey#equals(java.lang.Object)}.
     */
    @SuppressWarnings("unlikely-arg-type")
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testEquals() throws Exception {
        ConverterKey key1 = new ConverterKey(null, null);
        ConverterKey key2 = new ConverterKey(null, null);
        assertFalse(key1.equals(null));
        assertTrue(key1.equals(key2));
        assertTrue(key1.equals(key1));
        assertFalse(key1.equals(new CopyEntryImpl()));

        key2 = new ConverterKey(Fint.class, null);
        assertFalse(key1.equals(key2));

        key2 = new ConverterKey(null, Flong.class);
        assertFalse(key1.equals(key2));

        key1 = new ConverterKey(Fint.class, Flong.class);
        key2 = new ConverterKey(null, null);
        assertFalse(key1.equals(key2));

        key2 = new ConverterKey(Flong.class, null);
        assertFalse(key1.equals(key2));

        key2 = new ConverterKey(Fint.class, Fint.class);
        assertFalse(key1.equals(key2));

        key2 = new ConverterKey(Fint.class, Flong.class);
        assertTrue(key1.equals(key2));

        key1 = new ConverterKey(Fint.class, false, Flong.class, false);
        key2 = new ConverterKey(Fint.class, true, Flong.class, false);
        assertFalse(key1.equals(key2));

        key2 = new ConverterKey(Fint.class, false, Flong.class, true);
        assertFalse(key1.equals(key2));
    }

}
