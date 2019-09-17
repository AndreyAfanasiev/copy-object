/**
 *
 */
package ru.aafanasiev.util.copier.converter.cstring;

import java.beans.ConstructorProperties;

/**
 * Parameters for Boolean to String converter.
 *
 * @author aafanasyev
 */
public class Str2BoolParams {
    /** String for true value */
    private String[] trueValues;
    /** String for false value */
    private String[] falseValues;
    /** Case sensitive flas */
    private boolean caseSensitive;

    /**
     * Default Constructor
     */
    public Str2BoolParams() {
        super();
    }

    /**
     * Constrictor with initialize fields
     *
     * @param trueValues String for true value
     * @param falseValues String for false value
     */
    @ConstructorProperties({"trueValue", "falseValue"})
    public Str2BoolParams(String[] trueValues, String[] falseValues) {
        super();
        this.trueValues = trueValues.clone();
        this.falseValues = falseValues.clone();
    }

    /**
     * Constrictor with full initialize fields
     *
     * @param trueValues String for true value
     * @param falseValues String for false value
     * @param caseSensitive Case sensitive for comparation
     */
    public Str2BoolParams(String[] trueValues, String[] falseValues, boolean caseSensitive) {
        super();
        this.trueValues = trueValues.clone();
        this.falseValues = falseValues.clone();
        this.caseSensitive = caseSensitive;
    }

    /**
     * @return the falseValue
     */
    public String[] getFalseValues() {
        return falseValues.clone();
    }

    /**
     * @return the trueValue
     */
    public String[] getTrueValues() {
        return trueValues.clone();
    }

    /**
     * @return the caseSensetive
     */
    public boolean isCaseSensitive() {
        return caseSensitive;
    }

    /**
     * @param caseSensitive the caseSensetive to set
     */
    public void setCaseSensitive(boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
    }

    /**
     * @param falseValue the falseValue to set
     */
    public void setFalseValues(String[] falseValue) {
        this.falseValues = falseValue.clone();
    }

    /**
     * @param trueValue the trueValue to set
     */
    public void setTrueValues(String[] trueValue) {
        this.trueValues = trueValue.clone();
    }
}
