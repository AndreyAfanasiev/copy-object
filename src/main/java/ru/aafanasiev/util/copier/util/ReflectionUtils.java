/**
 *
 */
package ru.aafanasiev.util.copier.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Reflection utility class
 *
 * @author <a href="mailto:aafanasiev@umail.ru">Andrey Afanasiev</a>
 * @version 2.0 2018-07-18
 */
public class ReflectionUtils {
    /** Logger */
    private static final Logger log = LoggerFactory.getLogger(ReflectionUtils.class);

    /** Setter prefix */
    private static final String SET = "set";
    /** Getter prefix */
    private static final String GET = "get";

    /**
     * Hidden default constructor for utility class
     */
    private ReflectionUtils() {
    }

    /**
     * <p>
     * Get class field value
     * </p>
     * The method returns the values of any field defined in the class. In particular, protected and private fields are
     * returned. If field are redefined, then the value of the field for the first class in hierarchy is returned.
     *
     * @param entity Field object
     * @param fieldName Field name
     * @return Field Field value or <code>null</code>
     */
    public static Object getField(Object entity, String fieldName) {
        if(null == entity || null == fieldName) {
            return null;
        }

        List<Class<?>> hierarhy = getHierarhy(entity.getClass());
        Field field = null;
        for(Class<?> w : hierarhy) {
            try {
                field = w.getDeclaredField(fieldName);
                break;
            } catch(NoSuchFieldException | SecurityException | IllegalArgumentException ex) { // NOSONAR
                log.error("Error getDeclaredField({}) in class {}",
                        fieldName, entity.getClass().getCanonicalName(), ex);
                field = null;
            }
        }
        if(null != field) {
            field.setAccessible(true);
            try {
                return field.get(entity);
            } catch(IllegalArgumentException | IllegalAccessException ex) { // NOSONAR
                return null;
            }
        }
        return null;
    }

    /**
     * Get the getter name by the name of the propery.
     *
     * @param propertyName The name of the property
     * @return The setter name
     */
    public static String getGetterName(String propertyName) {
        if(null == propertyName || 0 == propertyName.length()) {
            return GET;
        }
        return GET + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
    }

    /**
     * <p>
     * Get class hierarchy without interfaces
     * </p>
     * Hierarchy has only classes. Interfaces were not included into hierarchy.
     *
     * @param source The source class. Hierarhy'll be builded for this class.
     * @return Class list. Ordered from the source class to the Object class.
     */
    public static List<Class<?>> getHierarhy(Class<?> source) {
        List<Class<?>> result = new ArrayList<>();

        addLevel(result, source);

        return result;
    }

    /**
     * <p>
     * Get class hierarchy without interfaces
     * </p>
     * <p>
     * Hierarchy has classes and interfaces.
     * </p>
     * Interfaces are included immediately after the class in which they are implemeted. It is possible to duplicate
     * interfaces.
     *
     * @param source The source class. Hierarhy'll be builded for this class.
     * @return Class list. Ordered from the source class to the Object class.
     */
    public static List<Class<?>> getHierarhyWithInterface(Class<?> source) {
        List<Class<?>> result = new ArrayList<>();

        addLevelWithInterface(result, source);

        return result;
    }

    /**
     * Get the class method.<br>
     * Return any method defined in a class from the class hierarchy of the passed object. In particular, protected,
     * private and package (for differnet package) methods are returned. The method is ready to be called up. The
     * "Accessible" flag is set.
     *
     * @param clazz Object class with calculating metnod
     * @param methodName The name of the method
     * @param params Parameters classes array.
     * @return
     *         <p>
     *         The Method object for the method of class (or class from parent hierarhy class) matching the specified
     *         name and parameters
     *         </p>
     *         <p>
     *         Or <code>null</code> if method was not found
     *         </p>
     */
    public static Method getMethod(Class<?> clazz, String methodName, Class<?>... params) {
        if(null == clazz) {
            return null;
        }

        Method method;
        try {
            method = clazz.getDeclaredMethod(methodName, params);
        } catch(NoSuchMethodException ex) { // NOSONAR
            method = getMethod(clazz.getSuperclass(), methodName, params);
        }
        if(null != method) {
            method.setAccessible(true);
        }

        return method;
    }

