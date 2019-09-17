/**
 *
 */
package ru.aafanasiev.util.copier.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * Utililty file for working with Currency and other Numerics data<br>
 * It is Thread safe class.
 *
 * @author <a href="mailto:aafanasiev@umail.ru">Andrey Afanasiev</a>
 */
public class NumericUtils {
    /** Logger */
    private static final Logger log = LoggerFactory.getLogger(NumericUtils.class);

    /** Format object for BigDecimal without group delimeters */
    private static final LoadingCache<Thread, DecimalFormat> localFormatMap =
            CacheBuilder
                    .newBuilder()
                    .maximumSize(100)
                    .softValues()
                    .build(new CacheLoader<Thread, DecimalFormat>() {

                        @Override
                        public DecimalFormat load(Thread key) {
                            return getLocalDecimalFormat();
                        }

                    });

    /** Format object for BigDecimal with group delimeters */
    private static final LoadingCache<Thread, DecimalFormat> groupingFormatMap =
            CacheBuilder
                    .newBuilder()
                    .maximumSize(100)
                    .softValues()
                    .build(new CacheLoader<Thread, DecimalFormat>() {

                        @Override
                        public DecimalFormat load(Thread key) {
                            return getLocalDecimalFormatWithGrouping();
                        }

                    });

    private NumericUtils() {
        super();
    }

    /**
     * <p>
     * Get BigDecimal from string representation
     * </p>
     * String representaion without group delimeters. Pattern {@code "(999)999.99"}
     *
     * @param stringDecimal Digital as string
     * @return Digital as BigDecimal
     */
    public static BigDecimal getCurrency(String stringDecimal) {
        DecimalFormat localDecimalFormat;
        try {
            localDecimalFormat = localFormatMap.get(Thread.currentThread());
        } catch(ExecutionException ex) {
            localDecimalFormat = getLocalDecimalFormat();
            log.error("Error in format cache", ex);
        }

        try {
            return (BigDecimal) localDecimalFormat.parse(stringDecimal);
        } catch(ParseException ex) {
            return BigDecimal.valueOf(0.0D);
        }
    }

    /**
     * <p>
     * Get BigDecimal from string representation with group delimeters (for currency)
     * </p>
     * String representaion with group delimeters. Pattern {@code "(999 )999.99")}
     *
     * @param stringDecimal Digital as string
     * @return Digital as BigDecimal
     */
    public static BigDecimal getGroupingCurrency(String stringDecimal) {
        DecimalFormat localDecimalFormat;
        try {
            localDecimalFormat = groupingFormatMap.get(Thread.currentThread());
        } catch(ExecutionException ex) {
            localDecimalFormat = getLocalDecimalFormatWithGrouping();
            log.error("Error in format cache", ex);
        }

        try {
            return (BigDecimal) localDecimalFormat.parse(stringDecimal);
        } catch(ParseException ex) {
            return BigDecimal.valueOf(0.0D);
        }
    }

    /**
     * Create new format object for BigDecimal without group delimeters
     *
     * @return DecimalFormat object
     */
    private static DecimalFormat getLocalDecimalFormat() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(' ');
        symbols.setDecimalSeparator(',');
        String pattern = "#.##";
        DecimalFormat localDecimalFormat = new DecimalFormat(pattern, symbols);
        localDecimalFormat.setParseBigDecimal(true);

        return localDecimalFormat;
    }

    /**
     * Create new format object for BigDecimal with group delimeters
     *
     * @return DecimalFormat object
     */
    private static DecimalFormat getLocalDecimalFormatWithGrouping() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(' ');
        symbols.setDecimalSeparator(',');
        String pattern = "#.##";
        DecimalFormat localDecimalFormat = new DecimalFormat(pattern, symbols);
        localDecimalFormat.setGroupingUsed(true);
        localDecimalFormat.setParseBigDecimal(true);

        return localDecimalFormat;
    }

}
