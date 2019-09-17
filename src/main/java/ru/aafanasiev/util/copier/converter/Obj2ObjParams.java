/**
 *
 */
package ru.aafanasiev.util.copier.converter;

import ru.aafanasiev.util.copier.converter.base.CopyCustomConverter;

/**
 * Object to Object sopy parameters
 *
 * @author <a href="mailto:aafanasyev@umail.ru">Andrey Afanasiev</a>
 */
public class Obj2ObjParams {
    /** Converter class (if defined) */
    private CopyCustomConverter converter;
    /** Custom convereter parameters */
    private Object params;

    /**
     * @return the converter
     */
    public CopyCustomConverter getConverter() {
        return converter;
    }

    /**
     * @return the params
     */
    public Object getParams() {
        return params;
    }

    /**
     * @param converter the converter to set
     */
    public void setConverter(CopyCustomConverter converter) {
        this.converter = converter;
    }

    /**
     * @param params the params to set
     */
    public void setParams(Object params) {
        this.params = params;
    }
}
