/**
 *
 */
package ru.aafanasiev.util.copier.converter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import ru.aafanasiev.util.copier.converter.base.CopyCustomConverter;
import ru.aafanasiev.util.copier.converter.base.CopyErrorException;
import ru.aafanasiev.util.copier.i18.Messages;

/**
 * Cache for custom converter classes.
 *
 * @author <a href="mailto:aafanasyev@umail.ru">Andrey Afanasiev</a>
 */
public class CustomConverterCache {
    /** Cache map */
    private final ConcurrentMap<Class<? extends CopyCustomConverter>, CopyCustomConverter> cache;

    /**
     * Default constructor
     */
    public CustomConverterCache() {
        cache = new ConcurrentHashMap<>();
    }

    /**
     * Get instance
     *
     * @return Object instance
     */
    public static CustomConverterCache getInstance() {
        return PlaceHolder.self;
    }

    /**
     * To give custom convertes by its class
     *
     * @param clazz Converter class
     * @return Custom converter
     */
    public CopyCustomConverter getConverter(Class<? extends CopyCustomConverter> clazz) {
        return cache.computeIfAbsent(clazz, k -> createClazz(clazz));
    }

    /**
     * Create custom converter
     *
     * @param clazz Converter class
     * @return Custom converter
     */
    private CopyCustomConverter createClazz(Class<? extends CopyCustomConverter> clazz) {
        try {
            return clazz.newInstance();
        } catch(InstantiationException | IllegalAccessException ex) {
            throw new CopyErrorException(
                    String.format(Messages.getString("CustomConverterCache.0"), // NOSONAR //$NON-NLS-1$
                            clazz.getCanonicalName()),
                    ex); // NOSONAR
        }
    }

    /**
     * Singleton place holder
     *
     * @author aafanasyev
     */
    private static class PlaceHolder {
        /** Singleton reference */
        private static final CustomConverterCache self = new CustomConverterCache();

        /**
         * Private default constructor for util class
         */
        private PlaceHolder() {
        }
    }
}
