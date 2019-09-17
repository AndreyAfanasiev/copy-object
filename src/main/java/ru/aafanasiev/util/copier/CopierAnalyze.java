/**
 *
 */
package ru.aafanasiev.util.copier;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.aafanasiev.util.copier.compilator.AllObjectsAndInterfaces;
import ru.aafanasiev.util.copier.compilator.CopyNameCalculator;
import ru.aafanasiev.util.copier.converter.base.CopyEntry;
import ru.aafanasiev.util.copier.util.ReflectionUtils;

/**
 * Analizator class. <br>
 * It checks classes inheriance and finds setter and getter methods in analyzed class hierarhy.
 *
 * @author <a href="mailto:aafanasyev@umail.ru">Andrey Afanasiev</a>
 */
class CopierAnalyze {
    /** Logger */
    private static final Logger log = LoggerFactory.getLogger(CopierAnalyze.class);

    private final CopierOptions copyOptions;

    /**
     * Constructor with options
     *
     * @param copyOptions CopyOptions object with copier parameters
     */
    CopierAnalyze(CopierOptions copyOptions) {
        this.copyOptions = copyOptions;
    }

    /**
     * Geting all methods for class hierarhy.
     *
     * @param clazz Объект, для которого создается список методов.
     * @param root Root class for inheriance. This class and all classes before is not analyzed.
     * @return Method descriptions list.
     */
    List<Method> getMethods(Class<?> clazz, Class<?> root) {
        List<Method> result = new ArrayList<>();
        Set<String> mSet = new HashSet<>();

        addMethods(result, mSet, clazz, root);

        return result;
    }

    List<Method> getGetter(Class<?> clazz, Class<?> root) {
        List<Method> result = new ArrayList<>();
        Set<String> mMap = new HashSet<>();

        addMethods(result, mMap, clazz, root);

        return result;
    }

    /**
     * Scan all methods and create copy steps for copy algorithm
     *
     * @param fromMethods Method descriptions list for source class
     * @param toMethods Method descriptions list for destination class
     * @return Copy steps for copy algorithm
     */
    List<CopyEntry> scanMethods(List<Method> fromMethods, List<Method> toMethods) {
        if(null == fromMethods || null == toMethods) {
            return Collections.emptyList();
        }

        List<CopyEntry> result = new ArrayList<>(fromMethods.size());

        Map<String, Method> toMap = createToMap(toMethods);
        Set<String> compiledMethods = new HashSet<>();

        for(Method method : fromMethods) {
            if(compiledMethods.contains(method.getName())) {
                continue;
            }
            compiledMethods.add(method.getName());
            CopyEntry copyEntry = processMethod(method, toMap);
            if(null != copyEntry) {
                result.add(copyEntry);
            }
        }

        return result;
    }

    /**
     * Add level methods list to hierarhy methods list
     *
     * @param result Hierarhy methods list.
     * @param clazz Hierarhy level class
     * @param root Root class for inheriance. This class and all classes before is not analyzed.
     */
    private void addMethods(List<Method> result, Class<?> clazz, Class<?> root) {
        Method[] methods = clazz.getDeclaredMethods();

        for(Method method : methods) {
            result.add(method);
        }

        if(null != clazz.getSuperclass() && root != clazz.getSuperclass()) {
            addMethods(result, clazz.getSuperclass(), root);
        }
    }

    /**
     * Add level methods list to hierarhy methods list
     *
     * @param result Hierarhy methods list.
     * @param clazz Hierarhy level class
     * @param root Root class for inheriance. This class and all classes before is not analyzed.
     */
    private void addMethods(List<Method> result, Set<String> mSet, Class<?> clazz, Class<?> root) {
        Method[] methods = clazz.getDeclaredMethods();

        for(Method method : methods) {
            if((method.getModifiers() & 0x1000) == 0 && // Non SYNTHETIC method
                    !mSet.contains(method.getName())) {
                result.add(method);
                mSet.add(method.getName());
            }
        }

        if(null != clazz.getSuperclass() && root != clazz.getSuperclass()) {
            addMethods(result, mSet, clazz.getSuperclass(), root);
        }
    }

