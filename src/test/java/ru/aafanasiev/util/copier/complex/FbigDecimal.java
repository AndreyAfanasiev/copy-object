/**
 *
 */
package ru.aafanasiev.util.copier.complex;

import java.math.BigDecimal;

/**
 * Целый тип
 *
 * @author aafanasyev
 */
public class FbigDecimal {
    private BigDecimal f;

    public FbigDecimal() {
        super();
    }

    public FbigDecimal(BigDecimal f) {
        this();
        this.f = f;
    }

    public FbigDecimal(long f) {
        this();
        this.f = BigDecimal.valueOf(f);
    }

    public FbigDecimal(double f) {
        super();
        this.f = BigDecimal.valueOf(f);
    }

    /**
     * @return the f
     */
    public BigDecimal getF() {
        return f;
    }

    /**
     * @param f the f to set
     */
    public void setF(BigDecimal f) {
        this.f = f;
    }
}
