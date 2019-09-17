/**
 *
 */
package ru.aafanasiev.util.copier.converter.cdate;

/**
 * Pframeters for date copy converters
 * 
 * @author aafanasyev
 */
public class DateConverterParameters {
    private boolean immutable;

    /**
     * @return the immutable
     */
    public boolean isImmutable() {
        return immutable;
    }

    /**
     * @param immutable the immutable to set
     */
    public void setImmutable(boolean immutable) {
        this.immutable = immutable;
    }
}
