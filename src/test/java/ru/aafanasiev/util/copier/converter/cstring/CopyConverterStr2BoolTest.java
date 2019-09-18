/**
 *
 */
package ru.aafanasiev.util.copier.converter.cstring;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.aafanasiev.annotations.CopyString2Boolean;
import ru.aafanasiev.util.copier.converter.base.ConverterKey;
import ru.aafanasiev.util.copier.converter.cdouble.CopyConverterDoubleBase;
import ru.aafanasiev.util.copier.util.ReflectionUtils;

/**
 * @author aafanasyev
 */
@Test(groups = {"UTIL"}, ignoreMissingDependencies = true)
public class CopyConverterStr2BoolTest extends CopyConverterDoubleBase {
    private static final String[] TRUE_VALUE = {"true", "t", "yes", "y", "1"};
    private static final String[] FALSE_VALUE = {"false", "f", "no", "n", "0"};
    private static final String[] TRUE_ALT_VALUE = {"good", "ok"};
    private static final String[] FALSE_ALT_VALUE = {"bad"};

    @BeforeMethod
    public void intitialize() {
        convertor = new CopyConverterStr2Bool();
        init();
        entry.setParameters(new Str2BoolParams(TRUE_VALUE, FALSE_VALUE));
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.bool.CopyConverterBool2Int#convert(ru.aafanasiev.util.copier.converter.base.CopyEntry, java.lang.Object)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    @CopyString2Boolean(caseSensitive = true)
    public void testConvert() throws Exception {
        Boolean result = (Boolean) convertor.convert(entry, "false");
        assertEquals(result, Boolean.FALSE);
        result = (Boolean) convertor.convert(entry, "FALSE");
        assertEquals(result, Boolean.FALSE);
        result = (Boolean) convertor.convert(entry, "f");
        assertEquals(result, Boolean.FALSE);
        result = (Boolean) convertor.convert(entry, "no");
        assertEquals(result, Boolean.FALSE);
        result = (Boolean) convertor.convert(entry, "n");
        assertEquals(result, Boolean.FALSE);
        result = (Boolean) convertor.convert(entry, "0");
        assertEquals(result, Boolean.FALSE);

        result = (Boolean) convertor.convert(entry, "true");
        assertEquals(result, Boolean.TRUE);
        result = (Boolean) convertor.convert(entry, "TRUE");
        assertEquals(result, Boolean.TRUE);
        result = (Boolean) convertor.convert(entry, "t");
        assertEquals(result, Boolean.TRUE);
        result = (Boolean) convertor.convert(entry, "yes");
        assertEquals(result, Boolean.TRUE);
        result = (Boolean) convertor.convert(entry, "y");
        assertEquals(result, Boolean.TRUE);
        result = (Boolean) convertor.convert(entry, "1");
        assertEquals(result, Boolean.TRUE);

        ((Str2BoolParams) entry.getParameters()).setCaseSensitive(true);
        result = (Boolean) convertor.convert(entry, "FALSE");
        assertNull(result);
        result = (Boolean) convertor.convert(entry, "TRUE");
        assertNull(result);

        ((Str2BoolParams) entry.getParameters()).setCaseSensitive(false);
        ((Str2BoolParams) entry.getParameters()).setTrueValues(new String[] {"good"});
        ((Str2BoolParams) entry.getParameters()).setFalseValues(new String[] {"bad"});
        result = (Boolean) convertor.convert(entry, "bad");
        assertEquals(result, Boolean.FALSE);

        result = (Boolean) convertor.convert(entry, "good");
        assertEquals(result, Boolean.TRUE);
    }

    /**
     * Test method for {@link ru.aafanasiev.util.copier.converter.bool.CopyConverterBool2Int#listConvertedPairs()}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testListConvertedPairs() throws Exception {
        ConverterKey[] array = convertor.listConvertedPairs();
        assertEquals(array.length, 1);
        assertEquals(array[0].getFromClazz(), String.class);
        assertEquals(array[0].getToClazz(), Boolean.class);
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.bool.CopyConverterBool2Int#getParams(ru.aafanasiev.util.copier.converter.base.CopyEntry)
     * getParams(CopyEntry)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    @CopyString2Boolean(trueValue = {"good", "ok"}, falseValue = {"bad"})
    public void testGetParams() throws Exception {
        entry.setFromMethod(ReflectionUtils.getMethod(CopyConverterStr2BoolTest.class,
                "testListConvertedPairs"));
        assertNotNull(entry.getFromMethod());
        Object result = convertor.getParams(entry);
        assertNotNull(result);
        assertEquals(result.getClass(), Str2BoolParams.class);
        Str2BoolParams params = (Str2BoolParams) result;
        assertNotNull(params.getTrueValues());
        assertNotNull(params.getFalseValues());
        assertFalse(params.isCaseSensitive());
        assertEquals(params.getTrueValues().getClass(), String[].class);
        assertEquals(params.getFalseValues().getClass(), String[].class);
        assertEquals(params.getTrueValues().length, TRUE_VALUE.length);
        assertEquals(params.getFalseValues().length, FALSE_VALUE.length);
        for(int i = 0; i < TRUE_VALUE.length; i++) {
            assertEquals(params.getTrueValues()[i], TRUE_VALUE[i]);
        }
        for(int i = 0; i < FALSE_VALUE.length; i++) {
            assertEquals(params.getFalseValues()[i], FALSE_VALUE[i],
                    String.format("index=%d value=%s", i, params.getFalseValues()[i]));
        }

        entry.setFromMethod(ReflectionUtils.getMethod(CopyConverterStr2BoolTest.class,
                "testGetParams"));
        assertNotNull(entry.getFromMethod());
        result = convertor.getParams(entry);
        assertNotNull(result);
        assertEquals(result.getClass(), Str2BoolParams.class);
        params = (Str2BoolParams) result;
        assertNotNull(params.getTrueValues());
        assertNotNull(params.getFalseValues());
        assertFalse(params.isCaseSensitive());
        assertEquals(params.getTrueValues().getClass(), String[].class);
        assertEquals(params.getFalseValues().getClass(), String[].class);
        assertEquals(params.getTrueValues().length, TRUE_ALT_VALUE.length);
        assertEquals(params.getFalseValues().length, FALSE_ALT_VALUE.length);
        for(int i = 0; i < TRUE_ALT_VALUE.length; i++) {
            assertEquals(params.getTrueValues()[i], TRUE_ALT_VALUE[i],
                    String.format("index=%d value=%s", i, params.getTrueValues()[i]));
        }
        for(int i = 0; i < FALSE_ALT_VALUE.length; i++) {
            assertEquals(params.getFalseValues()[i], FALSE_ALT_VALUE[i],
                    String.format("index=%d value=%s", i, params.getFalseValues()[i]));
        }

        entry.setFromMethod(ReflectionUtils.getMethod(CopyConverterStr2BoolTest.class,
                "testConvert"));
        assertNotNull(entry.getFromMethod());
        result = convertor.getParams(entry);
        assertNotNull(result);
        assertEquals(result.getClass(), Str2BoolParams.class);
        params = (Str2BoolParams) result;
        assertNotNull(params.getTrueValues());
        assertNotNull(params.getFalseValues());
        assertTrue(params.isCaseSensitive());
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.cstring.CopyConverterStr2Bool#compareString(java.lang.String, java.lang.String[], boolean)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testCompareString() throws Exception {
        Method m = ReflectionUtils.getMethod(CopyConverterStr2Bool.class, "compareString",
                String.class, String[].class, boolean.class);
        assertNotNull(m);

        boolean result = (boolean) m.invoke(convertor, null, new String[] {"a"}, true);
        assertFalse(result);

        result = (boolean) m.invoke(convertor, "a", null, true);
        assertFalse(result);
    }
}
