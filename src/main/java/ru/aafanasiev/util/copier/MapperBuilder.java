/**
 *
 */
package ru.aafanasiev.util.copier;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Mapper builder
 *
 * @author <a href="mailto:aafanasiev@umail.ru">Andrey Afanasiev</a>
 */
class MapperBuilder {
    private final CopierAnalyze copierAnalyze;
    private Class<?> fromClass;
    private Class<?> toClass;
    private List<Method> fromMethods;
    private List<Method> toMethods;
    private Map<String, Method> fromMap;
    private Map<String, Method> toMap;
    private Method fromMethod;
    private Method toMethod;

    private MapperBuilder(CopierAnalyze copierAnalyze) {
        this.copierAnalyze = copierAnalyze;
    }

    static MapperBuilder createBuilder(CopierAnalyze copierAnalyze) {
        return new MapperBuilder(copierAnalyze);
    }

    /**
     * Source class
     *
     * @param fromClass Class object
     * @return This builder
     */
    public MapperBuilder fromClass(Class<?> fromClass) {
        this.fromClass = fromClass;
        fromMethods = copierAnalyze.getGetter(fromClass, Object.class);
        fromMap = fromMethods.stream().collect(Collectors.toMap(Method::getName, Function.identity()));
        return this;
    }

    /**
     * Target class
     *
     * @param toClass Class object
     * @return This builder
     */
    public MapperBuilder toClass(Class<?> toClass) {
        this.toClass = toClass;
        toMethods = copierAnalyze.getGetter(toClass, Object.class);
        toMap = toMethods.stream().collect(Collectors.toMap(Method::getName, Function.identity()));
        return this;
    }

    public MapperBuilder createStandardMapper() {
        return this;
    }

    /**
     * Getter in source class
     *
     * @param methodName Method name
     * @return This object
     */
    public MapperBuilder from(String methodName) {
        checkClasses();
        return this;
    }

    /**
     * Setter in target class<br>
     * Setter must be setted after <code>to</code> method.
     *
     * @param methodName Method name
     * @return This object
     */
    public MapperBuilder to(String methodName) {
        checkClasses();
        this.toMethod = null;
        return this;
    }

    public MapperBuilder substr(int startPos) {
        return this;
    }

    public MapperBuilder substr(int startPos, int endPos) {
        return this;
    }

    public MapperBuilder str(int str) {
        return this;
    }

    public MapperBuilder concat() {
        return this;
    }

    private void checkClasses() {
        if(null == fromClass || null == toClass) {
            throw new IllegalStateException("fromClass and toClass must be defined");
        }
    }

}
