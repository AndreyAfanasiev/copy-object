/**
 *
 */
package ru.aafanasiev.util.copier.complex;

import java.util.Date;

import ru.aafanasiev.annotations.CopyImmutable;

/**
 * Целый тип
 *
 * @author aafanasyev
 */
public class FudateImmutable {
    private Date f;

    public FudateImmutable() {
        super();
    }

    public FudateImmutable(Date f) {
        this();
        this.f = f;
    }

    /**
     * @return the f
     */
    @CopyImmutable
    public Date getF() {
        return f;
    }

    /**
     * @param f the f to set
     */
    public void setF(Date f) {
        this.f = f;
    }
}
