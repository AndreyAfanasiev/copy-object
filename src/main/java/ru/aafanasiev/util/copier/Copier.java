/**
 *
 */
package ru.aafanasiev.util.copier;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.aafanasiev.util.copier.converter.base.ConverterKey;
import ru.aafanasiev.util.copier.converter.base.CopyEntry;
import ru.aafanasiev.util.copier.converter.base.CopyErrorException;
import ru.aafanasiev.util.copier.i18.Messages;

/**
 * Service class for copy one object to other object. This class analyze annotations for copy process control. <br>
 * Following annotations will be analyzed:
 * <ul>
 * <li>{@link ru.aafanasiev.annotations.NoCopy NoCopy} - Don't copy this getter</li>
 * <li>{@link ru.aafanasiev.annotations.CopyTo CopyTo} - Change variable name (use different setter).</li>
 * <li>{@link ru.aafanasiev.annotations.CopyImmutable CopyImmutable} - Copy cloned object (only for Date object
 * (java.util.Date, java.sql.Date) for a while)</li>
 * </ul>
 *
 * @author <a href="mailto:aafanasyev@umail.ru">Andrey Afanasiev</a>
 */
public class Copier {
    /** Logger */
    private static final Logger log = LoggerFactory.getLogger(Copier.class);

    /** Object analizer. Reading inheriance structure and method list. */
    private final CopierAnalyze analyze;
    /** Compiling copy algorithm. Analyze and compute data conversation algorithms. */
    private final CopierCompilator compilator;
    private final CopierOptions options;

    /**
     * Constructor
     *
     * @param copyOptions CopyOptions object with copier parameters
     */
    Copier(CopierOptions copyOptions) {
        this.options = copyOptions;
        analyze = new CopierAnalyze(copyOptions);
        compilator = new CopierCompilator(copyOptions);
    }

    /**
     * Clear internal cache
     */
    public void clearCache() {
        CopyAlgorithmsCache.getInstance().clear();
    }

    /**
     * Copy object
     *
     * @param from Source object
     * @param to Destination object
     * @return {@code true} - Object was be copied<br>
     *         {@code false} - Object was be not copied
     */
    public <T extends Object> T copy(Object from, T to) {

        return copy(from, to, Object.class);
    }

    /**
     * Copy object with concrete base class
     *
     * @param from Source object
     * @param to Destination object
     * @param rootClazz Root class for inheriance. This class and all classes before is not analyzed.
     * @return
     *         <ul>
     *         <li><code>not null</code> - target object</li>
     *         <li><code>null</code> - error in parameters</li>
     *         </ul>
     */
    public <T extends Object> T copy(Object from, T to, Class<?> rootClazz) {
        if(null == from || null == to) {
            return null;
        }
        Class<?> root = null == rootClazz ? Object.class : rootClazz;

        Class<?> fromClazz = from.getClass();
        Class<?> toClazz = to.getClass();
        List<CopyEntry> copyList = getCopyList(fromClazz, toClazz, root);

        if(log.isDebugEnabled()) {
            for(CopyEntry entry : copyList) {
                log.debug("Copy {} {}[{}]->{}({})[{}] Converter: {}",
                        entry.getFromMethod().getReturnType().getCanonicalName(), entry.getFromMethod().getName(),
                        entry.getFromMethod().getDeclaringClass().getSimpleName(),
                        entry.getToMethod().getName(),
                        entry.getToMethod().getParameters()[0].getType().getCanonicalName(),
                        entry.getToMethod().getDeclaringClass().getSimpleName(),
                        entry.getConverter().getClass().getSimpleName());
            }
        }

        copyObject(from, to, copyList);
        return to;
    }

    /**
     * @return the options
     */
    public CopierOptions getOptions() {
        return options;
    }

    /**
     * Create copy algorithms for two classes (classes pair).
     *
     * @param fromClazz Source class
     * @param toClazz Destination class
     * @param root Root class for inheriance. This class and all classes before is not analyzed.
     * @return Copy algorithm
     */
    private List<CopyEntry> compileCopy(Class<?> fromClazz, Class<?> toClazz, Class<?> root) {
        List<Method> fromMethods = analyze.getMethods(fromClazz, root);
        List<Method> toMethods = analyze.getMethods(toClazz, root);

        List<CopyEntry> copyList = analyze.scanMethods(fromMethods, toMethods);

        return compilator.compileCopyList(copyList);
    }

    /**
     * @param from Source object
     * @param to Destination object
     * @param copyList Copy algorithm
     */
    private void copyObject(Object from, Object to, List<CopyEntry> copyList) {
        for(CopyEntry entry : copyList) {
            try {
                Object value = entry.getFromMethod().invoke(from);
                value = entry.getConverter().convert(entry, value);
                entry.getToMethod().invoke(to, value);
            } catch(IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException ex) {
                String message = String.format(Messages.getString("Copier.0"), //$NON-NLS-1$
                        entry.getFromMethod(), entry.getToMethod(),
                        entry.getConverter().getClass().getCanonicalName());
                throw new CopyErrorException(message, ex); // NOSONAR
            }
        }
    }

    /**
     * Get copy programm for classes pair
     *
     * @param fromClazz Source class
     * @param toClazz Destination class
     * @param root Root class for inheriance. This class and all classes before is not analyzed.
     * @return Copy algorithms
     */
    private List<CopyEntry> getCopyList(Class<?> fromClazz, Class<?> toClazz, Class<?> root) {
        ConverterKey key = new ConverterKey(fromClazz, toClazz);

        return CopyAlgorithmsCache.getInstance().getAlgorithmsCache()
                .computeIfAbsent(key, k -> compileCopy(fromClazz, toClazz, root));
    }
}
