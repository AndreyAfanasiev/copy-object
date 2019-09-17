/**
 *
 */
package ru.aafanasiev.util.copier.complex;

/**
 * Целый тип
 *
 * @author aafanasyev
 */
public class Fobj1 {
    private Custom1 f;

    public Fobj1() {
        super();
    }

    public Fobj1(Custom1 f) {
        this();
        this.f = f;
    }

    /**
     * @return the f
     */
    public Custom1 getF() {
        return f;
    }

    /**
     * @param f the f to set
     */
    public void setF(Custom1 f) {
        this.f = f;
    }
}
