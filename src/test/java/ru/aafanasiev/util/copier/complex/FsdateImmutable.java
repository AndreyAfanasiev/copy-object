/**
 *
 */
package ru.aafanasiev.util.copier.complex;

import java.sql.Date;

import ru.aafanasiev.annotations.CopyImmutable;

/**
 * Целый тип
 *
 * @author aafanasyev
 */
public class FsdateImmutable {
    private Date f;

    public FsdateImmutable() {
        super();
    }

    public FsdateImmutable(Date f) {
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
