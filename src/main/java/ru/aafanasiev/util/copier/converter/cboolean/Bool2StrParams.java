/**
 *
 */
package ru.aafanasiev.util.copier.converter.cboolean;

import java.beans.ConstructorProperties;

/**
 * Parameters for Boolean to String converter.
 *
 * @author aafanasyev
 */
public class Bool2StrParams {
    /** String for true value */
    private String trueValue;
    /** String for false value */
    private String falseValue;

    /**
     * Default Constructor
     */
    public Bool2StrParams() {
        super();
    }

    /**
     * Constrictor with initialize fields
     *
     * @param trueValue String for true value
     * @param falseValue String for false value
     */
    @ConstructorProperties({"falseValue", "trueValue"})
    public Bool2StrParams(String trueValue, String falseValue) {
        super();
        this.trueValue = trueValue;
        this.falseValue = falseValue;
    }

    /**
     * @return the trueValue
     */
    public String getTrueValue() {
        return trueValue;
    }

    /**
     * @return the falseValue
     */
    public String getFalseValue() {
        return falseValue;
    }

    /**
     * @param trueValue the trueValue to set
     */
    public void setTrueValue(String trueValue) {
        this.trueValue = trueValue;
    }

    /**
     * @param falseValue the falseValue to set
     */
    public void setFalseValue(String falseValue) {
        this.falseValue = falseValue;
    }
}
