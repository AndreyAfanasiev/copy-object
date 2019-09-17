/**
 *
 */
package ru.aafanasiev.util.copier;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.aafanasiev.util.copier.complex.Fint;
import ru.aafanasiev.util.copier.converter.CopyConverterSimple;
import ru.aafanasiev.util.copier.converter.base.CopyEntry;
import ru.aafanasiev.util.copier.converter.base.CopyErrorException;
import ru.aafanasiev.util.copier.util.ReflectionUtils;

/**
 * @author aafanasyev
 */
@Test(groups = {"UTILS"}, ignoreMissingDependencies = true)
public class CopierTest {
    Copier copier;

    @BeforeMethod
    public final void initialize() {
        copier = CopierBuilder.builder().build();
    }

    /**
     * Test method for {@link ru.aafanasiev.util.copier.Copier#copy(java.lang.Object, java.lang.Object)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testCopyObjectCopyBad01() throws Exception {
        Method m = ReflectionUtils.getMethod(Copier.class, "copyObject",
                Object.class, Object.class, List.class);
        assertNotNull(m);

        List<CopyEntry> algorithm = new ArrayList<>();

        CopyEntry entry = new CopyEntry();
        entry.setConverter(new CopyConverterSimple());
        entry.setFromMethod(ReflectionUtils.getMethod(TestClass.class, "getOne"));
        assertNotNull(entry.getFromMethod());
        entry.setToMethod(ReflectionUtils.getMethod(TestClass.class, "getOne"));
        assertNotNull(entry.getToMethod());
        entry.setToName("getOne");

        algorithm.add(entry);

        TestClass objFrom = new TestClass();
        TestClass objTo = new TestClass();
        objFrom.setOne(1);

        boolean executed = false;
        try {
            m.invoke(CopierBuilder.builder().build(), objFrom, objTo, algorithm);
        } catch(InvocationTargetException ex) {
            assertNotNull(ex);
            assertNotNull(ex.getTargetException());
            assertEquals(ex.getTargetException().getClass(), CopyErrorException.class);
            // assertEquals(ex.getTargetException().getMessage(),
            // "Ошибка при копировании из"
            // + " ru.aafanasiev.util.copier.CopierTest.TestClass(getOne) в"
            // + " ru.aafanasiev.util.copier.CopierTest.TestClass(getOne).");
            executed = true;
        }
        assertTrue(executed);
    }

    /**
     * Test method for {@link ru.aafanasiev.util.copier.Copier#copy(java.lang.Object, java.lang.Object)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testCopyObjectCopyOk01() throws Exception {
        Method m = ReflectionUtils.getMethod(Copier.class, "copyObject",
                Object.class, Object.class, List.class);
        assertNotNull(m);

        List<CopyEntry> algorithm = new ArrayList<>();

        CopyEntry entry = new CopyEntry();
        entry.setConverter(new CopyConverterSimple());
        entry.setFromMethod(ReflectionUtils.getMethod(TestClass.class, "getOne"));
        assertNotNull(entry.getFromMethod());
        entry.setToMethod(ReflectionUtils.getMethod(TestClass.class, "setOne", int.class));
        assertNotNull(entry.getToMethod());
        entry.setToName("setOne");

        algorithm.add(entry);

        TestClass objFrom = new TestClass();
        TestClass objTo = new TestClass();
        objFrom.setOne(1);

        m.invoke(CopierBuilder.builder().build(), objFrom, objTo, algorithm);

        assertEquals(objFrom.getOne(), objTo.getOne());
    }

    /**
     * Test method for {@link ru.aafanasiev.util.copier.Copier#copy(java.lang.Object, java.lang.Object)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true, dependsOnMethods = {
            "testCopyObjectObjectClazz"})
    public void testCopyObjectObject() throws Exception {
        Fint i1 = new Fint();
        Fint i2 = new Fint();
        i1.setF(1);
        i2.setF(3);

        assertSame(copier.copy(i1, i2), i2);
        assertEquals(i2.getF(), i1.getF());

        i1.setF(10);
        assertSame(copier.copy(i1, i2), i2);
        assertEquals(i2.getF(), i1.getF());
    }

    /**
     * Test method for {@link ru.aafanasiev.util.copier.Copier#copy(java.lang.Object, java.lang.Object)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testCopyObjectObjectClazz() throws Exception {
        Fint i1 = new Fint();
        Fint i2 = new Fint();
        i1.setF(1);
        i2.setF(3);

        assertSame(copier.copy(i1, i2, null), i2);
        assertEquals(i2.getF(), i1.getF());

        assertNull(copier.copy(null, i2, Object.class));
        assertNull(copier.copy(i2, null, Object.class));
    }

    public static class TestClass {
        int one;
        float two;

        /**
         * @return the one
         */
        public int getOne() {
            return one;
        }

        /**
         * @return the two
         */
        public float getTwo() {
            return two;
        }

        /**
         * @param one the one to set
         */
        public void setOne(int one) {
            this.one = one;
        }

        /**
         * @param two the two to set
         */
        public void setTwo(float two) {
            this.two = two;
        }
    }
}
