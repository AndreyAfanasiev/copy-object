/**
 *
 */
package ru.aafanasiev.util.copier;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.reflections.Reflections;

import ru.aafanasiev.annotations.Converter;
import ru.aafanasiev.annotations.NameCalculator;
import ru.aafanasiev.util.copier.compilator.CopyNameCalculator;
import ru.aafanasiev.util.copier.compilator.ObjectNameCalculator;
import ru.aafanasiev.util.copier.converter.CopyConverterObj2Obj;
import ru.aafanasiev.util.copier.converter.base.CopyConverter;
import ru.aafanasiev.util.copier.util.ReflectionUtils;

/**
 * Builder for {@code Copier} object. <br>
 * Can set custom Converter and Name Compilators
 *
 * @author <a href="mailto:aafanasyev@umail.ru">Andrey Afanasiev</a>
 */
public class CopierBuilder {
    /** Logger */
    private static final Logger log = LoggerFactory.getLogger(CopierBuilder.class);

    private static final String BASE_CONVERTOR_PACKAGE = "ru.aafanasiev.util.copier.converter";
    private static final String BASE_COMPILATOR_PACKAGE = "ru.aafanasiev.util.copier.compilator";
    private static final int CONVERTERS_LIST_SIZE = 128;
    private static final int CALCULATORS_LIST_SIZE = 16;

    /** Additional converter packages list */
    private final List<String> converterPackages;
    /** Additional individual converters list */
    private final List<Class<? extends CopyConverter>> converterList;
    /** Additional name calculator packages list */
    private final List<String> nameCalculatorPackages;
    /** Additional individual name calculator list */
    private final List<Class<? extends CopyNameCalculator>> nameCalculatorList;

    private CheckCopyConverter checkCopyConverter = new CheckCopyConverter();
    private CheckNameCalculator checkNameCalculator = new CheckNameCalculator();

    /**
     * Default constrictor for Builder
     */
    private CopierBuilder() {
        converterPackages = new ArrayList<>();
        converterList = new ArrayList<>();
        nameCalculatorPackages = new ArrayList<>();
        nameCalculatorList = new ArrayList<>();
    }

    public static CopierBuilder builder() {
        return new CopierBuilder();
    }

    /**
     * Add individual custom converter
     *
     * @param converterClazz CopyConverter implememtation class
     * @return This CopierBuider object
     */
    public CopierBuilder addCopyConverter(Class<? extends CopyConverter> converterClazz) {
        converterList.add(converterClazz);
        return this;
    }

    /**
     * Add package with custom converters
     *
     * @param converterPackage Converters' package as String
     * @return This CopierBuider object
     */
    public CopierBuilder addCopyConverterPackage(String converterPackage) {
        converterPackages.add(converterPackage);
        return this;
    }

    /**
     * Add individual custom name calculator
     *
     * @param nameCaplculatorClazz CopyNameCalculator implememtation class
     * @return This CopierBuider object
     */
    public CopierBuilder addNameCalculator(Class<? extends CopyNameCalculator> nameCaplculatorClazz) {
        nameCalculatorList.add(nameCaplculatorClazz);
        return this;
    }

    /**
     * Add package with custom name compilators
     *
     * @param nameCalculatorPackage Name compilators' package as String
     * @return This CopierBuider object
     */
    public CopierBuilder addNameCalculatorPackage(String nameCalculatorPackage) {
        nameCalculatorPackages.add(nameCalculatorPackage);
        return this;
    }

    /**
     * Build Copier
     *
     * @return Builded copier
     */
    public Copier build() {
        CopierOptions options = new CopierOptions();
        buildConverters(options);
        buildNameCalculators(options);
        return new Copier(options);
    }

    @SuppressWarnings("unchecked")
    private <T> void addInternalObjects(List<Class<? extends T>> fullCalculators,
            Class<? extends Annotation> annotation, Checker checker, String basePackage,
            Class<? extends T> lastClazz) {
        new Reflections(basePackage).getTypesAnnotatedWith(annotation).forEach(clazz -> {
            if(!lastClazz.equals(clazz) && checker.check(clazz)) {
                fullCalculators.add((Class<? extends T>) clazz);
            }
        });
        fullCalculators.add(lastClazz);
    }

    @SuppressWarnings("unchecked")
    private <T> void addPackage(List<Class<? extends T>> fullList, String packageName,
            Class<? extends Annotation> annotation, Checker cheker) {
        new Reflections(packageName).getTypesAnnotatedWith(annotation).forEach(clazz -> {
            if(cheker.check(clazz)) {
                fullList.add((Class<? extends T>) clazz);
            }
        });
    }

