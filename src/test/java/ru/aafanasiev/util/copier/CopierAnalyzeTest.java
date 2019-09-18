/**
 *
 */
package ru.aafanasiev.util.copier;

import static org.junit.Assert.assertNotNull;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.aafanasiev.util.copier.compilator.CopyNameCalculator;
import ru.aafanasiev.util.copier.compilator.ObjectNameCalculator;
import ru.aafanasiev.util.copier.converter.base.CopyEntry;
import ru.aafanasiev.util.copier.converter.base.CopyEntryImpl;
import ru.aafanasiev.util.copier.util.ReflectionUtils;

/**
 * @author aafanasyev
 */
@Test(groups = {"UTILS"}, ignoreMissingDependencies = true)
public class CopierAnalyzeTest {

    private CopierAnalyze analyze;

    @BeforeMethod
    public void setup() {
        Copier copier = CopierBuilder.builder().build();
        analyze = new CopierAnalyze(copier.getOptions());
    }

    /**
     * Test method for {@link CopierAnalyze#addMethods(List, Class, Class)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testAddMethods() throws Exception {
        Method method = ReflectionUtils.getMethod(CopierAnalyze.class,
                "addMethods", List.class, Class.class, Class.class);

        // Test01
        List<Method> result = new ArrayList<>();
        method.invoke(analyze, result, ZeroLevelObj.class, Object.class);

        checkMethodEntryList(result, 16, 17, new String[] {
                "getIndex", "setIndex", "getAbs", "setAbs",
                "isBool", "setBool", "getStr", "setStr",
                "getStr2", "setStr2", "getAbs2", "setAbs2",
                "get", "is", "set", "getDummy"
        });

        // Test02
        result = new ArrayList<>();
        method.invoke(analyze, result, FirstLevelObj.class, Object.class);

        checkMethodEntryList(result, 6, 7, new String[] {
                "getAbs", "setAbs", "isBool", "setBool", "get", "set"
        });

        // Test03
        result = new ArrayList<>();
        method.invoke(analyze, result, SecondLevelObj.class, Object.class);

        checkMethodEntryList(result, 8, 10, new String[] {
                "getAbs", "setAbs", "isBool", "setBool", "get", "set"
        });

        // Test04
        result = new ArrayList<>();
        method.invoke(analyze, result, SecondLevelObj.class, FirstLevelObj.class);

        checkMethodEntryList(result, 2, 3, new String[] {
                "getAbs", "setAbs"
        });
    }

    /**
     * Test method for {@link CopierAnalyze#findSetter(CopyEntry, List)}.
     */
    @SuppressWarnings("unchecked")
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testFindSetter() throws Exception {
        Method method = ReflectionUtils.getMethod(CopierAnalyze.class,
                "findSetter", CopyEntry.class, Map.class);
        Method methodMap = ReflectionUtils.getMethod(CopierAnalyze.class,
                "createToMap", List.class);

        Map<String, Method> toMap = new HashMap<>();
        CopyEntry copyEntry = new CopyEntryImpl();
        // "getIndex", "setIndex", "getAbs", "setAbs",
        // "isBool", "setBool", "getStr", "setStr",
        // "getStr2", "setStr2", "getAbs2", "setAbs2",
        // "get", "is", "set", "getDummy"

        // Test01
        assertFalse(((Boolean) method.invoke(analyze, null, toMap)).booleanValue());

        // Test02
        assertFalse(((Boolean) method.invoke(analyze, copyEntry, null)).booleanValue());

        // Test02
        assertFalse(((Boolean) method.invoke(analyze, copyEntry, toMap)).booleanValue());

        // Test03
        List<Method> toMethods = analyze.getMethods(ZeroLevelObj.class, Object.class);
        toMap = (Map<String, Method>) methodMap.invoke(analyze, toMethods);
        // Test04
        copyEntry = new CopyEntryImpl(ZeroLevelObj.class.getMethod("getIndex"), "setWork", null);
        assertFalse(((Boolean) method.invoke(analyze, copyEntry, toMap)).booleanValue());

        copyEntry = new CopyEntryImpl(ZeroLevelObj.class.getMethod("getIndex"), "setIndex", null);
        assertTrue(((Boolean) method.invoke(analyze, copyEntry, toMap)).booleanValue());
        assertNotNull(copyEntry.getToMethod());
        assertEquals(copyEntry.getToMethod(), ZeroLevelObj.class.getMethod("setIndex", int.class));

        // Test05
        copyEntry = new CopyEntryImpl(ZeroLevelObj.class.getMethod("getIndex"), "setWork", null);
        assertFalse(((Boolean) method.invoke(analyze, copyEntry, toMap)).booleanValue());

        // Test06
        toMethods = analyze.getMethods(FirstLevelObj.class, Object.class);
        toMap = (Map<String, Method>) methodMap.invoke(analyze, toMethods);
        copyEntry = new CopyEntryImpl(FirstLevelObj.class.getMethod("getAbs"), "setAbs", null);
        assertFalse(((Boolean) method.invoke(analyze, copyEntry, toMap)).booleanValue());
    }

