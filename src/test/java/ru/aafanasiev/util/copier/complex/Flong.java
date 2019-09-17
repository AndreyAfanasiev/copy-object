/**
 *
 */
package ru.aafanasiev.util.copier.complex;

/**
 * Целый тип
 *
 * @author aafanasyev
 */
public class Flong {
    private long f;

    public Flong() {
        super();
    }

    public Flong(long f) {
        this();
        this.f = f;
    }

    /**
     * @return the f
     */
    public long getF() {
        return f;
    }

    /**
     * @param f the f to set
     */
    public void setF(long f) {
        this.f = f;
    }
}