    /**
     * Converters builder<br>
     * Generation orders:
     * <ol>
     * <li>Individual user converters classes</li>
     * <li>Converter classes from user packages</li>
     * <li>Internal converters except CopyConveterObj2Obj</li>
     * <li>CopyConveterObj2Obj converter (must be last converter)</li>
     * </ol>
     *
     * @param options Copy options object
     */
    private void buildConverters(CopierOptions options) {
        List<Class<? extends CopyConverter>> fullConverters = new ArrayList<>(CONVERTERS_LIST_SIZE);
        options.getCopyConverters().clear();
        fullConverters.addAll(converterList);
        converterPackages.forEach(
                packageName -> addPackage(fullConverters, packageName, Converter.class, checkCopyConverter));
        /* Next row is musted be last row for adding converters' methods */
        addInternalObjects(fullConverters, Converter.class, checkCopyConverter, BASE_CONVERTOR_PACKAGE,
                CopyConverterObj2Obj.class);
        generateObjects(options.getCopyConverters(), fullConverters);
    }

    /**
     * Create Name Calculators list in CopyOptions
     */
    private void buildNameCalculators(CopierOptions options) {
        final List<Class<? extends CopyNameCalculator>> fullCalculators = new ArrayList<>(CALCULATORS_LIST_SIZE);
        options.getNameCalculators().clear();
        fullCalculators.addAll(nameCalculatorList);
        nameCalculatorPackages.forEach(
                packageName -> addPackage(fullCalculators, packageName, NameCalculator.class, checkNameCalculator));
        /* Next row is musted be last row for adding name calculators' methods */
        addInternalObjects(fullCalculators, NameCalculator.class, checkNameCalculator, BASE_COMPILATOR_PACKAGE,
                ObjectNameCalculator.class);
        final List<CopyNameCalculator> genList = new ArrayList<>(fullCalculators.size());
        generateObjects(genList, fullCalculators);
        final List<NameCalculatorHolder> srtList = new ArrayList<>(fullCalculators.size());
        genList.forEach(calculator -> {
            NameCalculator annotation = calculator.getClass().getAnnotation(NameCalculator.class);
            int priority = annotation == null ? 1 : annotation.value();
            srtList.add(new NameCalculatorHolder(calculator, priority));
        });
        Collections.sort(srtList, new NameCalculatorComparator());
        srtList.forEach(holder -> options.getNameCalculators().add(holder.nameCalculator));
    }

    /**
     * Generate objects list by object classes list
     *
     * @param result Objects list
     * @param fullCalculators Object classes list
     */
    private <T> void generateObjects(List<T> result, List<Class<? extends T>> fullCalculators) {
        for(Class<? extends T> clazz : fullCalculators) {
            try {
                result.add(clazz.newInstance());
            } catch(InstantiationException | IllegalAccessException ex) {
                log.error("Creation error for class {}", clazz.getCanonicalName(), ex);
            }
        }
    }

    /**
     * Check instanseOf for CopyConveter interface
     *
     * @author aafanasyev
     */
    static class CheckCopyConverter implements Checker {

        /*
         * (non-Javadoc)
         * @see ru.aafanasiev.util.copier.CopierBuilder.Checker#check(java.lang.Class)
         */
        @Override
        public boolean check(Class<?> clazz) {
            return ReflectionUtils.instanseOf(clazz, CopyConverter.class);
        }
    }

    /**
     * Check instanseOf
     *
     * @author aafanasyev
     */
    @FunctionalInterface
    interface Checker {
        /**
         * Check instanseOf method
         *
         * @param clazz Tested class
         * @return <code>true</code> - clazz is instanse of a class,<br>
         *         <code>false</code> - clazz is not instanse of a class
         */
        public boolean check(Class<?> clazz);
    }

    /**
     * Check instanseOf for CopyNameCalculator interface
     *
     * @author aafanasyev
     */
    static class CheckNameCalculator implements Checker {

        /*
         * (non-Javadoc)
         * @see ru.aafanasiev.util.copier.CopierBuilder.Checker#check(java.lang.Class)
         */
        @Override
        public boolean check(Class<?> clazz) {
            return ReflectionUtils.instanseOf(clazz, CopyNameCalculator.class);
        }
    }

    private static class NameCalculatorHolder {
        private int priority;
        private CopyNameCalculator nameCalculator;

        private NameCalculatorHolder(CopyNameCalculator nameCalculator, int priority) {
            this.nameCalculator = nameCalculator;
            this.priority = priority;
        }
    }

    private static class NameCalculatorComparator implements Comparator<NameCalculatorHolder> {

        @Override
        public int compare(NameCalculatorHolder o1, NameCalculatorHolder o2) {
            if(o1.priority == o2.priority) {
                return 0;
            } else {
                return o1.priority < o2.priority ? 1 : -1;
            }
        }

    }
}
