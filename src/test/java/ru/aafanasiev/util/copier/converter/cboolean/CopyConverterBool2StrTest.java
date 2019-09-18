/**
 *
 */
package ru.aafanasiev.util.copier.converter.cboolean;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.aafanasiev.annotations.CopyBoolean2String;
import ru.aafanasiev.util.copier.converter.base.ConverterKey;
import ru.aafanasiev.util.copier.converter.cdouble.CopyConverterDoubleBase;
import ru.aafanasiev.util.copier.util.ReflectionUtils;

/**
 * @author aafanasyev
 */
@Test(groups = {"UTIL"}, ignoreMissingDependencies = true)
public class CopyConverterBool2StrTest extends CopyConverterDoubleBase {
    private static final String TRUE_VALUE = "true";
    private static final String FALSE_VALUE = "false";
    private static final String TRUE_ALT_VALUE = "ok";
    private static final String FALSE_ALT_VALUE = "no";

    @BeforeMethod
    public void intitialize() {
        convertor = new CopyConverterBool2Str();
        init();
        entry.setParameters(new Bool2StrParams(TRUE_VALUE, FALSE_VALUE));
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.bool.CopyConverterBool2Int#convert(ru.aafanasiev.util.copier.converter.base.CopyEntry, java.lang.Object)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testConvert() throws Exception {
        String result = (String) convertor.convert(entry, Boolean.FALSE);
        assertEquals(result, "false");

        result = (String) convertor.convert(entry, Boolean.TRUE);
        assertEquals(result, "true");

        ((Bool2StrParams) entry.getParameters()).setTrueValue("good");
        ((Bool2StrParams) entry.getParameters()).setFalseValue("bad");
        result = (String) convertor.convert(entry, Boolean.FALSE);
        assertEquals(result, "bad");

        result = (String) convertor.convert(entry, Boolean.TRUE);
        assertEquals(result, "good");
    }

    /**
     * Test method for {@link ru.aafanasiev.util.copier.converter.bool.CopyConverterBool2Int#listConvertedPairs()
     * listConvertedPairs()}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testListConvertedPairs() throws Exception {
        ConverterKey[] array = convertor.listConvertedPairs();
        assertEquals(array.length, 1);
        assertEquals(array[0].getFromClazz(), Boolean.class);
        assertEquals(array[0].getToClazz(), String.class);
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.copier.converter.bool.CopyConverterBool2Int#getParams(ru.aafanasiev.util.copier.converter.base.CopyEntry)
     * getParams(CopyEntry)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    @CopyBoolean2String(trueValue = TRUE_ALT_VALUE, falseValue = FALSE_ALT_VALUE)
    public void testGetParams() throws Exception {
        entry.setFromMethod(ReflectionUtils.getMethod(CopyConverterBool2StrTest.class,
                "testListConvertedPairs"));
        assertNotNull(entry.getFromMethod());
        Object result = convertor.getParams(entry);
        assertNotNull(result);
        assertEquals(result.getClass(), Bool2StrParams.class);
        Bool2StrParams params = (Bool2StrParams) result;
        assertEquals(params.getTrueValue(), TRUE_VALUE);
        assertEquals(params.getFalseValue(), FALSE_VALUE);

        entry.setFromMethod(ReflectionUtils.getMethod(CopyConverterBool2StrTest.class,
                "testGetParams"));
        assertNotNull(entry.getFromMethod());
        result = convertor.getParams(entry);
        assertNotNull(result);
        assertEquals(result.getClass(), Bool2StrParams.class);
        params = (Bool2StrParams) result;
        assertEquals(params.getTrueValue(), TRUE_ALT_VALUE);
        assertEquals(params.getFalseValue(), FALSE_ALT_VALUE);
    }
}
