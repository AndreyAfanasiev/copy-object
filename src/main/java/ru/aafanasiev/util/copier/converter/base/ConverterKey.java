/**
 *
 */
package ru.aafanasiev.util.copier.converter.base;

import java.beans.ConstructorProperties;

/**
 * Converter key. <br>
 * Conains source and target classes and instance comparation flags.
 *
 * @author <a href="mailto:aafanasyev@umail.ru">Andrey Afanasiev</a>
 */
public class ConverterKey {
    /** Source class */
    private final Class<?> fromClazz;
    /** Destination class */
    private final Class<?> toClazz;
    /** Source class compare is using <code>instanceof</code> */
    private final boolean fromInstance;
    /** Destination class compare is using <code>instanceof</code> */
    private final boolean toInstance;
    /** Priority for instanceOf convereter */
    private final int priority;
    /** ObjectMapper Name */
    private final String mapperName;

    /**
     * Full constructor for this class
     *
     * @param fromClazz Source class
     * @param fromInstance If <code>true</code>: source class compare is using <code>instanceof</code>
     * @param toClazz Destination class
     * @param toInstance If <code>true</code>: destination class compare is using <code>instanceof</code>
     */
    @ConstructorProperties({"fromClazz", "fromInstance", "toClazz", "toInstance"})
    public ConverterKey(Class<?> fromClazz, boolean fromInstance,
            Class<?> toClazz, boolean toInstance) {
        this(fromClazz, fromInstance, toClazz, toInstance, 1, null);
    }

    /**
     * Full constructor for this class
     *
     * @param fromClazz Source class
     * @param fromInstance If <code>true</code>: source class compare is using <code>instanceof</code>
     * @param toClazz Destination class
     * @param toInstance If <code>true</code>: destination class compare is using <code>instanceof</code>
     * @param priority Priority of this converter
     */
    @ConstructorProperties({"fromClazz", "fromInstance", "toClazz", "toInstance", "priority"})
    public ConverterKey(Class<?> fromClazz, boolean fromInstance,
            Class<?> toClazz, boolean toInstance, int priority, String mapperName) {
        this.fromClazz = fromClazz;
        this.toClazz = toClazz;
        this.fromInstance = fromInstance;
        this.toInstance = toInstance;
        this.priority = priority;
        this.mapperName = mapperName;
    }

    /**
     * Short constructor for this class. Use only source and destination classes.
     *
     * @param fromClazz Source class
     * @param toClazz Destination class
     */
    @ConstructorProperties({"fromClazz", "toClazz"})
    public ConverterKey(Class<?> fromClazz, Class<?> toClazz) {
        this(fromClazz, false, toClazz, false);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(getClass() != obj.getClass()) {
            return false;
        }
        ConverterKey other = (ConverterKey) obj;
        if(fromClazz == null) {
            if(other.fromClazz != null) {
                return false;
            }
        } else if(!fromClazz.equals(other.fromClazz)) {
            return false;
        }

        if(toClazz == null) {
            if(other.toClazz != null) {
                return false;
            }
        } else if(!toClazz.equals(other.toClazz)) {
            return false;
        }
        if(fromInstance != other.fromInstance) {
            return false;
        }
        if(toInstance != other.toInstance) {
            return false;
        }
        return true;
    }

    /**
     * @return the fromClazz
     */
    public Class<?> getFromClazz() {
        return fromClazz;
    }

    /**
     * @return the priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * @return the toClazz
     */
    public Class<?> getToClazz() {
        return toClazz;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int result = fromInstance ? 1 : 0;
        result = 31 * result + (toInstance ? 1 : 0);
        result = 31 * result + ((fromClazz == null) ? 0 : fromClazz.hashCode());
        return 31 * result + ((toClazz == null) ? 0 : toClazz.hashCode());
    }

    /**
     * @return the fromInstance
     */
    public boolean isFromInstance() {
        return fromInstance;
    }

    /**
     * @return the toInstance
     */
    public boolean isToInstance() {
        return toInstance;
    }

    /**
     * @return the mapperName
     */
    public String getMapperName() {
        return mapperName;
    }

}