    /**
     * Test method for {@link CopierAnalyze#getMethods(Class, Class)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testGetMethods() throws Exception {
        List<Method> result = analyze.getMethods(ZeroLevelObj.class, Object.class);

        checkMethodEntryList(result, 16, 17, new String[] {
                "getIndex", "setIndex", "getAbs", "setAbs",
                "isBool", "setBool", "getStr", "setStr",
                "getStr2", "setStr2", "getAbs2", "setAbs2",
                "get", "is", "set", "getDummy"
        });

        result = analyze.getMethods(List.class, Object.class);
        assertEquals(result.size(), 23);
    }

    /**
     * Test method for {@link CopierAnalyze#createToMap(List)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testCreateToMap() throws Exception {
        List<Method> entryList = new ArrayList<>();

        Method method1 = Test1.class.getMethod("setVal", int.class);
        assertNotNull(method1);
        entryList.add(method1);

        Method method2 = Test2.class.getMethod("setVal", int.class);
        assertNotNull(method2);
        entryList.add(method2);

        Method m = ReflectionUtils.getMethod(CopierAnalyze.class, "createToMap", List.class);
        assertNotNull(m);

        @SuppressWarnings("unchecked")
        Map<String, Method> res = (Map<String, Method>) m.invoke(analyze, entryList);
        assertEquals(res.size(), 1);
        assertEquals(res.get("setVal").getDeclaringClass(), Test1.class);
    }

    /**
     * Test method for {@link CopierAnalyze#processMethod(MethodEntry, List)}.
     */
    @SuppressWarnings("unchecked")
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testProcessMethod() throws Exception {
        Method methodAnalyze = ReflectionUtils.getMethod(CopierAnalyze.class, "processMethod", Method.class, Map.class);
        Method methodMap = ReflectionUtils.getMethod(CopierAnalyze.class, "createToMap", List.class);

        Map<String, Method> toMap = new HashMap<>();
        CopyEntry copyEntry = null;
        // "getIndex", "setIndex", "getAbs", "setAbs",
        // "isBool", "setBool", "getStr", "setStr",
        // "getStr2", "setStr2", "getAbs2", "setAbs2",
        // "get", "is", "set", "getDummy"

        // Test01
        assertNull(methodAnalyze.invoke(analyze, null, toMap));

        // Test02
        Method method = ZeroLevelObj.class.getMethod("get");
        assertNull(methodAnalyze.invoke(analyze, method, null));

        // Test04
        assertNull(methodAnalyze.invoke(analyze, method, toMap));

        // Test05
        List<Method> toMethods = analyze.getMethods(ZeroLevelObj.class, Object.class);
        toMap = (Map<String, Method>) methodMap.invoke(analyze, toMethods);

        method = ZeroLevelObj.class.getMethod("getIndex");
        copyEntry = (CopyEntry) methodAnalyze.invoke(analyze, method, toMap);
        assertNotNull(copyEntry);
        assertEquals(copyEntry.getToName(), "setIndex");
        assertEquals(copyEntry.getToMethod(), ZeroLevelObj.class.getMethod("setIndex", int.class));

        // Test06
        method = ZeroLevelObj.class.getMethod("getAbs");
        copyEntry = (CopyEntry) methodAnalyze.invoke(analyze, method, toMap);
        assertNull(copyEntry);

        // Test07
        method = ZeroLevelObj.class.getMethod("getDummy");
        copyEntry = (CopyEntry) methodAnalyze.invoke(analyze, method, toMap);
        assertNull(copyEntry);

        // Test08
        method = ZeroLevelObj.class.getMethod("get");
        copyEntry = (CopyEntry) methodAnalyze.invoke(analyze, method, toMap);
        assertNull(copyEntry);

        // Test09
        method = ZeroLevelObj.class.getMethod("getAbs2");
        copyEntry = (CopyEntry) methodAnalyze.invoke(analyze, method, toMap);
        assertNull(copyEntry);
    }

