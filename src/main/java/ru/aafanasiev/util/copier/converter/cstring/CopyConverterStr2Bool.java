/**
 *
 */
package ru.aafanasiev.util.copier.converter.cstring;

import ru.aafanasiev.annotations.Converter;
import ru.aafanasiev.annotations.CopyString2Boolean;
import ru.aafanasiev.util.copier.converter.base.ConverterKey;
import ru.aafanasiev.util.copier.converter.base.CopyConverter;
import ru.aafanasiev.util.copier.converter.base.CopyEntry;

/**
 * Convert from Boolean to String
 *
 * @author aafanasyev
 */
@Converter
public class CopyConverterStr2Bool implements CopyConverter {
    private static final String[] TRUE_VALUE = {"true", "t", "yes", "y", "1"};
    private static final String[] FALSE_VALUE = {"false", "f", "no", "n", "0"};

    /*
     * (non-Javadoc)
     * @see ru.aafanasiev.util.copier.CopyConverter#convert(ru.aafanasiev.util.copier. CopyEntry, java.lang.Object)
     */
    @Override
    public Object convert(CopyEntry entry, Object value) {
        Str2BoolParams params = (Str2BoolParams) entry.getParameters();
        if(null != value && String.class.equals(value.getClass())) {
            String w = (String) value;
            if(compareString(w, params.getTrueValues(), params.isCaseSensitive())) {
                return Boolean.TRUE;
            } else if(compareString(w, params.getFalseValues(), params.isCaseSensitive())) {
                return Boolean.FALSE;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /*
     * (non-Javadoc)
     * @see ru.aafanasiev.util.copier.converter.base.SimpleCopyConverterBase#getParameters(ru.
     * aafanasiev.util.copier.converter.base.CopyEntry)
     */
    @Override
    public Object getParams(CopyEntry entry) {
        CopyString2Boolean annotation = entry.getFromMethod()
                .getAnnotation(CopyString2Boolean.class);
        if(null == annotation) {
            return new Str2BoolParams(TRUE_VALUE, FALSE_VALUE);
        } else {
            return new Str2BoolParams(annotation.trueValue(), annotation.falseValue(),
                    annotation.caseSensitive());
        }
    }

    /*
     * (non-Javadoc)
     * @see ru.aafanasiev.util.copier.CopyConverter#listConvertedPairs()
     */
    @Override
    public ConverterKey[] listConvertedPairs() {
        return new ConverterKey[] {new ConverterKey(String.class, Boolean.class)};
    }

    /**
     * Compare string with string list.
     *
     * @param value Source string
     * @param valuesList Value list
     * @param caseSensitive Case sensitive for comparation
     * @return
     */
    private boolean compareString(String value, String[] valuesList, boolean caseSensitive) {
        if(null == value || null == valuesList) {
            return false;
        }

        boolean result = false;
        String trimValue = value.trim();
        for(String w : valuesList) {
            if(caseSensitive) {
                result = trimValue.equals(w);
            } else {
                result = trimValue.equalsIgnoreCase(w);
            }
            if(result) {
                break;
            }
        }
        return result;
    }
}
