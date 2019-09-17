/**
 *
 */
package ru.aafanasiev.util.copier;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNotSame;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.aafanasiev.annotations.Converter;
import ru.aafanasiev.annotations.NameCalculator;
import ru.aafanasiev.util.copier.CopierBuilder.CheckCopyConverter;
import ru.aafanasiev.util.copier.CopierBuilder.CheckNameCalculator;
import ru.aafanasiev.util.copier.CopierBuilder.Checker;
import ru.aafanasiev.util.copier.compilator.CopyNameCalculator;
import ru.aafanasiev.util.copier.compilator.ObjectNameCalculator;
import ru.aafanasiev.util.copier.converter.CopyConverterObj2Obj;
import ru.aafanasiev.util.copier.converter.base.CopyConverter;
import ru.aafanasiev.util.copier.test.TestCopyConverter1;
import ru.aafanasiev.util.copier.test.TestCopyConverter2;
import ru.aafanasiev.util.copier.test.TestCopyConverter3;
import ru.aafanasiev.util.copier.test.TestCopyConverter4;
import ru.aafanasiev.util.copier.test.TestNameCalculator1;
import ru.aafanasiev.util.copier.test.TestNameCalculator2;
import ru.aafanasiev.util.copier.test.TestNameCalculator3;
import ru.aafanasiev.util.copier.test.TestNameCalculator4;
import ru.aafanasiev.util.copier.util.ReflectionUtils;

/**
 * @author aafanasyev
 */
@Test(groups = {"CopierBuilderTest", "UTILS"}, ignoreMissingDependencies = true, suiteName = "CopyObject")
public class CopierBuilderTest {
    /** Logger */
    private static final Logger log = LoggerFactory.getLogger(CopierBuilderTest.class);

    private static final String TEST_PACKAGE = "ru.aafanasiev.util.copier.test";
    private static final String BASE_CONVERTOR_PACKAGE = "ru.aafanasiev.util.copier.converter";
    private static final String BASE_COMPILATOR_PACKAGE = "ru.aafanasiev.util.copier.compilator";
    private static final int CONVERTERS_LIST_SIZE = 128;
    private static final int CALCULATORS_LIST_SIZE = 16;

    private CopierBuilder builder;
    private CheckCopyConverter checkCopyConverter = new CheckCopyConverter();
    private CheckNameCalculator checkNameCalculator = new CheckNameCalculator();

    @BeforeMethod
    public void createCopierBuilder() throws Exception {
        builder = CopierBuilder.builder();
    }

