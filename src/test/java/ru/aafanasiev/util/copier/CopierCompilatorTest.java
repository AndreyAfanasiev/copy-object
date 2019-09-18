/**
 *
 */
package ru.aafanasiev.util.copier;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.aafanasiev.annotations.CopyImmutable;
import ru.aafanasiev.util.copier.converter.CopyConverterSimple;
import ru.aafanasiev.util.copier.converter.base.ConverterKey;
import ru.aafanasiev.util.copier.converter.base.CopyConverter;
import ru.aafanasiev.util.copier.converter.base.CopyEntry;
import ru.aafanasiev.util.copier.converter.base.CopyEntryImpl;
import ru.aafanasiev.util.copier.converter.base.CopyErrorException;
import ru.aafanasiev.util.copier.converter.cint.CopyConverterInt2Long;
import ru.aafanasiev.util.copier.util.ReflectionUtils;

/**
 * @author aafanasyev
 */
@Test(groups = {"UTILS"}, ignoreMissingDependencies = true)
public class CopierCompilatorTest {
    CopierCompilator compilator;

    @BeforeMethod
    public void setup() {
        Copier copier = CopierBuilder.builder().build();
        compilator = new CopierCompilator(copier.getOptions());
    }

    /**
     * Test method for {@link CopierCompilator#compileCopy(CopyEntry)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testCompileCopy01() throws Exception {
        Method method = ReflectionUtils.getMethod(CopierCompilator.class, "compileCopy", CopyEntry.class);
        assertNotNull(method);

        CopyEntry entry = new CopyEntryImpl();

        assertFalse((Boolean) method.invoke(compilator, entry));

        Method toMethod = ReflectionUtils.getMethod(TestCopy.class, "setiField", int.class);
        assertNotNull(toMethod);
        entry.setToMethod(toMethod);
        assertFalse((Boolean) method.invoke(compilator, entry));

        Method fromMethod = ReflectionUtils.getMethod(TestCopy.class, "getiField");
        assertNotNull(fromMethod);
        entry.setFromMethod(fromMethod);
        assertTrue((Boolean) method.invoke(compilator, entry));

        Method immutableMethod = ReflectionUtils.getMethod(TestCopy.class, "getiFieldImmutable");
        assertNotNull(immutableMethod);
        entry.setFromMethod(immutableMethod);
        assertTrue((Boolean) method.invoke(compilator, entry));
        entry.setFromMethod(fromMethod);

        toMethod = ReflectionUtils.getMethod(TestCopy.class, "setiField2", int.class, int.class);
        assertNotNull(toMethod);
        entry.setToMethod(toMethod);
        assertFalse((Boolean) method.invoke(compilator, entry));

        toMethod = ReflectionUtils.getMethod(TestCopy.class, "setiField3", List.class);
        assertNotNull(toMethod);
        entry.setToMethod(toMethod);
        assertFalse((Boolean) method.invoke(compilator, entry));
    }

    /**
     * Test method for {@link CopierCompilator#compileCopyList(List)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testCompileCopyList() throws Exception {
        List<CopyEntry> entryList = new ArrayList<>();

        CopyEntry entry = new CopyEntryImpl();

        Method fromMethod = ReflectionUtils.getMethod(TestCopy.class, "getiField");
        Method toMethod = ReflectionUtils.getMethod(TestCopy.class, "setiField", int.class);
        assertNotNull(fromMethod);
        assertNotNull(toMethod);
        entry.setFromMethod(fromMethod);
        entry.setToMethod(toMethod);
        entryList.add(entry);

        entry = new CopyEntryImpl();
        toMethod = ReflectionUtils.getMethod(TestCopy.class, "setiField3", List.class);
        assertNotNull(fromMethod);
        assertNotNull(toMethod);
        entry.setFromMethod(fromMethod);
        entry.setToMethod(toMethod);
        entryList.add(entry);

        entryList = compilator.compileCopyList(entryList);

        assertEquals(entryList.size(), 1);
        assertEquals(entryList.get(0).getConverter().getClass(), CopyConverterSimple.class);

        entryList = compilator.compileCopyList(null);
        assertNotNull(entryList);
        assertEquals(entryList.size(), 0);
    }

    /**
     * Test method for {@link CopierCompilator#compileCopy(CopyEntry)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testGetConverter01() throws Exception {
        Method method = ReflectionUtils.getMethod(CopierCompilator.class, "getConverter", ConverterKey.class);
        assertNotNull(method);

        CopyConverter converter = (CopyConverter) method.invoke(compilator,
                new ConverterKey(String.class, String.class));
        assertEquals(converter.getClass(), CopyConverterSimple.class);

        converter = (CopyConverter) method.invoke(compilator,
                new ConverterKey(Integer.class, Long.class));
        assertEquals(converter.getClass(), CopyConverterInt2Long.class);
    }

    /**
     * Test method for {@link CopierCompilator#compileCopy(CopyEntry)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testGetConverter() throws Exception {
        Method mGetConverter = ReflectionUtils.getMethod(CopierCompilator.class, "getConverter", ConverterKey.class);
        TestConverter converter = new TestConverter();
        CopierOptions options = new CopierOptions();
        options.getCopyConverters().add(converter);
        CopierCompilator compilator = new CopierCompilator(options);

        ConverterKey entry = new ConverterKey(ArrayList.class, false, ArrayList.class, false);
        CopyConverter result = (CopyConverter) mGetConverter.invoke(compilator, entry);
        assertSame(result, converter);

        entry = new ConverterKey(ArrayList.class, false, Set.class, true);
        result = (CopyConverter) mGetConverter.invoke(compilator, entry);
        assertSame(result, converter);

        entry = new ConverterKey(LinkedList.class, true, ArrayList.class, false);
        result = (CopyConverter) mGetConverter.invoke(compilator, entry);
        assertSame(result, converter);

        entry = new ConverterKey(LinkedList.class, true, LinkedList.class, true);
        result = (CopyConverter) mGetConverter.invoke(compilator, entry);
        assertSame(result, converter);

        entry = new ConverterKey(Map.class, false, Map.class, false);
        result = (CopyConverter) mGetConverter.invoke(compilator, entry);
        assertNull(result);

        entry = new ConverterKey(ArrayList.class, false, Map.class, false);
        result = (CopyConverter) mGetConverter.invoke(compilator, entry);
        assertNull(result);

        entry = new ConverterKey(Map.class, true, Map.class, false);
        result = (CopyConverter) mGetConverter.invoke(compilator, entry);
        assertNull(result);

        entry = new ConverterKey(LinkedList.class, true, Map.class, false);
        result = (CopyConverter) mGetConverter.invoke(compilator, entry);
        assertNull(result);

        entry = new ConverterKey(LinkedList.class, true, Map.class, true);
        result = (CopyConverter) mGetConverter.invoke(compilator, entry);
        assertNull(result);
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.CopierCompilator#registerConverter(ru.aafanasiev.util.copier.converter.base.CopyConverter)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true,
            expectedExceptions = {CopyErrorException.class},
            expectedExceptionsMessageRegExp = "Duplucate converter for types.*")
    public void testRegisterConverterBad01() throws Exception {
        CopierOptions options = new CopierOptions();
        options.getCopyConverters().add(new TestConverterBad01());
        @SuppressWarnings("unused")
        CopierCompilator compilator = new CopierCompilator(options);
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.CopierCompilator#registerConverter(ru.aafanasiev.util.copier.converter.base.CopyConverter)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true,
            expectedExceptions = {CopyErrorException.class},
            expectedExceptionsMessageRegExp = "Duplucate converter for comparison.*")
    public void testRegisterConverterBad02() throws Exception {
        CopierOptions options = new CopierOptions();
        options.getCopyConverters().add(new TestConverterBad02());
        @SuppressWarnings("unused")
        CopierCompilator compilator = new CopierCompilator(options);
    }

    /**
     * Test method for {@link CopierCompilator#compileCopy(CopyEntry)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testSimpleType() throws Exception {
        Method method = ReflectionUtils.getMethod(CopierCompilator.class, "getSimpleType", Class.class);
        assertNotNull(method);

        Class<?> clazz = (Class<?>) method.invoke(compilator, String.class);
        assertEquals(clazz, String.class);

        clazz = (Class<?>) method.invoke(compilator, Integer.class);
        assertEquals(clazz, Integer.class);

        clazz = (Class<?>) method.invoke(compilator, int.class);
        assertEquals(clazz, Integer.class);

        clazz = (Class<?>) method.invoke(compilator, long.class);
        assertEquals(clazz, Long.class);

        clazz = (Class<?>) method.invoke(compilator, double.class);
        assertEquals(clazz, Double.class);

        clazz = (Class<?>) method.invoke(compilator, float.class);
        assertEquals(clazz, Float.class);

        clazz = (Class<?>) method.invoke(compilator, boolean.class);
        assertEquals(clazz, Boolean.class);
    }

    /**
     * Test coverter class
     *
     * @author aafanasyev
     */
    public static class TestConverter implements CopyConverter {

