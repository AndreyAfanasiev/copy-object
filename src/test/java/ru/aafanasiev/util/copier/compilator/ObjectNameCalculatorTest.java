/**
 *
 */
package ru.aafanasiev.util.copier.compilator;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.aafanasiev.annotations.CopyTo;
import ru.aafanasiev.annotations.NoCopy;
import ru.aafanasiev.util.copier.converter.base.CopyEntry;
import ru.aafanasiev.util.copier.converter.base.CopyEntryImpl;
import ru.aafanasiev.util.copier.util.ReflectionUtils;

/**
 * @author aafanasyev
 */
@Test(groups = {"UTILS"}, ignoreMissingDependencies = true)
public class ObjectNameCalculatorTest {
    private ObjectNameCalculator calculator;
    private CopyEntry entry;

    @BeforeMethod
    public void initialize() {
        calculator = new ObjectNameCalculator();
        entry = new CopyEntryImpl();
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.ObjectNameCalculator#calculateToName(ru.aafanasiev.util.copier.converter.base.CopyEntry)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testCalculateToName() throws Exception {
        entry.setFromMethod(ReflectionUtils.getMethod(TestClass.class, "getTestInt"));
        assertTrue(calculator.calculateToName(entry));
        assertEquals(entry.getToName(), "setTestInt");

        entry.setFromMethod(ReflectionUtils.getMethod(TestClass.class, "getTestDouble"));
        assertTrue(calculator.calculateToName(entry));
        assertEquals(entry.getToName(), "setTestFloat");

        entry.setFromMethod(ReflectionUtils.getMethod(TestClass.class, "getTestDummy"));
        assertFalse(calculator.calculateToName(entry));

        entry.setFromMethod(ReflectionUtils.getMethod(TestClass.class, "get"));
        assertFalse(calculator.calculateToName(entry));

        entry.setFromMethod(ReflectionUtils.getMethod(TestClass.class, "getNoCopy"));
        assertFalse(calculator.calculateToName(entry));

        entry.setFromMethod(ReflectionUtils.getMethod(TestClass.class, "getAbstract"));
        assertFalse(calculator.calculateToName(entry));

        assertFalse(calculator.calculateToName(null));

        entry.setFromMethod(null);
        assertFalse(calculator.calculateToName(entry));
    }

    /**
     * Test method for {@link ru.aafanasiev.util.copier.converter.ObjectNameCalculator#returnClasses()}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testReturnClasses() throws Exception {
        assertEquals(calculator.returnClasses().getClass(), Class[].class);
        Class<?>[] result = calculator.returnClasses();
        assertEquals(result.length, 1);
        assertEquals(result[0], AllObjectsAndInterfaces.class);
    }

    /**
     * Test class
     *
     * @author aafanasyev
     */
    @SuppressWarnings("unused")
    private static abstract class TestClass {
        /**
         * Normal method fo test
         *
         * @return
         */
        public int getTestInt() {
            return 0;
        }

        /**
         * Set "CopyTo"
         *
         * @return
         */
        @CopyTo("testFloat")
        public double getTestDouble() {
            return 0d;
        }

        /**
         * Dummy "CopyTo" name
         *
         * @return
         */
        @CopyTo("")
        public float getTestDummy() {
            return 0f;
        }

        /**
         * Dummy "CopyTo" name
         *
         * @return
         */
        public String get() {
            return "";
        }

        /**
         * NoCopy annotation test
         */
        @NoCopy
        public Long getNoCopy() {
            return Long.valueOf(0L);
        }

        /**
         * NoCopy annotation test
         */
        public abstract Long getAbstract();
    }
}
