package ru.aafanasiev.util.copier.converter.base;

import java.lang.reflect.Method;

public interface CopyEntry {

    /**
     * Get convertor
     *
     * @return The converter for this copy step
     */
    CopyConverter getConverter();

    /**
     * Get source method
     *
     * @return Source method
     */
    Method getFromMethod();

    /**
     * Get convertors parameters
     *
     * @return Convertors parameters
     */
    Object getParameters();

    /**
     * Get destination method
     *
     * @return Destination method
     */
    Method getToMethod();

    /**
     * Get destination method name
     *
     * @return Destination method name
     */
    String getToName();

    /**
     * Set converter
     *
     * @param converter The converter to set
     */
    void setConverter(CopyConverter converter);

    /**
     * Set source method
     *
     * @param fromMethod Source method to set
     */
    void setFromMethod(Method fromMethod);

    /**
     * Set converter parameters
     *
     * @param parameters Converter parameters to set
     */
    void setParameters(Object parameters);

    /**
     * Set destination method
     *
     * @param toMethod Destination method to set
     */
    void setToMethod(Method toMethod);

    /**
     * Set destination method name
     *
     * @param toName Destination method name to set
     */
    void setToName(String toName);

    /**
     * @return the targetGetter
     */
    boolean isTargetGetter();

    /**
     * @param targetGetter the targetGetter to set
     */
    void setTargetGetter(boolean targetGetter);

}
