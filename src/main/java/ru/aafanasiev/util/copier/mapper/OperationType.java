/**
 *
 */
package ru.aafanasiev.util.copier.mapper;

/**
 * Operation type.
 *
 * @author <a href="mailto:aafanasiev@umail.ru">Andrey Afanasiev</a>
 */
public enum OperationType {
    /** Simple copy: object to object */
    SIMPLE,
    /** Get field */
    READ_VALUE,
    /** Convert simple value (one source - one target) */
    SIMPLE_CONVERT,
    /** Custom converter: one object ro one object */
    CUSTOM_CONVERTER,
    /** Concatenate strings */
    CONCAT,
    /** String constant */
    STRING_CONSTANT,
    /** Double contsant */
    DOUBLE_CONSTANT,
    /** Long constant */
    LONG_CONSTANT,
    /** Integer constant */
    INT_CONSTANT,
    /** BigDecimal Constant */
    BIG_DECIMAL_CONSTANT,
    /** Substring for string value */
    SUBSTR,
    /** Add numeric value */
    ADD,
    /** Substract numeric value */
    MINUS,
    /** Multiplication number value */
    MULTIPLICATION,
    /** Divide numeric value */
    DIVIDE,
    /** Open (Left) brace */
    OPEN_BRACE,
    /** Close (right) brace */
    CLOSE_BRACE,
    /** Vector converter: one target method - many source object */
    VECTOR_CONVERTER,
    /** Converter from full source object to one target field (method) */
    FULL_CONVERTER,
    /** Collection converter (List, Set) */
    COLLECTION_CONVERTER,
    /** Map converter */
    MAP_CONVERTER;
}
