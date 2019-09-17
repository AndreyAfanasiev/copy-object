/**
 *
 */
package ru.aafanasiev.util.copier;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ru.aafanasiev.annotations.CopyTo;

/**
 * @author aafanasyev
 */
public class ConverterTestObj {
    int im;
    long lm;
    float fm;
    double dm;
    Integer iojbj;
    Long lobj;
    Float fobj;
    Double dobj;
    String sm;
    boolean bool;
    Date time;
    java.sql.Date sqlTime;
    List<Long> ll;
    Set<String> ss;
    Map<String, Long> slm;

    public ConverterTestObj() {
        ll = new ArrayList<>();
        ss = new HashSet<>();
        slm = new HashMap<>();
    }

    /**
     * @return the dm
     */
    public double getDm() {
        return dm;
    }

    /**
     * @return the dm
     */
    @CopyTo("fm")
    public double getDmF() {
        return dm;
    }

    /**
     * @return the dobj
     */
    public Double getDobj() {
        return dobj;
    }

    /**
     * @return the fm
     */
    public float getFm() {
        return fm;
    }

    /**
     * @return the fm
     */
    @CopyTo("dm")
    public float getFmD() {
        return fm;
    }

    /**
     * @return the fobj
     */
    public Float getFobj() {
        return fobj;
    }

    /**
     * @return the im
     */
    public int getIm() {
        return im;
    }

    /**
     * @return the im
     */
    @CopyTo("lm")
    public int getImL() {
        return im;
    }

    /**
     * @return the iojbj
     */
    public Integer getIobj() {
        return iojbj;
    }

    /**
     * @return the ll
     */
    public List<Long> getLl() {
        return ll;
    }

    /**
     * @return the lm
     */
    public long getLm() {
        return lm;
    }

    /**
     * @return the lm
     */
    @CopyTo("im")
    public long getLmI() {
        return lm;
    }

    /**
     * @return the lobj
     */
    public Long getLobj() {
        return lobj;
    }

    /**
     * @return the slm
     */
    public Map<String, Long> getSlm() {
        return slm;
    }

    /**
     * @return the sm
     */
    public String getSm() {
        return sm;
    }

    /**
     * @return the sqlTime
     */
    public java.sql.Date getSqlTime() {
        return sqlTime;
    }

    /**
     * @return the sqlTime
     */
    @CopyTo("time")
    public java.sql.Date getSqlTimeTime() {
        return sqlTime;
    }

    /**
     * @return the ss
     */
    public Set<String> getSs() {
        return ss;
    }

    /**
     * @return the time
     */
    public Date getTime() {
        return time;
    }

    /**
     * @return the time
     */
    @CopyTo("sqlTime")
    public Date getTimeSqlTime() {
        return time;
    }

    /**
     * @return the bool
     */
    public boolean isBool() {
        return bool;
    }

    /**
     * @param bool the bool to set
     */
    public void setBool(boolean bool) {
        this.bool = bool;
    }

    /**
     * @param dm the dm to set
     */
    public void setDm(double dm) {
        this.dm = dm;
    }

    /**
     * @param dobj the dobj to set
     */
    public void setDobj(Double dobj) {
        this.dobj = dobj;
    }

    /**
     * @param fm the fm to set
     */
    public void setFm(float fm) {
        this.fm = fm;
    }

    /**
     * @param fobj the fobj to set
     */
    public void setFobj(Float fobj) {
        this.fobj = fobj;
    }

    /**
     * @param im the im to set
     */
    public void setIm(int im) {
        this.im = im;
    }

    /**
     * @param iobj the iojbj to set
     */
    public void setIobj(Integer iobj) {
        this.iojbj = iobj;
    }

    /**
     * @param ll the ll to set
     */
    public void setLl(List<Long> ll) {
        this.ll = ll;
    }

    /**
     * @param lm the lm to set
     */
    public void setLm(long lm) {
        this.lm = lm;
    }

    /**
     * @param lobj the lobj to set
     */
    public void setLobj(Long lobj) {
        this.lobj = lobj;
    }

    /**
     * @param slm the slm to set
     */
    public void setSlm(Map<String, Long> slm) {
        this.slm = slm;
    }

    /**
     * @param sm the sm to set
     */
    public void setSm(String sm) {
        this.sm = sm;
    }

    /**
     * @param sqlTime the sqlTime to set
     */
    public void setSqlTime(java.sql.Date sqlTime) {
        this.sqlTime = sqlTime;
    }

    /**
     * @param ss the ss to set
     */
    public void setSs(Set<String> ss) {
        this.ss = ss;
    }

    /**
     * @param time the time to set
     */
    public void setTime(Date time) {
        this.time = time;
    }
}