    /**
     * Get property name for getter. First letter is upercase
     *
     * @param getter Getter definition
     * @return Property name. First letter is upercase.
     */
    public static String getMethodProperty(Method getter) {
        if(null == getter) {
            return null;
        }

        String m = getter.getName();

        if(m.startsWith("is")) {
            if(m.length() < 3) {
                return null;
            }
            return m.substring(2);
        } else if(m.startsWith("get")) {
            if(m.length() < 4) {
                return null;
            }
            return m.substring(3);
        }

        return null;
    }

    /**
     * Get property name for getter. First letter is lowcase.
     *
     * @param getter Getter definition
     * @return Property name
     */
    public static String getPropertyName(Method getter) {
        String m = getMethodProperty(getter);

        return null != m ? (m.substring(0, 1).toLowerCase() + m.substring(1)) : null;
    }

    /**
     * Get setter from class by getter.<br>
     *
     * @param entity Object for setter
     * @param getter Getter
     * @return Setter or <code>null</code> (if absent)
     */
    public static Method getSetter(Object entity, Method getter) {
        if(null == entity || null == getter) {
            return null;
        }
        String setterName = "set" + getMethodProperty(getter);
        Class<?> type = getter.getReturnType();
        Method setter = getMethod(entity.getClass(), setterName, type);
        if(null == setter) {
            log.error("Could not find setter \"{}\" with type {} in object {}. Getter {} in class {}",
                    setterName, type.getCanonicalName(),
                    entity.getClass().getCanonicalName(),
                    getter.getName(),
                    getter.getDeclaringClass().getCanonicalName());
        }

        return setter;
    }

    /**
     * Get the setter name by the name of the propery.
     *
     * @param propertyName The name of the property
     * @return The setter name
     */
    public static String getSetterName(String propertyName) {
        if(null == propertyName || 0 == propertyName.length()) {
            return SET;
        }
        return SET + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
    }

    /**
     * Get string with method modifier.
     *
     * @param method The method
     * @return The Modificator string by pattern:<br>
     *         <code>(public|protected|private|) [abstract] [static] [final] [synchronized]</code>
     */
    public static String getVisible(Method method) {
        StringBuilder result = new StringBuilder();
        int flags = method.getModifiers();

        if(Modifier.isPublic(flags)) {
            result.append("public ");
        } else if(Modifier.isProtected(flags)) {
            result.append("protected ");
        } else if(Modifier.isPrivate(flags)) {
            result.append("private ");
        }
        if(Modifier.isAbstract(flags)) {
            result.append("abstract ");
        }
        if(Modifier.isStatic(flags)) {
            result.append("static ");
        }
        if(Modifier.isFinal(flags)) {
            result.append("final ");
        }
        if(Modifier.isSynchronized(flags)) {
            result.append("synchronized ");
        }

        return result.toString();
    }

