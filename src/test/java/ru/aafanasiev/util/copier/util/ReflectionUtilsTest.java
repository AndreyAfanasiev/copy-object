/**
 *
 */
package ru.aafanasiev.util.copier.util;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:aafanasyev@luxoft.com">aafanasyev</a>
 * @version 1.0 2017-04-10
 */
@Test(groups = {"ReflectionUtilTest", "UTILS"}, ignoreMissingDependencies = true)
public class ReflectionUtilsTest {
    private static final String LOGIN_1 = "well";
    private static final long RET_VALUE = 2L;
    private static final int INITIAL_INT = 0;
    private static final int VALUE_INT = 0;

    @DataProvider(name = "setterPair")
    public Object[][] setterPair() {
        return new String[][] {
                {"a", "setA"},
                {"B", "setB"},
                {"abc", "setAbc"},
                {"aBc", "setABc"},
                {"AbC", "setAbC"},
                {"", "set"},
                {null, "set"}
        };
    }

    @DataProvider(name = "getterPair")
    public Object[][] getterPair() {
        return new String[][] {
                {"a", "getA"},
                {"B", "getB"},
                {"abc", "getAbc"},
                {"aBc", "getABc"},
                {"AbC", "getAbC"},
                {"", "get"},
                {null, "get"}
        };
    }

