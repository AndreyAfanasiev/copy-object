/**
 *
 */
package ru.aafanasiev.util.copier.compilator;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.aafanasiev.annotations.CopyCollection;
import ru.aafanasiev.annotations.NameCalculator;
import ru.aafanasiev.util.copier.converter.base.CopyEntry;
import ru.aafanasiev.util.copier.util.ReflectionUtils;

/**
 * Calculate destination method name for standard objects
 *
 * @author <a href="mailto:aafanasyev@umail.ru">Andrey Afanasiev</a>
 */
@NameCalculator
public class CollectionNameCalculator extends NameCalculatorBase {
    /** Logger */
    private static final Logger log = LoggerFactory.getLogger(CollectionNameCalculator.class);

    /*
     * (non-Javadoc)
     * @see
     * ru.aafanasiev.util.copier.compilator.NameCalculatorBase#calculateToName(ru.aafanasiev.util.copier.converter.base.
     * CopyEntry)
     */
    @Override
    public boolean calculateToName(CopyEntry entry) {
        if(null == entry || null == entry.getFromMethod()) {
            return false;
        }

        if(!checkNoCopy(entry)) {
            return false;
        }

        String propertyName = getPropertyName(entry);
        if(null == propertyName) {
            return false;
        }

        CollectionsParams params = analyzeCopyCollection(entry);
        entry.setParameters(params);

        switch(params.getMode()) {
            case COPY_ELEMENTS:
            case CONVERT_ELEMENTS:
            case CLONE_ELEMENTS:
                entry.setToName(ReflectionUtils.getGetterName(propertyName));
                break;
            case CLONE_COLLECTION:
            case COPY_COLLECTION:
                entry.setToName(ReflectionUtils.getSetterName(propertyName));
                break;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * @see ru.aafanasiev.util.copier.CopyNameCalculator#listRootMethods()
     */
    @Override
    public Class<?>[] returnClasses() {
        return new Class<?>[] {Collection.class};
    }

    /**
     * @param entry Copy entry element
     * @return Copy parameters
     */
    private CollectionsParams analyzeCopyCollection(CopyEntry entry) {
        CollectionsParams result = new CollectionsParams();
        CopyCollection annotation = entry.getFromMethod().getAnnotation(CopyCollection.class);
        if(null != annotation) {
            result.setMode(annotation.mode());
            result.setToClass(annotation.targetClass());
            result.setLevels(annotation.levels());
        }

        return result;
    }

}