    /**
     * Check <code>clazz <b>instanseof</b> baseClazz</code>
     *
     * @param clazz The checked class
     * @param baseCLazz The base class
     * @return {@code true} - clazz is instanse of baseClazz,<br>
     *         {@code false} - clazz is not instanse of baseClazz
     */
    public static boolean instanseOf(Class<?> baseCLazz, Class<?> clazz) {
        if(baseCLazz == null || clazz == null) {
            return false;
        }
        List<Class<?>> hierarhy = getHierarhyWithInterface(baseCLazz);
        for(Class<?> level : hierarhy) {
            if(clazz.equals(level)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get object field via getter<br>
     *
     * @param entity Field object
     * @param getter Getter definition
     * @return Field value or <code>null</code>
     */
    public static Object invokeGetter(Object entity, Method getter) {
        if(null == entity || null == getter) {
            return null;
        }

        try {
            return getter.invoke(entity);
        } catch(IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) { // NOSONAR
            log.error("Invocation error for method {} in class {}",
                    getter.getName(), entity.getClass().getCanonicalName(), ex);

            return null;
        }
    }

    /**
     * Get object field via getter<br>
     *
     * @param entity Field object
     * @param getterName Getter name
     * @return Field value or <code>null</code>
     */
    public static Object invokeGetter(Object entity, String getterName) {
        if(null == entity || null == getterName) {
            return null;
        }

        Method method = getMethod(entity.getClass(), getterName);
        return invokeGetter(entity, method);
    }

    /**
     * Run the method of the class.
     *
     * @param object The Object which contains of the method
     * @param method The method declaration (Obect {@code method})
     * @param params The parameters array
     * @return Return method value or {@code null} if return type is {@code void}
     */
    public static Object invokeMethod(Object object, Method method, Object... params) {
        if(null == method || null == object) {
            return null;
        }
        try {
            return method.invoke(object, params);
        } catch(InvocationTargetException ex) {
            if(ex.getCause() instanceof RuntimeException) {
                throw(RuntimeException) ex.getCause();
            }
            throw new InvocationMethodException(String.format("Invocation Target Exception for method %s in class %s",
                    method.getName(), object.getClass().getCanonicalName()), ex);
        } catch(IllegalAccessException | IllegalArgumentException ex) {
            log.error("Invocation error for method {} in class {}",
                    method.getName(), object.getClass().getCanonicalName(), ex);
        }
        return null;
    }

    /**
     * <p>
     * Run the method of the class by method name and parameters.
     * </p>
     * This is a <b>dangerous</b> method. If the parameter type in the method is specified as the interface, and the
     * parameter is passed as an object (which is always the case), then the method will not be found.
     *
     * @param object The Object which contains of the method
     * @param methodName The method name ({@code String})
     * @param params The parameters array
     * @return Return method value or {@code null} if return type is {@code void}
     */
    public static Object invokeMethod(Object object, String methodName, Object... params) {
        if(null == methodName || 0 == methodName.length() || null == object) {
            return null;
        }
        Class<?>[] paramClasses = new Class<?>[params.length];
        for(int i = 0; i < params.length; i++) {
            paramClasses[i] = params[i].getClass();
        }
        Method method = getMethod(object.getClass(), methodName, paramClasses);
        return null == method ? null : invokeMethod(object, method, params);
    }

    /**
     * Get object field<br>
     *
     * @param entity Field object
     * @param fieldName Field name
     * @param value New field value
     * @return <code>true</code> - setted field,<br>
     *         <code>false</code>- could not set field
     */
    public static boolean setField(Object entity, String fieldName, Object value) {
        if(null == entity || null == fieldName) {
            return false;
        }

        List<Class<?>> hierarhy = getHierarhy(entity.getClass());
        Field field = null;
        for(Class<?> w : hierarhy) {
            try {
                field = w.getDeclaredField(fieldName);
                if(null != field) {
                    break;
                }
            } catch(NoSuchFieldException | SecurityException | IllegalArgumentException ex) { // NOSONAR
                field = null;
            }
        }
        if(null != field) {
            field.setAccessible(true);
            try {
                field.set(entity, value);
            } catch(IllegalArgumentException | IllegalAccessException ex) { // NOSONAR
                return false;
            }
        } else {
            log.error("Could not find field {} in classes {} and it' subclasses",
                    fieldName, entity.getClass().getCanonicalName());
        }
        return true;

    }

    /**
     * Add one level to class hierarhy
     *
     * @param hierarhy Classes list. Order from source to Object.
     * @param current Current hierarhy level
     */
    private static void addLevel(List<Class<?>> hierarhy, Class<?> current) {
        if(null == current) {
            return;
        }

        hierarhy.add(current);

        addLevel(hierarhy, current.getSuperclass());
    }

    /**
     * Add one level to class hierarhy with all interfaces
     *
     * @param hierarhy Classes list. Order from source to Object.
     * @param current Current hierarhy level
     */
    private static void addLevelWithInterface(List<Class<?>> hierarhy, Class<?> current) {
        if(null == current) {
            return;
        }
        hierarhy.add(current);

        Class<?>[] interfaces = current.getInterfaces();
        for(Class<?> i : interfaces) {
            hierarhy.add(i);
            addLevelWithInterface(hierarhy, i);
        }

        addLevelWithInterface(hierarhy, current.getSuperclass());
    }
}
