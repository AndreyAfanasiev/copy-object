/**
 *
 */
package ru.aafanasiev.util.copier;

/**
 * @author aafanasyev
 */
public class SecondLevelObj extends FirstLevelObj {
    long value;

    @Override
    public long getAbs() {
        return value;
    }

    @Override
    public void setAbs(long value) {
        this.value = value;
    }
}
