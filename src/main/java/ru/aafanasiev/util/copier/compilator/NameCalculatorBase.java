/**
 *
 */
package ru.aafanasiev.util.copier.compilator;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.apache.commons.lang3.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.aafanasiev.annotations.CopyTo;
import ru.aafanasiev.annotations.NoCopy;
import ru.aafanasiev.util.copier.converter.base.CopyEntry;
import ru.aafanasiev.util.copier.util.ReflectionUtils;

/**
 * Calculate destination method name for standard objects
 *
 * @author <a href="mailto:aafanasyev@umail.ru">Andrey Afanasiev</a>
 */
public abstract class NameCalculatorBase implements CopyNameCalculator {
    /** Logger */
    private static final Logger log = LoggerFactory.getLogger(NameCalculatorBase.class);

    protected boolean checkNoCopy(CopyEntry entry) {
        Method fromMethod = entry.getFromMethod();
        if(Modifier.isAbstract(fromMethod.getModifiers())) {
            return false;
        }
        if(fromMethod.isAnnotationPresent(NoCopy.class)) {
            return false;
        }

        return true;
    }

    protected String getPropertyName(CopyEntry entry) {
        String propertyName = ReflectionUtils.getPropertyName(entry.getFromMethod());
        if(null == propertyName) {
            return null;
        }

        CopyTo destination = entry.getFromMethod().getAnnotation(CopyTo.class);
        if(null != destination) {
            propertyName = destination.value().trim();
            if(StringUtils.isEmpty(propertyName)) {
                log.error("Annotation @CopyTo for method {} in class {} has not destination property name",
                        entry.getFromMethod().getName(), entry.getFromMethod().getDeclaringClass().getCanonicalName());
                return null;
            }
        }
        return propertyName;
    }
}