    /**
     * Create map for recieve object methods.<br>
     * Key - method name<br>
     * z Value - method entry
     *
     * @param toMethods Revieved ibject methods list
     * @return Recieved object methods map
     */
    private Map<String, Method> createToMap(List<Method> toMethods) {
        Map<String, Method> result = new HashMap<>(toMethods.size());
        for(Method method : toMethods) {
            String name = method.getName();
            Method w = result.get(name);
            if(null == w) {
                result.put(name, method);
            }
        }
        return result;
    }

    private CopyNameCalculator findNameCalculator(Method fromMethod) {
        Class<?> returnType = fromMethod.getReturnType();
        for(CopyNameCalculator calculator : copyOptions.getNameCalculators()) {
            for(Class<?> clazz : calculator.returnClasses()) {
                if(AllObjectsAndInterfaces.class.equals(clazz) ||
                        (null != returnType && ReflectionUtils.instanseOf(returnType, clazz))) {
                    return calculator;
                }
            }
        }

        throw new IllegalStateException(String.format("Could not found Name Calculator for type %s",
                null == returnType ? "<null>" : returnType.getName()));
    }

    /**
     * Finding setter for single copy operation.
     *
     * @param entry Single copy operation description
     * @param toMap Methods map for destination class
     * @return <b><code>true</code></b> - Found method. Copy block is valid<br>
     *         <b><code>false</code></b> - Not found method. Copy block is not valid. This block must be removed from
     *         list.
     */
    private boolean findSetter(CopyEntry entry, Map<String, Method> toMap) {
        if(null == entry || null == toMap) {
            return false;
        }
        Method toMethod = toMap.get(entry.getToName());
        if(null == toMethod) {
            return false;
        }

        if(Modifier.isAbstract(toMethod.getModifiers())) {
            log.error("Found abstract metod setter {} in class {} for getter {} in class {}",
                    toMethod.getName(), toMethod.getDeclaringClass().getCanonicalName(),
                    entry.getFromMethod().getName(), entry.getFromMethod().getDeclaringClass()
                            .getCanonicalName());
            return false;
        }
        entry.setToMethod(toMethod);
        return true;
    }

    /**
     * Finding setter for single copy operation.
     *
     * @param entry Single copy operation description
     * @param toMap Methods map for destination class
     * @return <b><code>true</code></b> - Found method. Copy block is valid<br>
     *         <b><code>false</code></b> - Not found method. Copy block is not valid. This block must be removed from
     *         list.
     */
    private boolean findGetter(CopyEntry entry, Map<String, Method> toMap) {
        if(null == entry || null == toMap) {
            return false;
        }
        Method toMethod = toMap.get(entry.getToName());
        if(null == toMethod) {
            return false;
        }

        if(Modifier.isAbstract(toMethod.getModifiers())) {
            log.error("Found abstract metod setter {} in class {} for getter {} in class {}",
                    toMethod.getName(), toMethod.getDeclaringClass().getCanonicalName(),
                    entry.getFromMethod().getName(), entry.getFromMethod().getDeclaringClass()
                            .getCanonicalName());
            return false;
        }
        entry.setToMethod(toMethod);
        return true;
    }

    /**
     * Processing one method<br>
     * Creating single copy description.<br>
     *
     * @param inEntry Description of copy entry
     * @param toMap Methods map for destination class
     */
    private CopyEntry processMethod(Method fromMethod, Map<String, Method> toMap) {
        if(null == fromMethod || null == toMap) {
            return null;
        }

        CopyNameCalculator nameCalculator = findNameCalculator(fromMethod);
        CopyEntry entry = new CopyEntry(fromMethod);
        if(!nameCalculator.calculateToName(entry)) {
            return null;
        }

        if(!findSetter(entry, toMap)) {
            log.error("Could not find setter with name {} for getter {} in class {}",
                    entry.getToName(), entry.getFromMethod().getName(),
                    entry.getFromMethod().getDeclaringClass().getCanonicalName());
            return null;
        }
        return entry;
    }
}
