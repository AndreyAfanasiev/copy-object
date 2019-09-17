/**
 *
 */
package ru.aafanasiev.util.copier.mapper;

import java.lang.reflect.Method;

/**
 * Mapper builder
 *
 * @author <a href="mailto:aafanasiev@umail.ru">Andrey Afanasiev</a>
 */
public class MapperBuilder {
    private Class<?> fromClass;
    private Class<?> toClass;
    private Method fromMethod;
    private Method toMethod;

    /**
     * Source class
     *
     * @param fromClass Class object
     * @return This builder
     */
    public MapperBuilder fromClass(Class<?> fromClass) {
        this.fromClass = fromClass;
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