        /*
         * (non-Javadoc)
         * @see ru.aafanasiev.util.copier.converter.base.CopyConverter#convert(ru.aafanasiev.
         * util.copier.converter.base.CopyEntry, java.lang.Object)
         */
        @Override
        public Object convert(CopyEntry entry, Object value) {
            return null;
        }

        /*
         * (non-Javadoc)
         * @see ru.aafanasiev.util.copier.converter.base.CopyConverter#listConvertedPairs()
         */
        @Override
        public ConverterKey[] listConvertedPairs() {
            return new ConverterKey[] {
                    new ConverterKey(ArrayList.class, false, ArrayList.class, false),
                    new ConverterKey(ArrayList.class, false, Set.class, true),
                    new ConverterKey(LinkedList.class, true, ArrayList.class, false),
                    new ConverterKey(LinkedList.class, true, LinkedList.class, true)

            };
        }
    }

    /**
     * Test coverter class
     *
     * @author aafanasyev
     */
    public static class TestConverterBad01 implements CopyConverter {

        @Override
        public Object convert(CopyEntry entry, Object value) {
            return null;
        }

        @Override
        public ConverterKey[] listConvertedPairs() {
            return new ConverterKey[] {
                    new ConverterKey(ArrayList.class, false, ArrayList.class, false),
                    new ConverterKey(ArrayList.class, false, ArrayList.class, false)
            };
        }
    }

    /**
     * Test coverter class
     *
     * @author aafanasyev
     */
    public static class TestConverterBad02 implements CopyConverter {

        @Override
        public Object convert(CopyEntry entry, Object value) {
            return null;
        }

        @Override
        public ConverterKey[] listConvertedPairs() {
            return new ConverterKey[] {
                    new ConverterKey(ArrayList.class, false, ArrayList.class, true),
                    new ConverterKey(ArrayList.class, false, ArrayList.class, true)
            };
        }
    }

    @SuppressWarnings("unused")
    private class TestCopy {
        int iField;

        /**
         * @return the iField
         */
        public int getiField() {
            return iField;
        }

        /**
         * @return the iField
         */
        @CopyImmutable
        public int getiFieldImmutable() {
            return iField;
        }

        /**
         * @param iField the iField to set
         */
        public void setiField(int iField) {
            this.iField = iField;
        }

        /**
         * @param iField2 the iField2 to set
         */
        public void setiField2(int iField2, int well) {
            this.iField = iField2 + well;
        }

        /**
         * @param iField3 the iField3 to set
         */
        public void setiField3(List<Integer> iField3) {
            this.iField = iField3.get(0);
        }
    }
}
