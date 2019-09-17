/**
 *
 */
package ru.aafanasiev.util.copier.complex;

import java.util.Date;

/**
 * Целый тип
 *
 * @author aafanasyev
 */
public class Fudate {
    private Date f;

    public Fudate() {
        super();
    }

    public Fudate(Date f) {
        this();
        this.f = f;
    }

    /**
     * @return the f
     */
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
