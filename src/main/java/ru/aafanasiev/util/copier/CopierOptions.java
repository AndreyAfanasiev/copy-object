/**
 *
 */
package ru.aafanasiev.util.copier;

import java.util.ArrayList;
import java.util.List;

import ru.aafanasiev.util.copier.compilator.CopyNameCalculator;
import ru.aafanasiev.util.copier.converter.base.CopyConverter;

/**
 * Copy system parameters. <br>
 * Contains converters and Name calculators lists.
 *
 * @author <a href="mailto:aafanasyev@umail.ru">Andrey Afanasiev</a>
 */
public class CopierOptions {
    private static final int CONVERTERS_LIST_SIZE = 128;

    /** List of data converters */
    private final List<CopyConverter> copyConverters;
    /** Name calculators list */
    private final List<CopyNameCalculator> nameCalculators;

    /**
     * Default constructor
     */
    public CopierOptions() {
        copyConverters = new ArrayList<>(CONVERTERS_LIST_SIZE);
        nameCalculators = new ArrayList<>(CONVERTERS_LIST_SIZE);
    }

    /**
     * @return the copyConverters
     */
    public List<CopyConverter> getCopyConverters() {
        return copyConverters;
    }

    /**
     * Get list of name calculators
     *
     * @return the nameCalculators
     */
    public List<CopyNameCalculator> getNameCalculators() {
        return nameCalculators;
    }
}
