/**
 *
 */
package ru.aafanasiev.util.copier.converter.cboolean;

import ru.aafanasiev.annotations.Converter;
import ru.aafanasiev.annotations.CopyBoolean2String;
import ru.aafanasiev.util.copier.converter.base.ConverterKey;
import ru.aafanasiev.util.copier.converter.base.CopyConverter;
import ru.aafanasiev.util.copier.converter.base.CopyEntry;

/**
 * Convert from Boolean to String
 *
 * @author aafanasyev
 */
@Converter
public class CopyConverterBool2Str implements CopyConverter {
    private static final String TRUE_VALUE = "true";
    private static final String FALSE_VALUE = "false";

    /*
     * (non-Javadoc)
     * @see ru.aafanasiev.util.copier.CopyConverter#convert(ru.aafanasiev.util.copier. CopyEntry, java.lang.Object)
     */
    @Override
    public Object convert(CopyEntry entry, Object value) {
        Bool2StrParams params = (Bool2StrParams) entry.getParameters();
        if(null != value && Boolean.class.equals(value.getClass())) {
            if(((Boolean) value).booleanValue()) {
                return params.getTrueValue();
            } else {
                return params.getFalseValue();
            }
        } else {
            return null;
        }
    }

    /*
     * (non-Javadoc)
     * @see ru.aafanasiev.util.copier.CopyConverter#listConvertedPairs()
     */
    @Override
    public ConverterKey[] listConvertedPairs() {
        return new ConverterKey[] {new ConverterKey(Boolean.class, String.class)};
    }

    /*
     * (non-Javadoc)
     * @see ru.aafanasiev.util.copier.converter.base.SimpleCopyConverterBase#getParameters(ru.
     * aafanasiev.util.copier.converter.base.CopyEntry)
     */
    @Override
    public Object getParams(CopyEntry entry) {
        CopyBoolean2String annotation = entry.getFromMethod().getAnnotation(
                CopyBoolean2String.class);
        if(null == annotation) {
            return new Bool2StrParams(TRUE_VALUE, FALSE_VALUE);
        } else {
            return new Bool2StrParams(annotation.trueValue(), annotation.falseValue());
        }
    }
}