    /**
     * Test method for {@link ru.aafanasiev.util.copier.CopierBuilder#addNameCalculatorPackage(java.lang.String)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testAddCalculatorPackage() throws Exception {
        @SuppressWarnings("unchecked")
        List<String> nameCalculatorPackages = (List<String>) ReflectionUtils.getField(builder, "nameCalculatorPackages");
        assertNotNull(nameCalculatorPackages);
        assertTrue(nameCalculatorPackages.isEmpty());

        CopierBuilder res = builder.addNameCalculatorPackage(TEST_PACKAGE);
        assertSame(res, builder);
        assertEquals(nameCalculatorPackages.size(), 1);
        assertEquals(nameCalculatorPackages.get(0), TEST_PACKAGE);
    }

    /**
     * Test method for {@link ru.aafanasiev.util.copier.CopierBuilder#addCopyConverter(java.lang.Class)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testAddCopyConverter() throws Exception {
        @SuppressWarnings("unchecked")
        List<Class<? extends CopyConverter>> converterList =
                (List<Class<? extends CopyConverter>>) ReflectionUtils.getField(builder, "converterList");
        assertNotNull(converterList);
        assertTrue(converterList.isEmpty());

        CopierBuilder res = builder.addCopyConverter(TestCopyConverter1.class);
        assertSame(res, builder);
        assertEquals(converterList.size(), 1);
        assertEquals(converterList.get(0), TestCopyConverter1.class);
    }

    /**
     * Test method for {@link ru.aafanasiev.util.copier.CopierBuilder#addCopyConverterPackage(java.lang.String)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testAddCopyConverterPackage() throws Exception {
        @SuppressWarnings("unchecked")
        List<String> converterPackages = (List<String>) ReflectionUtils.getField(builder, "converterPackages");
        assertNotNull(converterPackages);
        assertTrue(converterPackages.isEmpty());

        CopierBuilder res = builder.addCopyConverterPackage(TEST_PACKAGE);
        assertSame(res, builder);
        assertEquals(converterPackages.size(), 1);
        assertEquals(converterPackages.get(0), TEST_PACKAGE);
    }

    /**
     * Test method for {@link ru.aafanasiev.util.copier.CopierBuilder#addInternalConvertors(java.util.List)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testAddInternalObjects() throws Exception {
        List<Class<? extends CopyConverter>> result = new ArrayList<>();
        assertTrue(result.isEmpty());

        Method method = ReflectionUtils.getMethod(builder.getClass(), "addInternalObjects",
                List.class, Class.class, Checker.class, String.class, Class.class);
        assertNotNull(method);
        ReflectionUtils.invokeMethod(builder, method, result, Converter.class,
                checkCopyConverter, BASE_CONVERTOR_PACKAGE, CopyConverterObj2Obj.class);
        assertFalse(result.isEmpty());
        log.debug("Internal converters number from directories: {}", result.size());
        assertEquals(result.get(result.size() - 1), CopyConverterObj2Obj.class);
    }

    /**
     * Test method for {@link ru.aafanasiev.util.copier.CopierBuilder#addNameCalculator(java.lang.Class)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testAddNameCalculator() throws Exception {
        @SuppressWarnings("unchecked")
        List<Class<? extends CopyNameCalculator>> nameCalculatorList =
                (List<Class<? extends CopyNameCalculator>>) ReflectionUtils.getField(builder, "nameCalculatorList");
        assertNotNull(nameCalculatorList);
        assertTrue(nameCalculatorList.isEmpty());

        CopierBuilder res = builder.addNameCalculator(TestNameCalculator1.class);
        assertSame(res, builder);
        assertEquals(nameCalculatorList.size(), 1);
        assertEquals(nameCalculatorList.get(0), TestNameCalculator1.class);
    }

    /**
     * Test method for {@link ru.aafanasiev.util.copier.CopierBuilder#addConverterpackages(java.util.List)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testAddPackage01() throws Exception {
        List<Class<? extends CopyConverter>> result = new ArrayList<>();
        assertTrue(result.isEmpty());

        Method method = ReflectionUtils.getMethod(builder.getClass(), "addPackage", List.class, String.class,
                Class.class, Checker.class);
        assertNotNull(method);
        ReflectionUtils.invokeMethod(builder, method, result, TEST_PACKAGE, Converter.class, checkCopyConverter);
        assertFalse(result.isEmpty());
        assertEquals(result.size(), 2);
        assertTrue(result.contains(TestCopyConverter1.class));
        assertFalse(result.contains(TestCopyConverter2.class));
        assertFalse(result.contains(TestCopyConverter3.class));
        assertTrue(result.contains(TestCopyConverter4.class));
    }

    /**
     * Test method for {@link ru.aafanasiev.util.copier.CopierBuilder#addConverterpackages(java.util.List)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testAddPackage02() throws Exception {
        List<Class<? extends CopyConverter>> result = new ArrayList<>();
        assertTrue(result.isEmpty());

        Method method = ReflectionUtils.getMethod(builder.getClass(), "addPackage", List.class, String.class,
                Class.class, Checker.class);
        assertNotNull(method);
        ReflectionUtils.invokeMethod(builder, method, result, TEST_PACKAGE, NameCalculator.class, checkNameCalculator);
        assertFalse(result.isEmpty());
        assertEquals(result.size(), 2);
        assertTrue(result.contains(TestNameCalculator1.class));
        assertFalse(result.contains(TestNameCalculator2.class));
        assertFalse(result.contains(TestNameCalculator3.class));
        assertTrue(result.contains(TestNameCalculator4.class));
    }

    /**
     * Test method for {@link ru.aafanasiev.util.copier.CopierBuilder#build()}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testBuild01() throws Exception {
        Copier copier1 = builder.build();
        assertNotNull(copier1);
        Copier copier2 = builder.build();
        assertNotNull(copier2);
        assertNotSame(copier1, copier2);

    }

    /**
     * Test method for {@link ru.aafanasiev.util.copier.CopierBuilder#buildConverters()}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true, dependsOnMethods = {"testGenerateConverters"})
    public void testBuildConverters01() throws Exception {
        CopierOptions options = new CopierOptions();
        List<CopyConverter> converters = getInternalConverters(options);

        Method method = ReflectionUtils.getMethod(builder.getClass(), "buildConverters", CopierOptions.class);
        assertNotNull(method);

        ReflectionUtils.invokeMethod(builder, method, options);
        List<CopyConverter> res = options.getCopyConverters();
        assertNotNull(res);
        assertEquals(res.size(), converters.size());

        /* Check clear converter list */
        ReflectionUtils.invokeMethod(builder, method, options);
        res = options.getCopyConverters();
        assertNotNull(res);
        assertEquals(res.size(), converters.size());
    }

    /**
     * Test method for {@link ru.aafanasiev.util.copier.CopierBuilder#buildConverters()}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testBuildConverters02() throws Exception {
        CopierOptions options = new CopierOptions();
        List<CopyConverter> converters = getInternalConverters(options);

        builder.addCopyConverter(TestCopyConverter1.class);
        builder.addCopyConverter(TestCopyConverter2.class);
        builder.addCopyConverter(TestCopyConverter4.class);
        Method method = ReflectionUtils.getMethod(builder.getClass(), "buildConverters", CopierOptions.class);
        assertNotNull(method);

        ReflectionUtils.invokeMethod(builder, method, options);
        List<CopyConverter> res = options.getCopyConverters();
        assertNotNull(res);
        assertEquals(res.size(), converters.size() + 2);
    }

    /**
     * Test method for {@link ru.aafanasiev.util.copier.CopierBuilder#buildConverters()}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testBuildConverters03() throws Exception {
        CopierOptions options = new CopierOptions();
        List<CopyConverter> converters = getInternalConverters(options);

        Method method = ReflectionUtils.getMethod(builder.getClass(), "buildConverters", CopierOptions.class);
        assertNotNull(method);

        builder.addCopyConverterPackage(TEST_PACKAGE);
        ReflectionUtils.invokeMethod(builder, method, options);
        List<CopyConverter> res = options.getCopyConverters();
        assertNotNull(res);
        assertEquals(res.size(), converters.size() + 1);
    }

    /**
     * Test method for {@link ru.aafanasiev.util.copier.CopierBuilder#buildConverters()}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testBuildNameCalculators01() throws Exception {
        CopierOptions options = new CopierOptions();
        List<CopyNameCalculator> calculators = getInternalNameCalculators(options);

        Method method = ReflectionUtils.getMethod(builder.getClass(), "buildNameCalculators", CopierOptions.class);
        assertNotNull(method);

        ReflectionUtils.invokeMethod(builder, method, options);
        List<CopyNameCalculator> res = options.getNameCalculators();
        assertNotNull(res);
        assertEquals(res.size(), calculators.size());

        /* Check clear converter list */
        ReflectionUtils.invokeMethod(builder, method, options);
        res = options.getNameCalculators();
        assertNotNull(res);
        assertEquals(res.size(), calculators.size());
    }

    /**
     * Test method for {@link ru.aafanasiev.util.copier.CopierBuilder#buildConverters()}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testBuildNameCalculators02() throws Exception {
        CopierOptions options = new CopierOptions();
        List<CopyNameCalculator> calculators = getInternalNameCalculators(options);

        builder.addNameCalculator(TestNameCalculator1.class);
        builder.addNameCalculator(TestNameCalculator2.class);
        builder.addNameCalculator(TestNameCalculator4.class);
        Method method = ReflectionUtils.getMethod(builder.getClass(), "buildNameCalculators", CopierOptions.class);
        assertNotNull(method);

        ReflectionUtils.invokeMethod(builder, method, options);
        List<CopyNameCalculator> res = options.getNameCalculators();
        assertNotNull(res);
        assertEquals(res.size(), calculators.size() + 2);
    }

    /**
     * Test method for {@link ru.aafanasiev.util.copier.CopierBuilder#buildConverters()}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testBuildNameCalculators03() throws Exception {
        CopierOptions options = new CopierOptions();
        List<CopyNameCalculator> calculators = getInternalNameCalculators(options);

        builder.addNameCalculatorPackage(TEST_PACKAGE);
        Method method = ReflectionUtils.getMethod(builder.getClass(), "buildNameCalculators", CopierOptions.class);
        assertNotNull(method);

        ReflectionUtils.invokeMethod(builder, method, options);
        List<CopyNameCalculator> res = options.getNameCalculators();
        assertNotNull(res);
        assertEquals(res.size(), calculators.size() + 1);
    }

    /**
     * Test method for {@link ru.aafanasiev.util.copier.CopierBuilder#checkCopyConverter(java.lang.Class)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testCheckCopyConverter() throws Exception {
        assertTrue(checkCopyConverter.check(CopyConverter.class));
        assertFalse(checkCopyConverter.check(CopierBuilder.class));
        assertTrue(checkCopyConverter.check(TestCopyConverter1.class));
        assertFalse(checkCopyConverter.check(TestCopyConverter3.class));
    }

    /**
     * Test method for {@link ru.aafanasiev.util.copier.CopierBuilder#checkCopyConverter(java.lang.Class)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testCheckNameCompilator() throws Exception {
        assertTrue(checkNameCalculator.check(CopyNameCalculator.class));
        assertFalse(checkNameCalculator.check(CopierBuilder.class));
        assertTrue(checkNameCalculator.check(TestNameCalculator1.class));
        assertFalse(checkNameCalculator.check(TestNameCalculator3.class));
    }

    /**
     * Test method for {@link ru.aafanasiev.util.copier.CopierBuilder#getInstance()}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testCopierBuilder() throws Exception {
        CopierBuilder builder1 = CopierBuilder.builder();
        assertNotSame(builder1, builder);
    }

    /**
     * Test method for {@link ru.aafanasiev.util.copier.CopierBuilder#generateConvertors(java.util.List)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testGenerateConverters() throws Exception {
        List<Class<? extends CopyConverter>> fullConverters = new ArrayList<>();
        assertTrue(fullConverters.isEmpty());

        Method method1 = ReflectionUtils.getMethod(builder.getClass(), "addInternalObjects",
                List.class, Class.class, CopierBuilder.Checker.class, String.class, Class.class);
        assertNotNull(method1);
        ReflectionUtils.invokeMethod(builder, method1, fullConverters, Converter.class, checkCopyConverter,
                BASE_CONVERTOR_PACKAGE, CopyConverterObj2Obj.class);

        Method method = ReflectionUtils.getMethod(builder.getClass(), "generateObjects", List.class, List.class);
        assertNotNull(method);
        List<CopyConverter> res = new ArrayList<>();
        ReflectionUtils.invokeMethod(builder, method, res, fullConverters);
        assertNotNull(res);
        assertEquals(res.size(), fullConverters.size());
        for(int i = 0; i < res.size(); i++) {
            assertEquals(res.get(i).getClass(), fullConverters.get(i));
        }

    }

    private List<CopyConverter> getInternalConverters(CopierOptions options) {
        List<Class<? extends CopyConverter>> fullConverters = new ArrayList<>(CONVERTERS_LIST_SIZE);

        Method method1 = ReflectionUtils.getMethod(builder.getClass(), "addInternalObjects",
                List.class, Class.class, Checker.class, String.class, Class.class);
        assertNotNull(method1);
        ReflectionUtils.invokeMethod(builder, method1, fullConverters, Converter.class,
                checkCopyConverter, BASE_CONVERTOR_PACKAGE, CopyConverterObj2Obj.class);

        Method method2 = ReflectionUtils.getMethod(builder.getClass(), "generateObjects", List.class, List.class);
        assertNotNull(method2);
        assertNotNull(options.getCopyConverters());
        assertTrue(options.getCopyConverters().isEmpty());
        ReflectionUtils.invokeMethod(builder, method2, options.getCopyConverters(), fullConverters);
        assertFalse(options.getCopyConverters().isEmpty());

        List<CopyConverter> converters = new ArrayList<>();
        converters.addAll(options.getCopyConverters());
        assertFalse(converters.isEmpty());
        assertEquals(converters.size(), fullConverters.size());

        return converters;
    }

    private List<CopyNameCalculator> getInternalNameCalculators(CopierOptions options) {
        List<Class<? extends NameCalculator>> fullConverters = new ArrayList<>(CALCULATORS_LIST_SIZE);

        Method method1 = ReflectionUtils.getMethod(builder.getClass(), "addInternalObjects",
                List.class, Class.class, Checker.class, String.class, Class.class);
        assertNotNull(method1);
        ReflectionUtils.invokeMethod(builder, method1, fullConverters, NameCalculator.class,
                checkNameCalculator, BASE_COMPILATOR_PACKAGE, ObjectNameCalculator.class);

        Method method2 = ReflectionUtils.getMethod(builder.getClass(), "generateObjects", List.class, List.class);
        assertNotNull(method2);
        assertNotNull(options.getNameCalculators());
        assertTrue(options.getNameCalculators().isEmpty());
        ReflectionUtils.invokeMethod(builder, method2, options.getNameCalculators(), fullConverters);
        assertFalse(options.getNameCalculators().isEmpty());

        List<CopyNameCalculator> calculators = new ArrayList<>();
        calculators.addAll(options.getNameCalculators());
        assertFalse(calculators.isEmpty());
        assertEquals(calculators.size(), fullConverters.size());

        return calculators;
    }
}
