/**
 *
 */
package ru.aafanasiev.util.copier.complex;

import java.math.BigInteger;

/**
 * Целый тип
 *
 * @author aafanasyev
 */
public class FbigInt {
    private BigInteger f;

    public FbigInt() {
        super();
    }

    public FbigInt(BigInteger f) {
        this();
        this.f = f;
    }

    public FbigInt(long l) {
        this();
        f = BigInteger.valueOf(l);
    }

    /**
     * @return the f
     */
    public BigInteger getF() {
        return f;
    }

    /**
     * @param f the f to set
     */
    public void setF(BigInteger f) {
        this.f = f;
    }
}
