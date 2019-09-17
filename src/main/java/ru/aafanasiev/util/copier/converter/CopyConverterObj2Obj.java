/**
 *
 */
package ru.aafanasiev.util.copier.converter;

import ru.aafanasiev.annotations.Converter;
import ru.aafanasiev.annotations.CopyCustom;
import ru.aafanasiev.util.copier.converter.base.ConverterKey;
import ru.aafanasiev.util.copier.converter.base.CopyConverter;
import ru.aafanasiev.util.copier.converter.base.CopyEntry;

/**
 * Convert from Object to Object
 *
 * @author <a href="mailto:aafanasyev@umail.ru">Andrey Afanasiev</a>
 */
@Converter
public class CopyConverterObj2Obj implements CopyConverter {
    /*
     * (non-Javadoc)
     * @see ru.aafanasiev.util.copier.CopyConverter#convert(ru.aafanasiev.util.copier. CopyEntry, java.lang.Object)
     */
    @Override
    public Object convert(CopyEntry entry, Object value) {
        Obj2ObjParams params = (Obj2ObjParams) entry.getParameters();
        if(null == params.getConverter()) {
            if(null != entry.getFromMethod() && null != entry.getToMethod()) {
                if(entry.getFromMethod().getReturnType().equals(
                        entry.getToMethod().getParameterTypes()[0])) {
                    return value;
                } else {
                    return null;
                }
            } else {
                return value;
            }
        } else {
            return params.getConverter()
                    .convert(entry.getFromMethod(), entry.getToMethod(),
                            params.getParams(), value);
        }
    }

    /*
     * (non-Javadoc)
     * @see ru.aafanasiev.util.copier.converter.base.SimpleCopyConverterBase#getParameters(ru.
     * aafanasiev.util.copier.converter.base.CopyEntry)
     */
    @Override
    public Object getParams(CopyEntry entry) {
        Obj2ObjParams result = new Obj2ObjParams();
        CopyCustom conv = entry.getFromMethod().getAnnotation(CopyCustom.class);

        if(null != conv) {
            result.setConverter(CustomConverterCache.getInstance().getConverter(conv.value()));
            result.setParams(result.getConverter().getParameters(entry.getFromMethod()));
        }

        return result;
    }

    /*
     * (non-Javadoc)
     * @see ru.aafanasiev.util.copier.CopyConverter#listConvertedPairs()
     */
    @Override
    public ConverterKey[] listConvertedPairs() {
        return new ConverterKey[] {
                new ConverterKey(Object.class, true, Object.class, true, 0, null)
        };
    }
}
