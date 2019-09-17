/**
 *
 */
package ru.aafanasiev.util.copier.complex;

/**
 * Целый тип
 *
 * @author aafanasyev
 */
public class Fobj2 {
    private Custom2 f;

    public Fobj2() {
        super();
    }

    public Fobj2(Custom2 f) {
        this();
        this.f = f;
    }

    /**
     * @return the f
     */
    public Custom2 getF() {
        return f;
    }

    /**
     * @param f the f to set
     */
    public void setF(Custom2 f) {
        this.f = f;
    }
}
