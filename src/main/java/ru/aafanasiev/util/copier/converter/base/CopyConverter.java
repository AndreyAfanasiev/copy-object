/**
 *
 */
package ru.aafanasiev.util.copier.converter.base;

/**
 * Copy Converter interface.
 *
 * @author <a href="mailto:aafanasyev@umail.ru">Andrey Afanasiev</a>
 */
public interface CopyConverter {
    /**
     * Converting source data object to destination data object
     *
     * @param entry Single copy operation description
     * @param value Source object
     * @return Destination object
     */
    Object convert(final CopyEntry entry, final Object value);

    /**
     * Classes pairs (source-destination) list
     *
     * @return Converter pairs array
     */
    ConverterKey[] listConvertedPairs();

    /**
     * Get Parameters entry for this copy step.
     *
     * @param entry Single copy step description
     * @return Parametes object or <code>null</code>
     */
    default Object getParams(final CopyEntry entry) {
        return null;
    }
}
