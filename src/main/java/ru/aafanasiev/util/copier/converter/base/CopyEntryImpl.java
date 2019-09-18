package ru.aafanasiev.util.copier.converter.base;

import java.beans.ConstructorProperties;
import java.lang.reflect.Method;

/**
 * Single copy step description om copy algoriths.
 *
 * @author <a href="mailto:aafanasyev@umail.ru">Andrey Afanasiev</a>
 */
public class CopyEntryImpl implements CopyEntry {
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
    public CopyEntryImpl() {
        this(null, null, null);
    }

    /**
     * Create object and set parameters for source
     *
     * @param fromMethod Source method
     */
    @ConstructorProperties({"fromMethod"})
    public CopyEntryImpl(Method fromMethod) {
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
    public CopyEntryImpl(Method fromMethod, String toName, Method toMethod) {
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
    @Override
    public CopyConverter getConverter() {
        return converter;
    }

    /**
     * Get source method
     *
     * @return Source method
     */
    @Override
    public Method getFromMethod() {
        return fromMethod;
    }

    /**
     * Get convertors parameters
     *
     * @return Convertors parameters
     */
    @Override
    public Object getParameters() {
        return parameters;
    }

    /**
     * Get destination method
     *
     * @return Destination method
     */
    @Override
    public Method getToMethod() {
        return toMethod;
    }

    /**
     * Get destination method name
     *
     * @return Destination method name
     */
    @Override
    public String getToName() {
        return toName;
    }

    /**
     * Set converter
     *
     * @param converter The converter to set
     */
    @Override
    public void setConverter(CopyConverter converter) {
        this.converter = converter;
    }

    /**
     * Set source method
     *
     * @param fromMethod Source method to set
     */
    @Override
    public void setFromMethod(Method fromMethod) {
        this.fromMethod = fromMethod;
    }

    /**
     * Set converter parameters
     *
     * @param parameters Converter parameters to set
     */
    @Override
    public void setParameters(Object parameters) {
        this.parameters = parameters;
    }

    /**
     * Set destination method
     *
     * @param toMethod Destination method to set
     */
    @Override
    public void setToMethod(Method toMethod) {
        this.toMethod = toMethod;
    }

    /**
     * Set destination method name
     *
     * @param toName Destination method name to set
     */
    @Override
    public void setToName(String toName) {
        this.toName = toName;
    }

    /**
     * @return the targetGetter
     */
    @Override
    public boolean isTargetGetter() {
        return targetGetter;
    }

    /**
     * @param targetGetter the targetGetter to set
     */
    @Override
    public void setTargetGetter(boolean targetGetter) {
        this.targetGetter = targetGetter;
    }
}
