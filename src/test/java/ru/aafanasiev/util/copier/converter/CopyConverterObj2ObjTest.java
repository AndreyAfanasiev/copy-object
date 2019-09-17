/**
 *
 */
package ru.aafanasiev.util.copier.converter;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.aafanasiev.annotations.CopyCustom;
import ru.aafanasiev.util.copier.converter.base.ConverterKey;
import ru.aafanasiev.util.copier.converter.base.CopyCustomConverter;
import ru.aafanasiev.util.copier.converter.base.CopyEntry;
import ru.aafanasiev.util.copier.util.ReflectionUtils;

/**
 * Test converter Object to Object
 *
 * @author aafanasyev
 */
@Test(groups = {"UTIL"}, ignoreMissingDependencies = true)
public class CopyConverterObj2ObjTest {
    private static final String CUSTOM_PARAMS = "a";
    private static final String VALUE = "b";

    private CopyConverterObj2Obj converter;

    @BeforeMethod
    public void inituialize() {
        converter = new CopyConverterObj2Obj();
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.CopyConverterObj2Obj#getParams(ru.aafanasiev.util.copier.converter.base.CopyEntry)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testGetParams01() throws Exception {
        CopyConverterObj2Obj converter = new CopyConverterObj2Obj();
        ;
        CopyEntry entry = new CopyEntry();
        entry.setConverter(converter);

        getTestInt(entry);

        converter.getParams(entry);
        assertNotNull(entry.getParameters());
        assertEquals(entry.getParameters().getClass(), Obj2ObjParams.class);
        Obj2ObjParams params = (Obj2ObjParams) entry.getParameters();
        assertNull(params.getConverter());
        assertNull(params.getParams());
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.CopyConverterObj2Obj#getParams(ru.aafanasiev.util.copier.converter.base.CopyEntry)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testGetParams02() throws Exception {
        CopyConverterObj2Obj converter = new CopyConverterObj2Obj();
        ;
        CopyEntry entry = new CopyEntry();
        entry.setConverter(converter);

        getTestEntry1(entry);

        converter.getParams(entry);
        assertNotNull(entry.getParameters());
        assertEquals(entry.getParameters().getClass(), Obj2ObjParams.class);
        Obj2ObjParams params = (Obj2ObjParams) entry.getParameters();
        assertNotNull(params.getConverter());
        assertEquals(params.getConverter().getClass(), TestCustomConverter1.class);
        assertNull(params.getParams());
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.CopyConverterObj2Obj#getParams(ru.aafanasiev.util.copier.converter.base.CopyEntry)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testGetParams03() throws Exception {
        CopyConverterObj2Obj converter = new CopyConverterObj2Obj();
        ;
        CopyEntry entry = new CopyEntry();
        entry.setConverter(converter);

        getTestEntry2(entry);

        converter.getParams(entry);
        assertNotNull(entry.getParameters());
        assertEquals(entry.getParameters().getClass(), Obj2ObjParams.class);
        Obj2ObjParams params = (Obj2ObjParams) entry.getParameters();
        assertNotNull(params.getConverter());
        assertEquals(params.getConverter().getClass(), TestCustomConverter2.class);
        assertNotNull(params.getParams());
        assertEquals(params.getParams(), CUSTOM_PARAMS);
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.CopyConverterObj2Obj#convert(ru.aafanasiev.util.copier.converter.base.CopyEntry, java.lang.Object)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testConvert01() throws Exception {
        CopyConverterObj2Obj converter = new CopyConverterObj2Obj();
        ;
        CopyEntry entry = new CopyEntry();
        entry.setConverter(converter);

        getTestInt(entry);

        Object result = converter.convert(entry, VALUE);
        assertEquals(result, VALUE);
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.CopyConverterObj2Obj#convert(ru.aafanasiev.util.copier.converter.base.CopyEntry, java.lang.Object)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testConvert02() throws Exception {
        CopyConverterObj2Obj converter = new CopyConverterObj2Obj();
        ;
        CopyEntry entry = new CopyEntry();
        entry.setConverter(converter);

        getTestEntry1(entry);

        Object result = converter.convert(entry, VALUE);
        assertEquals(result, VALUE);
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.CopyConverterObj2Obj#convert(ru.aafanasiev.util.copier.converter.base.CopyEntry, java.lang.Object)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testConvert03() throws Exception {
        CopyConverterObj2Obj converter = new CopyConverterObj2Obj();
        ;
        CopyEntry entry = new CopyEntry();
        entry.setConverter(converter);

        getTestEntry2(entry);

        Object result = converter.convert(entry, VALUE);
        assertEquals(result, VALUE + CUSTOM_PARAMS);
    }

    /**
     * Test method for {@link ru.aafanasiev.util.copier.converter.CopyConverterObj2Obj#listConvertedPairs()}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testListConvertedPairs() throws Exception {
        CopyConverterObj2Obj converter = new CopyConverterObj2Obj();
        ;
        CopyEntry entry = new CopyEntry();
        entry.setConverter(converter);

        assertEquals(converter.listConvertedPairs().getClass(), ConverterKey[].class);
        ConverterKey[] result = converter.listConvertedPairs();
        assertEquals(result.length, 1);
        assertEquals(result[0].getFromClazz(), Object.class);
        assertEquals(result[0].getToClazz(), Object.class);
        assertTrue(result[0].isFromInstance());
        assertTrue(result[0].isToInstance());
    }

    private Method getTestInt(CopyEntry entry) {
        Method result = ReflectionUtils.getMethod(TestClass.class, "getTestInt");
        assertNotNull(result);
        entry.setFromMethod(result);
        entry.setParameters(converter.getParams(entry));

        return result;
    }

    private Method getTestEntry1(CopyEntry entry) {
        Method result = ReflectionUtils.getMethod(TestClass.class, "getTestEntry1");
        assertNotNull(result);
        entry.setFromMethod(result);
        entry.setParameters(converter.getParams(entry));

        return result;
    }

    private Method getTestEntry2(CopyEntry entry) {
        Method result = ReflectionUtils.getMethod(TestClass.class, "getTestEntry2");
        assertNotNull(result);
        entry.setFromMethod(result);
        entry.setParameters(converter.getParams(entry));

        return result;
    }

    @SuppressWarnings("unused")
    private static class TestClass {

        public int getTestInt() {
            return 1;
        }

        @CopyCustom(TestCustomConverter1.class)
        public CopyEntry getTestEntry1() {
            return new CopyEntry();
        }

        @CopyCustom(TestCustomConverter2.class)
        public CopyEntry getTestEntry2() {
            return new CopyEntry();
        }
    }

    public static class TestCustomConverter1 implements CopyCustomConverter {

        @Override
        public Object convert(Method source, Method destination, Object params, Object value) {
            return value;
        }

        @Override
        public Object getParameters(Method source) {
            return null;
        }
    }

    public static class TestCustomConverter2 implements CopyCustomConverter {

        @Override
        public Object convert(Method source, Method destination, Object params, Object value) {
            return value + (String) params;
        }

        @Override
        public Object getParameters(Method source) {
            return CUSTOM_PARAMS;
        }
    }

}
