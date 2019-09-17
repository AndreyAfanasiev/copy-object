/**
 *
 */
package ru.aafanasiev.util.copier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.aafanasiev.util.copier.util.ReflectionUtils;
import ru.aafanasiev.util.copier.converter.base.ConverterKey;
import ru.aafanasiev.util.copier.converter.base.CopyConverter;
import ru.aafanasiev.util.copier.converter.base.CopyEntry;
import ru.aafanasiev.util.copier.converter.base.CopyErrorException;

/**
 * Compilator for copy algorithm (copy programm). <br>
 * Compilator finds and sets setter for each copy step.
 *
 * @author <a href="mailto:aafanasyev@umail.ru">Andrey Afanasiev</a>
 */
class CopierCompilator {
    /** Logger */
    private static final Logger log = LoggerFactory.getLogger(CopierCompilator.class);

    /**
     * Converters map. <br>
     * It is cache of a simple converters (strong equals for both classes). Simple conterter converts data from the
     * fisrt concrete class to the second concrete class. <br>
     * Key - pair source-destination classes,<br>
     * value - copy converter
     */
    private final Map<ConverterKey, CopyConverter> simpleConverters;

    /**
     * List converetrs with instanceOf comparison
     */
    private final List<ConvertersStorage> instanceOfConverters;

    /**
     * Map for primitive types (for example, <i><code>int.class</code> -> <code>Integer.class</code></i>)
     */
    private final Map<Class<?>, Class<?>> simpleTypeConverterMap;

    /**
     * Constrictor with options. <br>
     * Create maps and set converters and simple types converters map.
     *
     * @param copyOptions CopyOptions object with copier parameters
     */
    CopierCompilator(CopierOptions copyOptions) {
        simpleTypeConverterMap = new HashMap<>();
        simpleConverters = new HashMap<>();
        instanceOfConverters = new ArrayList<>();
        copyOptions.getCopyConverters().forEach(this::registerConverter);
        Collections.sort(instanceOfConverters, new ConverterStorageComparator());
        createSimpleTypeEqualsMap(simpleTypeConverterMap);
    }

    /**
     * Create simple types converter map
     *
     * @param converterMap Simple types convertion map
     */
    private static final void createSimpleTypeEqualsMap(Map<Class<?>, Class<?>> converterMap) {
        converterMap.put(int.class, Integer.class);
        converterMap.put(long.class, Long.class);
        converterMap.put(float.class, Float.class);
        converterMap.put(double.class, Double.class);
        converterMap.put(boolean.class, Boolean.class);
    }

    /**
     * Compile copy operations list (copy algorithm)
     *
     * @param copyList The copy operations list
     * @return The CopyEntry list (copy algorithm)
     */
    List<CopyEntry> compileCopyList(List<CopyEntry> copyList) {
        if(null == copyList) {
            return Collections.emptyList();
        }

        List<CopyEntry> result = new ArrayList<>(copyList.size());
        copyList.forEach(entry -> {
            if(compileCopy(entry)) {
                result.add(entry);
            }
        });

        return result;

    }

    /**
     * Complie one copy operation.<br>
     * Compilation consists of following operations:
     * <ul>
     * <li>Check setter parameters number</li>
     * <li>Checking the types that the Setter returns and accepts the Getter</li>
     * <li>Calculate data conversation</li>
     * <li>Find and compile copy data annotations</li>
     * <li>Compyle CopyImmutable annotation for Date types</li>
     * <li>Compilation List, Set, Map (пока не реализовано)</li>
     * </ul>
     * Following converts is correct:
     * <table border="1">
     * <tr>
     * <td><b>Source type</b></td>
     * <td><b>Destinations types</b></td>
     * </tr>
     * <tr>
     * <td>&nbsp;int, Integer</td>
     * <td>long, Integer, Long, String, float, double, Float, Double, BigDecimal, BigInteger, Boolean(+),
     * boolean(+)</td>
     * </tr>
     * <tr>
     * <td>long, Long</td>
     * <td>int(*), Integer(*), Long, String, float, double, Float, Double, BigDecimal, BigInteger, Boolean(+),
     * boolean(+)</td>
     * </tr>
     * <tr>
     * <td>String</td>
     * <td>int,long,Integer, Long, float, bouble, Float, Double, boolean(#), Boolean(#), BigDecimal,
     * BigInteger&nbsp;</td>
     * </tr>
     * <tr>
     * <td>boolean, Boolean</td>
     * <td>boolean, Boolean, String, int(+), long(+)</td>
     * </tr>
     * <tr>
     * <td>&nbsp;float, Float</td>
     * <td>double,Float, Double, BigDecimal, BigInteger, String</td>
     * </tr>
     * <tr>
     * <td>&nbsp;double, Double</td>
     * <td>float(*),Float(*), Double, BigDecimal, BigInteger, String</td>
     * </tr>
     * </table>
     * ,где
     * <dt>(*)</dt>
     * <dd>Possible loss of accuracy and conversion error</dd>
     * <dt>(+)</dt>
     * <dd>Conversion by C/C++ rules:<br>
     * <code><b>true</code></b> - value not 0 for source int, value 1 for destination int<br>
     * <code><b>false</code></b> - value 0</br>
     * </dd>
     * <dt>(#)</dt>
     * <dd>Conversion by following rules:<br>
     * <code><b>true</code></b> - to string "true", from string <code>"true", "yes", "1"</code><br>
     * <code><b>false</code></b> - to string "false", from string <code>"false", "no", "0"</code></br>
     * </dd>
     *
     * @param entry Single copy step description
     * @return Compilation result
     *         <ul>
     *         <li><code><b>true</code></b> - copy operation is correct</li>
     *         <li><code><b>false</code></b> - copy operation is bad. Operation will be removed.</li>
     *         </ul>
     */
    private boolean compileCopy(CopyEntry entry) {
        if(null == entry.getToMethod() || null == entry.getFromMethod()) {
            return false;
        }
        Class<?>[] toClazzes = entry.getToMethod().getParameterTypes();
        Class<?> fromClazz = entry.getFromMethod().getReturnType();
        if(toClazzes.length != 1) {
            log.error("Setter method {} in class {} must have 1 parameter. Current number of parameter: {}",
                    entry.getToName(), entry.getToMethod().getDeclaringClass().getCanonicalName(),
                    toClazzes.length);
            return false;
        }
        Class<?> toClazz = toClazzes[0];

        toClazz = getSimpleType(toClazz);
        fromClazz = getSimpleType(fromClazz);
        CopyConverter converter = getConverter(new ConverterKey(fromClazz, toClazz));
        if(null == converter) {
            log.error("For source class {} and target class {} could not converter for getter {} and setter {}",
                    fromClazz, toClazz, entry.getFromMethod().getName(), entry.getToMethod().getName());
            return false;
        }
        entry.setConverter(converter);
        entry.setParameters(converter.getParams(entry));

        return true;
    }