    /**
     * Test method for {@link ru.aafanasiev.util.ReflectionUtils#getField(java.lang.Object, java.lang.String)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testGetFieldObjectString() throws Exception {
        UserEntity entry = new UserEntity();
        entry.setActive(true);
        Object result = ReflectionUtils.getField(null, "active");
        assertNull(result);

        result = ReflectionUtils.getField(entry, (String) null);
        assertNull(result);

        result = ReflectionUtils.getField(entry, "noField");
        assertNull(result);

        result = ReflectionUtils.getField(entry, "active");
        assertNotNull(result);
    }

    /**
     * Test method for {@link ru.aafanasiev.util.ReflectionUtils#getHierarhyWithInterface(java.lang.Class)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testGetHierarhyWithInterface() throws Exception {
        List<Class<?>> hierarhy = ReflectionUtils.getHierarhyWithInterface(BaseWithInterface.class);
        assertTrue(hierarhy.contains(BaseWithInterface.class));
        assertTrue(hierarhy.contains(Serializable.class));
        assertTrue(hierarhy.contains(Object.class));
        assertTrue(hierarhy.indexOf(BaseWithInterface.class) < hierarhy.indexOf(Serializable.class));
        assertTrue(hierarhy.indexOf(Serializable.class) < hierarhy.indexOf(Object.class));

        hierarhy = ReflectionUtils.getHierarhyWithInterface(WorkWithInterfaces.class);
        assertTrue(hierarhy.contains(WorkWithInterfaces.class));
        assertTrue(hierarhy.contains(Cloneable.class));
        assertTrue(hierarhy.contains(BaseWithInterface.class));
        assertTrue(hierarhy.contains(Serializable.class));
        assertTrue(hierarhy.contains(Object.class));
        assertTrue(hierarhy.indexOf(WorkWithInterfaces.class) < hierarhy.indexOf(Cloneable.class));
        assertTrue(hierarhy.indexOf(Cloneable.class) < hierarhy.indexOf(BaseWithInterface.class));
        assertTrue(hierarhy.indexOf(BaseWithInterface.class) < hierarhy.indexOf(Serializable.class));
        assertTrue(hierarhy.indexOf(Serializable.class) < hierarhy.indexOf(Object.class));
    }

    /**
     * Test method for {@link com.bl.mobile.server.sessions.util.ReflectionUtil#getClassMethod(java.lang.Class,
     * java.lang.String, java.lang.String, java.lang.Class<?>[])}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testGetMethod() throws Exception {
        Method result = ReflectionUtils.getMethod(ActivatedEntity.class, "isActive");
        assertNotNull(result);

        result = ReflectionUtils.getMethod(HashMap.class, "isActive");
        assertNull(result);

        result = ReflectionUtils.getMethod(ActivatedEntity.class, "getDummy");
        assertNull(result);

        result = ReflectionUtils.getMethod(UserEntity.class, "a");
        assertEquals(result, ReflectionUtils.getMethod(UserEntity.class, "a"));

        result = ReflectionUtils.getMethod(UserEntity.class, "test");
        assertEquals(result, ReflectionUtils.getMethod(SelfActivatedEntity.class, "test"));

        result = ReflectionUtils.getMethod(UserEntity.class, "isActive");
        assertEquals(result, ReflectionUtils.getMethod(ActivatedEntity.class, "isActive"));

        result = ReflectionUtils.getMethod(UserEntity.class, "hashCode");
        assertEquals(result, ReflectionUtils.getMethod(Object.class, "hashCode"));
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.ReflectionUtils.mobile.server.util.ReflectionUtil#getMethodProperty(java.lang.reflect.Method)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testGetMethodPropertyBad() throws Exception {
        Method getter = ReflectionUtils.getMethod(UserEntity.class, "is");
        assertNotNull(getter);
        assertNull(ReflectionUtils.getMethodProperty(getter));

        getter = ReflectionUtils.getMethod(UserEntity.class, "is");
        assertNotNull(getter);
        assertNull(ReflectionUtils.getMethodProperty(getter));

        getter = ReflectionUtils.getMethod(UserEntity.class, "get");
        assertNotNull(getter);
        assertNull(ReflectionUtils.getMethodProperty(getter));

        getter = ReflectionUtils.getMethod(UserEntity.class, "a");
        assertNotNull(getter);
        assertNull(ReflectionUtils.getMethodProperty(getter));

        getter = ReflectionUtils.getMethod(UserEntity.class, "setLogin", String.class);
        assertNotNull(getter);
        assertNull(ReflectionUtils.getMethodProperty(getter));
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.ReflectionUtils.mobile.server.util.ReflectionUtil#getMethodProperty(java.lang.reflect.Method)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testGetMethodPropertyOk() throws Exception {
        Method getter1 = ReflectionUtils.getMethod(UserEntity.class, "getLogin");
        assertNotNull(getter1);

        Method getter2 = ReflectionUtils.getMethod(SelfActivatedEntity.class, "isSelf");
        assertNotNull(getter2);

        assertNull(ReflectionUtils.getMethodProperty((Method) null));
        assertEquals(ReflectionUtils.getMethodProperty(getter1), "Login");
        assertEquals(ReflectionUtils.getMethodProperty(getter2), "Self");
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.ReflectionUtils.mobile.server.util.ReflectionUtil#getPropertyName(java.lang.reflect.Method)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testGetPropertyName() throws Exception {
        Method getter1 = ReflectionUtils.getMethod(UserEntity.class, "getLogin");
        assertNotNull(getter1);

        Method getter2 = ReflectionUtils.getMethod(SelfActivatedEntity.class, "isSelf");
        assertNotNull(getter2);

        assertNull(ReflectionUtils.getPropertyName((Method) null));
        assertEquals(ReflectionUtils.getPropertyName(getter1), "login");
        assertEquals(ReflectionUtils.getPropertyName(getter2), "self");
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.ReflectionUtils.mobile.server.util.ReflectionUtil#getSetter(java.lang.Object, java.lang.reflect.Method)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testGetSetter() throws Exception {
        UserEntity entity = new UserEntity();

        Method getter1 = ReflectionUtils.getMethod(UserEntity.class, "getLogin");
        assertNotNull(getter1);

        Method getter2 = ReflectionUtils.getMethod(UserEntity.class, "isSelf");
        assertNotNull(getter2);

        Method getter3 = ReflectionUtils.getMethod(UserEntity.class, "getUnknown");
        assertNotNull(getter2);

        assertNull(ReflectionUtils.getSetter((ActivatedEntity) null, getter1));
        assertNull(ReflectionUtils.getSetter(entity, (Method) null));
        assertNotNull(ReflectionUtils.getSetter(entity, getter1));
        assertNotNull(ReflectionUtils.getSetter(entity, getter2));
        assertNull(ReflectionUtils.getSetter(entity, getter3));
        assertEquals(ReflectionUtils.getSetter(entity, getter1).getName(), "setLogin");
        assertEquals(ReflectionUtils.getSetter(entity, getter2).getName(), "setSelf");
    }

    /**
     * Test method for {@link ru.aafanasiev.util.ReflectionUtils#getSetterName(java.lang.String)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true, dataProvider = "setterPair")
    public void testGetSetterName(String source, String result) throws Exception {
        assertEquals(ReflectionUtils.getSetterName(source), result);
    }

    /**
     * Test method for {@link ru.aafanasiev.util.ReflectionUtils#getSetterName(java.lang.String)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true, dataProvider = "getterPair")
    public void testGetGetterName(String source, String result) throws Exception {
        assertEquals(ReflectionUtils.getGetterName(source), result);
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.ReflectionUtils.mobile.server.util.ReflectionUtil#getVisible(java.lang.reflect.Method)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testGetVisible() throws Exception {
        Method m = ReflectionUtils.getMethod(TestModidier.class, "m01");
        assertEquals(ReflectionUtils.getVisible(m), "public static final ");

        m = ReflectionUtils.getMethod(TestModidier.class, "m02");
        assertEquals(ReflectionUtils.getVisible(m), "protected ");

        m = ReflectionUtils.getMethod(TestModidier.class, "m03");
        assertEquals(ReflectionUtils.getVisible(m), "synchronized ");

        m = ReflectionUtils.getMethod(TestModidier.class, "m04");
        assertEquals(ReflectionUtils.getVisible(m), "private ");

        m = ReflectionUtils.getMethod(TestModidier.class, "m05");
        assertEquals(ReflectionUtils.getVisible(m), "public abstract ");
    }

    /**
     * Test method for {@link ru.aafanasiev.util.ReflectionUtils#instanseOf(java.lang.Class, java.lang.Class)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testInstanseOf() throws Exception {
        assertTrue(ReflectionUtils.instanseOf(BaseWithInterface.class, Serializable.class));
        assertTrue(ReflectionUtils.instanseOf(BaseWithInterface.class, Object.class));
        assertFalse(ReflectionUtils.instanseOf(BaseWithInterface.class, List.class));
        assertFalse(ReflectionUtils.instanseOf(Serializable.class, Object.class));

        assertTrue(ReflectionUtils.instanseOf(BaseWithInterface.class, Serializable.class));
        assertTrue(ReflectionUtils.instanseOf(BaseWithInterface.class, Object.class));
        assertTrue(ReflectionUtils.instanseOf(WorkWithInterfaces.class, BaseWithInterface.class));
        assertTrue(ReflectionUtils.instanseOf(WorkWithInterfaces.class, Cloneable.class));
        assertTrue(ReflectionUtils.instanseOf(WorkWithInterfaces.class, Serializable.class));
        assertFalse(ReflectionUtils.instanseOf(WorkWithInterfaces.class, List.class));
        assertFalse(ReflectionUtils.instanseOf(Serializable.class, Object.class));
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.ReflectionUtils.mobile.server.util.ReflectionUtil#getField(java.lang.Object, java.lang.reflect.Method)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testInvokeGetter01() throws Exception {
        UserEntity entity = new UserEntity();
        Method getter = ReflectionUtils.getMethod(UserEntity.class, "getLogin");
        assertNotNull(getter);

        assertNull(ReflectionUtils.invokeGetter((UserEntity) null, getter));
        assertNull(ReflectionUtils.invokeGetter(entity, (Method) null));
        assertNull(ReflectionUtils.invokeGetter(entity, getter));
        assertNull(ReflectionUtils.invokeGetter(new HashMap<String, String>(), getter));

        entity.setLogin(LOGIN_1);
        String value = (String) ReflectionUtils.invokeGetter(entity, getter);
        assertEquals(value, LOGIN_1);
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.ReflectionUtils.mobile.server.util.ReflectionUtil#getField(java.lang.Object, java.lang.reflect.Method)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testInvokeGetter02() throws Exception {
        UserEntity entity = new UserEntity();
        Method getter = ReflectionUtils.getMethod(UserEntity.class, "getLogin");
        assertNotNull(getter);

        assertNull(ReflectionUtils.invokeGetter((UserEntity) null, "getLogin"));
        assertNull(ReflectionUtils.invokeGetter(entity, (Method) null));
        assertNull(ReflectionUtils.invokeGetter(entity, "getLogin"));
        assertNull(ReflectionUtils.invokeGetter(new HashMap<String, String>(), "getLogin"));

        entity.setLogin(LOGIN_1);
        String value = (String) ReflectionUtils.invokeGetter(entity, "getLogin");
        assertEquals(value, LOGIN_1);
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.ReflectionUtils#invokeMethod(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testInvokeMethodObjectMethodObjectArray01() throws Exception {
        InvokeTest invoker = new InvokeTest();

        assertFalse(invoker.runned1);
        assertEquals(invoker.value1, INITIAL_INT);
        Method method = ReflectionUtils.getMethod(InvokeTest.class, "method1", Integer.class);
        assertNotNull(method);
        long res = (long) ReflectionUtils.invokeMethod(invoker, method, VALUE_INT);
        assertTrue(invoker.runned1);
        assertEquals(invoker.value1, VALUE_INT);
        assertEquals(res, RET_VALUE);

        assertFalse(invoker.runned2);
        assertEquals(invoker.value2, INITIAL_INT);
        method = ReflectionUtils.getMethod(InvokeTest.class, "method2", int.class);
        assertNotNull(method);
        res = (long) ReflectionUtils.invokeMethod(invoker, method, VALUE_INT);
        assertTrue(invoker.runned2);
        assertEquals(invoker.value2, VALUE_INT);
        assertEquals(res, RET_VALUE);

        assertFalse(invoker.runned3);
        assertEquals(invoker.value3, INITIAL_INT);
        method = ReflectionUtils.getMethod(InvokeTest.class, "method3", Integer.class, List.class);
        assertNotNull(method);
        res = (long) ReflectionUtils.invokeMethod(invoker, method, VALUE_INT, new ArrayList<>());
        assertTrue(invoker.runned3);
        assertEquals(invoker.value3, VALUE_INT);
        assertEquals(res, RET_VALUE);

        method = ReflectionUtils.getMethod(InvokeTest.class, "method1", Integer.class);
        assertNotNull(method);
        assertNull(ReflectionUtils.invokeMethod(null, method, VALUE_INT));
        assertNull(ReflectionUtils.invokeMethod(invoker, (Method) null, VALUE_INT));
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.ReflectionUtils#invokeMethod(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true, expectedExceptions = {RuntimeException.class})
    public void testInvokeMethodObjectMethodObjectArray02() throws Exception {
        InvokeTest invoker = new InvokeTest();

        Method method = ReflectionUtils.getMethod(InvokeTest.class, "methodRuntimeException");
        assertNotNull(method);
        ReflectionUtils.invokeMethod(invoker, method);
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.ReflectionUtils#invokeMethod(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true, expectedExceptions = {InvocationMethodException.class})
    public void testInvokeMethodObjectMethodObjectArray03() throws Exception {
        InvokeTest invoker = new InvokeTest();

        Method method = ReflectionUtils.getMethod(InvokeTest.class, "methodException");
        assertNotNull(method);
        ReflectionUtils.invokeMethod(invoker, method);
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.ReflectionUtils#invokeMethod(java.lang.Object, java.lang.String, java.lang.Object[])}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testInvokeMethodObjectStringObjectArray() throws Exception {
        InvokeTest invoker = new InvokeTest();

        assertFalse(invoker.runned1);
        assertEquals(invoker.value1, INITIAL_INT);
        long res = (long) ReflectionUtils.invokeMethod(invoker, "method1", VALUE_INT);
        assertTrue(invoker.runned1);
        assertEquals(invoker.value1, VALUE_INT);
        assertEquals(res, RET_VALUE);

        assertFalse(invoker.runned2);
        assertEquals(invoker.value2, INITIAL_INT);
        assertNull(ReflectionUtils.invokeMethod(invoker, "method2", VALUE_INT));
        assertFalse(invoker.runned2);
        assertEquals(invoker.value2, INITIAL_INT);

        assertFalse(invoker.runned3);
        assertEquals(invoker.value3, INITIAL_INT);
        assertNull(ReflectionUtils.invokeMethod(invoker, "method3", VALUE_INT, new ArrayList<>()));
        assertFalse(invoker.runned3);
        assertEquals(invoker.value3, INITIAL_INT);

        assertNull(ReflectionUtils.invokeMethod(null, "method1", VALUE_INT));
        assertNull(ReflectionUtils.invokeMethod(invoker, (String) null, VALUE_INT));
        assertNull(ReflectionUtils.invokeMethod(invoker, "", VALUE_INT));
    }

    /**
     * Test method for
     * {@link ru.aafanasiev.util.ReflectionUtils#setField(java.lang.Object, java.lang.String, java.lang.Object)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testSetField() throws Exception {
        InvokeTest invoker = new InvokeTest();

        assertEquals(invoker.value1, INITIAL_INT);
        ReflectionUtils.setField(invoker, "value1", VALUE_INT);
        assertEquals(invoker.value1, VALUE_INT);

        invoker.value1 = INITIAL_INT;
        ReflectionUtils.setField(invoker, "value11", VALUE_INT);
        assertEquals(invoker.value1, INITIAL_INT);

        ReflectionUtils.setField(invoker, "runned1", VALUE_INT);
        assertEquals(invoker.value1, INITIAL_INT);
    }

    abstract class ActivatedEntity {
        private boolean active;

        public synchronized boolean isActive() {
            return active;
        }

        public synchronized final void setActive(boolean active) {
            this.active = active;
        }

        protected abstract void test();

    }

    class SelfActivatedEntity extends ActivatedEntity {
        private boolean self;

        public boolean isSelf() {
            return self;
        }

        public void setSelf(boolean self) {
            this.self = self;
        }

        @Override
        protected void test() {
        }
    }

    class UserEntity extends SelfActivatedEntity {
        private String login;

        public Object a() {
            return is();
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        protected Object get() {
            return null;
        }

        @SuppressWarnings("unused")
        private Object getUnknown() {
            return null;
        }

        private Object is() {
            return null;
        }
    }

    private static class BaseWithInterface implements Serializable {
        private static final long serialVersionUID = 7071817432060151986L;
    }

    private class InvokeTest {
        private boolean runned1 = false;
        private boolean runned2 = false;
        private boolean runned3 = false;
        private int value1 = INITIAL_INT;
        private int value2 = INITIAL_INT;
        private int value3 = INITIAL_INT;

        @SuppressWarnings("unused")
        public long method1(Integer i) {
            value1 = i.intValue();
            runned1 = true;
            return RET_VALUE;
        }

        @SuppressWarnings("unused")
        public long method2(int i) {
            value2 = i;
            runned2 = true;
            return RET_VALUE;
        }

        @SuppressWarnings("unused")
        public long method3(Integer i, List<String> list) {
            value3 = i.intValue();
            runned3 = true;
            return RET_VALUE;
        }

        @SuppressWarnings("unused")
        public void methodException() throws Exception {
            throw new Exception("It is Runtime exception");
        }

        @SuppressWarnings("unused")
        public void methodRuntimeException() {
            throw new RuntimeException("It is Runtime exception");
        }
    }

    @SuppressWarnings("unused")
    private abstract static class TestModidier {
        public static final void m01() {

        }

        public abstract void m05();

        protected void m02() {

        }

        synchronized void m03() {

        }

        private void m04() {

        }
    }

    private static class WorkWithInterfaces extends BaseWithInterface implements Cloneable {
        private static final long serialVersionUID = 8870557819215727825L;
    }
}