    /**
     * Test method for {@link CopierAnalyze#scanMethods(List, List)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testScanMethods01() throws Exception {
        List<Method> fromMethods = analyze.getMethods(ZeroLevelObj.class, Object.class);
        List<Method> toMethods = analyze.getMethods(ZeroLevelObj.class, Object.class);
        List<CopyEntry> result = null;

        // Test01
        result = analyze.scanMethods(null, toMethods);
        assertNotNull(result);
        assertEquals(result.size(), 0);

        // Test02
        result = analyze.scanMethods(fromMethods, null);
        assertNotNull(result);
        assertEquals(result.size(), 0);

        // Test03
        result = analyze.scanMethods(fromMethods, toMethods);
        assertNotNull(result);
        assertEquals(result.size(), 4);
    }

    /**
     * Test method for {@link CopierAnalyze#scanMethods(List, List)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testScanMethods02() throws Exception {
        List<Method> fromMethods = analyze.getMethods(SecondLevelObj.class, Object.class);
        List<Method> toMethods = analyze.getMethods(SecondLevelObj.class, Object.class);
        List<CopyEntry> result = null;

        // Test03
        result = analyze.scanMethods(fromMethods, toMethods);
        assertNotNull(result);
        assertEquals(result.size(), 2);
    }

    /**
     * Проверить верность результатов для списка методов класса
     *
     * @param result Список методов класса
     * @param simpleCount Число методов для чистого тестирования
     * @param jacocoCount Число методов для профилирования с использованием EclEmma или Jacoco
     * @param methodNames Массив со списком методов, которые должны быть в результате
     */
    private void checkMethodEntryList(List<Method> result, int simpleCount, int jacocoCount,
            String[] methodNames) {

        Set<String> names = new HashSet<>();
        for(Method m : result) {
            names.add(m.getName());
        }
        if(names.contains("$jacocoInit")) {
            assertEquals(result.size(), jacocoCount);
        } else {
            assertEquals(result.size(), simpleCount);
        }

        if(null != methodNames) {
            for(String name : methodNames) {
                assertTrue(names.contains(name));
            }
        }

    }

    @SuppressWarnings("unused")
    private class Test1 {
        int val;

        /**
         * @return the val
         */
        public int getVal() {
            return val;
        }

        /**
         * @param val the val to set
         */
        public void setVal(int val) {
            this.val = val;
        }
    }

    @SuppressWarnings("unused")
    private class Test2 {
        int val;

        /**
         * @return the val
         */
        public int getVal() {
            return val;
        }

        /**
         * @param val the val to set
         */
        public void setVal(int val) {
            this.val = val;
        }

        public void getVoid() {

        }
    }

