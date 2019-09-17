/**
 *
 */
package ru.aafanasiev.util.copier;

/**
 * @author aafanasyev
 */
public abstract class FirstLevelObj {
    int dummy;
    boolean bool;

    /**
     * @return the dummy
     */
    public int get() {
        return dummy;
    }

    public abstract long getAbs();

    /**
     * @return the bool
     */
    public boolean isBool() {
        return bool;
    }

    /**
     * @param dummy the dummy to set
     */
    public void set(int dummy) {
        this.dummy = dummy;
    }

    public abstract void setAbs(long value);

    /**
     * @param bool the bool to set
     */
    public void setBool(boolean bool) {
        this.bool = bool;
    }
}
