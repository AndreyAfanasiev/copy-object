/**
 *
 */
package ru.aafanasiev.util.copier;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import ru.aafanasiev.util.copier.converter.base.ConverterKey;
import ru.aafanasiev.util.copier.converter.base.CopyEntry;

/**
 * @author <a href="mailto:aafanasiev@umail.ru">Andrey Afanasiev</a>
 */
class CopyAlgorithmsCache {
    /**
     * Converters map. <br>
     * It is copy algorithms cache. <br>
     * It is cache of a simple converters. Simple conterter converts data from the fisrt concrete class to the second
     * concrete class. <br>
     * Key - class-to-class pair. <br>
     * Value - information about data converter
     */
    private final ConcurrentMap<ConverterKey, List<CopyEntry>> algorithmsCache;
    private final ConcurrentMap<String, ObjectMapper> mapperCache;

    private CopyAlgorithmsCache() {
        algorithmsCache = new ConcurrentHashMap<>();
        mapperCache = new ConcurrentHashMap<>();
    }

    public static CopyAlgorithmsCache getInstance() {
        return ClassHolder.self;
    }

    /**
     * Clear caches
     */
    public void clear() {
        algorithmsCache.clear();
    }

    /**
     * Get algorithms cache.
     *
     * @return The algorithms cache
     */
    public ConcurrentMap<ConverterKey, List<CopyEntry>> getAlgorithmsCache() {
        return algorithmsCache;
    }

    /**
     * @return the mapperCache
     */
    public ConcurrentMap<String, ObjectMapper> getMapperCache() {
        return mapperCache;
    }

    /**
     * Class holder for singleton pattern
     *
     * @author <a href="mailto:aafanasiev@umail.ru">Andrey Afanasiev</a>
     */
    private static class ClassHolder {
        /** Singeltom ancher */
        public static final CopyAlgorithmsCache self = new CopyAlgorithmsCache();

        private ClassHolder() {
        }
    }
}
