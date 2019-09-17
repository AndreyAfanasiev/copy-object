/**
 *
 */
package ru.aafanasiev.util.copier.complex;

import java.sql.Date;

/**
 * Целый тип
 *
 * @author aafanasyev
 */
public class Fsdate {
    private Date f;

    public Fsdate() {
        super();
    }

    public Fsdate(Date f) {
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
