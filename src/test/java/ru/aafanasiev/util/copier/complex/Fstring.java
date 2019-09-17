/**
 *
 */
package ru.aafanasiev.util.copier.complex;

/**
 * Целый тип
 *
 * @author aafanasyev
 */
public class Fstring {
    private String f;

    public Fstring() {
        super();
    }

    public Fstring(String f) {
        this();
        this.f = f;
    }

    /**
     * @return the f
     */
    public String getF() {
        return f;
    }

    /**
     * @param f the f to set
     */
    public void setF(String f) {
        this.f = f;
    }
}
