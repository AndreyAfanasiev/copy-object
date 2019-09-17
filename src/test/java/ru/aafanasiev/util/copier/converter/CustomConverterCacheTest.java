/**
 *
 */
package ru.aafanasiev.util.copier.converter;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.testng.annotations.Test;

import ru.aafanasiev.util.copier.util.ReflectionUtils;
import ru.aafanasiev.util.copier.converter.CustomConverterCache;
import ru.aafanasiev.util.copier.converter.base.CopyCustomConverter;
import ru.aafanasiev.util.copier.converter.base.CopyErrorException;

/**
 * @author aafanasyev
 */
@Test(groups = {"UTIL"}, ignoreMissingDependencies = true)
public class CustomConverterCacheTest {

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.CustomConverterCache#getInstance()}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testGetInstance() throws Exception {
        assertNotNull(CustomConverterCache.getInstance());
        assertSame(CustomConverterCache.getInstance(), CustomConverterCache.getInstance());
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.CustomConverterCache#getConverter(java.lang.Class)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testGetConverterOk() throws Exception {
        assertNotNull(CustomConverterCache.getInstance().getConverter(GoodTestConverter.class));
        assertSame(CustomConverterCache.getInstance().getConverter(GoodTestConverter.class),
                CustomConverterCache.getInstance().getConverter(GoodTestConverter.class));
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.CustomConverterCache#getConverter(java.lang.Class)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true,
            expectedExceptions = {CopyErrorException.class})
    public void testGetConverterBad() throws Exception {
        CustomConverterCache.getInstance().getConverter(BadTestConverter.class);
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.CustomConverterCache#getConverter(java.lang.Class)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testCreateClazzOk() throws Exception {
        Method m = ReflectionUtils.getMethod(CustomConverterCache.class, "createClazz",
                Class.class);
        assertNotNull(m);

        Object result = m.invoke(CustomConverterCache.getInstance(), GoodTestConverter.class);
        assertNotNull(result);
        assertEquals(result.getClass(), GoodTestConverter.class);

        boolean executed = false;
        try {
            m.invoke(CustomConverterCache.getInstance(), BadTestConverter.class);
        } catch(InvocationTargetException ex) {
            assertNotNull(ex);
            assertNotNull(ex.getTargetException());
            assertEquals(ex.getTargetException().getClass(), CopyErrorException.class);
            executed = true;
        }
        assertTrue(executed);
    }

    /**
     * Good test converter
     *
     * @author aafanasyev
     */
    public static class GoodTestConverter implements CopyCustomConverter {
        /**
         * Public default constructor
         */
        public GoodTestConverter() {
        }

        /*
         * (non-Javadoc)
         * @see ru.aafanasiev.util.CopyCustomConverter#convert(java.lang.reflect.Method,
         * java.lang.reflect.Method, java.lang.Object, java.lang.Object)
         */
        @Override
        public Object convert(Method source, Method destination, Object params, Object value) {
            return value;
        }

        /*
         * (non-Javadoc)
         * @see
         * ru.aafanasiev.util.CopyCustomConverter#getParameters(java.lang.reflect.Method)
         */
        @Override
        public Object getParameters(Method source) {
            return null;
        }
    }

    /**
     * Good test converter
     *
     * @author aafanasyev
     */
    public static class BadTestConverter implements CopyCustomConverter {
        /**
         * Private default constructor
         */
        private BadTestConverter() {
        }

        /*
         * (non-Javadoc)
         * @see ru.aafanasiev.util.CopyCustomConverter#convert(java.lang.reflect.Method,
         * java.lang.reflect.Method, java.lang.Object, java.lang.Object)
         */
        @Override
        public Object convert(Method source, Method destination, Object params, Object value) {
            return value;
        }

        /*
         * (non-Javadoc)
         * @see
         * ru.aafanasiev.util.CopyCustomConverter#getParameters(java.lang.reflect.Method)
         */
        @Override
        public Object getParameters(Method source) {
            return null;
        }
    }
}
