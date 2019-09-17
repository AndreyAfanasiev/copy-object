package ru.aafanasiev.util.copier.converter.base;

import java.beans.ConstructorProperties;
import java.lang.reflect.Method;

/**
 * Single copy step description om copy algoriths.
 *
 * @author <a href="mailto:aafanasyev@umail.ru">Andrey Afanasiev</a>
 */
public class CopyEntry {
    /** Source method */
    private Method fromMethod;
    /** Destination method name */
    private String toName;
    /** Destination method */
    private Method toMethod;
    /** Convertor object */
    private CopyConverter converter;
    /** Convertor object parameters (depends of the convertor) */
    private Object parameters;
    /** Target method is getter */
    private boolean targetGetter;

    /**
     * Default constructor
     */
    public CopyEntry() {
        this(null, null, null);
    }

    /**
     * Create object and set parameters for source
     *
     * @param fromMethod Source method
     */
    @ConstructorProperties({"fromMethod"})
    public CopyEntry(Method fromMethod) {
        this(fromMethod, null, null);
    }

    /**
     * Create object and full initialze it
     *
     * @param fromMethod Source method
     * @param toName Destination method name
     * @param toMethod Destination method
     */
    @ConstructorProperties({"fromMethod", "toName", "toMethod"})
    public CopyEntry(Method fromMethod, String toName, Method toMethod) {
        this.fromMethod = fromMethod;
        this.toName = toName;
        this.toMethod = toMethod;
        targetGetter = false;
    }

    /**
     * Get convertor
     *
     * @return The converter for this copy step
     */
    public CopyConverter getConverter() {
        return converter;
    }

    /**
     * Get source method
     *
     * @return Source method
     */
    public Method getFromMethod() {
        return fromMethod;
    }

    /**
     * Get convertors parameters
     *
     * @return Convertors parameters
     */
    public Object getParameters() {
        return parameters;
    }

    /**
     * Get destination method
     *
     * @return Destination method
     */
    public Method getToMethod() {
        return toMethod;
    }

    /**
     * Get destination method name
     *
     * @return Destination method name
     */
    public String getToName() {
        return toName;
    }

    /**
     * Set converter
     *
     * @param converter The converter to set
     */
    public void setConverter(CopyConverter converter) {
        this.converter = converter;
    }

    /**
     * Set source method
     *
     * @param fromMethod Source method to set
     */
    public void setFromMethod(Method fromMethod) {
        this.fromMethod = fromMethod;
    }

    /**
     * Set converter parameters
     *
     * @param parameters Converter parameters to set
     */
    public void setParameters(Object parameters) {
        this.parameters = parameters;
    }

    /**
     * Set destination method
     *
     * @param toMethod Destination method to set
     */
    public void setToMethod(Method toMethod) {
        this.toMethod = toMethod;
    }

    /**
     * Set destination method name
     *
     * @param toName Destination method name to set
     */
    public void setToName(String toName) {
        this.toName = toName;
    }

    /**
     * @return the targetGetter
     */
    public boolean isTargetGetter() {
        return targetGetter;
    }

    /**
     * @param targetGetter the targetGetter to set
     */
    public void setTargetGetter(boolean targetGetter) {
        this.targetGetter = targetGetter;
    }
}