    /**
     * Test method for {@link ru.aafanasiev.util.copier.CopierAnalyze#findNameCalculator(java.lang.reflect.Method)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true, expectedExceptions = {IllegalStateException.class})
    public void testFindNameCalculator01() throws Exception {
        CopierOptions options = new CopierOptions();
        CopierAnalyze analyze = new CopierAnalyze(options);
        Method method = ReflectionUtils.getMethod(Test2.class, "getVal");
        assertNotNull(method);
        Method testMethod = ReflectionUtils.getMethod(CopierAnalyze.class, "findNameCalculator", Method.class);
        assertNotNull(testMethod);

        CopyNameCalculator res = (CopyNameCalculator) ReflectionUtils.invokeMethod(analyze, testMethod, method);
    }

    /**
     * Test method for {@link ru.aafanasiev.util.copier.CopierAnalyze#findNameCalculator(java.lang.reflect.Method)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testFindNameCalculator02() throws Exception {
        CopyNameCalculator calculator = new ObjectNameCalculator();
        CopierOptions options = new CopierOptions();
        options.getNameCalculators().add(calculator);
        CopierAnalyze analyze = new CopierAnalyze(options);
        Method method = ReflectionUtils.getMethod(Test2.class, "getVal");
        assertNotNull(method);
        Method testMethod = ReflectionUtils.getMethod(CopierAnalyze.class, "findNameCalculator", Method.class);
        assertNotNull(testMethod);

        CopyNameCalculator res = (CopyNameCalculator) ReflectionUtils.invokeMethod(analyze, testMethod, method);
        assertSame(res, calculator);
    }

    /**
     * Test method for {@link ru.aafanasiev.util.copier.CopierAnalyze#findNameCalculator(java.lang.reflect.Method)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testFindNameCalculator03() throws Exception {
        CopyNameCalculator calculator = new ObjectNameCalculator();
        CopyNameCalculator intCalc = new IntNameCalculator();
        CopyNameCalculator floatCalc = new FloatNameCalculator();
        CopierOptions options = new CopierOptions();
        options.getNameCalculators().add(floatCalc);
        options.getNameCalculators().add(intCalc);
        options.getNameCalculators().add(calculator);
        CopierAnalyze analyze = new CopierAnalyze(options);
        Method method = ReflectionUtils.getMethod(Test2.class, "getVal");
        assertNotNull(method);
        Method testMethod = ReflectionUtils.getMethod(CopierAnalyze.class, "findNameCalculator", Method.class);
        assertNotNull(testMethod);

        CopyNameCalculator res = (CopyNameCalculator) ReflectionUtils.invokeMethod(analyze, testMethod, method);
        assertSame(res, intCalc);
    }

    /**
     * Test method for {@link ru.aafanasiev.util.copier.CopierAnalyze#findNameCalculator(java.lang.reflect.Method)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testFindNameCalculator04() throws Exception {
        CopyNameCalculator calculator = new ObjectNameCalculator();
        CopyNameCalculator intCalc = new IntNameCalculator();
        CopyNameCalculator floatCalc = new FloatNameCalculator();
        CopierOptions options = new CopierOptions();
        options.getNameCalculators().add(floatCalc);
        options.getNameCalculators().add(intCalc);
        options.getNameCalculators().add(calculator);
        CopierAnalyze analyze = new CopierAnalyze(options);
        Method method = ReflectionUtils.getMethod(Test2.class, "getVoid");
        assertNotNull(method);
        Method testMethod = ReflectionUtils.getMethod(CopierAnalyze.class, "findNameCalculator", Method.class);
        assertNotNull(testMethod);

        CopyNameCalculator res = (CopyNameCalculator) ReflectionUtils.invokeMethod(analyze, testMethod, method);
        assertSame(res, calculator);
    }

    public class IntNameCalculator extends ObjectNameCalculator {

        @Override
        public Class<?>[] returnClasses() {
            return new Class<?>[] {Integer.class, int.class};
        }

    }

    public class FloatNameCalculator extends ObjectNameCalculator {

        @Override
        public Class<?>[] returnClasses() {
            return new Class<?>[] {Float.class, float.class};
        }

    }
}
