/**
 *
 */
package ru.aafanasiev.util.copier.compilator;

import ru.aafanasiev.annotations.CopyCollection.CopyMode;

/**
 * Parameters for collection copy operation
 *
 * @author <a href="mailto:aafanasiev@umail.ru">Andrey Afanasiev</a>
 */
public class CollectionsParams {
    /** Operation mode */
    private CopyMode mode;
    /** Level for collection copy objects */
    private int levels;
    /** Class for new objects */
    private Class<?> toClass;

    public CollectionsParams() {
        mode = CopyMode.COPY_ELEMENTS;
        levels = 0;
        toClass = null;
    }

    /**
     * @return the levels
     */
    public int getLevels() {
        return levels;
    }

    /**
     * @return the mode
     */
    public CopyMode getMode() {
        return mode;
    }

    /**
     * @return the toClass
     */
    public Class<?> getToClass() {
        return toClass;
    }

    /**
     * @param levels the levels to set
     */
    public void setLevels(int levels) {
        this.levels = levels;
    }

    /**
     * @param mode the mode to set
     */
    public void setMode(CopyMode mode) {
        this.mode = mode;
    }

    /**
     * @param toClass the toClass to set
     */
    public void setToClass(Class<?> toClass) {
        this.toClass = toClass;
    }

}
