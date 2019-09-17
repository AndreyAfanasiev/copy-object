/**
 *
 */
package ru.aafanasiev.util.copier.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Date and Time utility class
 *
 * @author <a href="mailto:aafanasyev@umail.ru">Andrey Afanasiev</a>
 */
public class DateTimeUtils {
    private static final DateTimeFormatter DDMMYY_FORMATTER =
            DateTimeFormatter.ofPattern(
                    "dd.MM.yy");
    private static final DateTimeFormatter DDMMYYYY_FORMATTER =
            DateTimeFormatter.ofPattern(
                    "dd.MM.yyyy");
    private static final DateTimeFormatter YYDDMM_FORMATTER =
            DateTimeFormatter.ofPattern(
                    "yy.MM.dd");
    private static final DateTimeFormatter YYYYDDMM_FORMATTER;
    static {
        YYYYDDMM_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;
    }

    /**
     * Private constructor for utility class
     */
    private DateTimeUtils() {
        super();
    }

    /**
     * Convert LocalDate to java.util.Date
     *
     * @param date Source date
     * @return Date in format java.util.Date
     */
    public static Date convert2Date(LocalDate date) {
        return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Convert java.util.Date to LocalDate
     *
     * @param date Source date
     * @return Local Date
     */
    public static LocalDate convert2LocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Get LocalTme from string with pattern <code>hh:mm:ss</code>
     *
     * @param stringTime Time string
     * @return Local time
     */
    public static LocalTime getIsoLocalTime(String stringTime) {
        return LocalTime.parse(stringTime, DateTimeFormatter.ISO_LOCAL_TIME);
    }

    /**
     * Get LocalDate from string with pattern {@code dd.MM.yy}
     *
     * @param stringDate Date string
     * @return Дата Local Date
     */
    public static LocalDate getLocalDate4DdMmYy(String stringDate) {
        return LocalDate.parse(stringDate, DDMMYY_FORMATTER);
    }

    /**
     * Get LocalDate from string with pattern {@code dd.MM.yyyy}
     *
     * @param stringDate Date string
     * @return Дата Local Date
     */
    public static LocalDate getLocalDate4DdMmYyyy(String stringDate) {
        return LocalDate.parse(stringDate, DDMMYYYY_FORMATTER);
    }

    /**
     * Get LocalDate from string with pattern {@code yy.dd.MM}
     *
     * @param stringDate Date string
     * @return Дата Local Date
     */
    public static LocalDate getLocalDate4YyMmDd(String stringDate) {
        return LocalDate.parse(stringDate, YYDDMM_FORMATTER);
    }

    /**
     * Get LocalDate from string with pattern {@code yy.dd.MM}
     *
     * @param stringDate Date string
     * @return Дата Local Date
     */
    public static LocalDate getLocalDate4YyyyMmDd(String stringDate) {
        return LocalDate.parse(stringDate, YYYYDDMM_FORMATTER);
    }

    /**
     * Get string representation for LocalDate object. Format {@code dd.MM.yy}
     *
     * @param date date in LocalDate format
     * @return String date representaion in format {@code dd.MM.yy}
     */
    public static String putLocalDate4DdMmYy(LocalDate date) {
        return String.format("%1$td.%1$tm.%1$ty", date);
    }

    /**
     * Get string representation for LocalDate object. Format {@code dd.mm.yyyy}
     *
     * @param date date in LocalDate format
     * @return String date representaion in format {@code dd.mm.yyyy}
     */
    public static String putLocalDate4DdMmYyyy(LocalDate date) {
        return String.format("%1$td.%1$tm.%1$tY", date);
    }

    /**
     * Get string representation for LocalDate object. Format {@code yyyy-mm-dd}
     *
     * @param date date in LocalDate format
     * @return String date representaion in format {@code yyyy-mm-dd}
     */
    public static String putLocalDate4YyyyMmDd(LocalDate date) {
        return String.format("%1$tY-%1$tm-%1$td", date);
    }
}