    /**
     * Get convertor for data types
     *
     * @param key Pair data types
     * @return COnvertor or <code>null</code>.<br>
     */
    private CopyConverter getConverter(ConverterKey key) {
        /* Get strong equal for both source and target classes */
        CopyConverter result = simpleConverters.get(key);

        if(null != result) {
            return result;
        }
        return getInstanceofConverter(key);
    }

    /**
     * Get instanceOf converter. <br>
     * This method scans {@code instanceOfConverters} list and return first converter with equal key.
     *
     * @param key The source key with classes of getter and setter.
     * @return Converter or {@code null} if converter could not found
     */
    private CopyConverter getInstanceofConverter(ConverterKey key) {
        for(ConvertersStorage record : instanceOfConverters) {
            if(keyEquals(key, record.getKey())) {
                return record.getConverter();
            }
        }
        return null;
    }

    /**
     * Compare key of copy type and converter key
     *
     * @param key Key with copy objects types
     * @param key2 Converter key
     * @return {@code true} - keys are equals,<br>
     *         {@code false} - keys are differents
     */
    private boolean keyEquals(ConverterKey key, ConverterKey key2) {
        if(key2.isFromInstance()) {
            if(!ReflectionUtils.instanseOf(key.getFromClazz(), key2.getFromClazz())) {
                return false;
            }
        } else {
            if(key.getFromClazz() != key2.getFromClazz()) {
                return false;
            }
        }
        if(key2.isToInstance()) {
            if(!ReflectionUtils.instanseOf(key.getToClazz(), key2.getToClazz())) {
                return false;
            }
        } else {
            if(key.getToClazz() != key2.getToClazz()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Convert primitive type to warp objects
     *
     * @param source SOurce class
     * @return Normalized class
     */
    private Class<?> getSimpleType(Class<?> source) {
        Class<?> result = simpleTypeConverterMap.get(source);
        return null == result ? source : result;
    }

    /**
     * Register new converters
     *
     * @param converter The cconverter
     */
    private final void registerConverter(CopyConverter converter) {
        Set<ConverterKey> keySet = new HashSet<>();
        for(ConverterKey key : converter.listConvertedPairs()) {
            if(key.isFromInstance() || key.isToInstance()) {
                if(keySet.contains(key)) {
                    throw new CopyErrorException(
                            String.format(
                                    "Duplucate converter for comparison: from class: %s(instanceOf=%b), to class: %s(instanceOf=%b)",
                                    key.getFromClazz().getCanonicalName(), key.isFromInstance(),
                                    key.getToClazz().getCanonicalName(), key.isToInstance()));
                }
                keySet.add(key);
                instanceOfConverters.add(new ConvertersStorage(key, converter));
            } else {
                if(simpleConverters.containsKey(key)) {
                    throw new CopyErrorException(
                            String.format("Duplucate converter for types: from class: %s, to class: %s",
                                    key.getFromClazz().getCanonicalName(), key.getToClazz().getCanonicalName()));
                }
                simpleConverters.put(key, converter);
            }
        }
    }

    /**
     * Storage for second level CopyConverter storages.
     *
     * @author <a href="mailto:aafanasiev@umail.ru">Andrey Afanasiev</a>
     */
    private static class ConvertersStorage {
        private final ConverterKey key;
        private final CopyConverter converter;

        /**
         * Create records for concrete converter key
         *
         * @param key The converter key
         * @param converter The converter
         */
        public ConvertersStorage(ConverterKey key, CopyConverter converter) {
            this.key = key;
            this.converter = converter;
        }

        /**
         * @return the key
         */
        public ConverterKey getKey() {
            return key;
        }

        /**
         * @return the converter
         */
        public CopyConverter getConverter() {
            return converter;
        }
    }

    private static class ConverterStorageComparator implements Comparator<ConvertersStorage> {

        @Override
        public int compare(ConvertersStorage o1, ConvertersStorage o2) {
            if(o1.getKey().getPriority() == o2.getKey().getPriority()) {
                return 0;
            } else {
                return o1.getKey().getPriority() < o2.getKey().getPriority() ? 1 : -1;
            }
        }

    }
}
