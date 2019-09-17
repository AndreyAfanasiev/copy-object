/**
 *
 */
package ru.aafanasiev.util.copier;

import ru.aafanasiev.annotations.CopyTo;
import ru.aafanasiev.annotations.NoCopy;

/**
 * @author aafanasyev
 */
@SuppressWarnings("unused")
public class ZeroLevelObj {
    private long abs2;
    private String str;
    private String str2;
    private boolean bool;
    private int index;
    private long abs;

    /**
     * @return the str2
     */
    public String get() {
        return "get";
    }

    @NoCopy
    public long getAbs() {
        return 1L;
    }

    /**
     * @return the abs2
     */
    @CopyTo("")
    public long getDummy() {
        return 0L;
    }

    /**
     * @return the abs2
     */
    @CopyTo("abs3")
    public long getAbs2() {
        return 2L;
    }

    /**
     * @return the index
     */
    public int getIndex() {
        return 3;
    }

    /**
     * @return the str
     */
    @CopyTo("str2")
    public String getStr() {
        return "getStr";
    }

    /**
     * @return the str2
     */
    public String getStr2() {
        return "getStr2";
    }

    /**
     * @return the bool
     */
    public boolean is() {
        return true;
    }

    /**
     * @return the bool
     */
    public boolean isBool() {
        return false;
    }

    /**
     * @param str2 the str2 to set
     */
    public void set(String str2) {
        this.str2 = str2;
    }

    public void setAbs(long value) {
        this.abs = value;
    }

    /**
     * @param abs2 the abs2 to set
     */
    public void setAbs2(long abs2) {
        this.abs2 = abs2;
    }

    /**
     * @param bool the bool to set
     */
    public void setBool(boolean bool) {
        this.bool = bool;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * @param str the str to set
     */
    public void setStr(String str) {
        this.str = str;
    }

    /**
     * @param str2 the str2 to set
     */
    public void setStr2(String str2) {
        this.str2 = str2;
    }
}
